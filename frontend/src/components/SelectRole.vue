<template>
  <div class="loginpage">
    <div class="logo__container">
      <img class="big__logo" src="../assets/img/biglogo.png" />
      <img class="small__logo" src="../assets/img/small.png" />
    </div>
    <div class="q__modal">
      <i
        class="fa-solid fa-circle-question"
        id="question"
        @click="isExpModalViewed = true"
      ></i>
    </div>
    <div class="container">
      <div>
        <video-modal
          v-if="isExpModalViewed"
          @closeModal="isExpModalViewed = false"
        >
          <role-explain />
        </video-modal>
      </div>
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
          <p id="gen">성별:</p>

          <div class="select">
            <input type="radio" v-model="gender" value="M" id="select" /><label
              for="select"
              id="ma"
              >남성</label
            >
            <input
              type="radio"
              v-model="gender"
              value="W"
              id="select2"
              class="fe"
            /><label for="select2" id="fem">여성</label>
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
  </div>
</template>

<script setup>
import { apiInstance } from "@/api";
import router from "@/router";
import { ref } from "@vue/runtime-core";
import { useMember } from "../store/Member";
import RoleExplain from "./RoleExplain.vue";
import VideoModal from "@/components/practice/item/VideoModal.vue";
const api = apiInstance();
const memberStore = useMember();

const isExpModalViewed = ref(false);

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
.loginpage {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  width: 100vw;
  background-color: #eaf6ff;
}
.logo__container {
  width: 90%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding-bottom: 10vh;
  /* margin-block: 5vh; */
}
.big__logo {
  width: 90%;
}
.small__logo {
  width: 55%;
}
.q__modal {
  align-self: end;
  margin-inline: 10%;
  margin-block: 3vh;
}
#question {
  font-size: 2.3rem;
  /* position: fixed; */
  /* left: 84%; */
  /* top: 47%; */
  /* bottom: 8.9vh; */
  color: gold;
}
.container {
  /* border: solid 1px; */
  margin-inline: 10vw;
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 25vh;
}
.info-section {
  /* border: solid 1px blue; */
  width: 80vw;
}

.age-section {
  display: flex;
  justify-content: space-between;
  /* position: relative; */
  /* top: 35vh; */
}

.gender-section {
  display: flex;
  justify-content: space-between;
  padding-block: 5%;
  /* position: relative; */
  /* top: 36vh; */
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
  border: 1px solid #eaf6ff;
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
  position: absolute;
  right: 10%;
  top: 10%;
  /* top: 1vh; */
  z-index: 10;
}

.age-drawer .drawer-list-item {
  padding: 0;
  margin: 0;
  position: relative;
  right: 2vh;
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
  padding-inline: 2vw;
}
#select2 {
  position: relative;
  right: 3vh;
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
  display: flex;
  width: 95%;
  justify-content: space-between;
  /* border: 1px solid; */
}

.signup-button {
  background-color: #bae4ff;
  font-weight: bold;
}
</style>
