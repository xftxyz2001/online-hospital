//定义用户数据管理类型

//统一返回类型
export interface ResponseData {
  code: number;
  msg: string;
}

export interface UserStatusData {
  id: number;
  name: string;
  code: string;
  itemValues: UserStatusDataItemValues[];
}
//用户状态数据字典
export interface UserStatusDataItemValues {
  code: string;
  codeInt: number;
  disc: string;
}
