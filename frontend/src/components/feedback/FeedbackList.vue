<template>
  <div v-if="feedbackList.length">
    <feedback-article
      v-for="(feedbackArticle, index) in feedbackList"
      :key="index"
      :feedbackArticle="feedbackArticle"
      :index="index"
      @updateProps="(value) => updateFeedbackList(value)"
    />
  </div>
  <div v-else id="noResult">
    <p>조회 결과가 없습니다.</p>
  </div>
  <div v-if="member.role === 'APPLICANT'">
    <font-awesome-icon
      icon="fa-solid fa-circle-plus"
      id="feedback-button"
      @click="createModalHandler"
    />
    <feedback-create-modal
      v-if="createModal"
      @closemodal="createModalHandler"
      @createFeedback="updateList"
    />
  </div>
</template>

<script setup scoped>
import { apiInstance } from "@/api/index";

import FeedbackArticle from "./FeedbackArticle.vue";
import FeedbackCreateModal from "./FeedbackCreateModal.vue";
import { useMember } from "@/store/Member";
import { onMounted, onBeforeUnmount, ref } from "vue";
const api = apiInstance();
const memberStore = useMember();
const createModal = ref(false);

const { accessToken, member } = memberStore;
let feedbackList = ref([]);
let cursorId = ref();
let hasNext = ref();

onMounted(() => {
  document.addEventListener("scroll", scrollHandler);
});

onBeforeUnmount(() => {
  document.removeEventListener("scroll", scrollHandler);
});
const scrollHandler = (e) => {
  const scrollTop = document.documentElement.scrollTop;
  const clientHeight = document.documentElement.clientHeight;
  const scrollHeight = document.documentElement.scrollHeight;
  // isAtTheBottom.value = scrollHeight === scrollTop + clientHeight;
  const isAtTheBottom = scrollHeight === scrollTop + clientHeight;
  if (isAtTheBottom && !hasNext.value) {
    setTimeout(() => {
      moreList();
    }, 100);
  }
};

const moreList = () => {
  api
    .get(`/api/feedback-boards?cursorId=${cursorId.value}`, {
      headers: {
        "access-token": accessToken,
      },
    })
    .then((res) => {
      feedbackList.value.push(...res.data.content);
      hasNext.value = res.data.last;
      cursorId.value =
        res.data.content[res.data.content.length - 1].feedbackBoardId;
    })
    .catch((err) => {
      console.log(err);
    });
};

const createModalHandler = () => {
  createModal.value = !createModal.value;
};

const updateFeedbackList = (value) => {
  feedbackList.value.splice(value, 1);
};

const callApi = () => {
  api
    .get(`/api/feedback-boards`, {
      headers: {
        "access-token": accessToken,
      },
    })
    .then((res) => {
      if (res.data.content.length) {
        feedbackList.value = res.data.content;
        hasNext.value = res.data.last;
        cursorId.value =
          res.data.content[res.data.content.length - 1].feedbackBoardId;
      }
    })
    .catch((err) => {
      console.log(err);
    });
};

const updateList = () => {
  createModal.value = !createModal.value;
  document.documentElement.scrollTop = 0;
  callApi();
};

callApi();
</script>

<style scoped>
#feedback-button {
  font-size: 3.5rem;
  margin-left: 80%;
  position: fixed;
  bottom: 9%;
  color: var(--maincolor5);
}
#noResult {
  position: absolute;
  bottom: 45%;
  left: 30%;
  color: gray;
  font-size: 20px;
}
</style>
