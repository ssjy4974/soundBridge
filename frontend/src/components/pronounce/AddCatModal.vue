<template>
  <div class="cat__modal">
    <div class="close__button" @click="$emit('closemodal')">X</div>
    <p>카테고리 추가하기</p>
    <div>
      <input
        class="input__box"
        type="text"
        placeholder="카테고리 이름 입력하세요"
        v-model="newCategory"
      />
    </div>
    <div class="addbutton__container">
      <button @click="categoryHandler">추가하기 +</button>
    </div>
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
.close__button {
  display: flex;
  justify-content: end;
}
.addbutton__container {
  margin-top: 10%;
  align-self: center;
}
.input__box {
  flex-direction: column;
  justify-content: center;
  align-items: flex-end;
  padding: 5px;

  margin-left: 2%;
  margin-right: 2%;
  /* Black/200 / */
  width: 90%;
  border-top: 2px solid #f1f1f1;
  border-right: 2px solid #f1f1f1;
  border-bottom: 2px solid #f1f1f1;
  border-left: 1px solid #f1f1f1;

  filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
  border-radius: 8px;

  /* / Inside auto layout */

  flex: none;
  order: 0;
  flex-grow: 0;
}
</style>
