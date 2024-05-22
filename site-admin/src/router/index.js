import { createRouter, createWebHashHistory } from "vue-router";
import store from "@/vuex";
const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: "/",
      redirect: "/home/usermanagement",
      component: () => import("@/pages/home/index.vue"),
      children: [
        {
          path: "/home/usermanagement",
          component: () => import("@/pages/user_management/index.vue") //懒加载
        },
        {
          path: "/home/patientmanagement",
          component: () => import("@/pages/user_management/patient.vue") //懒加载
        },
        {
          path: "/home/userdetail",
          component: () => import("@/pages/user_management/userdetail.vue")
        },
        {
          path: "/home/patientdetail",
          component: () => import("@/pages/user_management/patientdetail.vue")
        },

        {
          path: "/home/hospitalmanagement",
          component: () => import("@/pages/hospital_management/index.vue")
        },
        {
          path: "/home/hospitaldetail",
          component: () => import("@/pages/hospital_management/hospitaldetail.vue")
        },
        {
          path: "/home/specialistdetail",
          component: () => import("@/pages/hospital_management/specialistdetail.vue")
        },
        {
          path: "/home/outpatientdetail",
          component: () => import("@/pages/hospital_management/outpatientdetail.vue")
        },
        {
          path: "/home/doctordetail",
          component: () => import("@/pages/hospital_management/doctordetail.vue")
        },
        {
          path: "/home/scheduleindex",
          component: () => import("@/pages/schedule_management/index.vue")
        },
        {
          path: "/home/schedulemanagement",
          component: () => import("@/pages/schedule_management/schedule.vue")
        },
        {
          path: "/home/orderInfo",
          component: () => import("@/pages/orderInfo_management/index.vue")
        },
        {
          path: "/home/inquiry",
          component: () => import("@/pages/inquiry_management/index.vue")
        }
      ]
    },
    {
      path: "/login",
      component: () => import("@/pages/login/index.vue")
    }
  ],
  //滚动行为：当切换路由时，让页面的滚动条滚到最上面
  scrollBehavior() {
    return {
      left: 0,
      top: 0
    };
  }
});
router.beforeEach((to, from, next) => {
  const useToken = store.getters.managerLoginToken;
  if (to.path != "/login" && useToken == "") {
    return next({ path: "/login" });
  }
  return next();
});
export default router;
