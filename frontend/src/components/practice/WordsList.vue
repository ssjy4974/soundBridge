<template>
  <div class="container">
    <div class="parent">
      <div
        class="child"
        v-for="(item, index) in MyDailyWord.mydailyword"
        :key="index"
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
    <!--  -->
    <!-- <div class="parent">
      <div class="child">
        <div class="link__container">
          <router-link :to="`/wordsdetail/${index}`">돈까스 </router-link>
        </div>
      </div>
      <div class="child">
        <router-link :to="`/wordsdetail/${index}`"> 돈까스 </router-link>
      </div>
      <div class="child">
        <router-link :to="`/wordsdetail/${index}`"> 돈까스 </router-link>
      </div>
      <div class="child">
        <router-link :to="`/wordsdetail/${index}`"> 돈까스 </router-link>
      </div>
      <div class="child">
        <router-link :to="`/wordsdetail/${index}`"> 돈까스 </router-link>
      </div>
      <div class="child">
        <router-link :to="`/wordsdetail/${index}`"> 돈까스 </router-link>
      </div>
      <div class="child">
        <router-link :to="`/wordsdetail/${index}`"> 돈까스 </router-link>
      </div>
      <div class="child">
        <router-link :to="`/wordsdetail/${index}`"> 돈까스 </router-link>
      </div>
      <div class="child">
        <router-link :to="`/wordsdetail/${index}`"> 돈까스 </router-link>
      </div>
      <div class="child">
        <router-link :to="`/wordsdetail/${index}`"> 돈까스 </router-link>
      </div>
      <div class="child">
        <router-link :to="`/wordsdetail/${index}`"> 돈까스 </router-link>
      </div>
      <div class="child">
        <router-link :to="`/wordsdetail/${index}`"> 돈까스 </router-link>
      </div>
      <div class="child">
        <router-link :to="`/wordsdetail/${index}`"> 돈까스 </router-link>
      </div>
      <div class="child">
        <router-link :to="`/wordsdetail/${index}`"> 돈까스 </router-link>
      </div>
      <div class="child">
        <router-link :to="`/wordsdetail/${index}`"> 돈까스 </router-link>
      </div>
    </div> -->
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
  console.log("?????????", MyDailyWord.mydailyword);
  // console.log("addWord form Wordmodal", isWordModal.value);

  isWordModal.value = !isWordModal.value;
  // console.log("isWordModal value", isWordModal.value);
};
</script>

<style lang="scss" scoped>
.container {
  // background-color: var(--maincolor2);
  display: flex;
  flex-direction: column;
  align-items: center;
}
.parent {
  margin-inline: 5vw;
  // border: 1px solid var(--maincolor3);
  // padding-inline: 5%;
  padding-block: 10%;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
}

.child {
  background-color: var(--maincolor2);
  border-radius: 8px;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 8vh;
  width: 40vw;
  color: white;
  margin-block: 3vh;
}
.child a {
  // color: var(--black);
}
</style>
