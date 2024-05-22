import { createApp } from "vue";
//导入App组件
import App from "@/App.vue";
//清除默认样式
import "@/style/reset.scss";
//引入全局组件 --顶部,侧边栏是全局组件
import ManagementTop from "@/components/management_top/index.vue";
import ManagementAside from "@/components/management_aside/index.vue";
//引入vue-router
import router from "@/router";
//引入element-plus
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
//中文风格
import zhCn from "element-plus/es/locale/lang/zh-cn";
//引入图标
import * as ElementPlusIconsVue from "@element-plus/icons-vue";
import store from "@/vuex";
const app = createApp(App);
//注册全局组件
app.component("ManagementTop", ManagementTop);
app.component("ManagementAside", ManagementAside);
//注册图标
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component);
}
//安装vue-router
app.use(router);
//安装element-plus,并配置为中文风格
app.use(ElementPlus, { locale: zhCn });
//安装vuex
app.use(store);
//将App组件挂载（mount）到index.html上
app.mount("#app");
