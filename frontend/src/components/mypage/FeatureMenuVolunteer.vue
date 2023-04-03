<template>
  <div>
    <div>
      <div id="menu-section">
        <router-link to="/mypagev/recordState" id="qs">
          <span>목소리 기부</span>
        </router-link>
        <router-link to="/mypagev/meetings" id="fd">
          <span>발음 피드백</span>
        </router-link>
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
#menu-section {
  margin-top: 13%;
  border-bottom: solid 2px;
  padding-block: 12px;
  border-bottom-color: var(--maincolor8);
  margin-bottom: 5%;
}

#qs {
  position: relative;
  font-size: 1.2rem;
  left: 15%;
}
#fd {
  /* text-align: center; */
  float: right;
  position: relative;
  font-size: 1.2rem;
  right: 15%;
}
</style>
