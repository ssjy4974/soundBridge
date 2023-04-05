import { apiInstance } from "./index.js";

const api = apiInstance();

async function voice(memberId, accessToken, success, fail) {
  console.log("voice", memberId);
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
    .then(success)
    .catch(fail);
}

async function voiceList(age, gender, features, accessToken, success, fail) {
  let fList = null;
  if (features && features.length > 0) {
    fList = features.join();
  }
  await api
    .get(
      `/api/voices`,
      {
        params: {
          voiceGender: gender,
          voiceAge: age,
          features: fList,
        },
      },
      {
        headers: {
          // "Content-Type": "application/json",
          "access-token": accessToken,
        },
      }
    )
    .then(success)
    .catch(fail);
}

async function select(memberId, voiceId, accessToken, success, fail) {
  console.log("select Voice", memberId, voiceId);
  await api
    .put(
      `/api/voices/select`,
      {
        memberId: memberId,
        voiceId: voiceId,
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

async function registVoice(
  voiceName,
  voiceAge,
  voiceGender,
  features,
  accessToken,
  success,
  fail
) {
  await api
    .post(
      `/api/voices/regist`,
      {
        voiceName,
        voiceAge,
        voiceGender,
        features,
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

export { voice, voiceList, select, registVoice };
