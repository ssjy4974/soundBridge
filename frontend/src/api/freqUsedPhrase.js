import { apiInstance } from "./index.js";
const api = apiInstance();

// GET catagory params(catagory)
async function getPhrase(catagoryId, accessToken, success, fail) {
  console.log("get category api call");
  await api
    .get(`api/quick-sentences/${catagoryId}`, {
      headers: {
        "access-token": accessToken,
      },
    })
    .then(success)
    .catch(fail);
}

// POST categories
//  Category name
async function postPhrase(sentence, categoryId, accessToken, success, fail) {
  console.log("post category api call");
  await api
    .post(
      `api/quick-sentences`,
      {
        sentence: sentence,
        categoryId: categoryId,
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

// PUT categories
async function putPhrase(sentenceId, sentence, accessToken, success, fail) {
  console.log("put category api call");
  await api
    .put(
      `api/quick-sentences`,
      {
        quickSentenceId: sentenceId,
        quickSentence: sentence,
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

// DELETE catagoires
async function deletePhrase(sentenceId, accessToken, success, fail) {
  await api
    .delete(`api/quick-sentences/${sentenceId}`, {
      headers: {
        "access-token": accessToken,
      },
    })
    .then(success)
    .catch(fail);
}

// PUT categories count
async function putPhraseCount(sentenceId, accessToken, success, fail) {
  console.log("put category api call");
  await api
    .put(`api/quick-sentences/${sentenceId}`, {
      headers: {
        "access-token": accessToken,
      },
    })
    .then(success)
    .catch(fail);
}
export { getPhrase, postPhrase, putPhrase, deletePhrase, putPhraseCount };
