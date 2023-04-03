<template>
  <span class="tag tag-lg" v-if="props.feature.featureName"
    >#{{ props.feature.featureName }}</span
  >
  <span class="tag tag-lg" v-else-if="props.feature === 'M'">#남</span>
  <span class="tag tag-lg" v-else-if="props.feature === 'W'">#여</span>
  <span class="tag tag-lg" v-else>#{{ props.feature }}대</span>
  <!-- <span class="tag tag-lg">#{{ props.feature }}</span> -->
</template>

<script setup>
import { defineProps, onBeforeMount, ref } from "vue";
const props = defineProps(["feature", "index"]);

onBeforeMount(() => {
  // console.log("@#@#", props.feature);
});
</script>

<style lang="scss" scoped>
.tag {
  display: inline-block;
  border-radius: 3px;
  padding: 0.2em 0.5em 0.3em;
  border-radius: 2px;
  background-color: var(--maincolor5);
  color: white;
  font-weight: 10px;
  margin: 0.25em 0.1em;
}
.tag-lg {
  font-size: 12px;
  border-radius: 4px;
}
</style>
