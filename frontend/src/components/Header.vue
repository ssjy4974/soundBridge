<template>
  <div class="header">
    <div class="back_icon">
      <font-awesome-icon @click="goback" icon="fa-solid fa-arrow-left" />
    </div>
    <div class="logo__div">
      <router-link to="/">
        <img src="@/assets/img/logo.png" id="logo" />
        <span id="text">연 &nbsp;청</span>
      </router-link>
    </div>
    <div></div>
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
  position: relative;
  right: 3vh;
  font-size: x-large;
  bottom: 1vh;
}
.back_icon {
  padding-inline: 3%;
}

#logo {
  width: 10%;
  height: auto;
  position: relative;
  right: 0.6vh;
  top: 1vh;
}

#text {
  color: black;
}
</style>
