<template>
  <div class="phrase__modal">
    <div class="close__button" @click="$emit('closemodalphrase')">
      <font-awesome-icon icon="fa-solid fa-xmark" />
    </div>
    <p>자주쓰는말 추가하기</p>
    <input
      class="input__box"
      type="text"
      v-model="newSentence"
      placeholder="자주쓰는 말을 입력하세요"
    />
    <div class="addbutton__container">
      <button
        @click="
          () => {
            addNewPhrase();
            $emit('closemodalphrase');
          }
        "
      >
        추가하기 +
      </button>
    </div>
  </div>
</template>

<script setup>
import { usePronounce } from "@/store/Pronounce";
import { ref } from "vue";
defineEmits(["closemodalphrase"]);
const categoryId = defineProps(["catId"]);

const newSentence = ref("");
const pronounce = usePronounce();
const {
  addQuickSentence,
  readQuickSentence,
  editQuickSentence,
  countQuickSentence,
  deleteQuickSentence,
} = pronounce;

const addNewPhrase = () => {
  // console.log(newSentence);
  addQuickSentence(newSentence.value, categoryId.catId);
};
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
