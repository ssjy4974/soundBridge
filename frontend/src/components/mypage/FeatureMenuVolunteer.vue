<template>
  <div>
    <div>
      <div id="menu-section">
        <div>
          <router-link to="/mypagev/recordState"> 목소리 기부 </router-link>
        </div>

        <div>
          <router-link to="/mypagev/meetings"> 발음 피드백 </router-link>
        </div>
      </div>
      <hr />

      <router-view></router-view>
    </div>
  </div>
</template>

<script setup>
import MyMeetings from "./MyMeetings.vue";
import recordState from "./RecordState.vue";
</script>

<style scoped>
#menu-section {
  display: flex;
  justify-content: space-around;
  margin-bottom: 5%;
}
a {
  text-decoration: none;
  color: black;
}
.router-link-active {
  color: #0b76bb;
}
hr {
  width: 95%;
  background: #5bbeff;
  height: 0.2vh;
  border: 0;
  margin-top: 10px;
}
</style>
