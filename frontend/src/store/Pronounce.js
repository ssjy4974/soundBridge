import { defineStore } from "pinia";
import { ref } from "vue";

export const usePronounce = defineStore("pronounce", () => {
  // state  == ref(), useState() 변수
  const freqUsedPhrase = ref();
  const freqUsedCat = ref(["일상", "마트", "교통수단"]);
  // action == function()  함수 -
  // state를 변화 시키는 함수 선언하기
  // POST, PUT 요청 보내는 함수 선언하기

  // getters == computed()  랜더링 될때 실행되는 함수 -
  // api 불러오는 함수 선언하고, response 값 state에 저장
  //
  // return
  return { freqUsedPhrase, freqUsedCat };
});
