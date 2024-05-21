//门诊信息接口
import request from "@/utils/request";

//枚举管理门诊信息路径
enum API {
  //专科接口地址
  OUTPATIENT_URL = "/hospital/outpatientInfo"
}
//根据医院id和专科id查询门诊
export const queryOutpatientInfoByHospitalAndSpecialistId = (hospitalId, specialistId) =>
  request.get(
    API.OUTPATIENT_URL +
      "/queryOutpatientInfoByHospitalAndSpecialistId?hospitalId=" +
      `${hospitalId}` +
      "&specialistId=" +
      `${specialistId}`
  );

//添加门诊
export const addOutpatient = addOutpatientDto => request.post(API.OUTPATIENT_URL + "/add", addOutpatientDto);

//根据id查询门诊
export const queryOutpatientById = id => request.get(API.OUTPATIENT_URL + "/queryById?id=" + `${id}`);

//更新门诊
export const updateOutpatient = updateOutpatientDto => request.put(API.OUTPATIENT_URL + "/update", updateOutpatientDto);
