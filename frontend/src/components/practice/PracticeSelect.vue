<template>
  <div>
    <router-link to="../practicebasics">
      <div class="basic">
        <h1>기본 발음 연습</h1>
        <div class="image">
          <img class="basic_image" src="@/assets/img/기역.png" />
          <img class="basic_image" src="@/assets/img/니은.png" />
          <img class="basic_image" src="@/assets/img/디귿.png" />
        </div>
      </div>
    </router-link>

    <router-link to="../practicewords">
      <div class="daily">
        <h1>일상 단어 연습</h1>
        <div class="image">
          <img class="word_image" src="@/assets/img/image10.png" />
          <img class="word_image" src="@/assets/img/image12.png" />
        </div>
      </div>
    </router-link>
  </div>
</template>

<script setup>
const IMAGE_PATH = import.meta.env.VITE_IMAGE_PATH;
</script>

<style scoped>
.basic {
  padding: 20px 0px;
  gap: 24px;
  text-align: center;
  position: absolute;
  width: 370px;
  height: 200px;
  left: 20px;
  top: 120px;

  background: #bae4ff;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 16px;
}
.image {
  display: flex;
  justify-content: space-around;
  align-items: center;
}
.daily {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px 0px;
  gap: 24px;

  position: absolute;
  width: 370px;
  height: 200px;
  left: 20px;
  top: 400px;

  background: #bae4ff;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 16px;
}
h1 {
  margin-top: 0px;
  margin-bottom: 50px;
  color: black;
}
.basic_image {
  width: 75px;
  height: 75px;
  padding: 3px;
  padding-left: 6.5px;
  vertical-align: middle;
}
.word_image {
  margin-top: -30px;
  padding: 10px;
}
</style>
