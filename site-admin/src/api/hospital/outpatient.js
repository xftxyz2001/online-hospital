//门诊信息接口
import request from "@/utils/request";

//专科接口地址
const OUTPATIENT_URL = "/hospital/outpatientInfo";
//根据医院id和专科id查询门诊
export const queryOutpatientInfoByHospitalAndSpecialistId = (hospitalId, specialistId) =>
  request.get(
    OUTPATIENT_URL +
      "/queryOutpatientInfoByHospitalAndSpecialistId?hospitalId=" +
      `${hospitalId}` +
      "&specialistId=" +
      `${specialistId}`
  );

//添加门诊
export const addOutpatient = addOutpatientDto => request.post(OUTPATIENT_URL + "/add", addOutpatientDto);

//根据id查询门诊
export const queryOutpatientById = id => request.get(OUTPATIENT_URL + "/queryById?id=" + `${id}`);

//更新门诊
export const updateOutpatient = updateOutpatientDto => request.put(OUTPATIENT_URL + "/update", updateOutpatientDto);
