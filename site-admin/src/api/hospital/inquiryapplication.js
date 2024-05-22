//医院信息接口
import request from "@/utils/request";

//医院接口地址
const INQUIRY_URL = "/inquiry/inquiry-application";
//根据条件分页查询用户
export const queryInquiryApplication = (pageNo, pageSize) =>
  request.post(INQUIRY_URL + "/list?pageNo=" + `${pageNo}` + "&pageSize=" + `${pageSize}`);
