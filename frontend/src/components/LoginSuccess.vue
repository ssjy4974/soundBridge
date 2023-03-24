<template>
  <div></div>
</template>

<script setup>
import { apiInstance } from "@/api";
import router from "@/router";
import { onBeforeMount } from "vue";
import { useRoute } from "vue-router";
import { useMypage } from "../store/Member";

const api = apiInstance();
const route = useRoute();
const memberStore = useMypage();
const { setAccessToken, accessToken } = memberStore;

onBeforeMount(async () => {
  await router.isReady();

  const accessToken = route.query.accessToken;

  if (accessToken) {
    memberStore.setAccessToken(accessToken);
    api
      .get(`/api/members`, {
        headers: {
          "access-token": memberStore.accessToken,
        },
      })
      .then((res) => {
        console.log(res.data);
      });
  }
  // router.push("/");
});
</script>

<style></style>
