<template>
  <div class="cat__modal">
    <div>
      <button type="button" id="logout" @click="logoutMember">로그아웃</button>
    </div>
  </div>
</template>

<script setup>
//ref
import { ref } from "vue";
import { useMember } from "@/store/Member";

const memberStore = useMember();

const logoutMember = () => {
  // console.log("logout");
  if (confirm("정말 로그아웃 하시겠습니까?")) memberStore.logout();
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
  width: 28%;
  z-index: 2;
  position: fixed;
  top: 10vh;
  left: 24vh;
}
.close__button {
  display: flex;
  justify-content: end;
}
.addbutton__container {
  margin-top: 10%;
  align-self: center;
}

#logout {
  position: relative;
  left: 8%;
  right: 8%;
  background-color: var(--maincolor5);
}
</style>
