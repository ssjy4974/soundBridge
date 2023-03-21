<template>
  <div>
    <input type="image" src="" alt="" />
    <img src="@/assets/img/loofy.png" @click="changeProfile" alt="??" />
    <p>img onclick 시 파일 선택할수 있게</p>
    <div>
      <h3>Nick Name</h3>
      <font-awesome-icon icon="fa-solid fa-pen" />
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
</script>

<style lang="scss" scoped></style>
