<template>
  <div class="base__modal">
    <p>제목</p>
    <input
      v-model="feedbackArticleSaveReq.title"
      type="text"
      placeholder="제목을 입력하세요"
    />
    <div>
      <VueDatePicker
        v-model="startTime"
        show-now-button
        :min-date="new Date()"
      ></VueDatePicker>
      <VueDatePicker
        v-model="endTime"
        show-now-button
        :min-date="new Date()"
      ></VueDatePicker>
      <button @click="feedbackBoardSave">전송</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { apiInstance } from "@/api/index";
import VueDatePicker from "@vuepic/vue-datepicker";
import "@vuepic/vue-datepicker/dist/main.css";

const api = apiInstance();
const startTime = ref();
const endTime = ref();

const feedbackArticleSaveReq = ref({
  title: undefined,
  startTime: undefined,
  endTime: undefined,
});

const feedbackBoardSave = () => {
  if (!feedbackArticleSaveReq.value.title) {
    alert("제목을 입력 해주세요");
    return;
  }

  if (!startTime.value) {
    alert("시작 시간를 선택 해주세요");
    return;
  }

  if (!endTime.value) {
    alert("종료 시간를 선택 해주세요");
    return;
  }

  feedbackArticleSaveReq.value.startTime = startTime.value;
  feedbackArticleSaveReq.value.endTime = endTime.value;
  api
    .post(`/api/feedback-boards`, feedbackArticleSaveReq.value)
    .then(() => {
      alert("등록 성공");
    })
    .catch((err) => {
      err;
      alert("등록 실패");
    });
};
</script>

<style scoped>
.base__modal {
  border: solid;
  width: 360px;
  height: 480px;
}
</style>
