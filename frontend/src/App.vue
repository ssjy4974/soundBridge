<script setup>
// style import
import "./style.css";
import "@fortawesome/fontawesome-free/css/all.css";

// component import
import FooterDisabled from "./components/FooterDisabled.vue";
import Header from "./components/Header.vue";
import FooterVolunteer from "./components/FooterVolunteer.vue";

import { useMember } from "@/store/Member";
const memberStore = useMember();
const role = memberStore.member.role;
console.log("role: ", role);
</script>

<template>
  <div>
    <Header v-if="role != null" />
    <div id="wrapper">
      <router-view
        v-bind:class="{
          router: role === 'APPLICANT' || role === 'HELPER',
        }"
      />
    </div>
    <!-- 로그인이 되어있지 않을때 display : none, 장애인 유저 로그인 상태면 장애인용 푸터, 봉사자일 때 봉사자 푸터 -->

    <FooterDisabled v-if="role === 'APPLICANT'" />
    <FooterVolunteer v-if="role === 'HELPER'" />
  </div>
</template>

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
