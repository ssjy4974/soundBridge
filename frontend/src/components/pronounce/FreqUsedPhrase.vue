<template>
  <div class="pre__border">
    <div>
      <span>카테고리1</span>
      <span> | 카테고리2</span>
      <span> | 카테고리3</span>
      <span @click="addCatModal"> | 추가하기 +</span>
      <AddCatModal v-if="isCatModal" @closemodal="addCatModal" />
      <hr />
    </div>
    <div>
      <a href="">v-for문으로 바꿀 예정</a><br />

      <a href="">안녕하세요</a> <br />
      <a href="">저는 누구 입니다</a><br />
      <a href="">음.. 무슨 말을 해야할까요</a><br />
      <a href=""> 컴포넌트 제작 중입니다.</a><br />
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

<style lang="scss" scoped></style>
