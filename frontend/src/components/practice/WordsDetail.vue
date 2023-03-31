<template>
  <div class>
    <div class="info">
      <div>연습 단어: {{ DailyWordList[Number(route.params.index)].word }}</div>
      <div>발음 방법</div>
      <div>{{ DailyWordList[Number(route.params.index)].guideWord }}</div>
    </div>
    <div class="myResult">
      <div>나의 발음</div>
      <div class="realLetter">
        <h1 v-text="transcript"></h1>
      </div>
    </div>
    <div class="practice" @click="tryHistoryHandler">{{ recordStatus }}</div>
  </div>
  <div class="parent">
    <div class="child" v-if="Number(route.params.index) > 0" @click="prev">
      이전
    </div>
    <div class="child" v-if="Number(route.params.index) < endIdx" @click="next">
      다음
    </div>
  </div>
  <div @click="backToList">돌아가기</div>
</template>

<script setup>
import { useRoute } from "vue-router";
import { ref, onMounted } from "vue";
import router from "@/router/index";
import { useMyDailyWord } from "@/store/DailyWord";
import Swal from "sweetalert2";

const MyDailyWord = useMyDailyWord();

const route = useRoute();
let DailyWordList = JSON.parse(localStorage.getItem("dailyWordList"))._value;
const tryCount = ref(DailyWordList.tryCount);
const successCount = ref(DailyWordList.successCount);
const endIdx = DailyWordList.length - 1;

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
  sr.maxAlternatives = 1;
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
      console.log(evt.results);
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

const tryHistoryHandler = () => {
  const index = Number(route.params.index);
  const wordMemberId = DailyWordList[index].wordMemberId;
  console.log(typeof wordMemberId);
  MyDailyWord.saveorupdatetryhistory(wordMemberId);
  sr.start();
  recordStatus.value = "녹음중";
};

const backToList = () => {
  console.log("리스트로");
  router.push(`/practicewords`);
};

const prev = () => {
  console.log("이전 클릭");
  const index = Number(route.params.index);
  router.replace(`/wordsdetail/${index - 1}`);
};

const next = () => {
  console.log("다음 클릭");
  const index = Number(route.params.index);
  router.replace(`/wordsdetail/${index + 1}`);
};

const CheckSuccess = (result) => {
  const t = result[0].transcript;
  const index = Number(route.params.index);
  if (t == DailyWordList[index].word) {
    sr.stop();
    const wordMemberId = DailyWordList[index].wordMemberId;
    MyDailyWord.updatesuccesscount(wordMemberId);
  } else {
    sr.stop();
    MyDailyWord.getmydailyword();
    DailyWordList = JSON.parse(localStorage.getItem("dailyWordList"))._value;
    console.log("성공횟수", DailyWordList[index].successCount);
    console.log("시도횟수", DailyWordList[index].tryCount);
    Swal.fire({
      title: "다시 한번 해볼까요?",
      html:
        "성공횟수 : " +
        DailyWordList[index].successCount +
        "<br/>" +
        "시도 횟수 : " +
        DailyWordList[index].tryCount,
      position: "bottom-end",
    });
  }
};
</script>

<style scoped>
/* div {
  border: solid;
} */

.parent {
  width: 90%;
  margin: 0 auto;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
  position: fixed;
  bottom: 7vh;
}

.child {
  background-color: aqua;
  width: 40%;
  height: 70px;
  margin: 20px 0;
}

.info {
  width: 100%;
  margin-top: 80px;
  background-color: aqua;
}

.myResult {
  width: 100%;
  margin-top: 30px;
  background-color: aqua;
}

.practice {
  width: 100%;
  margin-top: 30px;
  background-color: red;
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
</style>
