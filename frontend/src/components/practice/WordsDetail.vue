<template>
  <div class>
    <div>
      {{ MyDailyWord[index] }}
    </div>
    <div>올바른 발음</div>
    <div>{{ MyDailyWord[index] }}</div>
    <div>나의 발음</div>
    <div>???</div>
    <div>연습하기</div>
  </div>
  <div class="parent">
    <div class="child" v-if="index > 0" @click="prev">이전</div>
    <div class="child" v-if="index < endIdx - 1" @click="next">다음</div>
  </div>
</template>

<script setup>
import { useMyDailyWord } from "@/store/DailyWord";
import { useRoute } from "vue-router";
import router from "@/router/index";

const route = useRoute();
const index = route.params.index;
const MyDailyWord = localStorage.getItem("dailyWordList");
const endIdx = MyDailyWord.length;

const Recognition = window.SpeechRecognition || window.webkitSpeechRecognition;
const sr = new Recognition();

const prev = () => {
  console.log("이전 클릭");
  console.log(index);
  router.replace(`/wordsdetail/${index - 1}`);
};

const next = () => {
  console.log("다음 클릭");
  console.log(MyDailyWord);
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
</style>
