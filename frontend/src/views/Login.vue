<template>
  <div>
    <div id="loginBtn">
      <img
        class="kakao-btn"
        src="../assets/img/kakao_button.png"
        @click="kakaoLogin"
      />
      <img
        class="kakao-btn"
        src="../assets/img/naver_button.png"
        @click="naverLogin"
      />
      <img
        class="google-btn"
        src="../assets/img/google_button.png"
        @click="googleLogin"
      />
    </div>
    <div>
      <div class="login__container kakao__button">
        <button></button>
      </div>
      <div class="login__container naver__button"></div>
      <div class="login__container google__button"></div>
    </div>
  </div>
</template>

<script setup>
import { apiInstance } from "@/api";
import { onBeforeMount } from "vue";
import { useMypage } from "../store/Member";
import router from "@/router";

const api = apiInstance();

const kakaoLoginUrl = import.meta.env.VITE_KAKAO_LOGIN_PATH;
const naverLoginUrl = import.meta.env.VITE_NAVER_LOGIN_PATH;
const googleLoginUrl = import.meta.env.VITE_GOOGLE_LOGIN_PATH;

const memberStore = useMypage();
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
#loginBtn img {
  width: 90%;
  height: 50px;
}

.kakao-btn {
  margin-top: 10px;
}
.google-btn {
  margin-top: 10px;
}
img:hover {
  cursor: pointer;
}
</style>
