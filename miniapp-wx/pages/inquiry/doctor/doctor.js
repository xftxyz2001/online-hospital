// pages/inquiry/doctor/doctor.js
import { store } from "../../../store/store";
import { createStoreBindings } from "mobx-miniprogram-bindings";
import Notify from "@vant/weapp/notify/notify";
import userApi from "../../../api/userApi";
import inquiryApi from "../../../api/inquiryApi";
import hospitalApi from "../../../api/hospitalApi";
import systemApi from "../../../api/systemApi";
Page({
  /**
   * 页面的初始数据
   */
  data: {
    patientList: [],
    patientId: -1,
    description: "",
    hospitalId: 0,
    outpatientId: 0,
    doctorId: 0,
    fileList: [],
    confirmShow: false,
    outpatientName: "",
    doctorName: "",
    selectPatientName: ""
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
  submit() {
    if (this.data.description == "") {
      Notify({ type: "warning", message: "请输入描述" });
      return;
    }
    if (this.data.patientId == -1) {
      Notify({ type: "warning", message: "请选择就诊人" });
      return;
    }
    this.setData({
      confirmShow: true
    });
  },
  confirmSubmit() {
    if (this.data.fileList.length == 0) {
      this.setData({
        fileList: [
          {
            url: "",
            name: ""
          }
        ]
      });
    }
    this.inquiry();
  },
  inquiry() {
    inquiryApi
      .addInquiryApplication({
        doctorId: this.data.doctorId,
        hospitalId: this.data.hospitalId,
        outpatientId: this.data.outpatientId,
        patientId: this.data.patientId,
        image: this.data.fileList[0].url,
        description: this.data.description
      })
      .then(res => {
        if (res.code == 1) {
          wx.showToast({
            title: "申请成功",
            icon: "success",
            duration: 2000,
            success: function () {
              setTimeout(function () {
                wx.switchTab({
                  url: "/pages/index/index"
                });
              }, 2000);
            }
          });
        }
      });
  },
  changePatient(e) {
    this.setData({
      patientId: e.detail
    });
  },
  afterRead(event) {
    const { file } = event.detail;
    // 当设置 mutiple 为 true 时, file 为数组格式，否则为对象格式
    let that = this;
    systemApi.uploadImage(file.url).then(res => {
      if (res.code == 1) {
        let file = {
          url: res.data,
          name: "图片"
        };
        that.setData({ fileList: that.data.fileList.concat(file) });
      }
    });
  },
  //获取门诊和医生名称
  inquiryDoctorAndOutpatientIOnfo() {
    hospitalApi.queryDoctorInfoByDoctorId(this.data.doctorId).then(res => {
      if (res.code == 1) {
        this.setData({
          doctorName: res.data.name
        });
      }
    });
    hospitalApi.queryOutpatientInfoById(this.data.outpatientId).then(res => {
      if (res.code == 1) {
        this.setData({
          outpatientName: res.data.name
        });
      }
    });
  },
  onClick(e) {
    this.setData({
      selectPatientName: e.currentTarget.dataset.name
    });
  },
  //关闭对话框
  onClose() {
    this.setData({
      confirmShow: false
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.storeBindings = createStoreBindings(this, {
      store,
      fields: ["inquiryHospitalId", "inquiryOutpatientId", "inquiryDoctorId"]
    });
    this.setData({
      hospitalId: store.inquiryHospitalId,
      outpatientId: store.inquiryOutpatientId,
      doctorId: store.inquiryDoctorId
    });
    this.queryAllPatient();
    this.inquiryDoctorAndOutpatientIOnfo();
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
    this.queryAllPatient();
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {
    this.onLoad();
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
