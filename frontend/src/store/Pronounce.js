import { defineStore } from "pinia";
import { computed, ref } from "vue";
import { getCategories, postCategories, putCategories } from "@/api/category";

export const usePronounce = defineStore("pronounce", () => {
  const accessToken = "access-token 123";

  // state  == ref(), useState() 변수
  // 내가 쓰는 문장
  const freqUsedPhrase = ref();
  const freqUsedCat = ref();
  // action == function()  함수

  async function addCategory(category) {
    await postCategories(category, accessToken, ({ data }) => {
      console.log("POST category : ", data);
    });
  }

  //put api call
  async function editCategory(categoryId, category) {
    await putCategories(categoryId, category, accessToken, ({ data }) => {
      console.log("PUT category : ", data);
    });
  }

  // getters == computed()  랜더링 될때 실행되는 함수 -
  const readCategories = computed(async () => {
    await getCategories(accessToken, ({ data }) => {
      console.log("read catagory : ", data);
      freqUsedCat.value = data;
    });
  });
  // async function readCategories() {
  //   await getCategories(accessToken, ({ data }) => {
  //     console.log("read catagory : ", data);
  //     freqUsedCat.value = data;
  //   });
  // }

  // return
  return {
    freqUsedPhrase,
    freqUsedCat,
    readCategories,
    addCategory,
    editCategory,
  };
});
