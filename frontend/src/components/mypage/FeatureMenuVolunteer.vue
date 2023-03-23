<template>
  <div>
    <div>
      <p>TO Do : 봉사자용 마이페이지 메뉴 만드는중</p>

      <div id="menu-section">
        <router-link to="/mypagev/recordState"> 목소리 기부 </router-link>

        <router-link to="/mypagev/meetings"> 발음 피드백 </router-link>

        <hr />
      </div>

      <router-view></router-view>
    </div>
  </div>
</template>

<script setup>
import MyMeetings from "./MyMeetings.vue";
import recordState from "./RecordState.vue";
</script>

<style scoped>
a {
  text-decoration: none;
  color: black;
}

hr {
  width: 95%;
  background: #5bbeff;
  height: 1px;
  border: 0;
}
</style>
