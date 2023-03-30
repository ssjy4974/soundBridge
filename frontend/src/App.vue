<template>
  <!-- v-if="member" -->
  <div v-if="member">
    <Header v-if="member.role === 'APPLICANT' || member.role === 'HELPER'" />
    <div id="wrapper">
      <router-view
        v-bind:class="{
          router: member.role === 'APPLICANT' || member.role === 'HELPER',
        }"
      />
    </div>
    <!-- 로그인이 되어있지 않을때 display : none, 장애인 유저 로그인 상태면 장애인용 푸터, 봉사자일 때 봉사자 푸터 -->
    <FooterDisabled v-if="member.role === 'APPLICANT'" />
    <FooterVolunteer v-if="member.role === 'HELPER'" />
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
import { ref, watch } from "vue";
const memberStore = useMember();
// const memberStore = useMember();
const { member } = storeToRefs(memberStore);

// const role = ref(memberStore.member.role);
// console.log("memberStore.member.value.role: ", memberStore.member.value.role);

const callMember = async () => {
  memberStore.setMemberInfo();
};
callMember();
// watch(memberStore, () => {
//   console.log("watch console");
//   console.log("watch role : ", role);
//   console.log("memberStore.member: ", memberStore.member);
//   // console.log("memberStore.member.value: ", memberStore.member.value);
//   // console.log("memberStore.member.value.role: ", memberStore.member.value.role);
//   console.log("memberStore.member.role: ", memberStore.member.role);
// });
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
