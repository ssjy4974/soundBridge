<script setup>
// style import
import "./style.css";
import "@fortawesome/fontawesome-free/css/all.css";

// component import
import FooterDisabled from "./components/FooterDisabled.vue";
import Header from "./components/Header.vue";
</script>

<template>
  <div>
    <Header />
    <router-view class="router" />
    <FooterDisabled />
  </div>
</template>

<style scoped>
.router {
  padding-top: 70px;
  height: auto;
  min-height: 100%;
  padding-bottom: 64px;
}

/* .dummy {
  height: 20000px;
} */
</style>
