<template>
  <div>
    <div v-if="basicLetter">
      <h3>기본 발음 연습 디테일 컴포넌트</h3>
      <p>발음 명 : {{ basicLetter.letter }}</p>
      <div>
        <img
          :src="`${IMAGE_PATH}/${basicLetter.letterImage}`"
          alt="조음법 이미지"
        />
      </div>
      <div>
        <p>시도 횟수 : {{ basicLetter.tryCount }}</p>
        <p>성공 횟수 : {{ basicLetter.successCount }}</p>
      </div>
      <div>
        <div>
          <button @click="router.replace(`/practicebasics`)">
            뒤로가기 버튼 (아이콘)
          </button>
        </div>
        <div>
          <p>실제 발음 명 : {{ basicLetter.guidLetter }}</p>
          <p>나의 발음</p>
          <div class="transcript" v-text="transcript"></div>
        </div>
        <!-- 자음 모음 다 추가하면 index는 수정할 예정  -->
        <div v-if="route.params.basicLetterId < 14">
          <button @click="next">다음 버튼 (아이콘)</button>
        </div>
      </div>
      <div>
        <button @click="ToggleMic">연습</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRoute } from "vue-router";
import { apiInstance } from "@/api/index";
import { ref, onMounted } from "vue";
import router from "@/router/index";

const api = apiInstance();
const route = useRoute();

const basicLetter = ref();
const IMAGE_PATH = import.meta.env.VITE_IMAGE_PATH;

const transcript = ref("");
const isRecording = ref(false);

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
  sr.onstart = () => {
    console.log("연습 시작");
    isRecording.value = true;
  };
  sr.onend = () => {
    console.log("연습 종료");
    isRecording.value = false;
  };
  sr.onresult = (evt) => {
    for (let i = 0; i < evt.results.length; i++) {
      const result = evt.results[i];
      if (result.isFinal) {
        CheckSuccess(result);
      }
    }
    const t = Array.from(evt.results)
      .map((result) => result[0])
      .map((result) => result.transcript)
      .join("");
    transcript.value = t;
  };
});

const CheckSuccess = (result) => {
  const t = result[0].transcript;
  if (t == basicLetter.value.guidLetter) {
    sr.stop();
    api
      .put(
        `/api/try-histories/basic-letters/${basicLetter.value.basicLetterId}`
      )
      .then(() => {
        basicLetter.value.successCount++;
        alert("정답입니다.");
      })
      .catch((err) => {
        alert("다시 한번 시도 해주세요");
      });
  } else {
    sr.stop();
    alert("실패");
  }
};

const ToggleMic = () => {
  api
    .post(`/api/try-histories/basic-letters/${basicLetter.value.basicLetterId}`)
    .then(() => {
      basicLetter.value.tryCount++;
      sr.start();
    })
    .catch((err) => {
      err;
      alert("실패");
    });
};

const callApi = () => {
  api
    .get(`/api/basic-letters/${route.params.basicLetterId}`)
    .then((res) => {
      basicLetter.value = res.data;
    })
    .catch((err) => {
      err;
      alert("상세 조회 실패");
      router.replace("/");
    });
};

callApi();

const next = () => {
  const index = Number(route.params.basicLetterId);
  router.replace(`/practicebasicsdetail/${index + 1}`);
  api
    .get(`/api/basic-letters/${index + 1}`)
    .then((res) => {
      basicLetter.value = res.data;
    })
    .catch((err) => {
      err;
      alert("상세 조회 실패");
      router.replace("/");
    });
};
</script>

<style scoped>
img {
  width: 100px;
  height: 100px;
}
</style>
