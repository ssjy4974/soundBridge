<template>
  <div class="container">
    <h1>연습 문장 목록</h1>
    <div class="parent">
      <div
        class="child"
        v-for="(item, index) in MyDailyWord.sentenceList"
        :key="index"
      >
        <div class="xmak" @click="deleteHandler(item.wordMemberId)">
          <font-awesome-icon icon="fa-solid fa-xmark" style="font-size: 1rem" />
        </div>
        <div class="word">
          <router-link :to="`/sentence-detail/${index}`">
            {{ item.word }}
          </router-link>
        </div>
        <div class="myProgress">
          <div class="myBar" :id="`mybar` + index"></div>
          <div class="test">
            <div
              class="percent"
              :id="`my_percent` + index"
              v-if="item.tryCount != 0"
            ></div>
            <div class="percent" v-else>성공률:0%</div>
          </div>
        </div>
      </div>
    </div>
    <div class="addButton">
      <button class="addButton__button" @click="addSentenceModal">
        연습 문장 추가하기 +
      </button>
      <AddSentenceModal v-if="isSentenceModal" @completeAdd="completeAdd" />
    </div>
  </div>
</template>

<script setup>
import { useMyDailyWord } from "@/store/DailyWord";
import AddSentenceModal from "./AddSentenceModal.vue";
import { onUpdated, ref } from "vue";
import Swal from "sweetalert2";

const MyDailyWord = useMyDailyWord();
const per = ref([]);
const isSentenceModal = ref(false);

MyDailyWord.getmysentence();
onUpdated(() => {
  console.log("then", MyDailyWord.sentenceList);
  MyDailyWord.sentenceList.forEach((element, index) => {
    var percent = 0;
    if (element.tryCount == 0) {
      percent = 0;
    } else {
      percent = ((element.successCount / element.tryCount) * 100).toFixed(1);
    }
    per.value.push(percent.toFixed(1));
    let elem = document.querySelector(`#mybar${index}`);
    let elem2 = document.getElementById(`my_percent${index}`);
    elem.style.width = "0%";
    if (document.getElementById(`my_percent${index}`) != null) {
      elem2.textContent = `성공률: ${percent}%`;
    }
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
      MyDailyWord.deletedailyword(wordMemberId, "SENTENCE");
    }
  });
};

const addSentenceModal = () => {
  // console.log("addWord form Wordmodal", isWordModal.value);

  isSentenceModal.value = !isSentenceModal.value;
  // console.log("isWordModal value", isWordModal.value);
};

const completeAdd = () => {
  addSentenceModal();
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
  width: 100%;
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
  margin-inline: 3%;
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
  width: 300px;
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
