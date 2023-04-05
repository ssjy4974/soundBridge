<template>
  <div class="cat__modal">
    <div
      id="close"
      @click="$emit('updateList', checkedGender, checkedAge, feature)"
    >
      X
    </div>
    <br />
    <div id="all">
      <div id="filter">
        <b id="gender"> &nbsp;&nbsp;&nbsp;성&nbsp;별</b>
        <div id="dv">
          <input
            id="man"
            type="radio"
            v-model="checkedGender"
            name="gender"
            value="M"
          /><button
            id="but"
            :class="{ checked: checkedGender == `M` }"
            for="man"
          >
            <label for="man">남성</label>
          </button>
          <input
            id="woman"
            type="radio"
            v-model="checkedGender"
            name="gender"
            value="W"
          /><button
            id="but"
            :class="{ checked: checkedGender == `W` }"
            @click="checked2()"
            for="woman"
          >
            <label for="woman">여성</label>
          </button>
        </div>
        <b id="age">&nbsp;&nbsp;&nbsp;연&nbsp;령</b>
        <div class="parent" id="dv">
          <div class="child" v-for="(a, idx) in ageList" :key="idx">
            <input
              type="radio"
              v-model="checkedAge"
              :value="(idx + 1) * 10"
              :id="a"
            />
            <button
              id="but2"
              :class="{ checked: checkedAge == (idx + 1) * 10 }"
              :for="a"
            >
              <label :for="a">{{ a }}</label>
            </button>
          </div>
        </div>
      </div>
      <br />
      <div id="featarea">
        <b id="feat">&nbsp;&nbsp;&nbsp;특&nbsp;징</b>
        <div class="parent" id="dv">
          <div class="child" v-for="(f, idx) in featureList" :key="idx">
            <input type="checkbox" v-model="feature" :value="idx + 1" :id="f" />
            <button id="featchk" :class="checked(idx + 1)" :for="f">
              <label :for="f">{{ f }}</label>
            </button>
          </div>
        </div>
      </div>
      <br />
      <!-- {{ checkedGender }}
      {{ checkedAge }}
      {{ checkedFeatures }} -->
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
defineEmits(["closemodal"]);

let checkedGender = ref(null);
let checkedAge = ref(null);
const ageList = ["10대", "20대", "30대", "40대", "50대", "60대"];
const feature = ref([]);
const featureList = [
  "사랑스러운",
  "활기찬",
  "매력적인",
  "친절한",
  "열정적인",
  "새침한",
  "구수한",
  "감미로운",
  "신뢰가는",
  "친근한",
  "싹싹한",
  "무뚝뚝한",
  "개성있는",
  "저음의",
  "차분한",
  "쓸쓸한",
  "카리스마있는",
  "귀여운",
];

const checked = (target) => {
  const index = feature.value.indexOf(target);
  return index >= 0 ? { checked: true } : { checked: false };
};
const checked2 = () => {
  console.log("cg : ", checkedGender.value);
};
</script>

<style scoped>
#all {
  position: relative;
  top: 2vh;
}
#featarea {
  position: relative;
  bottom: 7vh;
}
#last {
  position: relative;
  bottom: 2.5vh;
}
.parent {
  width: 100%;
  margin: 0 auto;
  display: flex;
  position: relative;
  top: 2vh;
  flex-wrap: wrap;
  justify-content: space-evenly;
}

.child {
  width: 30%;
  height: 10px;
  margin: 25px 0;
  white-space: nowrap;
}
.cat__modal {
  background-color: #eaf6ff;
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
  height: 75%;
  position: fixed;
  top: 11vh;
  left: 5vw;
  width: 80%;
  z-index: 2;
}

#close {
  position: relative;
  left: 98%;
  display: block;
}
#gender {
  /* Auto layout */

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  padding: 1px;
  gap: 8px;

  width: 112%;
  height: 36px;

  background: rgba(204, 203, 203, 0.42);

  /* Inside auto layout */

  flex: none;
  order: 0;
  flex-grow: 0;
  position: relative;
  right: 2.3vh;
  bottom: 2vh;
}

#age {
  /* Auto layout */

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  padding: 1px;
  gap: 8px;

  width: 112%;
  height: 36px;

  background: rgba(204, 203, 203, 0.42);

  /* Inside auto layout */

  flex: none;
  order: 0;
  flex-grow: 0;
  position: relative;
  right: 2.3vh;
  top: 2vh;
}
#featchk {
  box-sizing: border-box;

  /* Auto layout */

  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 10px 18px;
  gap: 8px;
  margin-inline: 4%;
  width: 100px;
  height: 32px;

  /* MainBlue/300 */

  /* MainBlue/300 */

  border: 1px solid #8ad1ff;
  /* Shadow/xs */

  box-shadow: 0px 1px 2px rgba(16, 24, 40, 0.05);
  border-radius: 8px;

  /* Inside auto layout */

  flex: none;
  order: 0;
  flex-grow: 0;

  position: relative;
  top: 4.5vh;
}

#feat {
  /* Auto layout */

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  padding: 1px;
  gap: 8px;

  width: 112%;
  height: 36px;

  background: rgba(204, 203, 203, 0.42);

  /* Inside auto layout */

  flex: none;
  order: 0;
  flex-grow: 0;
  position: relative;
  right: 2.3vh;
  top: 7vh;
}

#dv {
  display: flex;
}
#but {
  box-sizing: border-box;

  /* Auto layout */

  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 10px 18px;
  gap: 8px;
  margin-inline: 5%;
  width: 40%;
  height: 32px;

  /* MainBlue/300 */

  /* MainBlue/300 */

  /* Shadow/xs */

  box-shadow: 0px 1px 2px rgba(16, 24, 40, 0.05);
  border-radius: 8px;

  /* Inside auto layout */

  flex: none;
  order: 0;
  flex-grow: 0;
  position: relative;
}
#filter {
  position: relative;
  bottom: 1vh;
}
#but2 {
  box-sizing: border-box;

  /* Auto layout */

  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 10px 18px;
  gap: 8px;
  margin-inline: 3%;
  /* MainBlue/300 */
  position: relative;
  left: 1vh;
  bottom: 1vh;
  height: 35px;
  /* MainBlue/300 */
  z-index: 80;
  /* Shadow/xs */

  box-shadow: 0px 1px 2px rgba(16, 24, 40, 0.05);
  border-radius: 8px;

  /* Inside auto layout */

  flex: none;
  order: 0;
  flex-grow: 0;
}
#but3 {
  box-sizing: border-box;

  /* Auto layout */

  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  padding: 10px 18px;
  gap: 8px;
  margin-inline: 5%;
  width: 50%;
  height: 32px;
  /* MainBlue/300 */

  /* MainBlue/300 */

  /* Shadow/xs */

  box-shadow: 0px 1px 2px rgba(16, 24, 40, 0.05);
  border-radius: 8px;

  /* Inside auto layout */

  flex: none;
  order: 0;
  flex-grow: 0;

  position: relative;
  top: 8vh;
  right: 35.6vh;
}
#chk {
  display: none;
}

input {
  display: none;
}

button {
  background-color: #8ad1ff;
}

.checked {
  background-color: #0297fa;
}
</style>
