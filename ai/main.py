import subprocess
from typing import List
from pydantic import BaseModel
from fastapi import FastAPI, File, UploadFile, Form
from starlette.middleware.cors import CORSMiddleware
from database import engineconn
from models import Record_Sentence, Record_State, Daily_Word, Word_Member, Member, Voice
from fastapi.responses import StreamingResponse
import os
import urllib.request
from s3 import s3util
import numpy as np
import tts
from io import BytesIO
import scipy.io.wavfile as wavf
from g2pk import G2p

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

CLIENT_ID = os.getenv('CLIENT_ID')
CLIENT_SECRET = os.getenv('CLIENT_SECRET')

class AudioOut(BaseModel):
    audio: List[float] = []

class AddWordBody(BaseModel):
    memberId: int
    dailyWord : str
    type : str

@app.get("/ai/records/{sentence_id}")
async def get_sentence(sentence_id):
    sentence = session.query(Record_Sentence).get(sentence_id)
    print(type(sentence))
    return sentence


@app.post("/ai/upload")
async def upload_audio(file: UploadFile = File(), member_id: int = Form()):
    content = await file.read()
    file_path = os.path.join(file.filename)
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
async def read_item(text: str, voice: int):
    find_voice = session.query(Voice).get(voice)
    print(find_voice.model_url)
    if (find_voice.model_url == None):
        normalized_text = tts.normalize_text(text)
        audio = synthesizer.tts(normalized_text, None, None)

        wav = BytesIO()
        wavf.write(wav, rate=22050, data=(np.array(audio).astype(np.float32)))
        return StreamingResponse(wav, media_type="audio/wav")
    else:
        encText = urllib.parse.quote(text)
        data = "speaker=" + find_voice.model_url + "&volume=0&speed=0&pitch=0&format=wav&text=" + encText;
        url = "https://naveropenapi.apigw.ntruss.com/tts-premium/v1/tts"
        request = urllib.request.Request(url)
        request.add_header("X-NCP-APIGW-API-KEY-ID", CLIENT_ID)
        request.add_header("X-NCP-APIGW-API-KEY", CLIENT_SECRET)
        response = urllib.request.urlopen(request, data=data.encode('utf-8'))
        rescode = response.getcode()

        if (rescode == 200):
            response_body = response.read()
            wav = BytesIO(response_body)

            return StreamingResponse(wav, media_type="audio/wav")


@app.post("/ai/daily-words")
def addDailyWord(addWordBody: AddWordBody):
    dailyWord = addWordBody.dailyWord
    memberId = addWordBody.memberId
    wordType = addWordBody.type
    findWord = session.query(Daily_Word).filter(Daily_Word.word==dailyWord).first()
    findMember = session.query(Member).filter(Member.member_id == memberId).first()

    print("단어 찾아보기", findWord)
    print("회원 찾아보기", findMember)
    if findWord is None:

        print("새로운 단어 등록")
        print(dailyWord)
        g2p = G2p()
        guideWord = g2p(dailyWord)

        print("g2p돌리기", guideWord)
        newWord = Daily_Word(word=dailyWord, guide_word=guideWord, type=wordType)
        session.add(newWord)
        session.commit()

    word =  session.query(Daily_Word).filter(Daily_Word.word == dailyWord).first()
    hasWord =  session.query(Word_Member).filter(Word_Member.daily_word_id == word.daily_word_id
                                      , Word_Member.member_id == findMember.member_id).first()
    print(hasWord,"단어 있니?")

    if hasWord is None:

        print("유저가 이미 등록한 단어가 아님")
        newWordMember = Word_Member(daily_word_id=word.daily_word_id, member_id=findMember.member_id)
        session.add(newWordMember)
        session.commit()


    return dailyWord