<template>
  <div class="TTS__container">
    <div class="cat__wrapper">
      <div class="cat__container">
        <div
          class="cat__item"
          :id="`${index}`"
          v-for="(category, index) in freqUsedCat"
          :key="index"
          @click="
            (e) => {
              activeHandler(index), phraseHandler(category.categoryId);
            }
          "
        >
          <p>
            {{ category.categoryName }}
          </p>
        </div>
      </div>
      <div class="catadd__button">
        <font-awesome-icon @click="addCatModal" icon="fa-solid fa-plus" />
      </div>
    </div>
    <AddCatModal v-if="isCatModal" @closemodal="addCatModal" />
    <div class="FUP__container">
      <div
        class="phrase__box"
        v-for="(phrase, index) in freqUsedPhrase"
        :key="index"
      >
        <div>
          <p @click="getAudio(phrase.sentence)">{{ phrase.sentence }}</p>
        </div>
        <div>
          <font-awesome-icon
            icon="fa-solid fa-x"
            @click="delPhraseHandler(phrase.quickSentenceId)"
          />
        </div>
      </div>
      <div class="freqButton__box">
        <p href="" @click="addPhraseModal">자주쓰는말 추가하기 +</p>
      </div>
    </div>
    <AddPhraseModal
      v-if="isPhraseModal"
      @closemodalphrase="addPhraseModal"
      :catId="parseCategoryId"
    />
  </div>
</template>

<script setup>
import { ref } from "vue";
import AddCatModal from "./AddCatModal.vue";
import AddPhraseModal from "./AddPhraseModal.vue";
import { usePronounce } from "@/store/Pronounce.js";
import { storeToRefs } from "pinia";
import Swal from "sweetalert2";

// data from store
const store = usePronounce();
const { freqUsedCat, freqUsedPhrase } = storeToRefs(store);

// 카테고리 길이만큼의 배열
// const categoryArray = ref(
//   Array.from({ length: freqUsedCat.value.length() }, (v, i) => false)
// );

// 페이지 타입 구분할 변수
const pageIndex = 1;
const idIndex = ref(undefined);
// api 호출 함수 실행 시키는 함수
const callCategoryAPI = async () => {
  await store.readCategories;
  if (freqUsedCat.value.length) {
    idIndex.value = 0;
    document.getElementById(idIndex.value).className = "cat__item touched";
    phraseHandler(freqUsedCat.value[0].categoryId);
  }
};
const callSentenceAPI = async () => {
  await store.readQuickSentence(parseCategoryId.value);
};

const isCatModal = ref(false);
const isPhraseModal = ref(false);

// catagory add modal
const addCatModal = () => {
  callCategoryAPI();
  // store.readQuickSentence();

  if (isPhraseModal.value == true) {
    isPhraseModal.value = false;
  }
  isCatModal.value = !isCatModal.value;
};

// Phrase Modal pop up
const addPhraseModal = () => {
  callSentenceAPI();
  if (isCatModal.value == true) {
    isCatModal.value = false;
  }
  isPhraseModal.value = !isPhraseModal.value;
  console.log("response data", freqUsedPhrase.value);
};

// category 별 phrase 불러오는 함수 + categoryID 저장하기
const parseCategoryId = ref(1);
const phraseHandler = (categoryId) => {
  // console.log(categoryId);
  store.readQuickSentence(categoryId);
  parseCategoryId.value = categoryId;
};

// Delete 자주쓰는문장 Handler
const delPhraseHandler = (sentenceId) => {
  Swal.fire({
    title: "삭제 하시겠습니까?",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Yes",
  }).then((result) => {
    if (result.isConfirmed) {
      store.deleteQuickSentence(sentenceId);
    }
  });
  // alert("삭제 되었습니다.");
  callSentenceAPI();
};

// 선택한 목소리로 TTS 실행하기
const getAudio = (text) => {
  console.log("TTS test", text);
  let audio = new Audio(
    `http://j8a703.p.ssafy.io/ai/infer/?text=${encodeURI(text)}`
  );
  audio.play();
};

const activeHandler = (index) => {
  if (idIndex.value !== undefined) {
    document.getElementById(idIndex.value).className = "cat__item";
  }
  document.getElementById(index).className = "cat__item touched";
  idIndex.value = document.getElementById(index).getAttribute("id");
};
// GET catagoires
callCategoryAPI();
callSentenceAPI();
</script>

<style lang="scss" scoped>
.FUP__container {
  height: 35vh;
  overflow: scroll;
}
.cat__wrapper {
  display: flex;
  justify-content: space-between;
  border-bottom: solid var(--maincolor2);
}
.cat__container {
  display: flex;
  width: 70%;
  align-items: center;
  height: 5vh;
  overflow-x: scroll;
}
.cat__item {
  // border-top: 5px solid var(--maincolor2);
  display: flex;
  width: fit-content;
  margin-top: -3%;
  padding-left: 3%;
  padding-right: 3%;
  height: 70%;
  border-radius: 5px;
  align-items: center;
}
p {
  text-align: center;
}
.catadd__button {
  padding-right: 4%;
  align-self: center;
  justify-self: center;
}
.TTS__container {
  border: solid var(--maincolor2);
  background-color: var(--black1);
  margin-inline: 2vw;
  margin-bottom: 2vh;
  padding: 1vh;
  border-radius: 16px;
}
.phrase__box {
  padding-inline: 3vw;
  height: 15%;
  border-bottom: var(--black5) solid 1px;
  display: flex;
  align-items: baseline;
  justify-content: space-between;
}
.freqButton__box {
  padding-inline: 3vw;
}
.touched {
  background-color: var(--maincolor2);
}
</style>
