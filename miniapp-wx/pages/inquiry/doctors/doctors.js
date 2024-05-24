// pages/inquiry/doctors/doctors.js
import { store } from "../../../store/store";
import { createStoreBindings } from "mobx-miniprogram-bindings";
import { promiseRequest } from "../../../utils/service";
Page({
  /**
   * 页面的初始数据
   */
  data: {
    hospitalId: 0,
    outpatientId: 0,
    doctorList: []
  },
  async getdoctorList() {
    const app = getApp();
    await promiseRequest({
      method: "Post",
      url: app.globalData.hospitalUrl + "/app/doctorInfo/queryInquiryDoctorList",
      data: {
        hospitalId: this.data.hospitalId,
        outpatientId: this.data.outpatientId
      }
    }).then(res => {
      if (res.code == 1) {
        this.setData({
          doctorList: res.data
        });
      }
    });
  },
  selectDoctor(e) {
    this.updateInquiryDoctorId(e.currentTarget.dataset.doctorid);
    wx.navigateTo({
      url: "/pages/inquiry/doctor/doctor"
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.storeBindings = createStoreBindings(this, {
      store,
      fields: ["inquiryOutpatientId", "inquiryHospitalId"],
      actions: ["updateInquiryDoctorId"]
    });
    this.setData({
      outpatientId: store.inquiryOutpatientId,
      hospitalId: store.inquiryHospitalId
    });
    this.getdoctorList();
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
  onUnload() {},

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {
    this.getdoctorList();
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
