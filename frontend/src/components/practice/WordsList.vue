<template>
  <div class="container">
    <div class="parent">
      <div
        class="child"
        @click=""
        v-for="(item, index) in MyDailyWord.mydailyword"
      >
        <router-link :to="`/wordsdetail/${index}`">
          {{ item.word }}
        </router-link>
      </div>
    </div>
    <div>
      <button @click="addWordModal">연습할 단어 추가하는 버튼</button>
      <AddWordModal v-if="isWordModal" @closemodal="addWordModal" />
    </div>
  </div>
</template>

<script setup>
import { useMyDailyWord } from "@/store/DailyWord";
import AddWordModal from "./AddWordModal.vue";
import { ref } from "vue";

const MyDailyWord = useMyDailyWord();

const isWordModal = ref(false);

const callAPI = () => {
  MyDailyWord.getmydailyword();
};
callAPI();

const addWordModal = () => {
  console.log("addWord form Wordmodal", isWordModal.value);

  isWordModal.value = !isWordModal.value;
  console.log("isWordModal value", isWordModal.value);
};
</script>

<style lang="scss" scoped>
.parent {
  width: 90%;
  border: 1px solid red;
  margin: 0 auto;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
}

.child {
  background-color: aqua;
  width: 40%;
  height: 70px;
  margin: 20px 0;
}
</style>
