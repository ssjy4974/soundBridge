<template>
  <div class="direct__container">
    <input
      class="input__box"
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
    <div class="icon__box">
      <font-awesome-icon
        icon="fa-solid fa-microphone"
        @click="addmysentence(inputSentence)"
        style="font-size: 1.5rem"
      />
    </div>
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
<style lang="scss" scoped>
.direct__container {
  display: flex;
  align-content: end;
  justify-content: space-between;
  height: 4vh;
  margin-inline: 3vw;
  padding-inline: 5%;
  border-radius: 8px;
  background-color: var(--maincolor3);
}
.icon__box {
  align-self: center;
}
.input__box {
  width: 90%;
  border: none;
  background-color: var(--maincolor3);
}
</style>
