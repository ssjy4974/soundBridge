<template>
  <div>
    <div>
      <i class="fa-solid fa-gear" id="set" @click="profileOption"></i>
      <ProfileOption v-if="isProfileOption" @closemodal="profileOption" />
    </div>
    <div id="profileImg">
      <input
        type="file"
        @change="imgUploadEvent"
        accept=".jpg,.jpeg,.png"
        id="imgInput"
        style="display: none"
      />
      <label for="imgInput">
        <img :src="`${member.profile}`" id="profile" />
      </label>
      <!-- <img
        :src="`${member.profile}`"
        @click="imgUploadEvent"
        alt="profile"
        id="profile"
      /> -->
    </div>
    <div>
      <div id="nickname" v-if="!save" style="text-align: center">
        <span id="nickNameSpan">{{ member.nickname }} </span>
        <span @click="modifyNameInput">
          <font-awesome-icon icon="fa-solid fa-pen" id="pen" />
        </span>
      </div>
      <div v-else>
        <form id="modifyInput" @submit="modifyName" onsubmit="return false">
          <input type="text" v-model="name" id="updatename" /><br />
          <i
            class="fa-sharp fa-solid fa-pen-to-square"
            @click="modifyName"
            id="updatebut"
          ></i>
        </form>
        <span v-if="checkNickname" id="checkNickname">
          2~15글자내외, 특수문자 사용 불가능
        </span>
      </div>
    </div>

    <div id="mEmail">{{ member.email }}</div>
  </div>
</template>

<script setup>
import { computed, ref } from "vue";
import { useMember } from "@/store/Member";
import { storeToRefs } from "pinia";
import ProfileOption from "@/components/mypage/item/ProfileOption.vue";

const IMAGE_PATH = import.meta.env.VITE_IMAGE_PATH;

const memberStore = useMember();

const isProfileOption = ref(false);

const { accessToken, member } = memberStore;
const maxSize = 2 * 1024 * 1024;

let name = ref(member.nickname);
let checkNickname = ref(false);

let save = ref(false);

const profileOption = () => {
  isProfileOption.value = !isProfileOption.value;
};

const modifyNameInput = () => {
  save.value = !save.value;
};

let reg_nickname = /^[A-z가-힣0-9_-]{2,15}$/;
const modifyName = (e) => {
  console.log("닉네임 수정 버튼?", name.value);
  if (reg_nickname.test(name.value)) {
    if (confirm("닉네임을 수정하시겠습니까?"))
      memberStore.modifyNickName(name.value);
    save.value = !save.value;
    if (checkNickname.value) {
      checkNickname.value = !checkNickname.value;
    }
  } else {
    console.log("닉네임 규칙에 맞게");
    if (!checkNickname.value) {
      checkNickname.value = !checkNickname.value;
    }
  }
  // name = ref(member.nickname);
  console.log("save ", save.value);
};

const imgUploadEvent = (e) => {
  console.log(accessToken, "  id ", member.profile, e);
  changeProfile(e.target.files);
};

let reg_img = ["jpg", "jpeg", "png"];
const changeProfile = (f) => {
  console.log("fff", f);
  const modifyImgName = f[0].name;

  const formData = new FormData();
  formData.append("memberId", member.memberId);
  formData.append("profile", f[0]);

  const ext = modifyImgName.split(".").pop().toLowerCase();
  if (reg_img.includes(ext) && f[0].size < maxSize) {
    memberStore.modifyMyProfile(formData);
    console.log("end profile", member.profile);
  } else {
    alert("2MB이하의 jpg, jpeg, png 이미지만 가능합니다!");
    console.log("이미지 규칙에 맞게");
  }
};
</script>

<style lang="scss" scoped>
#updatebut {
  position: fixed;
  left: 38vh;
  bottom: 66vh;
  font-size: 1.7rem;
}

#updatename {
  border: 1px solid #3a389e;
  height: 40px;
  position: fixed;
  font-family: NanumSquare3;
  font-size: 1.3rem;
  left: 5vh;
  text-align: center;
}
#profile {
  width: 170px;
  height: 170px;
  margin-bottom: 10px;
  border-radius: 50%;
  position: relative;
  left: 28.6%;
}

#div_modifyImg {
  border-radius: 5px;
  border: 2px solid black;
  position: absolute;
  top: 48%;
  left: 48%;
  transform: translate(-50%, -50%);
}

#modifyProfile {
  background-color: white;
  width: 30px;
  height: 30px;
}

#nickNameSpan {
  margin-right: 10px;
  position: relative;
  font-size: 1.5rem;
  color: var(--maincolor9);
}
#mEmail {
  margin-top: 3%;
  text-align: center;
  font-size: 11px;
  color: var(--maincolor9);
}

#pen {
  position: relative;
  font-size: 1.3rem;
  color: var(--maincolor7);
}

#set {
  position: relative;
  left: 90%;
  font-size: 1.3rem;
  color: var(--maincolor7);
}
</style>
<!-- style="display: none" -->
