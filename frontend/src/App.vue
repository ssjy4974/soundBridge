<script setup>
// style import
import "./style.css";
import "@fortawesome/fontawesome-free/css/all.css";

// component import
import FooterDisabled from "./components/FooterDisabled.vue";
import Header from "./components/Header.vue";
</script>

<template>
  <Header />
  <div class="dummy"></div>
  <router-view class="router" />
  <FooterDisabled />
</template>

<style scoped>
.router {
  padding-top: 70px;
}

/* .dummy {
  height: 20000px;
} */
</style>
