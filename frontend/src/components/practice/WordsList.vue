<template>
  <div class="container">
    <p>자주쓰는 단어 추가하는 버튼 기능 추가됨.</p>
    <div class="parent">
      <div class="child">비빔밥</div>
      <div class="child">비빔밥</div>
      <div class="child">비빔밥</div>
      <div class="child">비빔밥</div>
      <div class="child">비빔밥</div>
      <div class="child">비빔밥</div>
      <div class="child">비빔밥</div>
      <div class="child">비빔밥</div>
    </div>
    <div>
      <button>연습할 단어 추가하는 버튼</button>
      <div>
        <p>
          버튼 클릭시 단어 추가할 수 있는 모달이나 영역이 나타나게 할 거임 뜸
        </p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { apiInstance } from "@/api/index";
import { onBeforeMount, ref } from "@vue/runtime-core";
import router from "@/router/index";

const api = apiInstance();
</script>

<style lang="scss" scoped>
.container {
}

.parent {
  width: 90%;
  border: 1px solid red;
  margin: 0 auto;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
}

.child {
  background-color: aqua;
  width: 40%;
  height: 70px;
  margin: 20px 0;
}
</style>
