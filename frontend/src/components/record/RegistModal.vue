<template>
  <div class="cat_modal">
    <div id="close">
      <i class="fas fa-times" @click="$emit('closeModal')"></i>
    </div>
    <br />
    <div>
      <div>
        <b id="name"> &nbsp;&nbsp;&nbsp;목소리&nbsp;이름</b>
        <div>
          <input
            type="text"
            v-model="voiceName"
            placeholder="목소리 이름을 입력해주세요.."
          />
        </div>

        <b id="gender"> &nbsp;&nbsp;&nbsp;성&nbsp;별</b>
        <div id="dv">
          <input
            id="man"
            type="radio"
            v-model="checkedGender"
            name="gender"
            value="M"
            class="checkType"
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
            class="checkType"
          /><button
            id="but"
            :class="{ checked: checkedGender == `W` }"
            for="woman"
          >
            <label for="woman">여성</label>
          </button>
        </div>
        <b id="age">&nbsp;&nbsp;&nbsp;연&nbsp;령</b>
        <div id="dv">
          <input
            id="age10"
            type="radio"
            v-model="checkedAge"
            name="age"
            value="10"
            class="checkType"
          /><button
            id="but2"
            :class="{ checked: checkedAge == 10 }"
            for="age10"
          >
            <label for="age10">10대</label>
          </button>
          <input
            id="age20"
            type="radio"
            v-model="checkedAge"
            name="age"
            value="20"
            class="checkType"
          /><button
            id="but2"
            :class="{ checked: checkedAge == 20 }"
            for="age20"
          >
            <label for="age20">20대</label>
          </button>
          <input
            id="age30"
            type="radio"
            v-model="checkedAge"
            name="age"
            value="30"
            class="checkType"
          /><button
            id="but2"
            :class="{ checked: checkedAge == 30 }"
            for="age30"
          >
            <label for="age30">30대</label>
          </button>
          <div id="dv">
            <input
              type="radio"
              v-model="checkedAge"
              name="age"
              value="40"
              id="age40"
              class="checkType"
            />
            <button
              id="but3"
              :class="{ checked: checkedAge == 40 }"
              for="age40"
            >
              <label for="age40">40대</label>
            </button>
            <input
              type="radio"
              v-model="checkedAge"
              name="age"
              value="50"
              id="age50"
              class="checkType"
            />
            <button
              id="but3"
              :class="{ checked: checkedAge == 50 }"
              for="age50"
            >
              <label for="age50">50대</label>
            </button>
            <input
              type="radio"
              v-model="checkedAge"
              name="age"
              value="60"
              id="age60"
              class="checkType"
            />
            <button
              id="but3"
              :class="{ checked: checkedAge == 60 }"
              for="age60"
            >
              <label for="age60">60대</label>
            </button>
          </div>
        </div>
        <br />

        <b id="feat">&nbsp;&nbsp;&nbsp;특&nbsp;징</b>
        <div class="parent" id="dv">
          <div class="child" v-for="(f, idx) in featureList" :key="idx">
            <input
              type="checkbox"
              v-model="feature"
              :value="idx + 1"
              :id="f"
              class="checkType"
            />
            <button id="featchk" :class="checked(idx + 1)" :for="f">
              <label :for="f">{{ f }}</label>
            </button>
          </div>
        </div>
        <br />
      </div>
    </div>
    <button class="registBtn" @click="regist">저장 하기</button>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useMypage } from "@/store/MyPage";
import Swal from "sweetalert2";

const myPageStore = useMypage();
let voiceName = ref("");
let checkedGender = ref(0);
let checkedAge = ref(0);
const feature = ref([]);
const featureList = [
  "부드러운",
  "낮은",
  "높은",
  "담백한",
  "깊은",
  "가느다란",
  "선명한",
  "작은",
  "거친",
  "열정적인",
  "경쾌한",
  "발랄한",
  "어두운",
  "파워풀한",
  "시원한",
  "엄숙한",
  "매혹적인",
  "귀여운",
];

const checked = (target) => {
  const index = feature.value.indexOf(target);
  return index >= 0 ? { checked: true } : { checked: false };
};

const regist = () => {
  if (voiceName.value == "") {
    Swal.fire({
      text: "이름을 입력해주세요.",
      target: "#custom-target",
      customClass: {
        container: "position-absolute",
      },
      toast: true,
      position: "center-center",
      showConfirmButton: false,
      timer: 1000,
    });
    return;
  } else if (checkedGender.value == 0) {
    Swal.fire({
      text: "성별을 선택해주세요.",
      target: "#custom-target",
      customClass: {
        container: "position-absolute",
      },
      toast: true,
      position: "center-center",
      showConfirmButton: false,
      timer: 1000,
    });
    return;
  } else if (checkedAge.value == 0) {
    Swal.fire({
      text: "연령을 선택해주세요.",
      target: "#custom-target",
      customClass: {
        container: "position-absolute",
      },
      toast: true,
      position: "center-center",
      showConfirmButton: false,
      timer: 1000,
    });
    return;
  } else if (feature.value.length == 0) {
    Swal.fire({
      text: "특징을 선택해주세요.",
      target: "#custom-target",
      customClass: {
        container: "position-absolute",
      },
      toast: true,
      position: "center-center",
      showConfirmButton: false,
      timer: 1000,
    });
    return;
  }
  myPageStore.regist(
    voiceName.value,
    checkedAge.value,
    checkedGender.value,
    feature.value
  );
  emit("closeModal");
};
</script>

<style scoped>
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
}
.cat_modal {
  background-color: #eaf6ff;
  border-radius: 8px;
  box-shadow: 4px 4px 4px 4px var(--black4);
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-content: center;
  padding-inline: 5%;
  margin: auto;
  align-self: center;
  height: 835px;
  position: fixed;
  left: 5vw;
  width: 80%;
  z-index: 2;
}

#close {
  position: relative;
  left: 36vh;
  top: 1.3vh;
  bottom: 1.3vh;
}
#name {
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
}
#gender {
  /* Auto layout */
  margin-top: 50px;
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
  width: 27%;
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
  top: 4vh;
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

.checkType {
  display: none;
}

button {
  background-color: #8ad1ff;
}

.checked {
  background-color: #0297fa;
}

input {
  padding: 10px;
  position: relative;
  top: 1.5vh;
  width: 93%;
  border-color: transparent;
  border-radius: 20px;
  background: #fff9f9;
}

.registBtn {
  width: 335px;
  height: 40px;
  margin-top: 50px;
  background-color: #2bacff;
  border-radius: 32px;
  color: #ffffff;
  line-height: 0px;
}
</style>
