import { apiInstance } from "./index.js";

const api = apiInstance();

async function getMyDailyWord(accessToken, success, fail) {
  console.log("get my dailyword", accessToken);
  await api
    .get(`/api/daily-words`, {
      headers: {
        "access-token": accessToken,
      },
    })
    .then(success)
    .catch(fail);
}

async function addMyDailyWord(word, memberId, accessToken, success, fail) {
  await api
    .post(
      `/ai/daily-words`,
      {
        dailyWord: word,
        memberId: memberId,
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

async function saveOrUpdateTryHistory(
  wordMemberId,
  accessToken,
  success,
  fail
) {
  await api
    .post(
      `/api/try-histories/daily-words/${wordMemberId}`,
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

async function updateSuccessCount(wordMemberId, accessToken, success, fail) {
  await api
    .put(
      `/api/try-histories/daily-words/${wordMemberId}`,
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

export {
  getMyDailyWord,
  addMyDailyWord,
  saveOrUpdateTryHistory,
  updateSuccessCount,
};
