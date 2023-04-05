<template>
  <div class="pronounce">
    <div>
      <FreqUsedPhrase />
    </div>
    <div class="">
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
