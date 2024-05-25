// pages/inquiry/in-progress/in-progress.js
import { store } from "../../../store/store";
import { createStoreBindings } from "mobx-miniprogram-bindings";
import inquiryApi from "../../../api/inquiryApi";
Page({
  /**
   * 页面的初始数据
   */
  data: {
    message: "",
    messageVo: {}
  },

  queryMessage() {
    inquiryApi.queryChatMessage(store.inquiryLinkId).then(res => {
      if (res.code == 1) {
        this.setData({
          messageVo: res.data
        });
      }
    });
  },
  //进入窗口初始化
  inWindowInitial() {
    //设置在窗口
    inquiryApi.inWindow(store.inquiryLinkId);
    //清空未读数
    inquiryApi.clearUnread(store.inquiryLinkId);
  },
  //发送消息
  send() {
    const addMessageDto = {
      linkId: this.data.messageVo.linkId,
      inquiryApplicationId: this.data.messageVo.inquiryApplicationId,
      toUserId: this.data.messageVo.doctorId,
      content: this.data.message,
      type: 0
    };
    inquiryApi.sendMessage(addMessageDto).then(res => {
      if (res.code == 1) {
        this.setData({
          message: ""
        });
        const wsMessage = {
          linkId: this.data.messageVo.linkId,
          inquiryApplicationId: this.data.messageVo.inquiryApplicationId,
          toUserId: this.data.messageVo.doctorId,
          toUserIdentity: 1
        };
        wx.sendSocketMessage({
          data: JSON.stringify(wsMessage)
        });
        this.queryMessage();
      }
    });
  },
  //离开页面
  outWindow() {
    inquiryApi.outWindow(store.inquiryLinkId);
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    var that = this;
    this.storeBindings = createStoreBindings(this, {
      store,
      fields: ["inquiryLinkId"]
    });
    this.inWindowInitial();
    this.queryMessage();
    wx.onSocketMessage(function (res) {
      that.queryMessage();
    });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {},

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {},

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {},

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {
    this.storeBindings.destroyStoreBindings();
    this.outWindow();
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {},

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {},

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {}
});
