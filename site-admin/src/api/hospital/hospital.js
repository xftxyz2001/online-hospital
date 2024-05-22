//医院信息接口
import request from "@/utils/request";

//医院接口地址
const HOSPITAL_URL = "/hospital/hospital";
//获取全部医院信息
export const queryHospitalInfo = () => request.get(HOSPITAL_URL);
//根据医院id获取医院信息
export const queryHospitalInfoById = id => request.get(HOSPITAL_URL + "/queryById?id=" + `${id}`);
//更改医院信息
export const updateHospital = updateHospitalDto => request.put(HOSPITAL_URL + "/update", updateHospitalDto);
