<template>
  <div style="filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25))">
    <div
      class="flex justify-center items-center flex-grow-0 flex-shrink-0 relative gap-2 px-2"
    >
      <div class="box" v-if="props.voice">
        <img :src="`${props.voice.profile}`" alt="" id="profile" />
        <div class="">
          {{ props.voice.voiceName }}
        </div>

        <div class="">
          <voice-features
            v-for="(feature, index) in props.voice.featureResList"
            :key="index"
            :feature="feature"
            :index="index"
          >
          </voice-features>
        </div>
        <div>
          <button type="button" @click="selectVoice">선택하기</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import VoiceFeatures from "./item/VoiceFeatures.vue";
import { defineProps, onBeforeMount, ref } from "vue";
import { useMypage } from "@/store/MyPage";
import { voice } from "@/api/mypage";
const myPageStore = useMypage();

const props = defineProps(["voice", "index"]);
// let voice = ref(props);

const selectVoice = (e) => {
  e.preventDefault();
  console.log("목소리 선택!", props.voice.voiceId);
  myPageStore.selectVoice(props.voice.voiceId);
  window.location.reload(true);
};

onBeforeMount(() => {
  // console.log("@#@#", props.voice.featureResList);
});
</script>

<style lang="scss" scoped>
.box {
  padding: 10px 20px 1px 20px;
  box-shadow: 0px 0px 0px 1px;
  border-radius: 20px;
  margin-bottom: 30px;
}

#profile {
  width: 50px;
  height: 50px;
  border-radius: 50px;
}
</style>
