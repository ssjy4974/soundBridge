<template>
  <div id="parent">
    <video autoplay ref="video" id="myVideo1" />
  </div>
</template>

<script setup>
import { defineProps, onMounted, ref } from "vue";

const props = defineProps({ streamManager: Object });
const video = ref(null);
onMounted(() => {
  props.streamManager.addVideoElement(video.value);
});
</script>
<style scoped>
#myVideo1 {
  margin-top: -70px;
  height: 100vh;
  width: 100%;
  object-fit: cover;
  z-index: 11;
}
</style>
