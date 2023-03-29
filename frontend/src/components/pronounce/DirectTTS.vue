<template>
  <div class="direct__container">
    <div class="input__container">
      <input
        class="input__box"
        type="text"
        placeholder="텍스트를 입력하고 마이크 버튼을 누르세요"
        v-model="inputSentence"
        @input="callgetAPI"
      />
      <div class="icon__box">
        <font-awesome-icon
          icon="fa-solid fa-microphone"
          @click="
            () => {
              addSentenceHandler(inputSentence);
              // addmysentence(inputSentence);
            }
          "
          style="font-size: 1.3rem"
        />
      </div>
    </div>
    <div
      v-for="(mysen, index) in mySentences.mysentence"
      :key="index"
      class="auto__dropdown"
    >
      <div class="item">
        <p class="text">{{ mysen.sentence }}</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useMySentence } from "@/store/Sentence";
import { ref, watch } from "vue";

const mySentences = useMySentence();
const {
  sentence,
  mysentence,
  getmysentences,
  addmysentence,
  updatemysentence,
} = mySentences;

const inputSentence = ref("");

const callgetAPI = async () => {
  await getmysentences(inputSentence);

  // console.log("mysentence???", mysentence);
};

watch(inputSentence, () => {
  // callgetAPI();
});

const addSentenceHandler = () => {
  addSentenceHandler();
};
</script>
<style lang="scss" scoped>
.direct__container {
}
.icon__box {
  align-self: center;
}
.input__box {
  width: 90%;
  border: none;
  background-color: var(--maincolor3);
  align-self: center;
  height: 70%;
}
.input__container {
  display: flex;
  align-content: end;
  justify-content: space-between;
  height: 5vh;
  margin-inline: 3vw;
  padding-block: auto;
  padding-inline: 5%;
  border-radius: 8px;
  background-color: var(--maincolor3);
}
.auto__dropdown {
  background-color: var(--black1);
  justify-self: center;
  align-self: center;
  width: 80vw;
  border-bottom: solid var(--maincolor3);
  border-radius: 12px;
  position: absolute;
  padding-inline: 10%;
}
</style>
