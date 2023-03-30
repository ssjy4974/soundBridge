import { apiInstance } from "./index.js";
const api = apiInstance();

// GET catagory
async function getCategories(accessToken, success, fail) {
  console.log("get category api call");
  await api
    .get(`api/categories`, {
      headers: {
        "access-token": accessToken,
      },
    })
    .then(success)
    .catch(fail);
}

// POST categories
//  Category name
async function postCategories(category, accessToken, success, fail) {
  console.log(`post category api call ${category}`);
  await api
    .post(
      `api/categories`,
      {
        categoryName: category,
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
async function putCategories(categoryId, category, accessToken, success, fail) {
  console.log("put category api call");
  await api
    .put(
      `api/categories`,
      {
        categoryId: categoryId,
        categoryName: category,
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

export { getCategories, postCategories, putCategories };
