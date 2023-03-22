import axios from "axios";
// local vue api axios instance
function apiInstance() {
  const instance = axios.create({
    baseURL: ${API_BASE_URL}, //env로 설정 해줘야함
    headers: {
      "Content-Type": "application/json;charset=utf-8",
      accept: "application/json,",
    },
  });
  return instance;
}

export { apiInstance };
