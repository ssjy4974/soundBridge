<script setup>
// style import
import "./style.css";
import "@fortawesome/fontawesome-free/css/all.css";

// component import
import FooterDisabled from "./components/FooterDisabled.vue";
import Header from "./components/Header.vue";
import FooterVolunteer from "./components/FooterVolunteer.vue";
</script>

<template>
  <div>
    <Header />
    <div id="wrapper">
      <router-view class="router" />
    </div>
    <!-- 로그인이 되어있지 않을때 display : none, 장애인 유저 로그인 상태면 장애인용 푸터, 봉사자일 때 봉사자 푸터 -->

    <FooterDisabled v-if="userRole === 'APPLICANT'" />
    <FooterVolunteer v-if="userRole === 'HELPER'" />
  </div>
</template>

<script>
const userRole = "APPLICANT";
</script>

<style scoped>
#wrapper {
  height: 100%;
}
.router {
  padding-top: 70px;

  padding-bottom: 64px;
}

/* .dummy {
  height: 20000px;
} */
</style>
