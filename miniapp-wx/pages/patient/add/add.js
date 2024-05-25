// pages/patient/add/add.js
import { formatDate } from "../../../utils/util.js";
import Notify from "@vant/weapp/notify/notify";
import { areaList } from "@vant/area-data/index.js";
import userApi from "../../../api/userApi.js";
Page({
  /**
   * 页面的初始数据
   */
  data: {
    areaList,
    name: "",
    certificatesType: "身份证",
    certificatesNo: "",
    sex: "1",
    formatBirthDate: formatDate(new Date()),
    birthDate: new Date().getTime,
    minDate: new Date("1900-01-01T00:00").getTime(),
    maxDate: new Date().getTime(),
    birthDateDialogVisible: false,
    phone: "",
    isMarry: "0",
    areacode: "110101",
    provinceName: "北京市",
    cityName: "北京市",
    districtName: "东城区",
    areaDialogVisible: false,
    areaDetail: "",
    formatter(type, value) {
      if (type === "year") {
        return `${value}年`;
      }
      if (type === "month") {
        return `${value}月`;
      }
      return value;
    }
  },
  changeSex(event) {
    this.setData({
      sex: event.detail
    });
  },
  changeIsMarry(event) {
    this.setData({
      isMarry: event.detail
    });
  },
  changeBirthDate(event) {
    this.setData({
      birthDate: event.detail,
      formatBirthDate: formatDate(new Date(event.detail))
    });
  },
  selectBirthDate() {
    this.setData({
      birthDateDialogVisible: true
    });
  },
  confirmBirthDate() {
    this.setData({
      birthDateDialogVisible: false
    });
  },
  cancelBirthDate() {
    this.setData({
      birthDateDialogVisible: false
    });
  },
  changeArea(event) {
    this.setData({
      provinceName: event.detail.values[0].name,
      cityName: event.detail.values[1].name,
      districtName: event.detail.values[2].name
    });
  },
  selectDistract() {
    this.setData({
      areaDialogVisible: true
    });
  },
  cancelArea() {
    this.setData({
      areaDialogVisible: false
    });
  },
  confirmArea() {
    this.setData({
      areaDialogVisible: false
    });
  },
  submit() {
    userApi
      .addPatient({
        name: this.data.name,
        certificatesType: this.data.certificatesType,
        certificatesNo: this.data.certificatesNo,
        sex: this.data.sex,
        birthdate: this.data.formatBirthDate,
        phone: this.data.phone,
        isMarry: this.data.isMarry,
        provinceName: this.data.provinceName,
        cityName: this.data.cityName,
        districtName: this.data.districtName,
        address: this.data.areaDetail,
        areaCode: this.data.areacode
      })
      .then(res => {
        if (res.code == 1) {
          Notify({ type: "success", message: "添加成功" });
          wx.navigateTo({
            url: "/pages/patient/list/list"
          });
        }
      });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {},

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
  onPullDownRefresh() {},

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {},

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {}
});
