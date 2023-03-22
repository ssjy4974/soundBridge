<template>
  <div v-if="props.feedbackArticle">
    <p>제목 : {{ props.feedbackArticle.title }}</p>
    <p>작성자 : {{ props.feedbackArticle.nickname }}</p>
    <p>시작 시간 : {{ props.feedbackArticle.startTime }}</p>
    <p>종료 시간 : {{ props.feedbackArticle.endTime }}</p>
    <div>
      <button @click="acceptMeeting(props.feedbackArticle)">
        봉사자의 경우 수락하기 버튼이 보임</button
      ><br />
      <font-awesome-icon
        icon="fa-solid fa-trash"
        @click="deleteFeedbackArticle"
      />
      <FeedbackCreateModal v-if="createModal" />
    </div>
  </div>
</template>

<script setup>
import { defineProps, defineEmits } from "vue";
import { apiInstance } from "@/api/index";
import FeedbackCreateModal from "./FeedbackCreateModal.vue";
import { ref } from "vue";
import Swal from "sweetalert2";

const api = apiInstance();
const props = defineProps(["feedbackArticle", "index"]);
const emit = defineEmits(["updateProps"]);

const meetingSaveReq = ref({
  feedbackBoardId: undefined,
  title: undefined,
  helperId: undefined,
  startTime: undefined,
  endTime: undefined,
});

const deleteFeedbackArticle = () => {
  Swal.fire({
    title: "삭제 하시겠습니까?",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Yes",
  }).then((result) => {
    if (result.isConfirmed) {
      api
        .delete(`/api/feedback-boards/${props.feedbackArticle.feedbackBoardId}`)
        .then(() => {
          emit("updateProps", { index: props.index });
          Swal.fire("삭제되었습니다", "success");
        })
        .catch((err) => {
          err;
          console.log(err);
        });
    }
  });
};

const acceptMeeting = (feedbackArticle) => {
  meetingSaveReq.value.feedbackBoardId = feedbackArticle.feedbackBoardId;
  meetingSaveReq.value.title = feedbackArticle.title;
  meetingSaveReq.value.helperId = feedbackArticle.writerId;
  meetingSaveReq.value.startTime = feedbackArticle.startTime;
  meetingSaveReq.value.endTime = feedbackArticle.endTime;

  api
    .post(`/api/meetings`, meetingSaveReq.value)
    .then(() => {
      alert("상담 수락 완료");
      emit("updateProps", { index: props.index });
    })
    .catch((err) => {
      console.log(err);
    });
};
</script>

<style lang="scss" scoped></style>
