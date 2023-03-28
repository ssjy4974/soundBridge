<template>
  <div class="STT__container">
    <div>
      <p>stt가 표시 되는 곳.</p>
      <p>play 아이콘 클릭하면STT 실행</p>
    </div>
    <div class="microphone__icon">
      <font-awesome-icon icon="fa-solid fa-play" />
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.STT__container {
  height: 32vh;
  margin-inline: 2vw;
  margin-top: 2vh;
  padding: 1vh;
  border-radius: 16px;
  border: solid var(--maincolor2) 3px;
  display: block;
}
.microphone__icon {
}
</style>
