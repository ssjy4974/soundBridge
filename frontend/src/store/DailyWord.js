import { defineStore } from "pinia";
import { getMyDailyWord, addMyDailyWord } from "@/api/dailyword";
import { ref } from "vue";
import { useMember } from "./Member";

export const useMyDailyWord = defineStore("mydailyword", () => {
  const mydailyword = ref();
  const memberStore = useMember();

  // GET
  async function getmydailyword() {
    await getMyDailyWord(memberStore.accessToken, ({ data }) => {
      mydailyword.value = data;
      localStorage.setItem("dailyWordList", JSON.stringify(mydailyword));
      console.log("Get method responses", mydailyword.value);
    });
  }

  // POST
  async function addmydailyword(newWord) {
    await addMyDailyWord(newWord, memberStore.accessToken, ({ data }) => {
      console.log(data, " add new word");

      getMyDailyWord(memberStore.accessToken, ({ data }) => {
        mydailyword.value = data;
        console.log("Get method responses", mydailyword.value);
      });
    });
  }

  // getters == computed()  랜더링 될때 실행되는 함수 -
  // api 불러오는 함수 선언하고, response 값 state에 저장
  //
  return {
    getmydailyword,
    addmydailyword,
    mydailyword,
  };
});
