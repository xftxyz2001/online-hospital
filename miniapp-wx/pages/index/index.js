// index.js
import { promiseRequest } from "../../utils/service";

import WebsocketHeartbeat from "websocket-heartbeat-miniprogram";
import Toast from "@vant/weapp/toast/toast";

Page({
  data: {
    unread: 0,
    token: "",
    reTimenum: 2000, //3秒一次重新链接
    reTimer: null, //重新链接的对象
    heartTimer: null, //心跳定时器对象
    heartTime: 2000, //心跳时间
    closeTimer: null, //自动关闭连接定时器对象
    closeTime: 3000, //自动关闭时间
    showWarn: false //是否展示网络不稳定警告
  },

  async hasUnread() {
    const app = getApp();
    await promiseRequest({
      method: "GET",
      url: app.globalData.inquiryUrl + "/app/chat-list/allUnread"
    }).then(res => {
      if (res.code == 1) {
        this.setData({
          unread: res.data.unread
        });
        if (res.data.unread != 0)
          wx.setTabBarBadge({
            //tabbar右上角添加文本
            index: 1,
            text: res.data.unread + ""
          });
      }
    });
  },
  onShow() {
    this.hasUnread();
  },
  onLoad(options) {
    this.hasUnread();
    this.getHeartConnect();
  },
  toBill() {
    Toast("功能暂不开放");
  },
  getHeartConnect() {
    const app = getApp();
    var that = this;
    wx.getStorage({
      key: "token",
      success: function (res) {
        console.log(res.data);
        WebsocketHeartbeat({
          miniprogram: wx,
          connectSocketParams: {
            url: `${app.globalData.wsInquiryUrl}/${res.data}`
          },
          pingTimeout: that.heartTime,
          pongTimeout: that.closeTime,
          reconnectTimeout: that.reTimenum, //多少秒一次重新链接
          pingMsg: "heart"
        }).then(task => {
          that.wxHeadObj = task;
          task.onOpen = () => {
            //钩子函数
            console.log("open");
          };
          task.onClose = () => {
            //钩子函数
            console.log("close");
            //非正常关闭  提示网络异常并开始重新链接
            that.showWarn = true;
            //清除物资倒计时
            clearInterval(that.countTimer);
          };
          task.onError = e => {
            //钩子函数
            console.log("onError：", e);
          };
          task.onMessage = msg => {
            //钩子函数
            if (that.showWarn) {
              that.hasUnread();
            }
            that.showWarn = false;
            that.hasUnread();
          };
          task.onReconnect = () => {
            //钩子函数
            console.log("reconnect...");
          };
          task.socketTask.onOpen(data => {
            //原生实例注册函数，重连后丢失
            console.log("socketTask open");
          });
          task.socketTask.onMessage(data => {
            //原生实例注册函数，重连后丢失
            console.log("socketTask data");
          });
        });
      }
    });
  },

  toRegistration() {
    wx.navigateTo({
      url: "/pages/registration/index/index"
    });
  },
  toInquiry() {
    wx.navigateTo({
      url: "/pages/inquiry/index/index"
    });
  },
  toHospitalInfo() {
    wx.navigateTo({
      url: "/pages/hospitalInfo/index/index"
    });
  },
  toOutpatientInfo() {
    wx.navigateTo({
      url: "/pages/outpatientInfo/hospital/hospital"
    });
  },
  toDoctorInfo() {
    wx.navigateTo({
      url: "/pages/doctorInfo/index/index"
    });
  },
  toReport() {
    wx.navigateTo({
      url: "/pages/report/list/list"
    });
  }
});
