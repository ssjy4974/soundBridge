<template>
  <div class="main">
    <div class="container" v-if="basicLetters">
      <div v-for="(basicLetter, index) in basicLetters" :key="index">
        <router-link :to="`/practicebasicsdetail/${basicLetter.basicLetterId}`">
          <div class="wrap">
            <img :src="`${IMAGE_PATH}/${basicLetter.letterImage}`" />
            <!-- <h2 class="wrap_text">{{ basicLetter.successCount }}</h2> -->
          </div>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useBasicLetterStore } from "@/store/BasicLetter";
import { storeToRefs } from "pinia";

const store = useBasicLetterStore();
const { basicLetters } = storeToRefs(store);
const IMAGE_PATH = import.meta.env.VITE_IMAGE_PATH;

store.getBasicLetters();
</script>

<style scoped>
.main {
  height: 100%;
  width: 100%;
}
.container {
  display: flex;
  padding-top: 5px;
  padding-left: 1px;
  background: #ffffff;
  height: 100%;
  width: 100%;
  flex-wrap: wrap;
  justify-content: stretch;
}
img {
  width: 125px;
  height: 110px;
  padding: 5px;
  margin-left: 1px;
  vertical-align: auto;
}
.wrap_text {
  position: absolute;
  transform: translate(330%, -200%);
  color: black;
}
</style>
