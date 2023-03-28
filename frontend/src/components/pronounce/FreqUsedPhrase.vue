<template>
  <div class="TTS__container">
    <div class="cat__container">
      <span v-for="(category, c) in freqUsedCat" :key="c">
        {{ category.categoryName }} |
      </span>
      <span @click="addCatModal"> | 추가하기 +</span>
      <AddCatModal v-if="isCatModal" @closemodal="addCatModal" />
    </div>
    <div class="FUP__container">
      <p v-for="(phrase, index) in freqUsedPhrase" :key="index">
        {{ phrase }}
      </p>

      <p href="" @click="addPhraseModal">자주쓰는말 추가하기 +</p>
      <br />
    </div>
    <AddPhraseModal v-if="isPhraseModal" @closemodalphrase="addPhraseModal" />
  </div>
</template>

<script setup>
import { ref, watch } from "vue";
import AddCatModal from "./AddCatModal.vue";
import AddPhraseModal from "./AddPhraseModal.vue";
import { usePronounce } from "@/store/Pronounce.js";
import { storeToRefs } from "pinia";

// data from store
const store = usePronounce();
const { freqUsedCat } = storeToRefs(store);

const callAPI = () => {
  store.readCategories;
};
watch(freqUsedCat);
const freqUsedPhrase = store.freqUsedPhrase;

const isCatModal = ref(false);
const isPhraseModal = ref(false);

// catagory add modal
const addCatModal = () => {
  console.log(
    "cat phrase form catmodal",
    isCatModal.value,
    isPhraseModal.value
  );

  isCatModal.value = !isCatModal.value;
  console.log("isCatModal value", isCatModal.value);
};

// Phrase Modal pop up
const addPhraseModal = () => {
  console.log(
    "cat phrase form phrasemodal",
    isCatModal.value,
    isPhraseModal.value
  );

  isPhraseModal.value = !isPhraseModal.value;
  console.log("response data", freqUsedPhrase);
  // console.log("isPhraseModal value", isPhraseModal.value);
};
// GET catagoires
callAPI();
console.log("freq used cat", freqUsedCat.value);
</script>

<style lang="scss" scoped>
.FUP__container {
  height: 35vh;
  overflow: scroll;
}
.cat__container {
  display: flex;
  align-items: center;
  height: 5vh;
  border-bottom: solid var(--maincolor2);
  overflow-x: scroll;
}
.TTS__container {
  margin-inline: 2vw;
  margin-bottom: 2vh;
  padding: 1vh;
  border-radius: 16px;
  border: solid var(--maincolor2) 3px;
}
</style>
