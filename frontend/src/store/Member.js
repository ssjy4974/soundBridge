import { defineStore } from "pinia";
import {
  modifyMyProfile,
  modifyNickName,
  getMemberInfo,
  signUp,
} from "@/api/member";

export const useMember = defineStore("member", {
  // state  == ref(), useState() 변수
  state: () => ({
    member: {
      memberId: 1,
      email: null,
      nickname: "Nick",
      profile:
        "https://yt3.googleusercontent.com/ytc/AL5GRJVAMq-8ooMfNc0HDE6hWKlNr4vQXpba8gPxpya5B14=s900-c-k-c0x00ffffff-no-rj",
      role: null,
    },
    accessToken: "access-token 123",
  }),
  //   getters == computed()  // 랜더링 될때 실행되는 함수
  //   action == function()  // 함수
  actions: {
    async modifyNickName(nickName) {
      console.log("nickName", nickName, this.member.memberId);
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
      console.log("ID ", formData.get("memberId"));
      await modifyMyProfile(
        formData,
        this.accessToken,
        ({ data }) => {
          console.log(data, "modify Profile");
          this.member.profile = data;
          console.log(this.member.profile);
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
          this.member.memberId = data.memberId;
          this.member.email = data.email;
          this.member.nickname = data.nickname;
          this.member.profile = data.profile;
          this.member.role = data.role;
        },
        (error) => {
          console.log(error.response.data);
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
  },

  getters: {
    getMemberInfo(state) {
      return state.member;
    },
  },

  // return {states, action} 반환 하면됨
  //   return {};
});
