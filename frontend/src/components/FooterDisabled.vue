<template>
  <div class="footer pre__border">
    <div>
      <router-link to="/pronounce">
        <font-awesome-icon icon="fa-solid fa-clipboard" />
      </router-link>
    </div>
    <div>
      <router-link to="/feedback">
        <font-awesome-icon icon="fa-regular fa-comments" />
      </router-link>
    </div>
    <div>
      <router-link to="/pronounciationselection">
        <font-awesome-icon icon="fa-solid fa-microphone" />
      </router-link>
    </div>
    <div>
      <router-link to="/mypage">
        <font-awesome-icon icon="fa-solid fa-user" />
      </router-link>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.footer {
  width: 60vw;
  display: flex;
  justify-content: space-between;
  padding-inline: 10vw;

  text-align: center;
  height: 48px;
  align-content: center;
  position: fixed;
}
</style>
