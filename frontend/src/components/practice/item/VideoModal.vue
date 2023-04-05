<template>
  <div class="modal1">
    <div class="overlay" @click="$emit('closeModal')"></div>
    <div class="modal-card">
      <slot />
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.modal1,
.overlay {
  width: 100%;
  height: 100%;
  position: fixed;
  left: 0;
  top: 0;
  z-index: 20;
}
.overlay {
  opacity: 0.5;
  background-color: black;
}
.modal-card {
  position: relative;
  max-width: 97%;
  margin: auto;
  margin-top: 75%;
  padding: 0;
  background-color: white;
  z-index: 50;
  opacity: 1;
}
</style>
