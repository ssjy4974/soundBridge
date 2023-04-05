<template>
  <div>
    <div id="selectedVoice">
      <h3 class="title">내 목소리</h3>
      <div v-if="selectedVoice">
        <voice-select-menu :voice="selectedVoice[0]"> </voice-select-menu>
      </div>
    </div>
    <div>
      <div id="all">
        <b class="title" id="font">전체 보기</b>
        <span id="controls">
          <img
            src="@/assets/img/controls-alt.png"
            id="controls"
            style="float: right"
            @click="searchModal"
          />
          <!--  @closemodal="searchModal"  -->
          <SearchModal
            @updateList="
              (checkedGender, checkedAge, checkedFeatures) =>
                update(checkedGender, checkedAge, checkedFeatures)
            "
            v-if="isSearchModal"
          />
        </span>
      </div>

      <div v-if="voices[0]">
        <voice-select-menu
          v-for="(voice, index) in voices"
          :key="index"
          :voice="voice"
          :index="index"
        >
        </voice-select-menu>
      </div>
      <div v-else id="noResult">조회된 목소리가 없습니다!</div>
    </div>
  </div>
</template>

<script setup>
import { storeToRefs } from "pinia";
import { useMypage } from "@/store/MyPage";
import SearchModal from "@/components/pronounce/item/searchModal.vue";
import { ref } from "vue";
import VoiceSelectMenu from "@/components/pronounce/VoiceSelectMenu.vue";

const myPageStore = useMypage();

const { voices, selectedVoice } = storeToRefs(myPageStore);

let gender = ref();
let age = ref();
let features = ref([]);

const isSearchModal = ref(false);

const callApi = () => {
  myPageStore.getSelectedVoice();
  myPageStore.getVoiceList(age.value, gender.value, features.value);
};

const searchModal = () => {
  isSearchModal.value = !isSearchModal.value;
};

const update = (checkedGender, checkedAge, checkedFeatures) => {
  isSearchModal.value = false;
  gender.value = checkedGender;
  age.value = checkedAge;
  features.value = checkedFeatures;
  console.log("cat phrase form 3@#@#", age.value, gender.value, features);
  myPageStore.getVoiceList(age.value, gender.value, features.value);
};

callApi();
</script>

<style lang="scss" scoped>
#selectedVoice {
  margin-bottom: 10px;
}
#controls {
  // display: block;
  height: 28px;
  margin-right: 12px;
  padding-top: 8px;
}

#all {
  margin-bottom: 15px;
}

.title {
  margin-left: 16px;
}

#font {
  font-size: 24px;
}

#noResult {
  text-align: center;
  margin-top: 20%;
}
</style>
