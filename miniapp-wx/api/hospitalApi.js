import { promiseRequest } from "../utils/service";

const app = getApp();
const hospitalUrl = `http://${app.globalData.ONLINE_HOSPITAL_GATEWAY_HOST}/hospital`;

export default {
  queryDoctorInfoByDoctorId(doctorId) {
    return promiseRequest({
      url: `${hospitalUrl}/app/doctorInfo/queryById?doctorId=${doctorId}`,
      method: "GET"
    });
  }
};
