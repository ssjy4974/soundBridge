import { defineStore } from "pinia";
import { getMyDailyWord } from "@/api/dailyword";
import { ref } from "vue";

export const useMyDailyWord = defineStore("mydailyword", () => {
  const accessToken = "access-token 123";
  const mydailyword = ref();

  // GET
  async function getmydailyword() {
    await getMyDailyWord(accessToken, ({ data }) => {
      mydailyword.value = data;
      console.log("Get method responses", mydailyword.value);
    });
  }

  //   // POST
  //   async function addmysentence(newSentence) {
  //     sentence.value = newSentence;
  //     console.log("post new sentence", sentence.value);
  //     await addMySentence(sentence.value, accessToken, ({ data }) => {
  //       console.log(data, " get my sentence");
  //       sentence.value = data;
  //     });
  //   }

  // getters == computed()  랜더링 될때 실행되는 함수 -
  // api 불러오는 함수 선언하고, response 값 state에 저장
  //
  return {
    getmydailyword,
    mydailyword,
  };
});
