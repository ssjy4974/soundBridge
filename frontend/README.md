# Vue 3 + Vite

This template should help get you started developing with Vue 3 in Vite. The template uses Vue 3 `<script setup>` SFCs, check out the [script setup docs](https://v3.vuejs.org/api/sfc-script-setup.html#sfc-script-setup) to learn more.

## Recommended IDE Setup

- [VS Code](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar) (and disable Vetur) + [TypeScript Vue Plugin (Volar)](https://marketplace.visualstudio.com/items?itemName=Vue.vscode-typescript-vue-plugin).



## 상태관리도구 Pinia

```bash
npm install pinia
```



## Style Library

Vuetify or Tailwind CSS 고민중



## Router

```bash
npm install router@4
```

## Style

### Fontawesome

1. Install

```bash
npm install @fortawesome/fontawesome-free 
npm i --save @fortawesome/fontawesome-svg-core
npm i --save @fortawesome/free-solid-svg-icons
npm i --save @fortawesome/free-regular-svg-icons
npm i --save @fortawesome/free-brands-svg-icons
npm i --save @fortawesome/vue-fontawesome@latest-3
```



2. `main.js` 에서 설치한 모듈 불러오고 등록하기

```js
// main.js

// fontawesome import
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

// library는 icon들을 담을  그릇
import { library } from "@fortawesome/fontawesome-svg-core";

// fontawesome icon 타입별로 전부다 가져오기
import { fas } from "@fortawesome/free-solid-svg-icons";
import { far } from "@fortawesome/free-regular-svg-icons";
import { fab } from "@fortawesome/free-brands-svg-icons";

// 사용할 수 있게 library에 넣어주기
library.add(fas, fab, far);

```



3. 최상위 컴포넌트에서 fontawesome import 하기

```js
// App.vue
import '@fortawesome/fontawesome-free/css/all.css'
```



4. 적용하기

```vue
// footer.vue
<template>
  <div class="footer">
    <p><font-awesome-icon icon="fa-solid fa-user" /></p>
  </div>
</template>
```





## Frontend Project Convention

### Naming Convention

file : PascalCase   							ex : `DisabledMain.vue`



