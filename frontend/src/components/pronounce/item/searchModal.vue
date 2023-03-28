<template>
  <div class="cat__modal">
    <div
      id="close"
      @click="$emit('closemodal', checkedGender, checkedAge, checkedFeatures)"
    >
      X
    </div>
    <br />
    <div>
      <b>성별</b>
      <div>
        <label
          ><input
            type="radio"
            v-model="checkedGender"
            name="gender"
            value="M"
          />남</label
        >
        <label
          ><input
            type="radio"
            v-model="checkedGender"
            name="gender"
            value="W"
          />여</label
        >
      </div>
      <b>연령</b>
      <div>
        <label
          ><input
            type="radio"
            v-model="checkedAge"
            name="age"
            value="10"
          />10대</label
        >
        <label
          ><input
            type="radio"
            v-model="checkedAge"
            name="age"
            value="20"
          />20대</label
        >
      </div>
      <br />

      <b>특징</b>
      <div>
        <label
          ><input
            type="checkbox"
            v-model="checkedFeatures"
            value="1"
          />차분함</label
        >
        <label
          ><input
            type="checkbox"
            v-model="checkedFeatures"
            value="2"
          />산뜻함</label
        >
        <label
          ><input
            type="checkbox"
            v-model="checkedFeatures"
            value="3"
          />발랄함</label
        >
        <label
          ><input
            type="checkbox"
            v-model="checkedFeatures"
            value="4"
          />고음</label
        >
        <label
          ><input
            type="checkbox"
            v-model="checkedFeatures"
            value="5"
          />저음</label
        >
        <label
          ><input
            type="checkbox"
            v-model="checkedFeatures"
            value="6"
          />진지함</label
        >
      </div>
      <br />
      {{ checkedGender }}
      {{ checkedAge }}
      {{ checkedFeatures }}
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
defineEmits(["closemodal"]);

let checkedGender = ref(null);
let checkedAge = ref(null);
let checkedFeatures = ref([]);

// let checkResult = ref({
//   gender: checkedGender,
//   age: checkedAge,
//   features: checkedFeatures,
// });

// function emitModalResult() {
//   checkResult.gender = checkedGender;
//   checkResult.age = checkedAge;
//   checkResult.features = checkedFeatures;

//   console.log("check ? ", checkedGender, checkedAge, checkResult);
// }
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

#close {
  float: right;
  display: block;
}
</style>
