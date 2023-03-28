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

export { getMyDailyWord };
