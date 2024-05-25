// index.js
import Toast from "@vant/weapp/toast/toast";
import inquiryApi from "../../api/inquiryApi";
import { promiseWebsocket } from "../../utils/service";

Page({
  data: {
    unread: 0,
    showWarn: false //是否展示网络不稳定警告
  },
  hasUnread() {
    inquiryApi.queryAllUnread().then(res => {
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
    var that = this;
    promiseWebsocket().then(task => {
      task.onOpen = () => {
        //钩子函数
        console.log("open");
      };
      task.onClose = () => {
        //钩子函数
        console.log("close");
        //非正常关闭  提示网络异常并开始重新链接
        that.showWarn = true;
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
