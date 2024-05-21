import { createApp } from "vue";
import App from "./App.vue";
import "@/style/reset.css";

import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
import * as ElementPlusIconsVue from "@element-plus/icons-vue";
//引入vue-router
import router from "@/router";
import store from "@/vuex";

const app = createApp(App);
//安装vue-router
app.use(router);
app.use(ElementPlus);
app.use(store);
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component);
}
app.mount("#app");
