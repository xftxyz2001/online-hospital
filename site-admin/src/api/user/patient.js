//就诊人信息接口
import request from "@/utils/request";

//管理就诊人信息路径
const PATIENT_URL = "/user/patient";
const PATIENT_QUERY_BY_USER_ID = "/user/patient/listByUserId";
//根据userid查询就诊人
export const queryPatientListByUserId = (pageNo, pageSize, userId) =>
  request.get(
    PATIENT_QUERY_BY_USER_ID + "?pageNo=" + `${pageNo}` + "&pageSize=" + `${pageSize}` + "&userId=" + `${userId}`
  );
//删除就诊人
export const deletePatientById = id => request.delete(PATIENT_URL + "?id=" + `${id}`);
//查询id查询就诊人
export const queryPatientInfoById = id => request.get(PATIENT_URL + "?id=" + `${id}`);
//修改就诊人信息
export const editPatientInfo = editPatientInfoDto => request.put(PATIENT_URL, editPatientInfoDto);
//根据条件分页查询就诊人
export const queryPatientInfo = (pageNo, pageSize, queryPatientInfoDto) =>
  request.post(PATIENT_URL + "/list?pageNo=" + `${pageNo}` + "&pageSize=" + `${pageSize}`, queryPatientInfoDto);
