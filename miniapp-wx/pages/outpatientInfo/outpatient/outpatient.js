// pages/outpatientInfo/outpatient/outpatient.js
import { store } from "../../../store/store";
import { createStoreBindings } from "mobx-miniprogram-bindings";
import hospitalApi from "../../../api/hospitalApi";
Page({
  /**
   * 页面的初始数据
   */
  data: {
    letterList: [],
    outpatientList: []
  },
  //获取所有门诊信息
  getOutpatientInfo() {
    hospitalApi.queryOrderLetterList(store.outpatientInfoOfHospitalId).then(res => {
      if (res.code == 1) {
        this.setData({
          letterList: res.data.letterList,
          outpatientList: res.data.outpatientList
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
      field: ["outpatientInfoOfHospitalId"],
      actions: ["updateOutpatientInfoId"]
    });
    this.getOutpatientInfo();
  },
  selectOutpatient(e) {
    console.log(e);
    this.updateOutpatientInfoId(e.target.dataset.outpatientid);
    wx.navigateTo({
      url: "/pages/outpatientInfo/detail/detail"
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
    this.getOutpatientInfo();
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
