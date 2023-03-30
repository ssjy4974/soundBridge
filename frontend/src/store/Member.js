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
      email: null,
      nickname: null,
      profile: null,
      role: null,
    },
    accessToken: null,
  }),
  //   getters == computed()  // 랜더링 될때 실행되는 함수
  //   action == function()  // 함수
  actions: {
    async modifyNickName(nickName) {
      await modifyNickName(
        this.member.memberId,
        nickName,
        this.accessToken,
        ({ data }) => {
          console.log(data, " modify nickName");
          this.member.nickname = data;
        }
      );
    },

    async modifyMyProfile(formData) {
      await modifyMyProfile(
        formData,
        this.accessToken,
        ({ data }) => {
          console.log(data, "modify Profile");
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
          // this.member.memberId = data.memberId;
          // this.member.email = data.email;
          // this.member.nickname = data.nickname;
          // this.member.profile = data.profile;
          // this.member.role = data.role;
          this.member = data;
        },
        (error) => {
          console.log(error.response.data);
        }
      );
    },

    async refreshAccessToken() {
      await getNewAccessToken(
        ({ data }) => {
          // console.log(data);
          this.accessToken = data;
          this.member = this.setMemberInfo(this.accessToken);
        },
        (error) => {
          console.log(error);
          router.push({ name: "login" });
        }
      );
    },

    async setAccessToken(accessToken) {
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
        }
      );
    },

    async logout() {
      await logout(
        this.member.memberId,
        this.accessToken,
        () => {
          this.accessToken = null;
          router.push({ name: "login" });
        },
        () => {
          this.accessToken = null;
          // console.log(error);
          router.push({ name: "login" });
        }
      );
    },
  },

  getters: {
    getMemberInfo(state) {
      return state.member;
    },
  },

  // return {states, action} 반환 하면됨
  //   return {};
});
