<template>
  <div id="ovVideoContainer" v-if="props.streamManager">
    <ov-video id="ovVideo" :stream-manager="props.streamManager" />
  </div>
</template>

<script setup>
import OvVideo from "@/components/meeting/OvVideo.vue";

import { defineProps, ref } from "vue";

const props = defineProps({
  streamManager: Object,
});
</script>
