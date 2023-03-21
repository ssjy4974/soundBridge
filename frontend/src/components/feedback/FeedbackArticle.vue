<template>
  <div v-if="props.feedbackArticle">
    <p>제목 : {{ props.feedbackArticle.title }}</p>
    <p>작성자 : {{ props.feedbackArticle.nickname }}</p>
    <p>시작 시간 : {{ props.feedbackArticle.startTime }}</p>
    <p>종료 시간 : {{ props.feedbackArticle.endTime }}</p>
    <div>
      <button>봉사자의 경우 수락하기 버튼이 보임</button><br />

      <font-awesome-icon
        @click="createModalHandler"
        icon="fa-solid fa-pen-to-square"
      />
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
const createModal = ref(false);

const createModalHandler = () => {
  createModal.value = !createModal.value;
  console.log(createModal.value);
};
</script>

<style lang="scss" scoped></style>
