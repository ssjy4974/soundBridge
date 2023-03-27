import { defineStore } from "pinia";
import { voiceList, voice, select } from "@/api/mypage";
import { useMember } from "./Member";
import { ref } from "vue";

export const useMypage = defineStore("mypage", () => {
  // const voice = ref(0);
  // const nmae = ref(0);

  const voices = ref();
  const selectedVoice = ref();

  // getters == computed()  랜더링 될때 실행되는 함수
  // const doubleCount = computed(() => count.value * 2)
  const memberStore = useMember();

  // const voices = ref();

  async function getVoiceList(age, gender, features) {
    console.log("voice Feature", age, gender, features);
    await voiceList(
      age,
      gender,
      features,
      memberStore.accessToken,
      ({ data }) => {
        console.log(data, " Voice List Result");
        this.voices = data.content;
        console.log(this.voices);
      }
    );
  }

  async function getSelectedVoice() {
    await voice(
      memberStore.member.memberId,
      memberStore.accessToken,
      ({ data }) => {
        console.log(data, " Selected Voice Result");
        this.selectedVoice = data;
        console.log(this.selectedVoice);
      }
    );
  }

  async function selectVoice(voiceId) {
    await select(
      memberStore.member.memberId,
      voiceId,
      memberStore.accessToken,
      ({ data }) => {
        console.log(data, " Select Voice! ");
        this.selectedVoice = data;
      }
    );
  }

  // action == function()  함수

  return { selectedVoice, voices, getVoiceList, getSelectedVoice, selectVoice }; //반환 하면됨
});
