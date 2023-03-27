<template>
  <div class="container">
    <div class="title1">현재 녹음 중인 문장</div>

    <div class="sentence">{{ sentence }}</div>

    <div class="title2">목소리 생성 진행도</div>
    <div class="myProgress">
      <div class="myBar"></div>
      <div class="percent">{{ per }}%</div>
    </div>

    <div class="buttonSection">
      <button class="recordBtn" v-if="per == 0" @click="startRecord">
        기부 시작하기
      </button>
      <button class="recordBtn" v-else @click="toRecord">기부 이어하기</button>
    </div>
  </div>
</template>

<script setup>
import { onBeforeMount, ref } from "@vue/runtime-core";
import { apiInstance } from "@/api/index";
import router from "@/router/index";

const api = apiInstance();

const sentence = ref(null);
const per = ref(0);

onBeforeMount(() => {
  api.get(`/api/records/1/`).then((res) => {
    sentence.value = res.data.content;
    per.value = ((res.data.sentenceId / 3922) * 100).toFixed(1);
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
});

const toRecord = () => {
  router.replace(`/record`);
};

const startRecord = () => {
  api.post("/api/records/start").then(() => {
    router.replace(`/record`);
  });
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.recordBtn {
  width: 280px;
  height: 32px;
  background-color: #2bacff;
  border-radius: 32px;
  color: #ffffff;
  line-height: 0px;
}

.myProgress {
  width: 380px;
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
  left: 165px;
}

.buttonSection {
  margin-top: 40px;
}

.title1 {
  font-weight: bold;
  margin-top: 20px;
  margin-left: -250px;
}

.title2 {
  font-weight: bold;
  margin-top: 50px;
  margin-left: -256px;
}

.sentence {
  color: #46464680;
  margin-top: 10px;
  width: 400px;
}
</style>
