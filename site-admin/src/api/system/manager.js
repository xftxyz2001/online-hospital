//管理员请求
import request from "@/utils/request";
//管理管理员路径
const MANAGER_URL = "/system/manager";

export const managerLogin = managerLoginDto => request.post(MANAGER_URL + "/login", managerLoginDto);

export const managerQueryUsername = () => request.get(MANAGER_URL + "/queryUsername");
