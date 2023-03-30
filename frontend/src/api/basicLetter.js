import { apiInstance } from "./index.js";

const api = apiInstance();

const getBasicLetterList = async (accessToken, success, fail) => {
  api
    .get(`/api/basic-letters`, {
      headers: {
        "access-token": accessToken,
      },
    })
    .then(success)
    .catch(fail);
};

const getBasicLetterDetails = async (
  accessToken,
  basicLetterId,
  success,
  fail
) => {
  api
    .get(`/api/basic-letters/${basicLetterId}`, {
      headers: {
        "access-token": accessToken,
      },
    })
    .then(success)
    .catch(fail);
};

const updateTryCount = async (accessToken, basicLetterId, success, fail) => {
  api
    .post(`/api/try-histories/basic-letters/${basicLetterId}`, {
      headers: {
        "access-token": accessToken,
      },
    })
    .then(success)
    .catch(fail);
};

const updateSuccessCount = async (
  accessToken,
  basicLetterId,
  success,
  fail
) => {
  api
    .put(`/api/try-histories/basic-letters/${basicLetterId}`, {
      headers: {
        "access-token": accessToken,
      },
    })
    .then(success)
    .catch(fail);
};

export {
  getBasicLetterList,
  getBasicLetterDetails,
  updateTryCount,
  updateSuccessCount,
};
