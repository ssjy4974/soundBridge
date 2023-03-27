<template>
  <div class="main">
    <h3>발음 교정 기본 자음모음 메뉴 페이지</h3>
    <div class="container">
      <div v-for="(basicLetter, index) in basicLetterList" :key="index">
        <router-link :to="`/practicebasicsdetail/${basicLetter.basicLetterId}`">
          <img :src="`${IMAGE_PATH}/${basicLetter.letterImage}`" />
        </router-link>
      </div>
    </div>
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
h3 {
  margin-left: 20px;
}
.main {
  height: 100%;
  width: 95%;
}
.container {
  display: flex;
  border: 1px solid lightgray;
  border-radius: 10px;
  margin: 8px;
  padding-top: 5px;
  background: #ffffff;
  height: 100%;
  width: 100%;
  flex-wrap: wrap;
  justify-content: stretch;
}
img {
  width: 75px;
  height: 75px;
  padding: 3px;
  padding-left: 6.5px;
}
</style>
