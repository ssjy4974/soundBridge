<template>
  <div>
    <FeedbackArticle
      v-for="(feedbackArticle, index) in feedbackList"
      :key="index"
      :feedbackArticle="feedbackArticle"
      :index="index"
      @updateProps="(value) => updateFeedbackList(value)"
    />
    <div>
      <button @click="createModalHandler" id="feedback-button">
        피드백 요청글 작성하기
      </button>
      <FeedbackCreateModal
        v-if="createModal"
        @closemodal="createModalHandler"
      />
    </div>
  </div>
</template>

<script setup scoped>
import { apiInstance } from "@/api/index";
import { onMounted, onBeforeUnmount, ref } from "@vue/runtime-core";
import FeedbackArticle from "./FeedbackArticle.vue";
import FeedbackCreateModal from "./FeedbackCreateModal.vue";

const api = apiInstance();

let feedbackList = ref([]);
let cursorId = ref();
let hasNext = ref();
const createModal = ref(false);
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
    .get(`/api/feedback-boards?cursorId=${cursorId.value}`)
    .then((res) => {
      feedbackList.value.push(...res.data.content);
      hasNext.value = res.data.last;
      cursorId.value =
        res.data.content[res.data.numberOfElements - 1].feedbackBoardId;
    })
    .catch((err) => {
      console.log(err);
    });
};

const createModalHandler = () => {
  createModal.value = !createModal.value;
  console.log(createModal.value);
};

const updateFeedbackList = (value) => {
  feedbackList.value.splice(value, 1);
};

const callApi = () => {
  api
    .get(`/api/feedback-boards`, {
      params: cursorId.value,
    })
    .then((res) => {
      feedbackList.value = res.data.content;
      hasNext.value = res.data.last;
      cursorId.value =
        res.data.content[res.data.numberOfElements - 1].feedbackBoardId;
    })
    .catch((err) => {
      console.log(err);
    });
};

callApi();
</script>

<style lang="scss" scoped>
#feedback-button {
  margin-left: 21%;
}
</style>
