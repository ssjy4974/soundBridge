import axios from "axios";
// local vue api axios instance
function apiInstance() {
  const instance = axios.create({
    baseURL: "https://j8a703.p.ssafy.io", //env로 설정 해줘야함
    headers: {
      "Content-Type": "application/json;charset=utf-8",
      accept: "application/json,",
    },
  });
  return instance;
}

export { apiInstance };
