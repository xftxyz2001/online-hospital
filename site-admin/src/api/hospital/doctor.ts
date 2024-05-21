//医生信息接口
import request from "@/utils/request";

//枚举管理医生信息路径
enum API {
  //医生接口地址
  DOCTOR_URL = "/hospital/doctorInfo"
}
//根据医院和门诊id获取医生信息
export const queryDoctorInfoByHospitalAndOutpatientId = (hospitalId, outpatientId) =>
  request.get(
    API.DOCTOR_URL +
      "/queryByHospitalAndOutpatientId?hospitalId=" +
      `${hospitalId}` +
      "&outpatientId=" +
      `${outpatientId}`
  );
//根据id查询医生
export const queryDoctorById = id => request.get(API.DOCTOR_URL + "/queryById?doctorId=" + `${id}`);
//添加医生
export const addDoctor = addDoctorDto => request.post(API.DOCTOR_URL + "/add", addDoctorDto);
//修改医生
export const updateDoctor = updateDoctorDto => request.put(API.DOCTOR_URL + "/update", updateDoctorDto);
