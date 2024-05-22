//系统请求
import request from "@/utils/request";
//管理数据字典路径
const DICTIONARY_URL = "/system/dictionary/";

export const reqDictionary = code => request.get(DICTIONARY_URL + `${code}`);

export const reqUserStatusDictionary = () => reqDictionary("000");
