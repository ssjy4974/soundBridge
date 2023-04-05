<template>
  <div class="footer">
    <div>
      <router-link to="/pronounce">
        <font-awesome-icon icon="fa-regular fa-comments" />
      </router-link>
    </div>
    <div>
      <router-link to="/feedback">
        <font-awesome-icon icon="fa-solid fa-clipboard" />
      </router-link>
    </div>
    <div>
      <router-link to="/practice-select">
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
  max-width: 412px;

  width: 100vw;
  height: 5vh;
  display: flex;
  justify-content: space-around;
  text-align: center;
  align-content: center;
  background-color: var(--maincolor2);
  align-items: center;
  border-radius: 20px 24px 0px 0px;
  position: fixed;
  bottom: 0vh;
}
</style>
