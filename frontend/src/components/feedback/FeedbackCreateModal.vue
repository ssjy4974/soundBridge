<template>
  <div class="base__modal">
    <div @click="$emit('closemodal')" class="close__modal">
      <font-awesome-icon icon="fa-solid fa-xmark" />
    </div>
    <div class="feedback__title">
      <span>제목 </span>
      <input
        v-model="feedbackArticleSaveReq.title"
        type="text"
        placeholder="제목을 입력하세요"
      />
    </div>
    <div class="date__picker">
      <VueDatePicker
        v-model="startTime"
        show-now-button
        :min-date="new Date()"
      ></VueDatePicker>
      <br />
      <VueDatePicker
        v-model="endTime"
        show-now-button
        :min-date="new Date()"
      ></VueDatePicker>
      <br />
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

// Close Modal
defineEmits(["closemodal"]);
</script>

<style scoped>
.base__modal {
  height: 360px;
  background-color: var(--maincolor4);
  border-radius: 24px 24px 24px 24px;

  margin: auto;
  align-self: center;
  position: fixed;
  top: 30vh;
  left: 10vw;
  width: 80%;
  z-index: 2;
}
.date__picker {
  margin: 10%;
}
.feedback__title {
  margin-inline: 10%;
}
</style>
