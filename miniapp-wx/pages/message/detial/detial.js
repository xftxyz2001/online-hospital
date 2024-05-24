// pages/inquiry/in-progress/in-progress.js
import { promiseRequest } from "../../../utils/service";
import { store } from "../../../store/store";
import { createStoreBindings } from "mobx-miniprogram-bindings";
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
  async queryMessage() {
    const app = getApp();
    await promiseRequest({
      method: "GET",
      url: app.globalData.inquiryUrl + "/app/chat-message/query-chat-message?linkId=" + store.inquiryLinkId
    }).then(res => {
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
    const app = getApp();
    const that = this;
    wx.uploadFile({
      url: app.globalData.systemUrl + "/upload", // 仅为示例，非真实的接口地址
      filePath: file.url,
      name: "image",
      formData: { user: "test" },
      header: {
        token: wx.getStorageSync("token")
      },
      success(res) {
        // 上传完成需要更新 fileList
        let result = JSON.parse(res.data);
        let url = result.data;
        that.sendPicture(url);
      }
    });
  },
  //进入窗口初始化
  async inWindowInitial() {
    const app = getApp();
    //设置在窗口
    await promiseRequest({
      method: "PUT",
      url: app.globalData.inquiryUrl + "/app/chat-list/in-window?linkId=" + store.inquiryLinkId
    });
    //清空未读数
    await promiseRequest({
      method: "PUT",
      url: app.globalData.inquiryUrl + "/app/chat-list/clear-unread?linkId=" + store.inquiryLinkId
    });
  },
  //发送照片
  async sendPicture(pictureUrl) {
    const addMessageDto = {
      linkId: this.data.messageVo.linkId,
      inquiryApplicationId: this.data.messageVo.inquiryApplicationId,
      toUserId: this.data.messageVo.doctorId,
      content: pictureUrl,
      type: 1
    };
    const app = getApp();
    await promiseRequest({
      method: "POST",
      url: app.globalData.inquiryUrl + "/app/chat-message/send-message",
      data: addMessageDto
    }).then(res => {
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
  async send() {
    const addMessageDto = {
      linkId: this.data.messageVo.linkId,
      inquiryApplicationId: this.data.messageVo.inquiryApplicationId,
      toUserId: this.data.messageVo.doctorId,
      content: this.data.message,
      type: 0
    };
    const app = getApp();
    await promiseRequest({
      method: "POST",
      url: app.globalData.inquiryUrl + "/app/chat-message/send-message",
      data: addMessageDto
    }).then(res => {
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
  async outWindow() {
    const app = getApp();
    await promiseRequest({
      method: "PUT",
      url: app.globalData.inquiryUrl + "/app/chat-list/out-window?linkId=" + store.inquiryLinkId
    });
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
