<template>
  <div>
    <div v-if="myMeetings">
      <div
        class="meeting"
        v-for="myMeeting in myMeetings"
        :key="myMeeting.meetingdId"
      >
        <div class="upper">
          {{ myMeeting.title }}
          <span v-if="member.role == `HELPER`">
            지원자 :
            {{ myMeeting.applicantName }}
          </span>
          <span v-else>
            봉사자 :
            {{ myMeeting.helperName }}
          </span>
        </div>
        <div class="lower">
          <span class="date"
            >{{ strToDate(myMeeting.startTime)[0] }}<br />{{
              strToDate(myMeeting.startTime)[1]
            }}</span
          >
          <span class="date"
            >{{ strToDate(myMeeting.endTime)[0] }}<br />{{
              strToDate(myMeeting.endTime)[1]
            }}</span
          >
          <button class="room-btn" @click="createRoom(myMeeting.meetingId)">
            방 생성
          </button>
        </div>
        <hr />
      </div>
    </div>
  </div>
</template>

<script setup>
import { apiInstance } from "@/api/index";
import { onBeforeMount, ref } from "@vue/runtime-core";
import { useMember } from "@/store/Member";
import router from "@/router/index";

const memberStore = useMember();
const api = apiInstance();
const { accessToken, member } = memberStore;

let myMeetings = ref();

onBeforeMount(() => {
  api
    .get("/api/meetings", {
      headers: {
        "access-token": accessToken,
      },
    })
    .then((res) => {
      myMeetings.value = res.data.content;
    })
    .catch((err) => {
      console.log(err);
    });
});

const createRoom = (meetingId) => {
  api
    .post(`/api/meetings/rooms/${meetingId}`)
    .then(() => {
      router.replace(`/feedbackMeeting/${meetingId}`);
    })
    .catch((err) => {
      alert("방생성에 실패 하였습니다.");
    });
};

const strToDate = (str) => {
  let date = `${str.substring(0, 4)}년 ${str.substring(5, 7)}월 ${str.substring(
    8,
    10
  )}일`;
  let time = `${str.substring(11, 13)}시 ${str.substring(14, 16)}분`;
  return [date, time];
};
</script>

<style scoped>
.upper {
  display: flex;
  justify-content: space-around;
  font-size: 22px;
}
.lower {
  display: flex;
  justify-content: space-around;
  align-items: flex-start;
  margin-top: 20px;
}
.date {
  font-size: 13px;
  text-align: center;
  align-self: center;
}
.room-btn {
  border-radius: 16px;
}
hr {
  width: 95%;
  background: #ddd;
  height: 0.2vh;
  border: 0;
  margin-top: 10px;
}
</style>
