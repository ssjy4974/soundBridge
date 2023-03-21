import subprocess
from typing import List
from pydantic import BaseModel
from fastapi import FastAPI, File, UploadFile, Form
from starlette.middleware.cors import CORSMiddleware
from database import engineconn
from models import Record_Sentence, Record_State
from fastapi.responses import StreamingResponse
import os
from s3 import s3util
import numpy as np
import tts
from io import BytesIO
import scipy.io.wavfile as wavf


app = FastAPI()
app.add_middleware(
    CORSMiddleware,
    allow_origins=['*'],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)
engine = engineconn()
session = engine.sessionmaker()
s3util = s3util()
synthesizer = tts.synthesizer

class AudioOut(BaseModel):
    audio: List[float] = []


@app.get("/ai/records/{sentence_id}")
async def get_sentence(sentence_id):
    sentence = session.query(Record_Sentence).get(sentence_id)
    print(type(sentence))
    return sentence


@app.post("/ai/upload")
async def upload_audio(file: UploadFile = File(), member_id: int = Form()):
    UPLOAD_DIR = ".\\audio"  # 이미지를 저장할 서버 경로
    content = await file.read()
    file_path = os.path.join(UPLOAD_DIR, file.filename)
    result_file_path = file_path.rstrip('.weba') + ".wav"

    with open(file_path, "wb") as fp:
        fp.write(content)  # 서버 로컬 스토리지에 이미지 저장 (쓰기)

    subprocess.call(
        'ffmpeg -i {} -ab 160k -ac 2 -ar 44100 -vn {} -y'.format(
            file_path, result_file_path
        ),
        shell=True
    )
    os.remove(file_path)

    s3util.client.upload_file(result_file_path, 'yeon-chung', f"{member_id}/{file.filename}")
    os.remove(result_file_path)

    state = session.query(Record_State).filter(Record_State.member_id==member_id).first()
    state.sentence_id += 1
    session.commit()


    return session.query(Record_Sentence).get(state.sentence_id)


@app.get("/ai/infer/", response_class=StreamingResponse)
async def read_item(text: str):
    normalized_text = tts.normalize_text(text)
    audio = synthesizer.tts(normalized_text, None, None)

    wav = BytesIO()
    wavf.write(wav, rate=22050, data=(np.array(audio).astype(np.float32)))


    return StreamingResponse(wav, media_type="audio/wav")


