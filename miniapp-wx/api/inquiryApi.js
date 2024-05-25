import { promiseRequest } from "../utils/service";

const app = getApp();
const inquiryUrl = `http://${app.globalData.ONLINE_HOSPITAL_GATEWAY_HOST}/inquiry`;

export default {
  // promiseRequest({
  //   method: "GET",
  //   url: app.globalData.inquiryUrl + "/app/chat-list/allUnread"
  // }
  queryAllUnread() {
    return promiseRequest({
      method: "GET",
      url: `${inquiryUrl}/app/chat-list/allUnread`
    });
  },
  // promiseRequest({
  //   method: "POST",
  //   url: app.globalData.inquiryUrl + "/app/inquiry-application/add",
  //   data: {
  //     doctorId: this.data.doctorId,
  //     hospitalId: this.data.hospitalId,
  //     outpatientId: this.data.outpatientId,
  //     patientId: this.data.patientId,
  //     image: this.data.fileList[0].url,
  //     description: this.data.description
  //   }
  // })
  addInquiryApplication(data) {
    return promiseRequest({
      method: "POST",
      url: `${inquiryUrl}/app/inquiry-application/add`,
      data
    });
  },
  // promiseRequest({
  //   method: "GET",
  //   url: app.globalData.inquiryUrl + "/app/chat-message/query-chat-message?linkId=" + store.inquiryLinkId
  // })
  queryChatMessage(linkId) {
    return promiseRequest({
      method: "GET",
      url: `${inquiryUrl}/app/chat-message/query-chat-message?linkId=${linkId}`
    });
  },
  // promiseRequest({
  //   method: "PUT",
  //   url: app.globalData.inquiryUrl + "/app/chat-list/in-window?linkId=" + store.inquiryLinkId
  // })
  inWindow(linkId) {
    return promiseRequest({
      method: "PUT",
      url: `${inquiryUrl}/app/chat-list/in-window?linkId=${linkId}`
    });
  },
  // promiseRequest({
  //   method: "PUT",
  //   url: app.globalData.inquiryUrl + "/app/chat-list/clear-unread?linkId=" + store.inquiryLinkId
  // })
  clearUnread(linkId) {
    return promiseRequest({
      method: "PUT",
      url: `${inquiryUrl}/app/chat-list/clear-unread?linkId=${linkId}`
    });
  },
  // promiseRequest({
  //   method: "POST",
  //   url: app.globalData.inquiryUrl + "/app/chat-message/send-message",
  //   data: addMessageDto
  // })
  sendMessage(data) {
    return promiseRequest({
      method: "POST",
      url: `${inquiryUrl}/app/chat-message/send-message`,
      data
    });
  },
  // promiseRequest({
  //   method: "PUT",
  //   url: app.globalData.inquiryUrl + "/app/chat-list/out-window?linkId=" + store.inquiryLinkId
  // })
  outWindow(linkId) {
    return promiseRequest({
      method: "PUT",
      url: `${inquiryUrl}/app/chat-list/out-window?linkId=${linkId}`
    });
  },
  // promiseRequest({
  //   method: "GET",
  //   url: app.globalData.inquiryUrl + "/app/inquiry-application/query-waiting-inquiry-application-detail"
  // })
  queryWaitingInquiryApplicationDetail() {
    return promiseRequest({
      method: "GET",
      url: `${inquiryUrl}/app/inquiry-application/query-waiting-inquiry-application-detail`
    });
  },
  // promiseRequest({
  //   url: app.globalData.inquiryUrl + "/app/inquiry-application/queryById?id=" + store.inquiryDetailId,
  //   methed: "GET"
  // })
  queryInquiryApplicationById(id) {
    return promiseRequest({
      url: `${inquiryUrl}/app/inquiry-application/queryById?id=${id}`,
      method: "GET"
    });
  },
  // promiseRequest({
  //   url: app.globalData.inquiryUrl + "/app/inquiry-application/queryAll",
  //   methed: "GET"
  // })
  queryAll() {
    return promiseRequest({
      url: `${inquiryUrl}/app/inquiry-application/queryAll`,
      method: "GET"
    });
  },
  // promiseRequest({
  //   method: "PUT",
  //   url: app.globalData.inquiryUrl + "/app/inquiry-application/cancel?id=" + id
  // })
  cancel(id) {
    return promiseRequest({
      method: "PUT",
      url: `${inquiryUrl}/app/inquiry-application/cancel?id=${id}`
    });
  },
  // promiseRequest({
  //   url: app.globalData.inquiryUrl + "/app/chat-list/query-chat-list",
  //   method: "GET"
  // })
  queryChatList() {
    return promiseRequest({
      url: `${inquiryUrl}/app/chat-list/query-chat-list`,
      method: "GET"
    });
  }
};
