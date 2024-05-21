//系统请求
import request from "@/utils/request";
// import type {UserResponseData} from './type'
//枚举管理数据字典路径
enum API {
  DICTIONARY_URL = "/system/dictionary/"
}
export const reqDictionary = (code: Number) => request.get(API.DICTIONARY_URL + `${code}`);
