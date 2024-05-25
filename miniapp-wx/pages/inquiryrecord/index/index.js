// pages/inquiryrecord/index/index.js
import { store } from "../../../store/store";
import { createStoreBindings } from "mobx-miniprogram-bindings";
import Dialog from "@vant/weapp/dialog/dialog";
import inquiryApi from "../../../api/inquiryApi";
Page({
  /**
   * 页面的初始数据
   */
  data: {
    inquiryList: []
  },

  //查询所有问诊记录
  queryInquiryRecords() {
    inquiryApi.queryAll().then(res => {
      if (res.code == 1) {
        this.setData({
          inquiryList: res.data
        });
      }
    });
  },
  toDetail(e) {
    this.updateInquiryDetailId(e.target.dataset.id);
    wx.navigateTo({
      url: "/pages/inquiryrecord/detail/detail"
    });
  },
  cancel(e) {
    Dialog.confirm({
      title: "提示",
      message: "确定要取消吗？"
    })
      .then(() => {
        this.cancelInquiry(e.target.dataset.id);
      })
      .catch(() => {
        // on cancel
      });
  },
  cancelInquiry(id) {
    inquiryApi.cancel(id).then(res => {
      if (res.code == 1) {
        wx.showToast({
          title: "取消成功",
          icon: "success",
          duration: 2000
        });
        this.queryInquiryRecords();
      } else {
        wx.showToast({
          title: "操作失败",
          icon: "error",
          duration: 2000
        });
      }
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.storeBindings = createStoreBindings(this, {
      store,
      actions: ["updateInquiryDetailId"]
    });
    this.queryInquiryRecords();
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
    this.queryInquiryRecords();
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
