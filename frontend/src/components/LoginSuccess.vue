<template>
  <div></div>
</template>

<script setup>
import router from "@/router";
import { onBeforeMount } from "vue";
import { useRoute } from "vue-router";
import { useMember } from "../store/Member";

const route = useRoute();
const memberStore = useMember();
const { member } = memberStore;

onBeforeMount(async () => {
  await router.isReady();

  const accessToken = route.query.accessToken;

  if (accessToken) {
    await memberStore.setAccessToken(accessToken);
    console.log(" ls ", accessToken);
    await memberStore.setMemberInfo();
    if (!member.role) {
      router.push("/select-role");
    } else if (member.role === "HELPER") {
      router.push("/mypagev");
    } else {
      router.push("/pronounce");
    }
  }
});
</script>

<style></style>
