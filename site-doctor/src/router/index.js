import { createRouter, createWebHashHistory } from "vue-router";
import store from "@/vuex";
const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: "/",
      redirect: "/home/inquirymanagement",
      component: () => import("@/pages/home/home.vue"),
      children: [
        {
          path: "/home/inquirymanagement",
          //redirect:'/home/inquirymanagement/'
          component: () => import("@/pages/inquirymanagement/index.vue"),
          redirect: "/home/inquirymanagement/inprogress",
          children: [
            {
              path: "/home/inquirymanagement/inprogress",
              component: () => import("@/pages/inquirymanagement/inprogress.vue"),
              children: [
                {
                  path: "/home/inquirymanagement/inprogress/chatframe",
                  component: () => import("@/pages/inquirymanagement/chat_frame.vue")
                }
              ]
            },
            {
              path: "/home/inquirymanagement/finished",
              component: () => import("@/pages/inquirymanagement/finished.vue")
            },
            {
              path: "/home/inquirymanagement/waiting",
              component: () => import("@/pages/inquirymanagement/waiting.vue")
            }
          ]
        },
        {
          path: "/home/registrationmanagement",
          //redirect:'/home/inquirymanagement/'
          component: () => import("@/pages/registrationmanagement/index.vue")
        }
      ]
    },
    {
      path: "/login",
      component: () => import("@/pages/login/index.vue")
    }
  ],
  scrollBehavior() {
    return {
      left: 0,
      top: 0
    };
  }
});
router.beforeEach((to, from, next) => {
  const useToken = store.getters.doctorLoginToken;
  if (to.path != "/login" && useToken == "") return next({ path: "/login" });
  else return next();
});
export default router;
