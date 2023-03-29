<template>
  <div id="ovVideoContainer" v-if="props.streamManager">
    <ov-video id="ovVideo" :stream-manager="props.streamManager" />
  </div>
</template>

<script setup>
import OvVideo from "@/components/meeting/OvVideo2.vue";

import { defineProps, ref } from "vue";

const props = defineProps({
  streamManager: Object,
});
</script>

<style scoped>
#ovVideoContainer {
  /* border: 1px solid green; */
}
</style>
