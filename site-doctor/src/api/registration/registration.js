import request from "@/utils/request";

const REGISTRATION_URL = "/registration/orderInfo/web";
const REPORTINFO_UL = "/registration/reportInfo/web";
//查询所有等待中的问诊申请
export const queryRegistrationInfo = (pageNo, pageSize) =>
  request.post(REGISTRATION_URL + "/list?pageNo=" + `${pageNo}` + "&pageSize=" + `${pageSize}`);
//添加报告
export const addReport = addReportDto => request.post(REPORTINFO_UL + "/insert", addReportDto);
