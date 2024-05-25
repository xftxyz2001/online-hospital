// pages/registrationrecord/list/list.js
import { store } from "../../../store/store";
import { createStoreBindings } from "mobx-miniprogram-bindings";
import registrationApi from "../../../api/registrationApi";
Page({
  /**
   * 页面的初始数据
   */
  data: {
    orderList: []
  },

  gotoDetail(e) {
    this.updateRegistrationRecordDetailId(e.currentTarget.dataset.id);
    wx.navigateTo({
      url: "/pages/registrationrecord/detail/detail"
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.getAllOrder();
    this.storeBindings = createStoreBindings(this, {
      store,
      actions: ["updateRegistrationRecordDetailId"]
    });
  },
  //获取所有的订单信息
  getAllOrder() {
    registrationApi.queryAllOrder().then(res => {
      if (res.code == 1) {
        this.setData({
          orderList: res.data
        });
      }
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
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {
    this.getAllOrder();
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
