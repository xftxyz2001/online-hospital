import request from "@/utils/request";

const INQUIRY_INQUIRY_APPLICATION_URL = "/inquiry/web/inquiry-application";
//查询所有等待中的问诊申请
export const inquiryWaitingApplicationList = () => request.get(INQUIRY_INQUIRY_APPLICATION_URL + "/query-waiting-list");
//查询等待中的问诊申请详情
export const inquiryWaitingApplicationDetail = id =>
  request.get(INQUIRY_INQUIRY_APPLICATION_URL + "/query-waiting-detail?id=" + `${id}`);
//接诊
export const receptById = id => request.get(INQUIRY_INQUIRY_APPLICATION_URL + "/reception?id=" + `${id}`);
//查询所有已完成问诊
export const inquiryAllFinished = () => request.get(INQUIRY_INQUIRY_APPLICATION_URL + "/allFinished");
//查询单个已完成问诊
export const inquiryFinishedById = id =>
  request.get(INQUIRY_INQUIRY_APPLICATION_URL + "/queryFinishedById?id=" + `${id}`);
