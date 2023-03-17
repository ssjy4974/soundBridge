<template>
  <div class="base__modal">
    <div>
      <font-awesome-icon icon="fa-solid fa-xmark" />
    </div>
    <div>
      <p>게시글을 삭제 하시겠습니까?</p>
    </div>
    <div>
      <button @click="$emit('closemodal')">예</button>
    </div>
  </div>
</template>

<script setup>
import { defineProps, defineEmits } from "vue";
defineEmits(["closemodal"]);
const props = defineProps(["data"]);
</script>

<style scoped>
.base__modal {
  border: solid;
  width: 320px;
  height: 240px;
}
</style>
