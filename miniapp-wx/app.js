// app.js
// import {promisifyAll} from 'miniprogram-api-promise'
const wxp = (wx.p = {});

const ONLINE_HOSPITAL_GATEWAY_HOST = "localhost";

// promisifyAll(wx,wxp)
App({
  onLaunch: function () {
    // 调用
  },
  onPullDownRefresh: function () {
    this.onLaunch();
  },
  onRefresh: function () {
    //导航条加载动画
    wx.showNavigationBarLoading();
    setTimeout(function () {
      wx.hideNavigationBarLoading();
      //停止下拉刷新
      wx.stopPullDownRefresh();
    }, 2000);
  },

  globalData: {
    hospitalUrl: `http://${ONLINE_HOSPITAL_GATEWAY_HOST}/hospital`,
    registrationUrl: `http://${ONLINE_HOSPITAL_GATEWAY_HOST}/registration`,
    userUrl: `http://${ONLINE_HOSPITAL_GATEWAY_HOST}/user`,
    inquiryUrl: `http://${ONLINE_HOSPITAL_GATEWAY_HOST}/inquiry`,
    wsInquiryUrl: `ws://${ONLINE_HOSPITAL_GATEWAY_HOST}/inquiry/chat`,
    systemUrl: `http://${ONLINE_HOSPITAL_GATEWAY_HOST}/system`,
  },
});
