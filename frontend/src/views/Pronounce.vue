<template>
  <div>
    <h1>Pronounce 발화 페이지 성공</h1>
    <div>
      <FreqUsedPhrase />
    </div>
    <div class="pre__border">
      <DirectTTS />
    </div>
    <div>
      <OthersSTT />
    </div>
  </div>
</template>

<script setup>
import DirectTTS from "../components/pronounce/DirectTTS.vue";
import FreqUsedPhrase from "../components/pronounce/FreqUsedPhrase.vue";
import OthersSTT from "../components/pronounce/OthersSTT.vue";
</script>

<style lang="scss" scoped></style>
