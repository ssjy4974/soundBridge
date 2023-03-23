<template>
  <div class="footer pre__border">
    <div>
      <router-link to="/feedback">
        <font-awesome-icon icon="fa-solid fa-clipboard" />
      </router-link>
    </div>
    <div>
      <router-link to="/record">
        <font-awesome-icon icon="fa-solid fa-microphone" />
      </router-link>
    </div>
    <div>
      <router-link to="/mypaged">
        <font-awesome-icon icon="fa-solid fa-user" />
      </router-link>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.footer {
  width: 60vw;
  height: 48px;
  display: flex;
  justify-content: space-between;
  text-align: center;
  align-content: center;
  background-color: var(--maincolor2);

  padding-inline: 10vw;

  position: relative;
  transform: translateY(-100%);
}
</style>
