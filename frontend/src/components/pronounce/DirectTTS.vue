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
              getAudio(inputSentence);
              // addmysentence(inputSentence);
              inputSentence = '';
            }
          "
          style="font-size: 1.3rem"
        />
      </div>
    </div>
    <div
      v-for="(mysen, index) in mysentence"
      :key="index"
      class="auto__dropdown"
    >
      <!-- <p>{{ mysen }}</p> -->
      <div>
        <p>
          {{ mysen.sentence }}
        </p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useMySentence } from "@/store/Sentence";
import { storeToRefs } from "pinia";
import { ref, watch } from "vue";

const mySentences = useMySentence();
const { sentence, getmysentences, addmysentence, updatemysentence } =
  mySentences;
const { mysentence } = storeToRefs(mySentences);
// const sentenceList = storeToRefs(mysentence);
const inputSentence = ref("");

const callgetAPI = async () => {
  await getmysentences(inputSentence.value);
  // sentenceList.value = mySentences.mysentence;
};

watch(inputSentence, () => {
  callgetAPI();
  // mySentences.mysentence = mysentence.value;
});

const addSentenceHandler = (a) => {
  console.log(a);
  addmysentence(a);
};

// 선택한 목소리로 TTS 실행하기
const getAudio = (text) => {
  console.log("TTS test", text);
  let audio = new Audio(
    `http://j8a703.p.ssafy.io/ai/infer/?text=${encodeURI(text)}`
  );
  audio.play();
};

addSentenceHandler(inputSentence);
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
  // position: absolute;
  padding-inline: 10%;
}
</style>
