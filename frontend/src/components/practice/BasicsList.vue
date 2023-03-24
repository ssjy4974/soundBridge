<template>
  <div>
    <h3>발음 교정 기본 자음모음 메뉴 페이지</h3>
    <ul v-for="(basicLetter, index) in basicLetterList" :key="index">
      <li>{{ basicLetter.basicLetterId }}</li>
      <span> 자음 명 : {{ basicLetter.letter }}</span>
      <br />
      <span> 시도횟수 : {{ basicLetter.tryCount }}</span>
      <br />
      <span> 성공횟수 : {{ basicLetter.successCount }}</span>
      <br />
      <router-link :to="`/practicebasicsdetail/${basicLetter.basicLetterId}`">
        <img :src="`${IMAGE_PATH}/${basicLetter.letterImage}`" />
      </router-link>
    </ul>
  </div>
</template>

<script setup>
import { apiInstance } from "@/api/index";
import { ref } from "vue";

const api = apiInstance();
const basicLetterList = ref(undefined);
const IMAGE_PATH = import.meta.env.VITE_IMAGE_PATH;

const callApi = () => {
  api
    .get(`/api//basic-letters`)
    .then((res) => {
      basicLetterList.value = res.data;
    })
    .catch((err) => {
      console.log(err);
    });
};

callApi();
</script>

<style scoped>
img {
  width: 100px;
  height: 100px;
}
</style>
