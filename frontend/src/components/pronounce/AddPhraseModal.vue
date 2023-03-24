<template>
  <div class="phrase__modal">
    <div @click="$emit('closemodalphrase')">X</div>
    <p>자주쓰는말 추가하기</p>
    <input type="text" placeholder="자주쓰는 말을 입력하세요" />

    <button>추가하기 +</button>
  </div>
</template>

<script setup>
defineEmits(["closemodalphrase"]);
</script>

<style scoped>
.phrase__modal {
  background-color: var(--maincolor2);
  border-radius: 8px;
  box-shadow: 4px 4px 4px 4px var(--black4);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-content: center;
  padding-inline: 5%;
  padding-block: 5%;
  margin: auto;
  align-self: center;
  position: fixed;
  top: 30vh;
  left: 5vw;
  width: 80%;
  z-index: 2;
}
</style>
