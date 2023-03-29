import { defineStore } from "pinia";
import { computed, ref } from "vue";
import { getCategories, postCategories, putCategories } from "@/api/category";
import {
  updateQuickSentence,
  saveQuickSentence,
  getQuickSentence,
  countQuickSentenceAndCategory,
  delQuickSentence,
} from "@/api/quickSentence";

export const usePronounce = defineStore("pronounce", () => {
  const accessToken = "access-token 123";

  // state
  // 내가 쓰는 문장
  const freqUsedPhrase = ref();
  const freqUsedCat = ref();

  // action
  async function addCategory(category) {
    await postCategories(category, accessToken, ({ data }) => {
      console.log("API file POST category : ", data);
      freqUsedCat.value = data;
    });
  }

  //put api call
  async function editCategory(categoryId, category) {
    await putCategories(categoryId, category, accessToken, ({ data }) => {
      console.log("API file PUT category : ", data);
    });
  }

  // POST Quick Sentence
  async function addQuickSentence(sentence, categoryId) {
    await saveQuickSentence(sentence, categoryId, accessToken, ({ data }) => {
      // console.log("API file POST add new sentence : ", data);
      freqUsedPhrase.value = data;
    });
  }

  // GET Quick Sentence
  async function readQuickSentence(categoryId) {
    await getQuickSentence(categoryId, accessToken, ({ data }) => {
      // console.log("API file GET  sentences by category : ", data);
      freqUsedPhrase.value = data;
    });
  }

  // PUT Quick Sentence
  async function editQuickSentence(quickSentenceId, quickSentence) {
    await updateQuickSentence(
      quickSentenceId,
      quickSentence,
      accessToken,
      ({ data }) => {
        console.log("API file PUT sentence according to category");
      }
    );
  }
  // PUT count
  async function countQuickSentence(quickSentenceId) {
    await countQuickSentenceAndCategory(
      quickSentenceId,
      accessToken,
      ({ data }) => {
        console.log("API file, PUT count sentences", data);
      }
    );
  }

  // DELETE
  async function deleteQuickSentence(quickSentenceId) {
    await delQuickSentence(quickSentenceId, accessToken, ({ data }) => {
      console.log("API file, DELETE quick sentences response: ", data);
      freqUsedPhrase.value = data;
    });
  }
  // getters
  const readCategories = computed(async () => {
    await getCategories(accessToken, ({ data }) => {
      console.log("read catagory : ", data);
      freqUsedCat.value = data;
    });
  });

  // return
  return {
    freqUsedPhrase,
    freqUsedCat,
    readCategories,
    addCategory,
    editCategory,
    addQuickSentence,
    readQuickSentence,
    editQuickSentence,
    countQuickSentence,
    deleteQuickSentence,
  };
});
