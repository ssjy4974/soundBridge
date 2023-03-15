<template>
  <div>
    <h1>Feedback MainPage 게시판</h1>
    <FeedbackList />
    <FeedbackCreate />
  </div>
</template>

<script setup>
import FeedbackList from "@/components/feedback/FeedbackList.vue";
import FeedbackCreate from "@/components/feedback/FeedbackCreate.vue";
</script>

<style lang="scss" scoped></style>
