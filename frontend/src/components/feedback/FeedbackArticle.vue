<template>
  <div>
    <h3>장애인 회원 화면에서는 자신이 쓴 게시글만 보이게</h3>
    <p>제목 : db에서 가져올예정</p>
    <p>작성자 : 장애인 회원</p>
    <p>시작 시간 : data type - date</p>
    <p>종료 시간 : data type - date</p>
    <div>
      <button>봉사자의 경우 수락하기 버튼이 보임</button><br />

      <font-awesome-icon
        @click="createModalHandler"
        icon="fa-solid fa-pen-to-square"
      />
      <span> </span>

      <font-awesome-icon
        icon="fa-solid fa-trash"
        @click="deleteModalHandler"
        @closemodal="!deleteModal"
      />
      <FeedbackDeleteModal
        v-if="deleteModal"
        :data="deleteModal"
        @closemodal="deleteModalHandler"
      />
      <FeedbackCreateModal v-if="createModal" />
    </div>
  </div>
</template>

<script setup>
import FeedbackDeleteModal from "./FeedbackDeleteModal.vue";
import FeedbackCreateModal from "./FeedbackCreateModal.vue";
import { ref } from "vue";

const createModal = ref(false);
const createModalHandler = () => {
  createModal.value = !createModal.value;
  console.log(createModal.value);
};

const deleteModal = ref(false);
const deleteModalHandler = () => {
  deleteModal.value = !deleteModal.value;
  console.log(deleteModal.value);
};
</script>

<style lang="scss" scoped></style>
