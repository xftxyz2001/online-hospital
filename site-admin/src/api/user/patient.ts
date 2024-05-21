//就诊人信息接口
import request from "@/utils/request";

//枚举管理就诊人信息路径
enum API {
  PATIENT_URL = "/user/patient",
  PATIENT_QUERY_BY_USER_ID = "/user/patient/listByUserId"
}
//根据userid查询就诊人
export const queryPatientListByUserId = (pageNo, pageSize, userId) =>
  request.get(
    API.PATIENT_QUERY_BY_USER_ID + "?pageNo=" + `${pageNo}` + "&pageSize=" + `${pageSize}` + "&userId=" + `${userId}`
  );
//删除就诊人
export const deletePatientById = id => request.delete(API.PATIENT_URL + "?id=" + `${id}`);
//查询id查询就诊人
export const queryPatientInfoById = id => request.get(API.PATIENT_URL + "?id=" + `${id}`);
//修改就诊人信息
export const editPatientInfo = editPatientInfoDto => request.put(API.PATIENT_URL, editPatientInfoDto);
//根据条件分页查询就诊人
export const queryPatientInfo = (pageNo, pageSize, queryPatientInfoDto) =>
  request.post(API.PATIENT_URL + "/list?pageNo=" + `${pageNo}` + "&pageSize=" + `${pageSize}`, queryPatientInfoDto);
