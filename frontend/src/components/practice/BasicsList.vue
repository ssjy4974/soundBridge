<template>
  <div class="main">
    <div class="container" v-if="basicLetters">
      <div v-for="(basicLetter, index) in basicLetters" :key="index">
        <router-link :to="`/practicebasicsdetail/${basicLetter.basicLetterId}`">
          <div class="wrap">
            <img :src="`${IMAGE_PATH}/${basicLetter.letterImage}`" />
          </div>
        </router-link>
        <progress
          id="progressBar"
          :value="`${basicLetter.successCount}`"
          :max="`${basicLetter.tryCount}`"
        ></progress>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useBasicLetterStore } from "@/store/BasicLetter";
import { useMember } from "@/store/Member";
import { storeToRefs } from "pinia";

const store = useBasicLetterStore();
const memberStore = useMember();
const { basicLetters } = storeToRefs(store);
const IMAGE_PATH = import.meta.env.VITE_IMAGE_PATH;

const { accessToken } = memberStore;

store.getBasicLetters(accessToken);
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

#progressBar {
  width: 85%;
  height: 30px;
  left: 8%;
  position: relative;
}
</style>
