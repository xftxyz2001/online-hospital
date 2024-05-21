//医院信息接口
import request from "@/utils/request";

//枚举管理医院信息路径
enum API {
  //医院接口地址
  INQUIRY_URL = "/inquiry/inquiry-application"
}
//根据条件分页查询用户
export const queryInquiryApplication = (pageNo, pageSize) =>
  request.post(API.INQUIRY_URL + "/list?pageNo=" + `${pageNo}` + "&pageSize=" + `${pageSize}`);
