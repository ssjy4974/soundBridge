<template>
  <div class="content" v-if="props.basicLetter">
    <h1>발음 조음법 설명</h1>
    <div class="content_text_wrap">
      <div class="content_text">
        <p>{{ props.basicLetter.explanation }}</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { defineProps } from "vue";

const props = defineProps(["basicLetter"]);
</script>

<style scoped>
h1 {
  text-align: center;
  padding-top: 10px;
}
.content {
  position: relative;
}
.content_text_wrap {
  width: fit-content;
  width: 90%;
  padding-top: 5%;
  padding-bottom: 5%;
  padding-left: 5%;
  /* padding-right: 15%; */
}
.content_text {
  border: 3px solid;
  border-radius: 5px;
  padding: 5%;
}
</style>
