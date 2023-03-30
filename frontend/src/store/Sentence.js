import { defineStore } from "pinia";
import {
  updateMySentence,
  addMySentence,
  getMySentences,
} from "@/api/sentence";
import { ref } from "vue";
import { useMember } from "@/store/Member";

export const useMySentence = defineStore("mysentence", () => {
  const mymember = useMember();
  // console.log(accessToken);
  const accessToken = mymember.accessToken;
  const sentence = ref("감");
  const mysentence = ref();

  // GET
  async function getmysentences(newSentence) {
    sentence.value = newSentence;
    await getMySentences(
      sentence.value,
      accessToken,
      ({ data }) => {
        mysentence.value = data;
        console.log("Get method responses", mysentence.value);
      },
      ({ fail }) => {
        mysentence.value = {};
      }
    );
  }

  // POST
  async function addmysentence(newSentence) {
    sentence.value = newSentence;
    console.log("post new sentence", sentence.value);
    await addMySentence(sentence.value, accessToken, ({ data }) => {
      console.log(data, " get my sentence");
      sentence.value = data;
    });
  }

  // PUT
  async function updatemysentence(sentence) {
    console.log("put new sentence", sentence.value);
    await updateMySentence(sentence.value, accessToken, ({ data }) => {
      console.log(data, " get my sentence");
      sentence = data;
    });
  }

  // getters == computed()  랜더링 될때 실행되는 함수 -
  // api 불러오는 함수 선언하고, response 값 state에 저장
  //
  return {
    sentence,
    mysentence,
    getmysentences,
    addmysentence,
    updatemysentence,
  };
});
