import { createStore } from "vuex";

// 创建一个新的 store 实例
const store = createStore({
  state: {
    //取出sessionStorage中的
    doctorLoginToken: JSON.parse(sessionStorage.getItem("DOCTOR_LOGIN_TOKEN")) || "",
    chatLinkId: JSON.parse(sessionStorage.getItem("CHAT_LINK_ID")) || ""
  },
  getters: {
    doctorLoginToken: state => state.doctorLoginToken,
    chatLinkId: state => state.chatLinkId
  },
  mutations: {
    changeDoctorLoginToken(state, token) {
      state.doctorLoginToken = token;
      //设置sessionStorage中的USER_DETAIL_ID为传入的id
      sessionStorage.setItem("DOCTOR_LOGIN_TOKEN", JSON.stringify(token));
    },
    changeChatLinkId(state, id) {
      state.chatLinkId = id;
      //设置sessionStorage中的USER_DETAIL_ID为传入的id
      sessionStorage.setItem("CHAT_LINK_ID", JSON.stringify(id));
    }
  }
});
export default store;
