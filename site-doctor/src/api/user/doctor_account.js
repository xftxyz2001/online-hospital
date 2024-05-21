//系统请求
import request from "@/utils/request";
// import type {UserResponseData} from './type'
//枚举管理数据字典路径

const USER_DOCTOR_ACCOUNT_URL = "/hospital";

export const doctorLogin = doctorLoginDto =>
  request.post(USER_DOCTOR_ACCOUNT_URL + "/doctorInfo/web/login", doctorLoginDto);
