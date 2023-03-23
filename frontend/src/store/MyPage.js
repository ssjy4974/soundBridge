import { defineStore } from "pinia";
import { voiceList } from "@/api/mypage";
import { useMember } from "./Member";
import { ref } from "vue";

export const useMypage = defineStore("mypage", () => {
  // const voice = ref(0);
  // const nmae = ref(0);

  const voices = ref();

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
        console.log(data.content, " Voice List Result");
        this.voices = data.content;
        console.log(this.voices);
      }
    );
  }

  // action == function()  함수

  return { voices, getVoiceList }; //반환 하면됨
});
