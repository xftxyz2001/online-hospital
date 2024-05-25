// pages/registration/detail/detail.js
import { store } from "../../../store/store";
import { createStoreBindings } from "mobx-miniprogram-bindings";
import registrationApi from "../../../api/registrationApi";
Page({
  /**
   * 页面的初始数据
   */
  data: {
    dateList: [],
    selectedDate: "",
    //全部医生信息
    doctorList: [],
    //上午医生列表
    morningDoctorList: [],
    //下午医生列表
    afternoonDoctorList: [],
    //医院id
    registrationHospitalId: 0,
    //门诊
    registrationOutpatientId: 0,
    //选择的医生id
    registrationDoctorId: 0
  },

  //选择日期
  selectDate(e) {
    console.log(e);
    this.setData({
      selectedDate: e.target.dataset.selectdate,
      morningDoctorList: [],
      afternoonDoctorList: []
    });
    this.updateRegistrationDate(e.target.dataset.selectdate);

    this.getDoctorList();
  },
  //选择医生
  selectDoctor(e) {
    this.updateRegistrationDoctorId(e.currentTarget.dataset.doctorid);
    this.updateRegistrationHalf(e.currentTarget.dataset.half);
    wx.navigateTo({
      url: "/pages/registration/register/register"
    });
  },
  getDoctorList() {
    registrationApi
      .querySchedule({
        hospitalId: this.data.registrationHospitalId,
        outpatientId: this.data.registrationOutpatientId,
        workDate: this.data.selectedDate
      })
      .then(res => {
        if (res.code == 1) {
          this.setData({
            doctorList: res.data
          });
          this.doctorListHandle();
        }
      });
  },
  //将医生信息分类处理
  doctorListHandle() {
    this.setData({
      morningDoctorList: [],
      afternoonDoctorList: []
    });
    const list = this.data.doctorList;
    for (let i = 0; i < list.length; i++) {
      switch (list[i].workHalf) {
        case 0:
          this.setData({
            morningDoctorList: [...this.data.morningDoctorList, list[i]]
          });
          break;
        case 1:
          this.setData({
            afternoonDoctorList: [...this.data.afternoonDoctorList, list[i]]
          });
          break;
      }
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.storeBindings = createStoreBindings(this, {
      store,
      fields: ["registrationHospitalId", "registrationOutpatientId"],
      actions: ["updateRegistrationDoctorId", "updateRegistrationDate", "updateRegistrationHalf"]
    });
    this.setData({
      selectedDate: new Date().toISOString().substring(0, 10),
      registrationHospitalId: store.registrationHospitalId,
      registrationOutpatientId: store.registrationOutpatientId
    });
    this.updateRegistrationDate(this.data.selectedDate);
    for (var i = 0; i < 14; i++) {
      var day = new Date();
      day.setTime(day.getTime() + i * 24 * 60 * 60 * 1000);
      let yearDateString = day.toISOString().substring(0, 10);
      let dateString = day.toISOString().substring(5, 10);
      let dateObj = { yearDateString: yearDateString, dateString: dateString };
      this.setData({
        dateList: [...this.data.dateList, dateObj]
      });
    }
    this.getDoctorList();
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
    this.getDoctorList();
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
