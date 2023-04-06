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
        <div class="word">
          <router-link :to="`/words-detail/${index}`">
            {{ item.word }}
          </router-link>
        </div>
        <div class="myProgress">
          <div class="myBar" :id="`mybar` + index"></div>
          <div class="test">
            <div class="percent" v-if="item.tryCount != 0">
              성공률: {{ per[index] }}%
            </div>
            <div class="percent" v-else>성공률:0%</div>
          </div>
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
const per = ref([]);
const isWordModal = ref(false);

const callAPI = () => {
  MyDailyWord.getmydailyword().then(() => {
    console.log(MyDailyWord.mydailyword);
    MyDailyWord.mydailyword.forEach((element, index) => {
      var percent = 0;
      if (element.tryCount == 0) {
        percent = 0;
      } else {
        percent = (element.successCount / element.tryCount) * 100;
      }
      per.value.push(percent.toFixed(1));
      let elem = document.querySelector(`#mybar${index}`);
      var width = 1;
      var id = setInterval(frame, 1);
      function frame() {
        if (width >= percent) {
          clearInterval(id);
        } else {
          width++;
          elem.style.width = width + "%";
        }
      }
    });
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
      MyDailyWord.deletedailyword(wordMemberId, "DAILY_WORD");
      callAPI();
    }
  });
};

const addWordModal = () => {
  // console.log("addWord form Wordmodal", isWordModal.value);

  isWordModal.value = !isWordModal.value;
  callAPI();
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
  display: flex;
  flex-wrap: wrap;
  width: 95%;
  justify-content: space-evenly;
}

.child {
  background-color: var(--maincolor2);
  border-radius: 8px;
  box-shadow: 4px 4px 4px 0px var(--black4);
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  height: 14vh;
  width: 40vw;
  color: white;
  margin-block: 3vh;
  font-size: 1.7rem;
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
  left: 40%;
  // bottom: 8%;
  color: black;
  justify-self: flex-start;
  align-self: flex-end;
  // padding-bottom: 10px;
}

.word {
  // position: relative;
  width: 70%;
  // bottom: 30%;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  color: var(--maincolor8);
  text-align: center;
}

.addButton {
  // display: flex;
  // height: 10vh;
  width: fit-content;
  border-radius: 8px;
  background-color: var(--black1);
  position: fixed;
  bottom: 7vh;
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
  // align-content: center;
}
.test {
  width: 100%;
  display: flex;
  justify-content: center;
}
.myBar {
  position: absolute;
  width: 0%;
  height: 20px;
  border-radius: 32px;
  background-color: #8ad1ff;
  color: #0b76bb;
}

.percent {
  position: relative;
  color: black;
  font-size: 15px;
}
</style>
