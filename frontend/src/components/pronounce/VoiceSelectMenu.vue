<template>
  <div style="filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25))">
    <div
      class="flex justify-center items-center flex-grow-0 flex-shrink-0 relative gap-2 px-2"
    >
      <div class="box">
        <img :src="`${voice.profile}`" alt="" id="profile" :key="updateImage" />
        <div class="">
          {{ voice.voiceName }}
        </div>
        <voice-features></voice-features><voice-features></voice-features
        ><voice-features></voice-features><voice-features></voice-features
        ><voice-features></voice-features><voice-features></voice-features
        ><voice-features></voice-features><voice-features></voice-features>
        <br />
      </div>
    </div>
    <div>
      <div style="box-shadow: 0px 1px 2px 0 rgba(16, 24, 40, 0.05)">
        <p>선택하기</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import VoiceFeatures from "./item/VoiceFeatures.vue";
import { defineProps, onBeforeMount } from "vue";
const props = defineProps({ voice: Object });
let voice = JSON.parse(JSON.stringify(props)).voice;
onBeforeMount(() => {
  console.log(voice.age);
});
</script>

<style lang="scss" scoped>
.box {
  padding: 10px 20px 1px 20px;
  box-shadow: 0px 0px 0px 1px;
  border-radius: 20px;
}

#profile {
  width: 50px;
  height: 50px;
}
</style>
