<template>
  <div class="container">
    <div class="sentenceSection">
      <h3>{{ sentence }}</h3>
    </div>

    <div id="audioSection">
      <audio controls>녹음된 소리를 재생할 audio 엘리먼트</audio>
    </div>

    <br />
    <div id="recordBtnSection">
      <button class="recordBtn" @click="record" v-if="!isRecording">
        <img src="../assets/img/mike.png" />
      </button>
      <button class="recordBtn" @click="stop" v-else>
        <img src="../assets/img/stop.png" />
      </button>
    </div>

    <div id="saveBtnSection">
      <button class="saveBtn" v-if="curFile" @click="save">저장 하기</button>
      <button class="saveBtn disabled" v-else>저장 하기</button>
    </div>

    <h3 class="state">{{ sentenceNum }}/{{ totalSentenceCnt }}</h3>
    <regist-modal v-if="isRegistModal" @closeModal="modalState"></regist-modal>
  </div>
</template>

<script setup>
import { onBeforeMount, ref } from "@vue/runtime-core";
import { apiInstance } from "@/api/index";
import { useMember } from "@/store/Member";
import RegistModal from "../components/record/RegistModal.vue";

const totalSentenceCnt = 3922;
const memberStore = useMember();
const api = apiInstance();
const isRecording = ref(false);
const sentence = ref(null);
const sentenceNum = ref(0);
const curFile = ref(null);
const blob = ref(null);
const text = ref();
const isRegistModal = ref(false);

const { accessToken, member } = memberStore;
let mediaRecorder = null;

// 녹음 데이터 저장 배열
const audioArray = [];

onBeforeMount(() => {
  api
    .get(`/api/records/${member.memberId}/`, {
      headers: {
        "access-token": accessToken,
      },
    })
    .then((res) => {
      sentence.value = res.data.content;
      sentenceNum.value = res.data.sentenceId;
    });
});

const record = () => {
  // 마이크 mediaStream 생성: Promise를 반환하므로 async/await 사용
  navigator.mediaDevices
    .getUserMedia({
      audio: true,
    })
    .then((stream) => {
      mediaRecorder = new MediaRecorder(stream);

      // 녹음 시작
      mediaRecorder.start();
      mediaRecorder.ondataavailable = (event) => {
        audioArray.push(event.data); // 오디오 데이터가 취득될 때마다 배열에 담아둔다.
      };
      mediaRecorder.onstop = () => {
        blob.value = new Blob(audioArray, { type: "audio/webm" });
        audioArray.splice(0); // 기존 오디오 데이터들은 모두 비워 초기화한다.

        curFile.value = window.URL.createObjectURL(blob.value);
        const audioEl = document.querySelector("audio");

        // 이벤트 실행 시에 서버로 파일 POST
        audioEl.setAttribute("src", curFile.value);
        isRecording.value = false;
      };
      isRecording.value = true;
    });
};

const stop = () => {
  mediaRecorder.stop();
  mediaRecorder = null;
  isRecording.value = false;
};

const save = () => {
  let fd = new FormData();
  fd.append("file", new File([blob.value], `${sentenceNum.value}.weba`));
  fd.append("member_id", member.memberId);
  api
    .post("http://j8a703.p.ssafy.io/ai/upload", fd, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    })
    .then((res) => {
      sentence.value = res.data.content;
      sentenceNum.value = res.data.sentence_id;
      const audioEl = document.querySelector("audio");
      audioEl.setAttribute("src", null);
      curFile.value = null;
    });

  if (sentenceNum.value == totalSentenceCnt) {
    modalState();
  }
};

const modalState = () => {
  isRegistModal.value = !isRegistModal.value;
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.sentenceSection {
  display: flex;
  align-items: flex-end;
  text-align: center;
  line-height: 27px;
  width: 90%;
  height: 280px;
}

audio {
  margin-top: 50px;
  width: 400px;
}

.recordBtn {
  background-color: transparent !important;
  background-image: none !important;
  border-color: transparent;
  border: none;

  color: #ffffff;
}

.recordBtn img {
  width: 60px;
  height: 60px;
}

.saveBtn {
  width: 350px;
  height: 40px;
  margin-top: 30px;
  background-color: #2bacff;
  border-radius: 32px;
  color: #ffffff;
  line-height: 0px;
}

.disabled {
  background-color: #afacac;
}

.state {
  margin-top: 150px;
}
</style>
