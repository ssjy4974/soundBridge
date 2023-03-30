<template>
  <div class="main" v-if="basicLetter">
    <div class="letter">
      <h1>{{ basicLetter.letter }}</h1>
      <div class="letterImage">
        <img
          :src="`${IMAGE_PATH}/${basicLetter.guidImage}`"
          alt="조음법 이미지"
        />
      </div>
    </div>

    <div class="guidLetter">
      <h1>{{ basicLetter.guidLetter }}</h1>
    </div>
    <div class="realLetter">
      <h1 v-text="transcript"></h1>
    </div>
    <div>
      <div v-if="route.params.basicLetterId > 1">
        <i
          @click="prev"
          id="leftI"
          class="fas fa-solid fa-caret-left fa-3x"
        ></i>
      </div>
      <div v-if="route.params.basicLetterId < 14">
        <i
          @click="next"
          id="rightI"
          class="fas fa-solid fa-caret-right fa-3x"
        ></i>
      </div>
    </div>
    <div class="record" @click="ToggleMic">
      <span
        ><h3>
          {{ recordStatus }} <i id="mic" class="fa-solid fa-microphone"></i>
        </h3>
      </span>
    </div>
  </div>
</template>

<script setup>
import { useBasicLetterStore } from "@/store/BasicLetter";
import { useMember } from "@/store/Member";
import { storeToRefs } from "pinia";
import { useRoute } from "vue-router";
import { ref, onMounted } from "vue";
import router from "@/router/index";
import Swal from "sweetalert2";

const route = useRoute();
const store = useBasicLetterStore();
const memberStore = useMember();
const { basicLetter } = storeToRefs(store);
const { accessToken } = memberStore;
const IMAGE_PATH = import.meta.env.VITE_IMAGE_PATH;
const transcript = ref("");
const isRecording = ref(false);
const recordStatus = ref("연습하기");

const Recognition = window.SpeechRecognition || window.webkitSpeechRecognition;
const sr = new Recognition();

onMounted(() => {
  // 각각의 인식 결과마다 continuous한 results를 받을지 혹은 single result를 받을지 결정
  // 디폴트 값은 false, single word를 리턴함
  sr.continuous = false;
  // 중간 결과를 리턴할지 아닐지 결정
  // 아직 인식이 완료 되지 않은 중간 results
  // 인식이 모두 완료되면 isFinal이 true, 아직 인식 전이면 false
  // 인식이 모두 완료 되면 내가 발음 한 발음과 실제 발음을 비교하는 api 호출 함
  sr.interimResults = true;
  // 언어 설정, 설정하지 않으면 디폴트로 HTML 의 lang 속성값을 받음
  sr.lang = "ko-KR";
  sr.maxAlternatives = 0;
  sr.onstart = () => {
    console.log("연습 시작");
    isRecording.value = true;
  };
  sr.onend = () => {
    console.log("연습 종료");
    isRecording.value = false;
    recordStatus.value = "연습하기";
    transcript.value = "";
  };
  sr.onresult = (evt) => {
    for (let i = 0; i < evt.results.length; i++) {
      const result = evt.results[i];
      if (result.isFinal) {
        CheckSuccess(result);
      }
    }
    const t = Array.from(evt.results)
      .map((result) => result[0])
      .map((result) => result.transcript)
      .join("");
    transcript.value = t;
  };
});

store.getBasicLetter(accessToken, route.params.basicLetterId);
console.log("acc", accessToken);
const prev = () => {
  const index = Number(route.params.basicLetterId) - 1;
  router.replace(`/practicebasicsdetail/${index}`);
  store.getBasicLetter(accessToken, index);
};

const next = () => {
  const index = Number(route.params.basicLetterId) + 1;
  router.replace(`/practicebasicsdetail/${index}`);
  store.getBasicLetter(accessToken, index);
};

const CheckSuccess = (result) => {
  const t = result[0].transcript;
  if (t == basicLetter.value.guidLetter) {
    sr.stop();
    store.successPratice(accessToken, basicLetter.value.basicLetterId);
  } else {
    sr.stop();
    Swal.fire(
      "다시 한번 해볼까요?",
      "성공횟수 : " +
        basicLetter.value.successCount +
        "<br/>" +
        "시도 횟수 : " +
        basicLetter.value.tryCount,
      "question"
    );
  }
};

const ToggleMic = () => {
  store.tryPractice(accessToken, basicLetter.value.basicLetterId).then(() => {
    sr.start();
    recordStatus.value = "녹음중";
  });
};
</script>

<style scoped>
i {
  color: #2bacff;
  cursor: pointer;
}
#leftI {
  position: absolute;
  width: 32px;
  height: 32px;
  left: calc(45% - 32px / 2 - 147px);
  top: calc(43% - 32px / 2 + 159px);
}
#rightI {
  position: absolute;
  width: 32px;
  height: 32px;
  left: calc(55% - 32px / 2 + 147px);
  top: calc(43% - 32px / 2 + 159px);
}
#mic {
  color: black;
}
.main {
  height: 100%;
  width: 100%;
}
.letter {
  text-align: center;
}
.guidLetter {
  width: 100%;
  display: flex;
  justify-content: center;
  text-align: center;
}
h1,
h2 {
  margin-top: 20px;
}
.realLetter {
  width: 100%;
  display: flex;
  justify-content: center;
}
.realLetter h1 {
  border: 1px solid white;
  border-radius: 16px;
  margin-left: 35px;
  margin-right: 35px;
  background: #eaf6ff;
  text-align: center;
  color: #1990dd;
}
.letterImage {
  border: 1px solid white;
  border-radius: 32px;
  margin-left: 50px;
  margin-right: 50px;
  background: #eaf6ff;
  margin-bottom: 10px;
}
img {
  width: 150px;
  height: 300px;
  margin: 10%;
}
.record {
  display: flex;
  justify-content: center;
  border: 1px solid white;
  border-radius: 32px;
  margin-left: 20px;
  margin-right: 20px;
  background: #bae4ff;
  margin-bottom: 10px;
  text-align: center;
  cursor: pointer;
}
span h3 {
  padding: 10px;
  margin: 10px;
}
</style>
