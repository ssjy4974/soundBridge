<template>
  <div class>
    <div class="info">
      <div>연습 단어: {{ DailyWordList[Number(route.params.index)].word }}</div>
      <div>올바른 발음</div>
      <div>{{ DailyWordList[Number(route.params.index)].guideWord }}</div>
    </div>
    <div class="myResult">
      <div>나의 발음</div>
      <div>???</div>
    </div>
    <div class="practice" @click="tryHistoryHandler">연습하기</div>
  </div>
  <div class="parent">
    <div class="child" v-if="Number(route.params.index) > 0" @click="prev">
      이전
    </div>
    <div class="child" v-if="Number(route.params.index) < endIdx" @click="next">
      다음
    </div>
  </div>
</template>

<script setup>
import { useRoute } from "vue-router";
import router from "@/router/index";
import { useMyDailyWord } from "@/store/DailyWord";

const MyDailyWord = useMyDailyWord();

const route = useRoute();
const DailyWordList = JSON.parse(localStorage.getItem("dailyWordList"))._value;
const endIdx = DailyWordList.length - 1;

const Recognition = window.SpeechRecognition || window.webkitSpeechRecognition;
const sr = new Recognition();

const tryHistoryHandler = () => {
  const index = Number(route.params.index);
  const wordMemberId = DailyWordList[index].wordMemberId;
  MyDailyWord.saveorupdatetryhistory(wordMemberId);
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
</style>
