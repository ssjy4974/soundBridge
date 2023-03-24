<template>
  <div>
    <div v-if="basicLetter">
      <h3>기본 발음 연습 디테일 컴포넌트</h3>
      <p>발음 명 : {{ basicLetter.letter }}</p>
      <div>
        <img
          :src="`${IMAGE_PATH}/${basicLetter.letterImage}`"
          alt="조음법 이미지"
        />
      </div>

      <div>
        <div>
          <button @click="router.replace(`/practicebasics`)">
            뒤로가기 버튼 (아이콘)
          </button>
        </div>
        <div>
          <p>실제 발음 명 : {{ basicLetter.guidLetter }}</p>
          <p>
            유저가 연습한 발음 보여주기 (stt결과) 올바른 발음과 일치하면 성공횟
            수 +1
          </p>
        </div>
        <!-- 자음 모음 다 추가하면 index는 수정할 예정 -->
        <div v-if="route.params.basicLetterId < 14">
          <button @click="next">다음 버튼 (아이콘)</button>
        </div>
      </div>
      <div>
        <button>연습하기 누르면 STT 실행</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRoute } from "vue-router";
import { apiInstance } from "@/api/index";
import { ref } from "vue";
import router from "@/router/index";

const api = apiInstance();
const route = useRoute();

const basicLetter = ref();
const IMAGE_PATH = import.meta.env.VITE_IMAGE_PATH;

const callApi = () => {
  api
    .get(`/api/basic-letters/${route.params.basicLetterId}`)
    .then((res) => {
      basicLetter.value = res.data;
    })
    .catch((err) => {
      err;
      alert("상세 조회 실패");
      router.replace("/");
    });
};
callApi();

const next = () => {
  const index = Number(route.params.basicLetterId);
  router.replace(`/practicebasicsdetail/${index + 1}`);
  api
    .get(`/api/basic-letters/${index + 1}`)
    .then((res) => {
      basicLetter.value = res.data;
    })
    .catch((err) => {
      err;
      alert("상세 조회 실패");
      router.replace("/");
    });
};
</script>

<style scoped>
img {
  width: 100px;
  height: 100px;
}
</style>
