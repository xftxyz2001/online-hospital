// pages/doctorInfo/index/index.js
import { promiseRequest } from "../../../utils/service";
import { store } from "../../../store/store";
import { createStoreBindings } from "mobx-miniprogram-bindings";
Page({
  /**
   * 页面的初始数据
   */
  data: {
    name: "",
    pageSize: 15,
    currentPage: 1,
    doctorList: [],
    isloading: false
  },
  async queryDoctorList() {
    const app = getApp();
    await promiseRequest({
      url:
        app.globalData.hospitalUrl +
        "/doctorInfo/queryPage?pageNo=" +
        this.data.currentPage +
        "&pageSize=" +
        this.data.pageSize,
      method: "POST",
      data: {
        name: this.data.name
      }
    }).then(res => {
      if (res.code == 1) {
        this.setData({
          doctorList: this.data.doctorList.concat(res.data.items)
        });
        this.setData({
          isloading: false
        });
      }
    });
  },
  getDoctorList() {
    this.setData({
      isloading: true,
      currentPage: this.data.currentPage + 1
    });
    wx.showToast({
      title: "加载中",
      icon: "loading"
    });

    this.queryDoctorList();
  },
  searchDoctorList() {
    console.log(1);
    this.setData({
      currentPage: 1,
      doctorList: []
    });
    this.queryDoctorList();
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.storeBindings = createStoreBindings(this, {
      store,
      actions: ["updateDoctorInfoId"]
    });
    this.queryDoctorList();
  },
  toDetail(e) {
    this.updateDoctorInfoId(e.currentTarget.dataset.doctorid);
    wx.navigateTo({
      url: "/pages/doctorInfo/detial/detial"
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
    this.queryDoctorList();
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {
    console.log(1);
    if (this.data.isloading) return;
    this.getDoctorList();
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {}
});
