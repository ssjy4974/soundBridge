<template>
  <div>
    <!-- <input type="image" src="" alt="" /> -->
    <div id="profileImg">
      <img
        :src="`../src/assets/img/${member.profile}`"
        @click="changeProfile"
        alt="profile"
        id="profile"
        :key="updateImage"
      />
      <div id="div_modifyImg">
        <input
          type="file"
          @change="imgUploadEvent"
          accept=".jpg,.jpeg,.png"
          id="imgInput"
          style="display: none"
        />
        <label for="imgInput">
          <!--여기 이미지가 수정버튼 이미지-->
          <img src="@/assets/img/new.png" id="modifyProfile" />
        </label>
      </div>
    </div>
    <p>img onclick 시 파일 선택할수 있게</p>
    <div>
      <h3>Nick Name</h3>
      <font-awesome-icon icon="fa-solid fa-pen" />
    </div>
  </div>
</template>

<script setup>
import { computed, ref } from "vue";
import { useMypage } from "@/store/Member";
import { storeToRefs } from "pinia";
const maxSize = 2 * 1024 * 1024;

const memberStore = useMypage();

const updateImage = ref(0);

const { accessToken, member } = storeToRefs(memberStore);

const imgUploadEvent = (e) => {
  console.log(accessToken, "  id ", member.profile);
  changeProfile(e.target.files);
};

let reg_img = ["jpg", "jpeg", "png"];
const changeProfile = (f) => {
  const modifyImgName = f[0].name;

  const formData = new FormData();
  formData.append("memberId", 1);
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
#profile {
  width: 200px;
  height: 200px;
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
</style>
<!-- style="display: none" -->
