<template>
  <span class="tag tag-lg">#{{ props.feature.featureName }}</span>
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
  background-color: #454e56;
  color: #c9cecd;
  font-weight: 10px;
  margin: 0.25em 0.1em;
}
.tag-lg {
  font-size: 12px;
  border-radius: 4px;
}
</style>
