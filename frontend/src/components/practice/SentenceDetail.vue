<template>
  <div class="container">
    <div class="info__container" v-if="sentenceList">
      <div>
        <p>연습 문장</p>
        <p style="font-size: 1.5rem">
          {{ sentenceList[Number(route.params.index)].word }}
        </p>
      </div>
      <div>
        <p style="font-size: 1rem">발음 방법</p>
      </div>
      <div>
        <p style="font-size: 1.5rem">
          {{ sentenceList[Number(route.params.index)].guideWord }}
        </p>
      </div>
    </div>
    <!--  -->
    <div class="myvoice__container">
      <div>
        <p>나의 발음</p>
      </div>
      <div class="STT__box">
        <h2></h2>
        <h1 v-text="transcript" id="original"></h1>
      </div>
      <p>결과</p>
      <div class="STT__box">
        <h2></h2>
        <div id="compare"></div>
      </div>
    </div>
    <div class="record__container" @click="tryHistoryHandler">
      <button class="record__button">
        {{ recordStatus }}
      </button>
    </div>
    <!--  -->
    <div class="link__container" v-if="sentenceList">
      <div
        class="icon__box"
        v-if="Number(route.params.index) > 0"
        @click="prev"
      >
        <font-awesome-icon icon="fa-solid fa-chevron-left" />
      </div>
      <div v-else></div>
      <div
        class="icon__box"
        v-if="Number(route.params.index) < sentenceList.length - 1"
        @click="next"
      >
        <font-awesome-icon icon="fa-solid fa-chevron-right" />
      </div>
    </div>
    <div class="goback__container">
      <button class="record__button" @click="backToList">돌아가기</button>
    </div>
  </div>
</template>

<script setup>
import { useRoute } from "vue-router";
import { ref, onMounted } from "vue";
import { storeToRefs } from "pinia";
import router from "@/router/index";
import { useMyDailyWord } from "@/store/DailyWord";
import Swal from "sweetalert2";

const route = useRoute();
const store = useMyDailyWord();
const { sentenceList } = storeToRefs(store);
const callApi = () => {
  store.getmysentence();
};
callApi();

const transcript = ref("");
const isRecording = ref(false);
const recordStatus = ref("연습하기");

const Recognition = window.SpeechRecognition || window.webkitSpeechRecognition;
const sr = new Recognition();

onMounted(() => {
  // 각각의 인식 결과마다 continuous한 results를 받을지 혹은 single result를 받을지 결정
  // 디폴트 값은 false, single word를 리턴함
  sr.continuous = false;
  // 중간 결과를 리턴할지 아닐지 결정
  // 아직 인식이 완료 되지 않은 중간 results
  // 인식이 모두 완료되면 isFinal이 true, 아직 인식 전이면 false
  // 인식이 모두 완료 되면 내가 발음 한 발음과 실제 발음을 비교하는 api 호출 함
  sr.interimResults = true;
  // 언어 설정, 설정하지 않으면 디폴트로 HTML 의 lang 속성값을 받음
  sr.lang = "ko-KR";
  sr.maxAlternatives = 1;
  sr.onstart = () => {
    console.log("연습 시작");
    isRecording.value = true;
  };
  sr.onend = () => {
    console.log("연습 종료");
    isRecording.value = false;
    recordStatus.value = "연습하기";
    transcript.value = "";
  };

  sr.onresult = (evt) => {
    // console.log(evt.results);

    const t = Array.from(evt.results)
      .map((result) => result[0])
      .map((result) => result.transcript)
      .join("");

    transcript.value = t;

    const result = evt.results[0];

    if (result.isFinal) {
      CheckSuccess(result);
    }
  };
});

const tryHistoryHandler = () => {
  const index = Number(route.params.index);
  const wordMemberId = store.sentenceList[index].wordMemberId;
  console.log("1", index);
  store.saveorupdatetryhistory(wordMemberId, index);
  sr.start();
  recordStatus.value = "녹음중";
};

const backToList = () => {
  console.log("리스트로");
  router.push(`/practice-sentence`);
};

const prev = () => {
  console.log("이전 클릭");
  const index = Number(route.params.index);
  router.replace(`/sentence-detail/${index - 1}`);
};

const next = () => {
  console.log("다음 클릭");
  const index = Number(route.params.index);
  router.replace(`/sentence-detail/${index + 1}`);
};

const CheckSuccess = (result) => {
  const myPronunciation = result[0].transcript; // 최종 내 발음

  const index = Number(route.params.index); // 현재 연습 단어 인덱스

  const originalWord = store.sentenceList[index].word; //현재 연습 단어

  if (myPronunciation == store.sentenceList[index].word) {
    // 연습 단어와 내 발음이 정확히 일치하는 경우

    sr.stop();
    const wordMemberId = store.sentenceList[index].wordMemberId;
    store.updatesuccesscount(wordMemberId, index); // 성공 횟수 업데이트
    const span = document.createElement("span"); // 발음 담을 span 요소 생성
    span.textContent = myPronunciation; // 발음을 span의 text 로 할당
    span.style.color = "blue"; // 색깔 파랑으로 지정
    document.getElementById("compare").innerHTML = span.outerHTML; // html에 삽입
  } else {
    sr.stop();
    store.getmysentence();

    let text = myPronunciation;
    const coloredText = text
      .split("")
      .map((char, index) => {
        const span = document.createElement("span");
        span.textContent = char;

        if (index >= originalWord.length) {
          span.style.color = "red";
        } else {
          if (text.charAt(index) != originalWord.charAt(index)) {
            span.style.color = "red";
          } else {
            span.style.color = "blue";
          }
        }
        return span.outerHTML;
      })
      .join("");
    document.getElementById("compare").innerHTML = coloredText;

    Swal.fire({
      title: "다시 한번 해볼까요?",
      html:
        "성공횟수 : " +
        store.sentenceList[index].successCount +
        "<br/>" +
        "시도 횟수 : " +
        store.sentenceList[index].tryCount,
      position: "bottom-end",
    });
  }
};
</script>

<style scoped>
.container {
  display: flex;
  height: 90vh;
  flex-direction: column;
  align-items: center;
}
.info__container {
  box-shadow: 6px 6px 6px 6px var(--black3);
  width: 80vw;
  padding-inline: 4vw;
  padding-block: 2vh;
  border-radius: 16px;
  margin-top: 2vh;
  margin-bottom: 4vh;
}
.myvoice__container {
  box-shadow: 6px 6px 6px 6px var(--black3);
  width: 80vw;
  padding-inline: 4vw;
  padding-block: 2vh;
  border-radius: 16px;
  margin-bottom: 4vh;
  font-size: 1.2rem;
}
.STT__box {
  padding-block: 0.5vh;
  padding-inline: 2vw;
  background-color: var(--black2);
  /* font-size: 1.2rem; */
}
.link__container {
  width: 90vw;
  display: flex;
  align-content: space-between;
  justify-content: space-between;
  margin-block: 10vh;
}
.record__button {
  width: 70vw;
  background-color: var(--maincolor2);
  margin: 2vh;
}
.goback__container {
  position: fixed;
  bottom: 10vh;
}
.icon__box {
  font-size: 2rem;
}
</style>
