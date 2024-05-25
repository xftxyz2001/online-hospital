// pages/inquiry/in-progress/in-progress.js
import { store } from "../../../store/store";
import { createStoreBindings } from "mobx-miniprogram-bindings";
import inquiryApi from "../../../api/inquiryApi";
import systemApi from "../../../api/systemApi";
Page({
  /**
   * 页面的初始数据
   */
  data: {
    message: "",
    messageVo: {},
    show: false,
    canSend: false,
    inputShow: true,
    fileList: [],
    toView: "",
    scrollTop: 0 // 内容底部与顶部的距离
  },
  toViewBottomFun: function () {
    // 设置屏幕自动滚动到最后一条消息处
    let that = this;
    wx.createSelectorQuery()
      .select("#viewCommunicationBody")
      .boundingClientRect(function (rect) {
        wx.pageScrollTo({
          scrollTop: rect.height,
          duration: 100 // 滑动速度
        });
        that.setData({
          scrollTop: rect.height - that.data.scrollTop
        });
      })
      .exec();
  },

  previewImage: function (e) {
    wx.previewImage({
      urls: [e.target.dataset.url]
    });
  },
  queryMessage() {
    inquiryApi.queryChatMessage(store.inquiryLinkId).then(res => {
      if (res.code == 1) {
        this.setData({
          messageVo: res.data
        });
        this.setData({
          toView: `item${this.data.messageVo.messageVos.length - 1}`
        });
        this.toViewBottomFun();
        console.log(this.data.toView);

        if (res.data.remainNumber == 0) {
          this.setData({
            canSend: true
          });
        }
      }
    });
  },
  afterRead(event) {
    const { file } = event.detail;
    // 当设置 mutiple 为 true 时, file 为数组格式，否则为对象格式
    const that = this;
    systemApi.uploadImage(file.url).then(res => {
      if (res.code == 1) {
        that.sendPicture(res.data);
      }
    });
  },
  //进入窗口初始化
  inWindowInitial() {
    inquiryApi.inWindow(store.inquiryLinkId);
    inquiryApi.clearUnread(store.inquiryLinkId);
  },
  //发送照片
  sendPicture(pictureUrl) {
    const addMessageDto = {
      linkId: this.data.messageVo.linkId,
      inquiryApplicationId: this.data.messageVo.inquiryApplicationId,
      toUserId: this.data.messageVo.doctorId,
      content: pictureUrl,
      type: 1
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
  //点击详情
  detail() {
    this.setData({
      show: true
    });
  },
  onClose() {
    this.setData({ show: false });
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    var that = this;
    this.storeBindings = createStoreBindings(this, {
      store,
      fields: ["inquiryLinkId", "messageShowInput"]
    });
    this.setData({
      inputShow: store.messageShowInput
    });
    this.inWindowInitial();
    this.queryMessage();
    wx.onSocketMessage(function (res) {
      console.log(1);
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
  onPullDownRefresh() {
    this.inWindowInitial();
    this.queryMessage();
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {},

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {}
});
