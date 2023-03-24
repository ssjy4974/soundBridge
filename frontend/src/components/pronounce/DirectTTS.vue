<template>
  <div class="">
    <input
      style="width: 300px"
      type="text"
      placeholder="텍스트를 입력하고 마이크 버튼을 누르세요"
      v-model="inputSentence"
      @input="getmysentences(inputSentence)"
    />
    <div v-for="mysen in mySentences.mysentence">
      <p>
        {{ mysen.sentence }}
      </p>
    </div>
    <font-awesome-icon
      icon="fa-solid fa-microphone"
      @click="addmysentence(inputSentence)"
    />
  </div>
</template>

<script setup>
import { useMySentence } from "@/store/Sentence";
import { ref } from "vue";

const mySentences = useMySentence();
const {
  sentence,
  mysentence,
  getmysentences,
  addmysentence,
  updatemysentence,
} = mySentences;

const inputSentence = ref("");
// console.log("mysentence", sentence);
</script>
<style lang="scss" scoped></style>
