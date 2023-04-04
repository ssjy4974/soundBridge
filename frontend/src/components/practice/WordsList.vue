<template>
  <div class="container">
    <h1>연습 단어 목록</h1>
    <div class="parent">
      <div
        class="child"
        v-for="(item, index) in MyDailyWord.mydailyword"
        :key="index"
      >
        <div class="xmak" @click="deleteHandler(item.wordMemberId)">
          <font-awesome-icon icon="fa-solid fa-xmark" style="font-size: 1rem" />
        </div>
        <div>
          <router-link :to="`/words-detail/${index}`" class="word">
            {{ item.word }}
          </router-link>
        </div>
        <div class="myProgress">
          <div class="myBar"></div>
          <div class="percent" v-if="item.tryCount != 0">
            {{ (item.successCount / item.tryCount) * 100 }}%
          </div>
          <div class="percent" v-else>0%</div>
        </div>
      </div>
    </div>
    <div class="addButton">
      <button class="addButton__button" @click="addWordModal">
        연습 단어 추가하기 +
      </button>
      <AddWordModal v-if="isWordModal" @closemodal="addWordModal" />
    </div>
  </div>
</template>

<script setup>
import { useMyDailyWord } from "@/store/DailyWord";
import AddWordModal from "./AddWordModal.vue";
import { ref } from "vue";
import Swal from "sweetalert2";

const MyDailyWord = useMyDailyWord();
const per = ref(0);
const isWordModal = ref(false);

const callAPI = () => {
  MyDailyWord.getmydailyword().then(() => {
    per.value = ((3000 / 3922) * 100).toFixed(1);
    let elem = document.querySelector(".myBar");
    var width = 1;
    var id = setInterval(frame, 10);
    function frame() {
      if (width >= per.value) {
        clearInterval(id);
      } else {
        width++;
        elem.style.width = width + "%";
      }
    }
  });
};
callAPI();

const deleteHandler = (wordMemberId) => {
  Swal.fire({
    title: "삭제 하시겠습니까?",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Yes",
  }).then((result) => {
    if (result.isConfirmed) {
      MyDailyWord.deletedailyword(wordMemberId);
    }
  });
};

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
  box-shadow: 4px 4px 4px 0px var(--black4);
  display: flex;
  flex-direction: column;
  // justify-content: space-between;
  align-items: center;
  height: 14vh;
  width: 40vw;
  color: white;
  margin-block: 3vh;
  font-size: 40px;
}
.child a :hover {
  background-color: var(--maincolor6);
  border: 10px solid;
  // color: var(--black);
}

.close__button {
  display: flex;
  justify-content: end;
}
.xmak {
  margin-inline: 10%;
  // justify-self: flex-start;
  align-self: flex-end;
  padding-bottom: 10px;
}

.word {
  color: blue;
}
.addButton {
  // display: flex;
  height: 10vh;
  width: 100%;
  background-color: var(--black1);
  position: fixed;
  bottom: 0vh;
  left: 28vw;
}

.myProgress {
  width: 150px;
  height: 20px;
  margin-top: 10px;
  border-radius: 32px;
  background-color: #f1f1f1;
  display: flex;
  position: relative;
  box-shadow: 0 3px 6px rgba(0, 0, 0, 0.12), 0 5px 5px rgba(0, 0, 0, 0.22);
}

.myBar {
  width: 0%;
  height: 20px;
  border-radius: 32px;
  background-color: #8ad1ff;
  color: #0b76bb;
}

.percent {
  position: absolute;
  color: black;
  font-size: 15px;
  left: 50px;
}
</style>
