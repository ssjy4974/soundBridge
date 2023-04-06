<template>
  <div>
    <router-link to="../practice-basics">
      <div class="daily_wrap">
        <div class="daily">
          <h1>기본 발음 연습</h1>
          <div class="daily__box">
            <p>
              한글 자음을 연습 할 수 있는 페이지 입니다. 조음법과 영상으로
              발음을 연습 할 수 있습니다.
            </p>
          </div>
        </div>
      </div>
    </router-link>

    <router-link to="../practice-words">
      <div class="daily_wrap">
        <div class="daily">
          <h1>일상 단어 연습</h1>
          <div class="daily__box">
            <p>
              일상에서 자주 쓰이는 단어를 추가하여 발음 연습을 할 수 있습니다.
            </p>
          </div>
        </div>
      </div>
    </router-link>

    <router-link to="../practice-sentence">
      <div class="daily_wrap">
        <div class="daily">
          <h1>문장 연습</h1>
          <div class="sentence_box">
            <p>연습 하고 싶은 문장을 직접 등록하고 연습 할 수 있습니다.</p>
          </div>
        </div>
      </div>
    </router-link>
  </div>
</template>

<script setup>
const IMAGE_PATH = import.meta.env.VITE_IMAGE_PATH;
</script>

<style scoped>
.basic_wrap {
  padding-top: 15%;
  padding-bottom: 10%;
  display: flex;
  justify-content: center;
}
.basic {
  /* padding: 25px 0px; */
  padding-top: 10%;
  text-align: center;
  position: relative;
  width: 95%;

  background: #bae4ff;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 16px;
}
.basic__box {
  /* display: block; */
  /* padding: -40px; */
}
.image {
  display: flex;
  justify-content: space-around;
  align-items: center;
}
.daily_wrap {
  margin-top: 4%;
  display: flex;
  padding-bottom: 10%;

  justify-content: center;
}
.daily {
  /* margin-block: 3%; */
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px 0px;

  /* position: relative; */
  /* width: 370px;
  height: 200px;
  left: 20px;
  top: 400px; */
  width: 90%;

  background: #bae4ff;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 16px;
}
.sentence {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px 0px;
  gap: 24px;

  position: relative;
  /* width: 370px;
  height: 200px;
  left: 20px;
  top: 400px; */
  width: 95%;

  background: #bae4ff;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 16px;
}
h1 {
  color: black;
  margin-top: 3%;
}
.daily__box {
  /* padding-block: 2vh; */
  line-height: 1.7rem;
  width: 75%;
}
.sentence_box {
  width: 75%;
  line-height: 1.7rem;
  /* white-space: nowrap; */
}
</style>
