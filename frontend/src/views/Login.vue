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
    <router-link to="/pronounce"> 장애인용 메인 페이지로</router-link>
    <br />
    <router-link to="/mypagev"> 봉사자용 메인 페이지로</router-link>
    <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
    <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
    <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
    <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
    <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
    <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
    <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
    <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
  </div>
</template>

<script setup>
import { apiInstance } from "@/api";
import { useMypage } from "../store/Member";

const api = apiInstance();
const memberStore = useMypage();
const { accessToken } = memberStore;

const kakaoLoginUrl = import.meta.env.VITE_KAKAO_LOGIN_PATH;

const naverLoginUrl = import.meta.env.VITE_NAVER_LOGIN_PATH;

const googleLoginUrl = import.meta.env.VITE_GOOGLE_LOGIN_PATH;

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
