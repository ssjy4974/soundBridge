<template>
  <div id="loginpage" style="max-width: 412px">
    <img
      id="big-logo"
      src="../assets/img/biglogo.png"
      style="max-width: 412px"
    />
    <img
      id="small-logo"
      src="../assets/img/small.png"
      style="max-width: 412px"
    />
    <img id="start" src="../assets/img/start.png" style="max-width: 412px" />
    <div id="loginBtn" style="max-width: 412px">
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
  width: 90%;
  height: auto;
  top: 26vh;
  left: 2.5vh;
}
#small-logo {
  position: relative;
  top: 27vh;
  left: 12vh;
}
#start {
  position: relative;
  top: 59vh;
  right: 7vh;
}
#loginBtn {
  position: relative;
  top: 58vh;
  right: 1vh;
}
.bin {
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
  background-color: #eaf6ff;
}
</style>
