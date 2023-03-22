import { apiInstance } from "./index.js";

const api = apiInstance();
const userid = "";

async function modifyMyProfile(formData, accessToken, success, fail) {
  console.log("FD", typeof formData);
  await api
    .put(`/api/members/profile`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
        "access-token": accessToken,
      },
    })
    .then(success)
    .catch(fail);
}

export { modifyMyProfile };
