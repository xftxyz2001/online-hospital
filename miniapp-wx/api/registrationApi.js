import { promiseRequest } from "../utils/service";

const app = getApp();
const registrationUrl = `http://${app.globalData.ONLINE_HOSPITAL_GATEWAY_HOST}/registration`;

export default {
  // promiseRequest({
  //   method: "POST",
  //   url: app.globalData.registrationUrl + "/schedule/app",
  //   data: {
  //     hospitalId: this.data.registrationHospitalId,
  //     outpatientId: this.data.registrationOutpatientId,
  //     workDate: this.data.selectedDate
  //   }
  // })
  querySchedule(data) {
    return promiseRequest({
      method: "POST",
      url: `${registrationUrl}/schedule/app`,
      data
    });
  },
  // promiseRequest({
  //   method: "POST",
  //   url: app.globalData.registrationUrl + "/schedule/app/doctor",
  //   data: {
  //     hospitalId: this.data.hospitalId,
  //     outpatientId: this.data.OutpatientId,
  //     workDate: this.data.date,
  //     workHalf: this.data.half,
  //     doctorId: this.data.doctorId
  //   }
  // })
  queryDoctorSchedule(data) {
    return promiseRequest({
      method: "POST",
      url: `${registrationUrl}/schedule/app/doctor`,
      data
    });
  },
  // promiseRequest({
  //   method: "POST",
  //   url: app.globalData.registrationUrl + "/orderInfo/app/add",
  //   data: {
  //     amount: 0,
  //     doctorId: this.data.doctorId,
  //     hospitalId: this.data.hospitalId,
  //     outpatientId: this.data.OutpatientId,
  //     patientId: this.data.patientId,
  //     reserveDate: this.data.date,
  //     reserveHalf: this.data.half,
  //     reserveTime: this.data.time,
  //     scheduleId: this.data.scheduleId
  //   }
  // })
  addOrderInfo(data) {
    return promiseRequest({
      method: "POST",
      url: `${registrationUrl}/orderInfo/app/add`,
      data
    });
  },
  // promiseRequest({
  //   method: "GET",
  //   url: app.globalData.registrationUrl + "/orderInfo/app/queryOne?id=" + this.data.orderDetailId
  // })
  queryOneOrder(orderDetailId) {
    return promiseRequest({
      method: "GET",
      url: `${registrationUrl}/orderInfo/app/queryOne?id=${orderDetailId}`
    });
  },
  // promiseRequest({
  //   url: app.globalData.registrationUrl + "/orderInfo/app/update",
  //   method: "PUT",
  //   data: {
  //     id: this.data.orderDetailId,
  //     orderStatus: 3
  //   }
  // })
  cancelRegistration(data) {
    return promiseRequest({
      url: `${registrationUrl}/orderInfo/app/update`,
      method: "PUT",
      data
    });
  },
  // promiseRequest({
  //   method: "GET",
  //   url: app.globalData.registrationUrl + "/orderInfo/app/queryAll"
  // })
  queryAllOrder() {
    return promiseRequest({
      method: "GET",
      url: `${registrationUrl}/orderInfo/app/queryAll`
    });
  },
  // promiseRequest({
  //   method: "GET",
  //   url: app.globalData.registrationUrl + "/reportInfo/app/queryAll"
  // })
  queryAllReport() {
    return promiseRequest({
      method: "GET",
      url: `${registrationUrl}/reportInfo/app/queryAll`
    });
  }
};
