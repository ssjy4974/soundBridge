<template>
  <div>
    <div id="selectedVoice">
      <h3 class="">내 목소리</h3>
      <div v-if="selectedVoice">
        <voice-select-menu :voice="selectedVoice[0]"> </voice-select-menu>
      </div>
    </div>
    <div>
      <div><b class="font">전체 보기</b></div>

      <div id="controls">
        <img
          src="@/assets/img/controls-alt.png"
          id="controls"
          style="float: right"
        />
      </div>

      <div class="box">
        <voice-select-menu
          v-for="(voice, index) in voices"
          :key="index"
          :voice="voice"
          :index="index"
        >
        </voice-select-menu>
      </div>
    </div>
  </div>
</template>

<script setup>
import { storeToRefs } from "pinia";
import { useMypage } from "@/store/MyPage";
import VoiceSelectMenu from "@/components/pronounce/VoiceSelectMenu.vue";

const myPageStore = useMypage();

const { voices, selectedVoice } = storeToRefs(myPageStore);

// const updateVoices = (value) => {
//   voices.value.splice(value, 1);
// };

const callApi = () => {
  myPageStore.getSelectedVoice();
  myPageStore.getVoiceList();
};

callApi();
</script>

<style lang="scss" scoped>
#selectedVoice {
  margin-bottom: 10px;
}
#controls {
  display: block;
  height: 24px;
  margin-right: 5px;
}
.font {
  font-size: 24px;
}
</style>
