<template>
  <div class="cat__modal">
    <div @click="$emit('closemodal')">X</div>
    <p>카테고리 추가하기</p>
    <input
      type="text"
      placeholder="카테고리 이름 입력하세요"
      v-model="newCategory"
    />

    <button @click="categoryHandler">추가하기 +</button>
  </div>
</template>

<script setup>
//ref
import { ref } from "vue";
//store import
import { usePronounce } from "@/store/Pronounce";
const store = usePronounce();
const freqUsedCat = store.freqUsedCat;
const newCategory = ref("");

const categoryHandler = () => {
  // freqUsedCat.forEach((element) => {
  //   console.log(element.categoryName, element.categoryId, newCategory.value);
  //   if (element.categoryName == newCategory.value) {
  //     console.log("Found");
  //     // store.editCategory(element.categoryId, newCategory);
  //     return false;
  //   }
  // });
  store.addCategory(newCategory);
};

defineEmits(["closemodal"]);
</script>

<style scoped>
.cat__modal {
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
