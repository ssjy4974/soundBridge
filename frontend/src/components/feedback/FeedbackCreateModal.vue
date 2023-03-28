<template>
  <div class="base__modal">
    <div @click="$emit('closemodal')" class="close__modal" id="close-modal">
      <font-awesome-icon icon="fa-solid fa-xmark" />
    </div>
    <div class="feedback__title">
      <input
        v-model="feedbackArticleSaveReq.title"
        type="text"
        placeholder="요청글을 입력하세요"
        id="input-box"
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
      <button @click="feedbackBoardSave" id="send-button">전송</button>
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
  height: 250px;
  background-color: var(--maincolor2);
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
  margin-top: 10%;
}

#input-box {
  /* Auto layout */

  flex-direction: column;
  justify-content: center;
  align-items: flex-end;
  padding: 5px;

  margin-left: 2%;
  margin-right: 2%;
  /* Black/200 */

  border-top: 2px solid #f1f1f1;
  border-right: 2px solid #f1f1f1;
  border-bottom: 2px solid #f1f1f1;
  border-left: 1px solid #f1f1f1;

  filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
  border-radius: 8px;

  /* Inside auto layout */

  flex: none;
  order: 0;
  flex-grow: 0;
}

#send-button {
  display: flex;
  margin-left: 32%;
}

#close-modal {
  float: right;
  margin-right: 7%;
  position: relative;
  bottom: -3%;
}
</style>
