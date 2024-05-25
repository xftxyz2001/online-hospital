// pages/patient/list/list.js
import { store } from "../../../store/store";
import { createStoreBindings } from "mobx-miniprogram-bindings";
import userApi from "../../../api/userApi";
Page({
  /**
   * 页面的初始数据
   */
  data: {
    patientList: []
  },
  addPatient() {
    wx.navigateTo({
      url: "/pages/patient/add/add"
    });
  },
  queryAllPatient() {
    userApi.queryAllPatient().then(res => {
      if (res.code == 1) {
        this.setData({
          patientList: res.data
        });
      }
    });
  },
  patientDetail(e) {
    this.updatePatientDetailId(e.currentTarget.dataset.id);
    wx.navigateTo({
      url: "/pages/patient/detail/detail"
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.storeBindings = createStoreBindings(this, {
      store,
      fields: ["registrationOutpatientId"],
      actions: ["updatePatientDetailId"]
    });
    this.queryAllPatient();
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
    this.queryAllPatient();
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
