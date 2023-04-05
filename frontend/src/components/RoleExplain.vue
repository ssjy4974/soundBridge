<template>
  <div class="role-explain">
    <h1>역할 설명</h1>
    <p id="chung">
      청인 : 목소리를 기부하고, 농인들의 발음교정을 온라인 미팅으로 도와주는
      역할입니다.
    </p>
    <p id="nong">
      농인 : TTS와, STT를 활용하여 목소리를 사용하고, 상대방의 말을 문장으로
      받아 볼 수 있습니다. 또한, 발음교정을 도움받을 수 있습니다.
    </p>
  </div>
</template>

<script setup></script>

<style scoped>
h1 {
  text-align: center;
  padding-top: 10px;
}
#chung {
  margin-inline: 10%;
}
#nong {
  margin-inline: 10%;
}
.role-explain {
  padding: 4%;
}
</style>
