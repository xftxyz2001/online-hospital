//医院信息接口
import request from "@/utils/request";

//枚举管理医院信息路径
enum API {
  //医院接口地址
  HOSPITAL_URL = "/hospital/hospital"
}
//获取全部医院信息
export const queryHospitalInfo = () => request.get(API.HOSPITAL_URL);
//根据医院id获取医院信息
export const queryHospitalInfoById = id => request.get(API.HOSPITAL_URL + "/queryById?id=" + `${id}`);
//更改医院信息
export const updateHospital = updateHospitalDto => request.put(API.HOSPITAL_URL + "/update", updateHospitalDto);
