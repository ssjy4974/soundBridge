<template>
  <div class="container">
    <div class="info-section">
      <div class="age-section">
        <p>나이:</p>

        <div class="age" :class="showDrawer ? 'highlight' : ''" @click="show">
          <p class="age-copy">{{ age }}</p>
          <i
            class="icon fas fa-chevron-down"
            :style="{ transform: showDrawer ? 'rotate(180deg)' : '' }"
          ></i>
        </div>
        <transition name="slide">
          <div class="age-drawer" v-if="showDrawer">
            <ul class="drawer-list">
              <li
                class="drawer-list-item"
                v-for="a in ageList"
                :key="a"
                @click="updateAge(a)"
              >
                <div class="drawer-list-name">
                  <label>{{ a }}</label>
                </div>
              </li>
            </ul>
          </div>
        </transition>
      </div>

      <div class="gender-section">
        <p>성별:</p>

        <div class="select">
          <input type="radio" v-model="gender" value="male" id="select" /><label
            for="select"
            >남성</label
          >
          <input
            type="radio"
            v-model="gender"
            value="female"
            id="select2"
          /><label for="select2">여성</label>
        </div>
      </div>
    </div>

    <div class="button-section">
      <button @click="signUp(`HELPER`)" class="signup-button">
        목소리 기부하기<br />(청인)
      </button>
      <button @click="signUp(`APPLICANT`)" class="signup-button">
        목소리 사용하기<br />(농인)
      </button>
    </div>
  </div>
</template>

<script setup>
import { apiInstance } from "@/api";
import router from "@/router";
import { ref } from "@vue/runtime-core";
import { useMypage } from "../store/Member";

const api = apiInstance();
const memberStore = useMypage();

const ageList = ref(["10대", "20대", "30대", "40대", "50대", "60대"]);
const age = ref("연령대 선택");
const gender = ref("male");
const role = ref(null);
const showDrawer = ref(false);

const show = () => {
  showDrawer.value = !showDrawer.value;
};

const updateAge = (a) => {
  age.value = a;
  showDrawer.value = false;
};

const signUp = (role) => {
  if (age.value == "연령대 선택") {
    window.alert("연령대를 선택해주세요.");
    return;
  }
  memberStore.signUp(role, age.value.substring(0, 2), gender.value);
  if (role === "HELPER") {
    router.push("/mypagev");
  } else {
    router.push("/pronounce");
  }
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.info-section {
  width: 300px;
}

.age-section {
  display: flex;
  justify-content: space-between;
}

.gender-section {
  display: flex;
  justify-content: space-between;
}

.info-container {
  display: flex;
}

.age {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px;
  position: relative;
  border-radius: 5px;
  border: 1px solid #8f9cb2;
  width: 100px;
  height: 30px;
  transition: 0.4s border ease;
  background: white;
  cursor: pointer;
}

.age-copy {
  font-size: 14px;
  color: #6f7887;
  width: 100%;
}

.age .icon {
  color: #8f9cb2;
  transition: 0.5s transform ease;
  transform-origin: 50%;
}

.age-drawer {
  background: white;
  position: absolute;
  width: 120px;
  height: auto;
  box-shadow: 0 20px 40px 0 rgba(204, 204, 204, 0.1);
  border: 1px solid rgba(0, 0, 0, 0.1);
  margin: 10px 0;
  padding: 0px;
  border-radius: 5px;
}

.age-drawer .drawer-list,
.age-drawer .drawer-list-item {
  padding: 0;
  margin: 0;
}

.age-drawer .drawer-list-item {
  list-style-type: none;
  margin: 20px 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: #606f87;
}

.age-drawer .drawer-list-item:hover {
  background: #bae4ff;
  color: #fff;
}

.age-drawer .drawer-list-item:first-child,
.age-drawer .drawer-list-item:last-child {
  margin: 0;
}

.age-drawer .drawer-list-item .drawer-list-name {
  display: flex;
  padding: 12px 20px;
  align-items: center;
}

.age-drawer .drawer-list-item .drawer-list-name input {
  margin-right: 8px;
}

.age-drawer .drawer-list-item .drawer-list-value {
  width: 30px;
  height: 30px;
  font-size: 12px;
  background: #f4f5f7;
  padding: 8px;
  border-radius: 20px;
  color: #858688;
  display: flex;
  justify-content: center;
  align-items: center;
}

.highlight {
  border: 1px solid #646cff;
}

.highlight .age-copy,
.highlight .icon {
  color: #646cff;
}

.slide-enter {
  opacity: 0;
  transform: translateY(-20px);
  transition: 0.5s all ease;
}

.slide-enter-to {
  opacity: 1;
  transform: translateY(0px);
  transition: 0.5s all ease;
}

.slide-leave-to {
  opacity: 0;
  transform: translateY(-20px);
  transition: 0.5s all ease;
}

@media (max-width: 250px) {
  .drawer-age-value {
    opacity: 0;
  }
}

.select {
  padding: 15px 10px;
}
.select input[type="radio"] {
  display: none;
}
.select input[type="radio"] + label {
  display: inline-block;
  cursor: pointer;
  height: 40px;
  width: 90px;
  border: 1px solid #333;
  border-radius: 20px;
  line-height: 40px;
  text-align: center;
  font-weight: bold;
  font-size: 13px;
}
.select input[type="radio"] + label {
  background-color: #fff;
  color: #333;
}
.select input[type="radio"]:checked + label {
  background-color: #bae4ff;
}

.button-section {
  margin-top: 30px;
}

.signup-button {
  background-color: #bae4ff;
  font-weight: bold;
  margin-left: 20px;
  height: 60px;
}
</style>
