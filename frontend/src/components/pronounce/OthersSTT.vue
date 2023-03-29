<template>
  <div class="STT__container">
    <div>
      <p v-text="transcript"></p>
    </div>
    <div class="microphone__icon" @click="ToggleMic">
      <font-awesome-icon v-if="!isRecording" icon="fa-solid fa-headphones" />
      <font-awesome-icon v-else icon="fa-solid fa-pause" />
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
  height: 32vh;
  margin-inline: 2vw;
  margin-top: 2vh;
  padding: 1vh;
  border-radius: 16px;
  border: solid var(--maincolor2) 3px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.microphone__container {
  padding-inline: 5%;
  align-self: end;
}
.microphone__icon {
  font-size: 1.5rem;
}
.fa-headphones {
  position: absolute;
  width: 24px;
  height: 24px;
  left: calc(55% - 24px / 2 + 119px);
  top: calc(55% - 24px / 2 + 262px);
}

.fa-headphones {
  position: absolute;
  width: 24px;
  height: 24px;
  left: calc(55% - 24px / 2 + 119px);
  top: calc(55% - 24px / 2 + 262px);
}

.fa-pause {
  position: absolute;
  width: 24px;
  height: 24px;
  left: calc(55% - 24px / 2 + 119px);
  top: calc(55% - 24px / 2 + 262px);
}
</style>
