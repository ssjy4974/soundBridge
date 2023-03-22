<template>
  <div>
    <div>
      <hr />
      <p>TO Do : 봉사자용 마이페이지 메뉴 만드는중</p>

      <router-link to="/mypagev/recordState">
        <span>목소리 기부 |</span>
      </router-link>

      <router-link to="/mypagev/meetings">
        <span>발음 피드백 |</span>
      </router-link>

      <router-view></router-view>
    </div>
  </div>
</template>

<script setup>
import MyMeetings from "./MyMeetings.vue";
import recordState from "./RecordState.vue";
</script>

<style scoped></style>
