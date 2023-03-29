<template>
  <div class>
    <div>
      {{ MyDailyWord.mydailyword[index].word }}
    </div>
    <div>올바른 발음</div>
    <div>{{ MyDailyWord.mydailyword[index].guideWord }}</div>
    <div>나의 발음</div>
    <div>???</div>
    <div>연습하기</div>
  </div>
  <div class="parent">
    <div class="child">이전</div>
    <div class="child">다음</div>
  </div>
</template>

<script setup>
import { useMyDailyWord } from "@/store/DailyWord";
import { useRoute } from "vue-router";
const route = useRoute();
const index = route.params.index;

const MyDailyWord = useMyDailyWord();
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
