<template>
  <!-- v-if="member" -->
  <div v-if="member">
    <Header v-if="$route.path !== `/` && member.role" />
    <div id="wrapper">
      <router-view
        v-bind:class="{
          router: member.role === 'APPLICANT' || member.role === 'HELPER',
        }"
      />
    </div>
    <!-- 로그인이 되어있지 않을때 display : none, 장애인 유저 로그인 상태면 장애인용 푸터, 봉사자일 때 봉사자 푸터 -->
    <div
      v-if="$route.path !== `/` && !$route.path.includes(`feedback-meeting`)"
    >
      <FooterDisabled v-if="member.role === 'APPLICANT'" />
      <FooterVolunteer v-if="member.role === 'HELPER'" />
    </div>
  </div>
</template>

<script setup>
// style import
import "./style.css";
import "@fortawesome/fontawesome-free/css/all.css";

// component import
import FooterDisabled from "./components/FooterDisabled.vue";
import Header from "./components/Header.vue";
import FooterVolunteer from "./components/FooterVolunteer.vue";

import { useMember } from "@/store/Member";
import { storeToRefs } from "pinia";
const memberStore = useMember();
const { member } = storeToRefs(memberStore);
memberStore.setMemberInfo();

// console.log(${{}})
</script>

<style scoped>
#wrapper {
  height: 100%;
  overflow: scroll;
}
.router {
  padding-top: 74px;

  padding-bottom: 64px;
}

/* .dummy {
  height: 20000px;
} */
</style>
