<template>
  <div>
<<<<<<< HEAD
    <h1>
      발음 교정 기본 자음모음 메뉴 페이지 api 가져온거 연결하면서 디자인 바꿀
      생각이라 일단 공백
    </h1>
    <BasicsList />
=======
    <h3>발음 교정 기본 자음모음 메뉴 페이지</h3>
    <ul v-for="(basicLetter, index) in basicLetterList" :key="index">
      <li>{{ basicLetter.basicLetterId }}</li>
      <span> 자음 명 : {{ basicLetter.letter }}</span>
      <br />
      <span> 발음 : {{ basicLetter.guidLetter }}</span>
      <br />
      <span> 시도횟수 : {{ basicLetter.tryCount }}</span>
      <br />
      <span> 성공횟수 : {{ basicLetter.successCount }}</span>
      <br />
      <img :src="`${IMAGE_PATH}/${basicLetter.letterImage}`" />
    </ul>
>>>>>>> 58a4efd (S08P22A703-281 feat : 기본 발음 전체 조회 API 연결)
  </div>
</template>

<script setup>
<<<<<<< HEAD
import BasicsList from "@/components/practice/BasicsList.vue";
</script>
=======
import { apiInstance } from "@/api/index";
import { ref } from "vue";
import PracticeBasicsDetail from "./PracticeBasicsDetail.vue";
>>>>>>> 58a4efd (S08P22A703-281 feat : 기본 발음 전체 조회 API 연결)

const api = apiInstance();
const basicLetterList = ref();
const IMAGE_PATH = import.meta.env.VITE_IMAGE_PATH;

const callApi = () => {
  api
    .get(`/api//basic-letters`)
    .then((res) => {
      basicLetterList.value = res.data;
      console.log(basicLetterList.value);
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
