//管理员请求
import request from "@/utils/request";
// import type {UserResponseData} from './type'
//枚举管理管理员路径
enum API {
  MANAGER_URL = "/system/manager"
}
export const managerLogin = managerLoginDto => request.post(API.MANAGER_URL + "/login", managerLoginDto);

export const managerQueryUsername = () => request.get(API.MANAGER_URL + "/queryUsername");
