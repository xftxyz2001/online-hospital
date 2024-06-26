//专科信息接口
import request from "@/utils/request";
import { Apple } from "@element-plus/icons-vue";

//专科接口地址
const SPECIALIST_URL = "/hospital/specialist";
//根据医院id获取专科信息
export const querySpecialInfoByHospitalId = id =>
  request.get(SPECIALIST_URL + "/querySpecialistByHospitalId?id=" + `${id}`);
//根据专科id获取专科信息
export const querySpecialistInfoById = id => request.get(SPECIALIST_URL + "/queryById?id=" + `${id}`);
//添加专科
export const addSpecialist = addSpecialistDto => request.post(SPECIALIST_URL + "/add", addSpecialistDto);
//修改专科
export const updateSpecialist = updateSpecialistDto => request.put(SPECIALIST_URL + "/update", updateSpecialistDto);
