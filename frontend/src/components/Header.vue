<template>
  <div class="header pre__border">
    <router-link to="/pronounce">
      <p>Header disabled</p>
    </router-link>
    <router-link to="/mypagev">
      <p>Header volunteer</p>
    </router-link>
  </div>
</template>

<script setup></script>

<style scoped>
.header {
  text-align: center;
  height: 48px;
  position: fixed;
  width: 100vw;
}
</style>
