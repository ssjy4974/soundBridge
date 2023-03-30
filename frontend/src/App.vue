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
// const memberStore = storeToRefs(useMember());
const memberStore = useMember();

const role = ref(memberStore.member.role);
console.log("memberStore.member: ", memberStore.member);
console.log("memberStore.member.value: ", memberStore.member.value);
// console.log("memberStore.member.value.role: ", memberStore.member.value.role);
console.log("memberStore.member.role: ", memberStore.member.role);

const callMember = async () => {
  console.log("실행되면 뜰거야");
  await memberStore.getMemberInfo();
};

watch(memberStore, () => {
  console.log("watch console");
  console.log("watch role : ", role);
  console.log("memberStore.member: ", memberStore.member);
  console.log("memberStore.member.value: ", memberStore.member.value);
  console.log("memberStore.member.value.role: ", memberStore.member.value.role);
  console.log("memberStore.member.role: ", memberStore.member.role);
});
callMember();
</script>

<template>
  <div>
    <Header v-if="role === 'APPLICANT' || role === 'HELPER'" />
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
