<template>
  <div id="loginpage">
    <img id="big-logo" src="../assets/img/big.png" />
    <img id="small-logo" src="../assets/img/small.png" />
    <img id="start" src="../assets/img/start.png" />
    <div id="loginBtn">
      <img
        class="kakao-btn"
        src="../assets/img/kakao.png"
        @click="kakaoLogin"
      />
      <img
        class="naver-btn"
        src="../assets/img/naver.png"
        @click="naverLogin"
      />
      <img
        class="google-btn"
        src="../assets/img/google.png"
        @click="googleLogin"
      />
    </div>
  </div>
</template>

<script setup>
import { apiInstance } from "@/api";
import { onBeforeMount } from "vue";
import { useMember } from "../store/Member";
import router from "@/router";

const api = apiInstance();

const kakaoLoginUrl = import.meta.env.VITE_KAKAO_LOGIN_PATH;
const naverLoginUrl = import.meta.env.VITE_NAVER_LOGIN_PATH;
const googleLoginUrl = import.meta.env.VITE_GOOGLE_LOGIN_PATH;

const memberStore = useMember();
const { member } = memberStore;

onBeforeMount(() => {
  if (member.role === "HELPER") {
    router.push("/mypagev");
  } else if (member.role === "APPLICANT") {
    router.push("/pronounce");
  }
});

const kakaoLogin = () => {
  window.location.replace(kakaoLoginUrl);
};
const naverLogin = () => {
  window.location.replace(naverLoginUrl);
};
const googleLogin = () => {
  window.location.replace(googleLoginUrl);
};
</script>

<style scoped>
#big-logo {
  position: relative;
  top: 15vh;
  left: 14vh;
}
#small-logo {
  position: relative;
  top: 21vh;
  right: 8vh;
}
#start {
  position: relative;
  top: 47vh;
  left: 14vh;
}
#loginBtn {
  position: relative;
  top: 48vh;
  right: 1vh;
}

.kakao-btn {
  width: 26%;
  height: auto;
  position: relative;
  left: 11%;
  top: 15px;
}
.google-btn {
  width: 16%;
  height: auto;
  position: relative;
  left: 31%;
}
.naver-btn {
  width: 16%;
  height: auto;
  position: relative;
  left: 19%;
}

img:hover {
  cursor: pointer;
}

#loginpage {
  height: 100vh;
  width: 100vw;
  background: linear-gradient(
    30deg,
    #aecdfb 10.67%,
    rgba(212, 226, 249, 0.905562) 23.31%,
    rgba(174, 205, 251, 0.829285) 33.52%,
    rgba(174, 205, 251, 0.818389) 33.53%,
    rgba(208, 227, 255, 0.755677) 42.76%,
    rgba(180, 210, 254, 0.73255) 47.14%,
    rgba(182, 211, 255, 0.709422) 49.57%,
    rgba(198, 218, 249, 0.604087) 63.67%,
    rgba(174, 205, 251, 0.589524) 63.68%,
    rgba(218, 233, 255, 0.369324) 78.75%,
    rgba(174, 205, 251, 0) 104.02%,
    rgba(174, 205, 251, 0.895833) 104.02%
  );
}
</style>
