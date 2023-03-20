<template>
  <div>
    <div @click="$emit('closemodalphrase')">X</div>
    <p>자주쓰는말 추가하기</p>
    <input type="text" placeholder="자주쓰는 말을 입력하세요" />

    <button>추가하기 +</button>
  </div>
</template>

<script setup>
defineEmits(["closemodalphrase"]);
</script>

<style scoped></style>
