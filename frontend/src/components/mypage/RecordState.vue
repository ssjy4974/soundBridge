<template>
  <div>현재 녹음 중인 문장</div>

  <div>{{ sentence }}</div>

  <div>목소리 생성 진행도</div>
  <div id="myProgress">
    <div id="myBar">{{ per }}%</div>
  </div>

  <div id="buttonSection">
    <button id="recordBtn" v-if="per == 0" @click="toRecord">
      기부 시작하기
    </button>
    <button id="recordBtn" v-else @click="toRecord">기부 이어하기</button>
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
    let elem = document.getElementById("myBar");
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
    elem.innerHTML = per.value + "%";
  });
});

const toRecord = () => {
  router.replace(`/record`);
};
</script>

<style scoped>
#recordBtn {
  width: 280px;
  height: 32px;
  background-color: #2bacff;
  border-radius: 32px;
  color: #ffffff;
  line-height: 0px;
}

#myProgress {
  width: 280px;
  border-radius: 32px;
  background-color: #f1f1f1;
}

#myBar {
  width: 0%;
  height: 30px;
  border-radius: 32px;
  background-color: #8ad1ff;
  text-align: center;
  color: #0b76bb;
}
</style>
