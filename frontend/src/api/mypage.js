import { apiInstance } from "./index.js";

const api = apiInstance();
const userid = "";

async function getMyVoice(memberId, nickname, accessToken, success, fail) {
  console.log("modifyNickname", memberId, nickname);
  await api.put(
    `/api/members/nickname`,
    {
      memberId: memberId,
      nickname: nickname,
    },
    {
      headers: {
        "access-token": accessToken,
      },
    }
  );
}

async function getVoiceList(formData, accessToken, success, fail) {
  console.log("FD", typeof formData);
  await api
    .put(`/api/members/profile`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
        "access-token": accessToken,
      },
    })
    .then(success)
    .catch(fail);
}

export { modifyMyProfile, modifyNickName };
