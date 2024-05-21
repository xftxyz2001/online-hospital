//用户信息接口
import request from "@/utils/request";

//枚举管理用户信息路径
enum API {
  USER_URL = "/user/user/"
}
//根据条件分页查询用户
export const queryUserInfo = (pageNo, pageSize, queryUserInfoDto) =>
  request.post(API.USER_URL + "list?pageNo=" + `${pageNo}` + "&pageSize=" + `${pageSize}`, queryUserInfoDto);
//删除用户(逻辑删除)
export const deleteUserById = id => request.delete(API.USER_URL + `${id}`);
//启用用户
export const enableUserById = id => request.put(API.USER_URL + "enable/" + `${id}`);
//禁用用户
export const disenableUserById = id => request.put(API.USER_URL + "disenable/" + `${id}`);
//查询单个用户信息
export const queryUserInfoById = id => request.get(API.USER_URL + `${id}`);
//修改用户信息
export const editUserInfo = editUserInfoDto => request.put(API.USER_URL, editUserInfoDto);
