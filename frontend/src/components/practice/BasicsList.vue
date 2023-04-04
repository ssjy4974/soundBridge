<template>
  <div class="container" v-if="store.basicLetters">
    <h1>기본 발음 연습</h1>
    <div class="parent">
      <div
        class="child"
        v-for="(item, index) in store.basicLetters"
        :key="index"
      >
        <router-link :to="`/practice-basics-detail/${item.basicLetterId}`">
          <div class="img_wrap">
            <img :src="`${IMAGE_PATH}/${item.letterImage}`" />
          </div>
        </router-link>
        <div class="myProgress">
          <div class="myBar" :id="`mybar${index}`"></div>
          <div class="test">
            <div class="percent" v-if="item.tryCount != 0">
              성공률: {{ per[index] }}%
            </div>
            <div class="percent" v-else>성공률:0%</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useBasicLetterStore } from "@/store/BasicLetter";
import { ref, onUpdated } from "vue";

const store = useBasicLetterStore();
const IMAGE_PATH = import.meta.env.VITE_IMAGE_PATH;
const per = ref([]);

store.getBasicLetters();

onUpdated(() => {
  store.basicLetters.forEach((element, index) => {
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
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.parent {
  padding-top: 0px;
  margin-inline: 5vw;
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
}
.addButton {
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
.img_wrap {
  display: flex;
  justify-content: center;
}
img {
  width: 55%;
}
</style>
