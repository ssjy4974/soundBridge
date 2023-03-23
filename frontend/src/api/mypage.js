import { apiInstance } from "./index.js";

const api = apiInstance();
const userid = "";

async function myVoice(memberId, nickname, accessToken, success, fail) {
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

async function voiceList(age, gender, features, accessToken, success, fail) {
  console.log("VL", age, gender, features, accessToken);
  await api
    .get(
      `/api/voices`,
      {
        params: {
          voiceGender: gender,
          voiceAge: age,
          features: features,
        },
      },
      {
        headers: {
          "Content-Type": "application/json",
          "access-token": accessToken,
        },
      }
    )
    .then(success)
    .catch(fail);
}

export { myVoice, voiceList };
