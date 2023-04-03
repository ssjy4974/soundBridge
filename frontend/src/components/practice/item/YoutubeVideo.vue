<template>
  <div class="video-wrap" v-if="props.basicLetter">
    <iframe
      width="100%"
      allowfullscreen
      :src="`https://www.youtube.com/embed/fW_47jDrG2g?start=${basicLetter.startTime}&cc_load_policy=1`"
    ></iframe>
  </div>
</template>

<script setup>
import { defineProps } from "vue";

const props = defineProps(["basicLetter"]);
</script>

<style scoped>
.video-wrap {
  position: relative;
  padding-bottom: 56.25%;
  padding-top: 30px;
  height: 0;
  overflow: hidden;
}
.video-wrap iframe,
.video-wrap object,
.video-wrap embed {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
</style>
