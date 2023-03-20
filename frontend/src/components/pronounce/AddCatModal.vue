<template>
  <div>
    <div @click="$emit('closemodal')">X</div>
    <p>카테고리 추가하기</p>
    <input type="text" placeholder="카테고리 이름 입력하세요" />

    <button>추가하기 +</button>
  </div>
</template>

<script setup>
defineEmits(["closemodal"]);
</script>

<style scoped></style>
