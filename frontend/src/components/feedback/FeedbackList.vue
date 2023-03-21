<template>
  <div>
    <h2>Feedback List</h2>
    <h3>
      FeedbackArticle를 감싸는 컴포넌트로 회원별로 다른 화면이 나타나도록 설정할
      계획임
    </h3>
    <p>
      데이터 store에서 가져와서 for문 돌면서 feedbackArticle에 props 하는걸
      생각중
    </p>
    <hr />

    <FeedbackArticle />
  </div>
</template>

<script setup>
import FeedbackArticle from "./FeedbackArticle.vue";
</script>

<style lang="scss" scoped></style>
