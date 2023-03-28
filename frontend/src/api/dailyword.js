import { apiInstance } from "./index.js";

const api = apiInstance();

async function getMyDailyWord(accessToken, success, fail) {
  console.log("get my dailyword", accessToken);
  await api
    .get(`/ai/daily-words`, {
      headers: {
        "access-token": accessToken,
      },
    })
    .then(success)
    .catch(fail);
}

async function addMyDailyWord(word, accessToken, success, fail) {
  await api
    .post(
      `/api/my-sentence-logs`,
      {
        dailyWord: word,
        memberId: 1,
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

export { getMyDailyWord, addMyDailyWord };
