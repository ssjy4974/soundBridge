<template>
  <div>
    <div>
      <h3>기본 발음 연습 디테일 컴포넌트</h3>
      <p>발음 제목 (기역, 니은 이런거)</p>
      <div>
        <p>조음법 이미지 백에서 가져온거 보여주기</p>
        <img src="" alt="조음법 이미지" />
      </div>

      <div>
        <div>
          <button>뒤로가기 버튼 (아이콘)</button>
        </div>
        <div>
          <p>올바른 발음 보여주기 (그-, 느-)</p>
          <p>
            유저가 연습한 발음 보여주기 (stt결과) 올바른 발음과 일치하면 성공횟
            수 +1
          </p>
        </div>
        <div>
          <button>다음 버튼 (아이콘)</button>
        </div>
      </div>
      <div>
        <button>연습하기 누르면 STT 실행</button>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style lang="scss" scoped></style>
