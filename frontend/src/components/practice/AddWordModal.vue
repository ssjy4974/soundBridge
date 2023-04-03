<template>
  <div class="cat__modal">
    <div class="close__button" @click="$emit('closemodal')">X</div>
    <p>일상단어 추가하기</p>
    <div>
      <input
        class="input__box"
        type="text"
        placeholder="연습할 단어를 입력하세요"
        v-model="newWord"
      />
    </div>
    <div class="addbutton__container">
      <button
        @click="
          () => {
            wordHandler();
            $emit('closemodal');
          }
        "
      >
        추가하기 +
      </button>
    </div>
  </div>
</template>

<script setup>
//ref
import { ref } from "vue";
//store import
import { useMyDailyWord } from "@/store/DailyWord";
const store = useMyDailyWord();
const newWord = ref("");

const wordHandler = () => {
  store.addmydailyword(newWord.value);
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
