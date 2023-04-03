<template>
  <div class="box" v-if="props.voice">
    <div>
      <div id="show">
        <div style="float: left">
          <img :src="`${props.voice.profile}`" alt="" id="profile" />
        </div>
        <div id="name">
          {{ props.voice.voiceName }}
        </div>

        <div class="feature">
          <voice-features :feature="props.voice.voiceGender"></voice-features>
          <voice-features :feature="props.voice.age"></voice-features>
          <voice-features
            v-for="(feature, index) in props.voice.featureResList"
            :key="index"
            :feature="feature"
            :index="index"
          >
          </voice-features>
        </div>
      </div>
      <div style="text-align: right">
        <button type="button" @click="selectVoice">선택하기</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import VoiceFeatures from "./item/VoiceFeatures.vue";
import { defineProps, onBeforeMount, ref } from "vue";
import { useMypage } from "@/store/MyPage";
// import { voice } from "@/api/mypage";
const myPageStore = useMypage();

const props = defineProps(["voice", "index"]);
// let voice = ref(props);

const selectVoice = (e) => {
  e.preventDefault();
  console.log("목소리 선택!", props.voice.voiceId);
  myPageStore.selectVoice(props.voice.voiceId);
  window.location.reload(true);
};

onBeforeMount(() => {});
</script>

<style lang="scss" scoped>
.box {
  padding: 10px 0px 1px 15px;
  border-style: solid;
  border-radius: 20px;
  border-width: 1px;
  border-color: rgb(187, 187, 187);
  box-shadow: 0 4px 4px-4px rgb(240, 240, 240);
  margin-bottom: 30px;
  margin-left: 10px;
  margin-right: 10px;
}

button {
  color: white;
  font-size: 14px;
  background-color: var(--maincolor3);
  margin-bottom: 5px;
  border-radius: 30px;
  padding: 12px 20px 12px 20px;
  width: 100px;
  margin-right: 5px;
}
button:hover {
  border-color: var(--maincolor7);
}

#profile {
  width: 90px;
  height: 90px;
  border-radius: 50px;
  margin-right: 15px;
  margin-top: 10px;
}

#name {
  font-size: 20px;
  margin-bottom: 15px;
}

#show {
  min-height: 100px;
  height: auto;
}
</style>
