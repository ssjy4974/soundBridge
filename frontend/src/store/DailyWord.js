import { defineStore } from "pinia";
import {
  getMyDailyWord,
  getMySentence,
  addMyDailyWord,
  deleteDailyWord,
  saveOrUpdateTryHistory,
  updateSuccessCount,
} from "@/api/dailyword";
import { ref } from "vue";
import { useMember } from "./Member";
import Swal from "sweetalert2";

export const useMyDailyWord = defineStore("mydailyword", () => {
  const mydailyword = ref();
  const memberStore = useMember();

  // GET
  async function getmydailyword() {
    await getMyDailyWord(memberStore.accessToken, ({ data }) => {
      mydailyword.value = data;
    });
  }

  async function getmysentence() {
    await getMySentence(memberStore.accessToken, ({ data }) => {
      mydailyword.value = data;
      console.log("Get method responses", mydailyword.value);
    });
  }

  // POST
  async function addmydailyword(newWord, type) {
    await addMyDailyWord(
      newWord,
      memberStore.getMemberInfo.memberId,
      type,
      memberStore.accessToken,
      ({ data }) => {
        getMyDailyWord(memberStore.accessToken, ({ data }) => {
          mydailyword.value = data;
        });
      }
    );
  }

  async function saveorupdatetryhistory(wordMemberId, index) {
    await saveOrUpdateTryHistory(
      wordMemberId,
      memberStore.accessToken,
      ({ data }) => {
        mydailyword.value[index].tryCount++;
      }
    );
  }

  async function updatesuccesscount(wordMemberId, index) {
    await updateSuccessCount(
      wordMemberId,
      memberStore.accessToken,
      ({ data }) => {
        mydailyword.value[index].successCount++;
        Swal.fire({
          title: "성공!",
          html:
            "성공횟수 : " +
            mydailyword.value[index].successCount +
            "<br/>" +
            "시도 횟수 : " +
            mydailyword.value[index].tryCount,
          position: "bottom-end",
        });
      }
    );
  }

  async function deletedailyword(wordMemberId) {
    await deleteDailyWord(wordMemberId, memberStore.accessToken, ({ data }) => {
      getMyDailyWord(memberStore.accessToken, ({ data }) => {
        mydailyword.value = data;
      });
    });
  }

  // getters == computed()  랜더링 될때 실행되는 함수 -
  // api 불러오는 함수 선언하고, response 값 state에 저장
  //
  return {
    getmydailyword,
    getmysentence,
    addmydailyword,
    saveorupdatetryhistory,
    updatesuccesscount,
    deletedailyword,
    mydailyword,
  };
});
