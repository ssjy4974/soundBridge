<template>
  <div>
    <div id="selectedVoice">
      <h3 class="">내 목소리</h3>
      <div v-if="selectedVoice">
        <voice-select-menu :voice="selectedVoice[0]"> </voice-select-menu>
      </div>
    </div>
    <div>
      <h3 class="">전체 보기</h3>
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
import { computed, onBeforeMount, ref } from "vue";
const myPageStore = useMypage();

let { voices, selectedVoice } = storeToRefs(myPageStore);

const updateSelectedVoice = (value) => {
  selectedVoice.value.splice(value, 1);
};

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
</style>
