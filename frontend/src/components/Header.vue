<template>
  <div class="header">
    <div class="back_icon">
      <font-awesome-icon @click="goback" icon="fa-solid fa-arrow-left" />
    </div>
    <div class="logo__div">
      <router-link to="/pronounce">
        <p>Header disabled</p>
      </router-link>
      <router-link to="/mypagev">
        <p>Header volunteer</p>
      </router-link>
    </div>
    <div>
      <!-- <p>????</p> -->
    </div>
  </div>
</template>

<script setup>
import router from "@/router";
function goback() {
  router.go(-1);
}
</script>

<style scoped>
@media screen and (min-width: 412px) {
  .header {
    width: 412px;
  }
}
.header {
  background-color: var(--maincolor2);
  border-radius: 0px 0px 24px 24px;

  display: flex;
  text-align: center;
  height: 56px;
  position: fixed;
  width: 100%;
  z-index: 10;
  justify-content: space-between;
  align-items: center;
}
.logo__div {
  display: flex;
}
.back_icon {
  padding-inline: 3%;
}
</style>
