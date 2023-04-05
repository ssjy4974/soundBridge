import { apiInstance } from "./index.js";

const api = apiInstance();

async function getMyDailyWord(accessToken, success, fail) {
  await api
    .get(`/api/daily-words?type=DAILY_WORD`, {
      headers: {
        "access-token": accessToken,
      },
    })
    .then(success)
    .catch(fail);
}

async function getMySentence(accessToken, success, fail) {
  await api
    .get(`/api/daily-words?type=SENTENCE`, {
      headers: {
        "access-token": accessToken,
      },
    })
    .then(success)
    .catch(fail);
}

async function addMyDailyWord(
  word,
  memberId,
  type,
  accessToken,
  success,
  fail
) {
  await api
    .post(
      `/ai/daily-words`,
      {
        dailyWord: word,
        memberId: memberId,
        type: type,
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

async function deleteDailyWord(wordMemberId, accessToken, success, fail) {
  await api
    .delete(`api/daily-words/${wordMemberId}`, {
      headers: {
        "access-token": accessToken,
      },
    })
    .then(success)
    .catch(fail);
}

export {
  getMyDailyWord,
  getMySentence,
  addMyDailyWord,
  saveOrUpdateTryHistory,
  updateSuccessCount,
  deleteDailyWord,
};
