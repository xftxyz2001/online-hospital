import { createStore } from "vuex";

// 创建一个新的 store 实例
const store = createStore({
  state: {
    //取出sessionStorage中的USER_DETAIL_ID
    userDetailId: JSON.parse(sessionStorage.getItem("USER_DETAIL_ID")) || 0,
    patientDetailId: JSON.parse(sessionStorage.getItem("PATIENT_DETAIL_ID")) || 0,
    hospitalDetailId: JSON.parse(sessionStorage.getItem("HOSPITAL_DETAIL_ID")) || 0,
    specialistDetailId: JSON.parse(sessionStorage.getItem("SPECIALIST_DETAIL_ID")) || 0,
    outpatientDetailId: JSON.parse(sessionStorage.getItem("OUTPATIENT_DETAIL_ID")) || 0,
    doctorDetailId: JSON.parse(sessionStorage.getItem("DOCTOR_DETAIL_ID")) || 0,
    scheduleHospitalId: JSON.parse(sessionStorage.getItem("SCHEDULE_HOSPITAL_ID")) || 0,
    managerLoginToken: JSON.parse(sessionStorage.getItem("MANAGER_LOGIN_TOKEN")) || ""
  },
  getters: {
    //userDetailId
    userDetailId: state => state.userDetailId,
    patientDetailId: state => state.patientDetailId,
    hospitalDetailId: state => state.hospitalDetailId,
    specialistDetailId: state => state.specialistDetailId,
    outpatientDetailId: state => state.outpatientDetailId,
    doctorDetailId: state => state.doctorDetailId,
    scheduleHospitalId: state => state.scheduleHospitalId,
    managerLoginToken: state => state.managerLoginToken
  },
  mutations: {
    changeUserDetailId(state, id) {
      state.userDetailId = id;
      //设置sessionStorage中的USER_DETAIL_ID为传入的id
      sessionStorage.setItem("USER_DETAIL_ID", JSON.stringify(id));
    },
    changePatientDetailId(state, id) {
      state.patientDetailId = id;
      //设置sessionStorage中的USER_DETAIL_ID为传入的id
      sessionStorage.setItem("PATIENT_DETAIL_ID", JSON.stringify(id));
    },
    changeHospitalDetailId(state, id) {
      state.hospitalDetailId = id;
      //设置sessionStorage中的USER_DETAIL_ID为传入的id
      sessionStorage.setItem("HOSPITAL_DETAIL_ID", JSON.stringify(id));
    },
    changeSpecialistDetailId(state, id) {
      state.specialistDetailId = id;
      //设置sessionStorage中的USER_DETAIL_ID为传入的id
      sessionStorage.setItem("SPECIALIST_DETAIL_ID", JSON.stringify(id));
    },
    changeOutpatientDetailId(state, id) {
      state.outpatientDetailId = id;
      //设置sessionStorage中的USER_DETAIL_ID为传入的id
      sessionStorage.setItem("OUTPATIENT_DETAIL_ID", JSON.stringify(id));
    },
    changeDoctorDetailId(state, id) {
      state.doctorDetailId = id;
      //设置sessionStorage中的USER_DETAIL_ID为传入的id
      sessionStorage.setItem("DOCTOR_DETAIL_ID", JSON.stringify(id));
    },
    changeScheduleHospitalId(state, id) {
      state.scheduleHospitalId = id;
      //设置sessionStorage中的USER_DETAIL_ID为传入的id
      sessionStorage.setItem("SCHEDULE_HOSPITAL_ID", JSON.stringify(id));
    },
    changeManagerLoginToken(state, token) {
      state.managerLoginToken = token;
      //设置sessionStorage中的USER_DETAIL_ID为传入的id
      sessionStorage.setItem("MANAGER_LOGIN_TOKEN", JSON.stringify(token));
    }
  }
});
export default store;
