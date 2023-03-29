<template>
  <div class="STT__container">
    <div>
      <p v-text="transcript"></p>
      <!-- <p>play 아이콘 클릭하면STT 실행</p> -->
    </div>
    <div class="microphone__icon" @click="ToggleMic">
      <font-awesome-icon icon="fa-solid fa-play" />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";

const transcript = ref("");
const isRecording = ref(false);

const Recognition = window.SpeechRecognition || window.webkitSpeechRecognition;
const sr = new Recognition();

onMounted(() => {
  sr.continuous = true;
  sr.interimResults = true;
  // 언어 설정, 설정하지 않으면 디폴트로 HTML 의 lang 속성값을 받음
  sr.lang = "ko-KR";
  sr.maxAlternatives = 10000;
  sr.onstart = () => {
    console.log("발화 시작");
    isRecording.value = true;
  };
  sr.onend = () => {
    console.log("발화 종료");
    isRecording.value = false;
    transcript.value = "";
  };
  sr.onresult = (evt) => {
    const t = Array.from(evt.results)
      .map((result) => result[0])
      .map((result) => result.transcript)
      .join("");
    transcript.value = t;
  };
});

const ToggleMic = () => {
  if (transcript.value) {
    sr.stop();
  } else {
    sr.start();
  }
};
</script>

<style scoped>
.STT__container {
  height: 32vh;
  margin-inline: 2vw;
  margin-top: 2vh;
  padding: 1vh;
  border-radius: 16px;
  border: solid var(--maincolor2) 3px;
  display: block;
}
.microphone__icon {
}
</style>
