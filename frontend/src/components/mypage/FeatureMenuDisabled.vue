<template>
  <div>
    <div id="myinfo">
      <router-link to="/mypaged/freq-used-phrase" id="qs">
        <span>자주 쓰는말 </span>
      </router-link>
      <router-link to="/mypaged/meetings" id="fd">
        <span>발음 피드백 </span>
      </router-link>
      <router-link to="/voice-select" id="vo">
        <span>목소리 선택 </span>
      </router-link>
    </div>
    <router-view></router-view>
  </div>
</template>

<script setup>
import MyMeetings from "./MyMeetings.vue";
import FreqUsedPhrase from "../pronounce/FreqUsedPhrase.vue";
</script>

<style lang="scss" scoped>
#myinfo {
  margin-top: 13%;
  border-bottom: solid 2px;
  padding-block: 12px;
  border-bottom-color: var(--maincolor8);
  margin-bottom: 5%;
}
#qs {
  position: relative;
  font-size: 1.2rem;
  left: 4%;
}
#fd {
  position: relative;
  font-size: 1.2rem;
  left: 14%;
}
#vo {
  position: relative;
  font-size: 1.2rem;
  left: 23%;
}
</style>
