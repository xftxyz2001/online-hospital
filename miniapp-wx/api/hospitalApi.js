import { promiseRequest } from "../utils/service";

const app = getApp();
const hospitalUrl = `http://${app.globalData.ONLINE_HOSPITAL_GATEWAY_HOST}/hospital`;

export default {
  // promiseRequest({
  //   url: app.globalData.hospitalUrl + "/app/doctorInfo/queryById?doctorId=" + store.doctorInfoId,
  //   method: "GET"
  // })
  queryDoctorInfoByDoctorId(doctorId) {
    return promiseRequest({
      url: `${hospitalUrl}/app/doctorInfo/queryById?doctorId=${doctorId}`,
      method: "GET"
    });
  },
  // promiseRequest({
  //   url:
  //     app.globalData.hospitalUrl +
  //     "/doctorInfo/queryPage?pageNo=" +
  //     this.data.currentPage +
  //     "&pageSize=" +
  //     this.data.pageSize,
  //   method: "POST",
  //   data: {
  //     name: this.data.name
  //   }
  // })
  queryDoctorInfoPage(pageNo, pageSize, name) {
    return promiseRequest({
      url: `${hospitalUrl}/doctorInfo/queryPage?pageNo=${pageNo}&pageSize=${pageSize}`,
      method: "POST",
      data: {
        name: name
      }
    });
  },
  // promiseRequest({
  //   url: app.globalData.hospitalUrl + "/hospital/queryById?id=" + store.hospitalInfoId,
  //   method: "GET"
  // })
  queryHospitalInfoByHospitalId(hospitalId) {
    return promiseRequest({
      url: `${hospitalUrl}/hospital/queryById?id=${hospitalId}`,
      method: "GET"
    });
  },
  // promiseRequest({
  //   method: "GET",
  //   url: app.globalData.hospitalUrl + "/hospital"
  // })
  queryHospitalInfo() {
    return promiseRequest({
      method: "GET",
      url: `${hospitalUrl}/hospital`
    });
  },
  // promiseRequest({
  //   url: app.globalData.hospitalUrl + "/outpatientInfo/queryById?id=" + this.data.outpatientId,
  //   method: "GET"
  // })
  queryOutpatientInfoById(outpatientId) {
    return promiseRequest({
      url: `${hospitalUrl}/outpatientInfo/queryById?id=${outpatientId}`,
      method: "GET"
    });
  },
  // promiseRequest({
  //   method: "Post",
  //   url: app.globalData.hospitalUrl + "/app/doctorInfo/queryInquiryDoctorList",
  //   data: {
  //     hospitalId: this.data.hospitalId,
  //     outpatientId: this.data.outpatientId
  //   }
  // })
  queryInquiryDoctorList(hospitalId, outpatientId) {
    return promiseRequest({
      method: "POST",
      url: `${hospitalUrl}/app/doctorInfo/queryInquiryDoctorList`,
      data: {
        hospitalId,
        outpatientId
      }
    });
  },
  // promiseRequest({
  //   method: "GET",
  //   url: app.globalData.hospitalUrl + "/specialist/queryAppSpecialistTree?hospitalId=" + this.data.hospitalId
  // })
  queryAppSpecialistTree(hospitalId) {
    return promiseRequest({
      method: "GET",
      url: `${hospitalUrl}/specialist/queryAppSpecialistTree?hospitalId=${hospitalId}`
    });
  },
  // promiseRequest({
  //   method: "GET",
  //   url:
  //     app.globalData.hospitalUrl + "/outpatientInfo/order-letter-list?hospitalId=" + store.outpatientInfoOfHospitalId
  // })
  queryOrderLetterList(hospitalId) {
    return promiseRequest({
      method: "GET",
      url: `${hospitalUrl}/outpatientInfo/order-letter-list?hospitalId=${hospitalId}`
    });
  }
};
