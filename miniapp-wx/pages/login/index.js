// pages/login/index.js
import Toast from "@vant/weapp/toast/toast";
import Notify from "@vant/weapp/notify/notify";
import userApi from "../../api/userApi";
import systemApi from "../../api/systemApi";
// import { fail } from 'mobx-miniprogram/lib/internal';
const defaultAvatarUrl =
  "https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0";
Page({
  /**
   * 页面的初始数据
   */
  data: {
    code: "",
    show: false,
    avatarUrl: defaultAvatarUrl,
    userName: ""
  },
  onChooseAvatar(e) {
    const { avatarUrl } = e.detail;

    this.setData({
      avatarUrl
    });
  },
  //点击确定设置头像和昵称
  submitUserInfo() {
    if (this.data.userName == "") {
      Notify({ type: "warning", message: "昵称不能为空" });
    } else {
      const that = this;
      systemApi.uploadImage(this.data.avatarUrl).then(res => {
        if (res.code == 1) {
          that.setData({
            avatarUrl: res.data
          });
          that.updateUserInfo();
        } else {
          console.log("请重试");
        }
      });
    }
  },
  //修改昵称和昵称
  updateUserInfo() {
    userApi
      .updateUserInfo({
        username: this.data.userName,
        avatar: this.data.avatarUrl
      })
      .then(res => {
        if (res.code == 1) {
          Toast.success({
            message: "设置成功",
            forbidClick: true
          });
          setTimeout(function () {
            wx.switchTab({
              url: "/pages/index/index"
            });
          }, 1000);
        }
      });
  },
  login() {
    Toast.loading({
      message: "加载中...",
      forbidClick: true
    });
    wx.login({
      success: result => {
        this.setData({
          code: result.code
        });
        userApi.login(this.data.code).then(res => {
          if (res.code == 1) {
            wx.setStorageSync("isLogin", 1);
            wx.setStorageSync("token", res.data.token);
            if (res.data.isFirst == 0) {
              //不是第一次登陆，直接跳转
              wx.switchTab({
                url: "/pages/index/index"
              });
            } else {
              //是第一次登录，获取头像昵称信息
              this.setData({
                show: true
              });
            }
          }
        });
      },
      fail: err => {
        console.log(err);
      },
      complete: res => {}
    });
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    let that = this;
    wx.getStorage({
      key: "isLogin",
      success: function (res) {
        if (res.data == 1)
          //曾经登录过 直接通过
          that.login();
      },
      fail: err => {
        wx.setStorageSync("isLogin", 0);
        wx.setStorageSync("token", "");
      },
      complete: res => {
        console.log(res);
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
