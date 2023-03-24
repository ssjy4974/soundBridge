import { apiInstance } from "./index.js";

const api = apiInstance();

async function voice(memberId, accessToken, success, fail) {
  console.log("modifyNickname", memberId);
  await api
    .get(
      `/api/voices/${memberId}`,
      {},
      {
        headers: {
          "access-token": accessToken,
        },
      }
    )
    .then(success, console.log(success.data))
    .catch(fail);
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

export { voice, voiceList };
