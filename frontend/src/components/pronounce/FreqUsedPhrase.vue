<template>
  <div class="">
    <div class="cat__container">
      <span v-for="(catagory, index) in freqUsedCat" :key="index">
        {{ catagory }} |
      </span>
      <span @click="addCatModal"> | 추가하기 +</span>
      <AddCatModal v-if="isCatModal" @closemodal="addCatModal" />
    </div>
    <div class="FUP__container">
      <p v-for="(phrase, index) in freqUsedPhrase" :key="index">
        {{ phrase }}
      </p>
      <p>???</p>
      <p>???</p>
      <p>???</p>
      <p>???</p>
      <p>???</p>
      <p>???</p>
      <p>???</p>
      <p>???</p>
      <p href="" @click="addPhraseModal">자주쓰는말 추가하기 +</p>
      <br />
    </div>
    <AddPhraseModal v-if="isPhraseModal" @closemodalphrase="addPhraseModal" />
  </div>
</template>

<script setup>
import { ref } from "vue";
import AddCatModal from "./AddCatModal.vue";
import AddPhraseModal from "./AddPhraseModal.vue";
import { usePronounce } from "@/store/Pronounce.js";

// data from store
const store = usePronounce();
const freqUsedPhrase = store.freqUsedPhrase;
const freqUsedCat = store.freqUsedCat;

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
  console.log("isPhraseModal value", isPhraseModal.value);
};
console.log("isPhraseModal value", isPhraseModal.value);
</script>

<style lang="scss" scoped>
.FUP__container {
  height: 35vh;
  overflow: scroll;
}
.cat__container {
  height: 15%;
}
</style>
