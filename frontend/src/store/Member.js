import { defineStore } from "pinia";
import {
  modifyMyProfile,
  modifyNickName,
  getMemberInfo,
  signUp,
  getNewAccessToken,
  logout,
} from "@/api/member";
import router from "@/router";

export const useMember = defineStore("member", {
  // state  == ref(), useState() 변수
  state: () => ({
    member: {
      memberId: 0,
      email: "",
      nickname: "",
      profile: "",
      role: "",
      voiceId: 0,
    },
    accessToken: "",
  }),
  //   getters == computed()  // 랜더링 될때 실행되는 함수
  getters: {
    getMemberInfo(state) {
      return state.member;
    },
  },

  //   action == function()  // 함수
  actions: {
    async modifyNickName(nickName) {
      await modifyNickName(
        this.member.memberId,
        nickName,
        this.accessToken,
        ({ data }) => {
          this.member.nickname = data;
        }
      );
    },

    async modifyMyProfile(formData) {
      await modifyMyProfile(
        formData,
        this.accessToken,
        ({ data }) => {
          this.member.profile = data;
        },
        (error) => {
          console.log(error.response);
        }
      );
    },

    async setMemberInfo() {
      await getMemberInfo(
        this.accessToken,
        ({ data }) => {
          // console.log("Data", data);
          this.member.memberId = data.memberId;
          this.member.email = data.email;
          this.member.nickname = data.nickname;
          this.member.profile = data.profile;
          this.member.role = data.role;
          this.member.voiceId = data.voiceId;
          // localStorage.setItem("isLogin", this.member.role);/
        },
        (error) => {
          // console.log(error.code);
        }
      );
    },

    async refreshAccessToken() {
      await getNewAccessToken(
        ({ data }) => {
          this.accessToken = data;
        },
        (error) => {
          // console.log(error.response.status);
          router.push({ name: "login" });
        }
      );
    },

    async setAccessToken(accessToken) {
      // getters.checkLogin;
      // this.checkLogin;
      this.accessToken = accessToken;
    },

    async signUp(role, age, gender) {
      await signUp(
        this.member.memberId,
        role,
        age,
        gender,
        this.accessToken,
        () => {
          this.member.role = role;
          if (gender == "M") {
            this.member.voiceId = 1;
          } else {
            this.member.voiceId = 2;
          }
        }
      );
    },

    async logout() {
      await logout(
        this.member.memberId,
        this.accessToken,
        () => {
          this.accessToken = null;
          this.clearLogin;
          window.location.assign(`/`);
        },
        () => {
          this.accessToken = null;
          this.clearLogin;
          // console.log(error);
          window.location.assign(`/`);
        }
      );
    },
  },

  // return {states, action} 반환 하면됨
  //   return {};
});
