<template>
  <div class="STT__container">
    <div>
      <p class="STT__ptag" v-text="transcript"></p>
    </div>
    <div class="microphone__icon" @click="ToggleMic">
      <font-awesome-icon v-if="!isRecording" icon="fa-solid fa-headphones" />
      <font-awesome-icon v-else icon="fa-solid fa-pause" />
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";

const transcript = ref("이곳은 대화상자 입니다.");
const isRecording = ref(false);

const Recognition = window.SpeechRecognition || window.webkitSpeechRecognition;
const sr = new Recognition();

onMounted(() => {
  sr.continuous = true;
  sr.interimResults = true;
  // 언어 설정, 설정하지 않으면 디폴트로 HTML 의 lang 속성값을 받음
  sr.lang = "ko-KR";
  sr.maxAlternatives = 1000000;
  sr.onstart = () => {
    console.log("발화 시작");
    isRecording.value = true;
    transcript.value = "";
  };
  sr.onend = () => {
    console.log("발화 종료");
    isRecording.value = false;
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
  if (isRecording.value) {
    sr.stop();
  } else {
    sr.start();
  }
};
</script>

<style scoped>
.STT__container {
  background-color: var(--black1);

  height: 32vh;
  margin-inline: 2vw;
  margin-top: 2vh;
  padding: 1vh;
  border-radius: 16px;
  border: solid var(--maincolor2) 3px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;

  overflow: scroll;
}
.microphone__container {
  padding-inline: 5%;
}
.microphone__icon {
  align-self: end;
  font-size: 1.5rem;
  margin: 5%;
}
.STT__ptag {
  margin-block: 2%;
  font-size: 1.2rem;
  line-height: 1.8;
}
</style>
