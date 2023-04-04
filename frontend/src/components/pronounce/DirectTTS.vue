<template>
  <div class="direct__container">
    <div class="input__container">
      <input
        class="input__box"
        type="text"
        placeholder="텍스트를 입력하고 마이크 버튼을 누르세요"
        v-model="inputSentence"
        @input="callgetAPI"
        @keyup.enter="
          () => {
            getAudio(inputSentence);
            directMessageHandler();
            callgetAPI();
          }
        "
      />
      <div class="icon__box">
        <font-awesome-icon
          icon="fa-solid fa-microphone"
          @click="
            () => {
              addSentenceHandler(inputSentence);
              getAudio(inputSentence, member.voiceId);
              // addmysentence(inputSentence);
              inputSentence = '';
            }
          "
          style="font-size: 1.3rem"
        />
      </div>
    </div>
    <div class="auto__dropdown">
      <p
        v-for="(mysen, index) in mysentence"
        :key="index"
        @click="
          () => {
            // play tts
            getAudio(mysen.sentence);
            // auto complete 입력칸
            directMessageHandler();
            // 자동완성 후 store 업데이트
            callgetAPI();
          }
        "
      >
        {{ mysen.sentence }}
      </p>
    </div>
  </div>
</template>

<script setup>
import { useMySentence } from "@/store/Sentence";
import { useMember } from "@/store/Member";
import { storeToRefs } from "pinia";
import { ref, watch } from "vue";
// import { throttle } from "lodash";

const mySentences = useMySentence();
const memberStore = useMember();
const { sentence, getmysentences, addmysentence, updatemysentence } =
  mySentences;
const { mysentence } = storeToRefs(mySentences);
const { member } = storeToRefs(memberStore);
// const sentenceList = storeToRefs(mysentence);
const inputSentence = ref("");

const callgetAPI = async () => {
  await getmysentences(inputSentence.value);
  // sentenceList.value = mySentences.mysentence;
};

watch(inputSentence, () => {
  console.log(mysentence.value);
  callgetAPI();
  // mySentences.mysentence = mysentence.value;
});

const addSentenceHandler = (a) => {
  console.log(a);
  addmysentence(a);
};

// 선택한 목소리로 TTS 실행하기
const getAudio = (text, voice) => {
  console.log("TTS test", text);
  let audio = new Audio(
<<<<<<< HEAD
    `http://j8a703.p.ssafy.io/ai/infer/?text=${encodeURI(text)}&voice=${voice}`
=======
    `http://j8a703.p.ssafy.io/ai/infer/?text=${encodeURI(text)}&voice=1`
>>>>>>> 254a9e3 (style : 자동완성)
  );
  audio.play();
};
function directMessageHandler() {
  inputSentence.value = "";
}

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
  border-bottom: solid var(--maincolor3);
  border-radius: 12px;
  padding-inline: 10%;
  z-index: 2;
  position: absolute;
  width: 75%;
  left: 2vw;
}
</style>
