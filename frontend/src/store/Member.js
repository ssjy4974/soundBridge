import { defineStore } from "pinia";
import { modifyMyProfile } from "@/api/member";

export const useMypage = defineStore("mypage", {
  // state  == ref(), useState() 변수
  state: () => ({
    member: {
      memberId: null,
      email: null,
      nickname: null,
      profile: "loofy.png",
      role: null,
    },
    accessToken: "access-token 123",
  }),
  //   getters == computed()  // 랜더링 될때 실행되는 함수
  //   action == function()  // 함수
  actions: {
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
          console.log(error.response.data);
        }
      );
    },
  },

  // return {states, action} 반환 하면됨
  //   return {};
});
