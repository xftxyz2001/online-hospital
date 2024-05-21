//排班信息接口
import request from "@/utils/request";

//枚举管理排班信息路径
enum API {
  //排班接口地址
  SPECIALIST_URL = "/hospital/specialist",
  REGISTRATION_URL = "/registration"
}
//根据医院id和专科id查询树状门诊
export const querySpecialistTree = hospitalId =>
  request.get(API.SPECIALIST_URL + "/querySpecialistTree?hospitalId=" + `${hospitalId}`);
//根据医院id和门诊id和日期查询排班信息
export const querySchedule = queryScheduleDto => request.post(API.REGISTRATION_URL + "/schedule", queryScheduleDto);
//新增排班
export const addSchedule = addScheduleDto => request.post(API.REGISTRATION_URL + "/schedule/insert", addScheduleDto);
//根据id删除排班
export const deleteScheduleById = scheduleId =>
  request.delete(API.REGISTRATION_URL + "/schedule/delete?scheduleId=" + `${scheduleId}`);
//查询所有挂号
export const queryRegistrationInfo = (pageNo, pageSize) =>
  request.post(API.REGISTRATION_URL + "/orderInfo/list?pageNo=" + `${pageNo}` + "&pageSize=" + `${pageSize}`);
