import { apiInstance } from "./index.js";

const api = apiInstance();
const userid = "";

async function modifyNickName(memberId, nickname, accessToken, success, fail) {
  console.log("modifyNickname", memberId, nickname);
  await api
    .put(
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
    )
    .then(success)
    .catch(fail);
}

async function modifyMyProfile(formData, accessToken, success, fail) {
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

async function getMemberInfo(accessToken, success, fail) {
  console.log(accessToken);
  await api
    .get(`/api/members`, {
      headers: {
        "access-token": accessToken,
      },
    })
    .then(success)
    .catch(fail);
}

async function signUp(memberId, role, age, gender, accessToken, success, fail) {
  await api
    .put(
      `/api/members/add-info`,
      {
        memberId: memberId,
        role: role,
        age: age,
        gender: gender,
      },
      {
        headers: {
          "access-token": accessToken,
        },
      }
    )
    .then(success)
    .catch(fail);
}

async function getNewAccessToken(success, fail) {
  await api.get(`/api/token/tokenReissue`, {}).then(success).catch(fail);
}

export {
  modifyMyProfile,
  modifyNickName,
  getMemberInfo,
  signUp,
  getNewAccessToken,
};
