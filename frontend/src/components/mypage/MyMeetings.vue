<template>
  <div>
    <p>발음 피드백 미팅 일정을 보여주는 곳</p>
    <div v-if="myMeetings">
      <ul v-for="myMeeting in myMeetings" :key="myMeeting.meetingdId">
        <li>미팅아이디 : {{ myMeeting.meetingId }}</li>
        <br />
        타이틀 :
        {{
          myMeeting.title
        }}
        <br />
        봉사자 :
        {{
          myMeeting.applicantName
        }}
        <br />
        지원자 :
        {{
          myMeeting.helperName
        }}
        <br />
        <button @click="join(myMeeting.code)">방생성</button>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { apiInstance } from "@/api/index";
import { onBeforeMount, ref } from "@vue/runtime-core";
import router from "@/router/index";

const api = apiInstance();
let myMeetings = ref();

onBeforeMount(() => {
  api
    .get("/api/meetings")
    .then((res) => {
      myMeetings.value = res.data.content;
    })
    .catch((err) => {
      console.log(err);
    });
});

const join = (meetingId) => {
  router.replace(`/feedbackMeeting/${meetingId}`);
};
</script>

<style lang="scss" scoped></style>
