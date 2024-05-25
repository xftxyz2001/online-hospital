// pages/inquiryrecord/detail/detail.js
import { store } from "../../../store/store";
import { createStoreBindings } from "mobx-miniprogram-bindings";
import inquiryApi from "../../../api/inquiryApi";
Page({
  /**
   * 页面的初始数据
   */
  data: {
    inquiryInfo: {},
    show: false
  },
  previewImage: function (e) {
    wx.previewImage({
      urls: [e.target.dataset.url]
    });
  },
  //查询单个问诊记录
  queryInquiryRecord() {
    inquiryApi.queryInquiryApplicationById(store.inquiryDetailId).then(res => {
      if (res.code == 1) {
        this.setData({
          inquiryInfo: res.data
        });
      }
    });
  },
  descriptionDetail() {
    this.setData({
      show: true
    });
  },
  onClose() {
    this.setData({ show: false });
  },
  toMessageDetail() {
    if (this.data.inquiryInfo.status == 1) {
      //进行中
      this.updateInquiryLinkId(this.data.inquiryInfo.linkId);
      this.updateMessageShowInput(true);
      wx.navigateTo({
        url: "/pages/message/detial/detial"
      });
    } else if (this.data.inquiryInfo.status == 2) {
      this.updateInquiryLinkId(this.data.inquiryInfo.linkId);
      this.updateMessageShowInput(false);
      wx.navigateTo({
        url: "/pages/message/detial/detial"
      });
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.storeBindings = createStoreBindings(this, {
      store,
      actions: ["updateInquiryLinkId", "updateMessageShowInput"],
      field: ["inquiryDetailId"]
    });
    this.queryInquiryRecord();
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
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {
    this.queryInquiryRecord();
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
