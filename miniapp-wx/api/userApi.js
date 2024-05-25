import { promiseRequest } from "../utils/service";

const app = getApp();
const userUrl = `http://${app.globalData.ONLINE_HOSPITAL_GATEWAY_HOST}/user`;

export default {
  // promiseRequest({
  //   method: "GET",
  //   url: app.globalData.userUrl + "/app/patient/queryAll"
  // })
  queryAllPatient() {
    return promiseRequest({
      method: "GET",
      url: `${userUrl}/app/patient/queryAll`
    });
  },
  // promiseRequest({
  //   method: "PUT",
  //   url: app.globalData.userUrl + "/app/user/updateUserInfo",
  //   data: {
  //     username: this.data.userName,
  //     avatar: this.data.avatarUrl
  //   }
  // })
  updateUserInfo(data) {
    return promiseRequest({
      method: "PUT",
      url: `${userUrl}/app/user/updateUserInfo`,
      data
    });
  },
  // promiseRequest({
  //   method: "POST",
  //   url: app.globalData.userUrl + "/app/user/login",
  //   data: {
  //     code: this.data.code
  //   }
  // })
  login(code) {
    return promiseRequest({
      method: "POST",
      url: `${userUrl}/app/user/login`,
      data: {
        code
      }
    });
  },
  // promiseRequest({
  //   method: "POST",
  //   url: app.globalData.userUrl + "/app/patient/add",
  //   data: {
  //     name: this.data.name,
  //     certificatesType: this.data.certificatesType,
  //     certificatesNo: this.data.certificatesNo,
  //     sex: this.data.sex,
  //     birthdate: this.data.formatBirthDate,
  //     phone: this.data.phone,
  //     isMarry: this.data.isMarry,
  //     provinceName: this.data.provinceName,
  //     cityName: this.data.cityName,
  //     districtName: this.data.districtName,
  //     address: this.data.areaDetail,
  //     areaCode: this.data.areacode
  //   }
  // })
  addPatient(data) {
    return promiseRequest({
      method: "POST",
      url: `${userUrl}/app/patient/add`,
      data
    });
  },
  // promiseRequest({
  //   method: "GET",
  //   url: app.globalData.userUrl + "/app/patient/queryOne?patientId=" + this.data.patientId
  // })
  queryOnePatient(patientId) {
    return promiseRequest({
      method: "GET",
      url: `${userUrl}/app/patient/queryOne?patientId=${patientId}`
    });
  },
  // promiseRequest({
  //   method: "DELETE",
  //   url: app.globalData.userUrl + "/app/patient/delete?patientId=" + this.data.patientId
  // })
  deletePatient(patientId) {
    return promiseRequest({
      method: "DELETE",
      url: `${userUrl}/app/patient/delete?patientId=${patientId}`
    });
  },
  // promiseRequest({
  //   method: "PUT",
  //   url: app.globalData.userUrl + "/app/patient/update",
  //   data: {
  //     id: this.data.patientId,
  //     phone: this.data.newPhone,
  //     provinceName: this.data.newProvinceName,
  //     cityName: this.data.newCityName,
  //     districtName: this.data.newDistrictName,
  //     areaCode: this.data.newAreaCode,
  //     address: this.data.newAreaDetail
  //   }
  // })
  updatePatient(data) {
    return promiseRequest({
      method: "PUT",
      url: `${userUrl}/app/patient/update`,
      data
    });
  },
  // promiseRequest({
  //   method: "GET",
  //   url: app.globalData.userUrl + "/app/user/queryUserInfo"
  // })
  queryUserInfo() {
    return promiseRequest({
      method: "GET",
      url: `${userUrl}/app/user/queryUserInfo`
    });
  }
};
