<template>
  <div class="base__modal">
    <p>제목</p>
    <input type="text" placeholder="제목을 입력하세요" />
    <div>
      <h3>임시용으로 만든 시간 모듈 참고해서 구현하기</h3>
      <p>Start Date</p>
      {{ startDate }}
      <p>End Date</p>
      {{ endDate }}
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";

const startDate = ref(new Date());
const endDate = ref(new Date());
console.log(startDate.value);
</script>

<style scoped>
.base__modal {
  border: solid;
  width: 360px;
  height: 480px;
}
</style>
