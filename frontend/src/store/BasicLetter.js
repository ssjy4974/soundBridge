import { defineStore } from "pinia";
import {
  getBasicLetterDetails,
  getBasicLetterList,
  updateTryCount,
  updateSuccessCount,
} from "@/api/basicLetter";
import { ref } from "vue";
import router from "@/router/index";
import Swal from "sweetalert2";
import { useMember } from "./Member";

export const useBasicLetterStore = defineStore("basicLetter", () => {
  const basicLetters = ref();
  const basicLetter = ref();
  const memberStore = useMember();

  const getBasicLetters = async () => {
    await getBasicLetterList(memberStore.accessToken, ({ data }) => {
      basicLetters.value = data;
    });
  };

  const getBasicLetter = async (accessToken, basicLetterId) => {
    await getBasicLetterDetails(
      accessToken,
      basicLetterId,
      ({ data }) => {
        basicLetter.value = data;
      },
      () => {
        Swal.fire(
          "상세 조회 실패",
          "해당 id 값은 존재 하지 않습니다.",
          "error"
        );
        router.replace("/practicebasics");
      }
    );
  };

  const tryPractice = async (accessToken, basicLetterId) => {
    await updateTryCount(
      accessToken,
      basicLetterId,
      () => {
        basicLetter.value.tryCount++;
      },
      () => {
        Swal.fire("연습 시작 실패", "", "error");
      }
    );
  };

  const successPratice = async (accessToken, basicLetterId) => {
    await updateSuccessCount(
      accessToken,
      basicLetterId,
      () => {
        basicLetter.value.successCount++;

        Swal.fire(
          "성공!",
          "성공횟수 : " +
            basicLetter.value.successCount +
            "<br/>" +
            "시도 횟수 : " +
            basicLetter.value.tryCount,
          "success"
        );
      },
      () => {
        alert("다시 한번 시도 해주세요");
      }
    );
  };

  return {
    getBasicLetters,
    getBasicLetter,
    tryPractice,
    successPratice,
    basicLetters,
    basicLetter,
  };
});
