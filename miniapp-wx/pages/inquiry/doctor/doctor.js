// pages/inquiry/doctor/doctor.js
import { promiseRequest } from "../../../utils/service";
import { store } from "../../../store/store";
import { createStoreBindings } from "mobx-miniprogram-bindings";
import Notify from "@vant/weapp/notify/notify";
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
  async queryAllPatient() {
    const app = getApp();
    await promiseRequest({
      method: "GET",
      url: app.globalData.userUrl + "/app/patient/queryAll"
    }).then(res => {
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
  async inquiry() {
    const app = getApp();
    await promiseRequest({
      method: "POST",
      url: app.globalData.inquiryUrl + "/app/inquiry-application/add",
      data: {
        doctorId: this.data.doctorId,
        hospitalId: this.data.hospitalId,
        outpatientId: this.data.outpatientId,
        patientId: this.data.patientId,
        image: this.data.fileList[0].url,
        description: this.data.description
      }
    }).then(res => {
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
    const app = getApp();
    let that = this;
    wx.uploadFile({
      url: app.globalData.systemUrl + "/upload", // 仅为示例，非真实的接口地址
      filePath: file.url,
      name: "image",
      url: app.globalData.systemUrl + "/upload",
      header: {
        token: wx.getStorageSync("token")
      },
      success(res) {
        // 上传完成需要更新 fileList
        let result = JSON.parse(res.data);
        let url = result.data;
        let file = {
          url: url,
          name: "图片"
        };
        that.setData({ fileList: that.data.fileList.concat(file) });
      }
    });
  },
  //获取门诊和医生名称
  async inquiryDoctorAndOutpatientIOnfo() {
    let app = getApp();
    let result1 = await promiseRequest({
      url: app.globalData.hospitalUrl + "/doctorInfo/queryById?doctorId=" + this.data.doctorId,
      method: "GET"
    });
    if (result1.code == 1) {
      this.setData({
        doctorName: result1.data.name
      });
    }
    let result2 = await promiseRequest({
      url: app.globalData.hospitalUrl + "/outpatientInfo/queryById?id=" + this.data.outpatientId,
      method: "GET"
    });
    if (result2.code == 1) {
      this.setData({
        outpatientName: result2.data.name
      });
    }
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
