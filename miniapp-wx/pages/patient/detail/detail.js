// pages/patient/detail/detail.js
import { store } from "../../../store/store";
import { createStoreBindings } from "mobx-miniprogram-bindings";
import Dialog from "@vant/weapp/dialog/dialog";
import userApi from "../../../api/userApi";
Page({
  /**
   * 页面的初始数据
   */
  data: {
    patientId: 0,
    patientInfo: {}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  getPatientInfo() {
    userApi.queryOnePatient(this.data.patientId).then(res => {
      if (res.code == 1) {
        this.setData({
          patientInfo: res.data
        });
      }
    });
  },
  //点击修改信息
  goToUpdate() {
    this.updatePatientUpdateId(this.data.patientId);
    wx.navigateTo({
      url: "/pages/patient/update/update"
    });
  },
  //点击删除
  clickDelete() {
    Dialog.confirm({
      title: "提示",
      message: "确定删除就诊人吗?"
    })
      .then(() => {
        this.delete();
      })
      .catch(() => {
        // on cancel
      });
  },
  //确认删除
  delete() {
    userApi.deletePatient(this.data.patientId).then(res => {
      if (res.code == 1) {
        wx.navigateBack({
          url: "/pages/patient/list/list"
        });
        wx.showToast({
          title: "删除成功",
          icon: "success",
          duration: 2000
        });
      }
    });
  },
  onLoad(options) {
    this.storeBindings = createStoreBindings(this, {
      store,
      fields: ["patientDetailId"],
      actions: ["updatePatientUpdateId"]
    });
    this.setData({
      patientId: store.patientDetailId
    });
    this.getPatientInfo();
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
    this.getPatientInfo();
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
