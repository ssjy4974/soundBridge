import { apiInstance } from "./index.js";

const api = apiInstance();

// 내가 쓴 문장 저장, 업데이트 PUT
// 업데이트할 문장인 sentence도 parameter에 넣어주기.
// request header에 accessToken 들어가야해서 인자로 넣기.
async function updateMySentence(sentence, accessToken, success, fail) {
  console.log("update my sentence", sentence, accessToken);
  await api
    .put(
      `/api/my-sentence-logs`,
      {
        sentence: sentence,
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

//POST
async function addMySentence(sentence, accessToken, success, fail) {
  // console.log("add new sentence", sentence.value, accessToken);
  await api
    .post(
      `/api/my-sentence-logs`,
      {
        sentence: sentence,
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

// 내가 쓴 문장 자동완성 조회  GET
async function getMySentences(sentence, accessToken, success, fail) {
  // console.log("get my sentence", sentence.value, accessToken);
  await api
    .get(`/api/my-sentence-logs/${sentence}`, {
      headers: {
        "access-token": accessToken,
      },
    })
    .then(success)
    .catch(fail);
}

export { updateMySentence, addMySentence, getMySentences };
