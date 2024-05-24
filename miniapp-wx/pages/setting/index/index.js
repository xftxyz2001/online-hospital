// pages/setting/index/index.js
import { promiseRequest } from "../../../utils/service";
Page({
  /**
   * 页面的初始数据
   */
  data: {
    avatarUrl: "",
    userName: "",
    newUsername: "",
    phone: "",
    newPhone: "",
    avatarShow: false,
    setUsernameShow: false,
    setPhoneShow: false
  },
  //修改头像
  onChooseAvatar(e) {
    const { avatarUrl } = e.detail;
    const app = getApp();
    let that = this;
    wx.uploadFile({
      filePath: avatarUrl,
      name: "image",
      url: app.globalData.systemUrl + "/upload",
      header: {
        token: wx.getStorageSync("token")
      },
      async success(res) {
        const data = JSON.parse(res.data);
        const app = getApp();
        if (data.code == 1) {
          that.setData({
            avatarUrl: data.data
          });
          await promiseRequest({
            method: "PUT",
            url: app.globalData.userUrl + "/app/user/updateUserInfo",
            data: {
              avatar: data.data
            }
          }).then(res => {
            if (res.code == 1) {
              wx.showToast({
                title: "设置成功",
                icon: "success",
                duration: 2000
              });
            } else {
              wx.showToast({
                title: "设置失败",
                icon: "error",
                duration: 2000
              });
            }
          });
        } else {
          wx.showToast({
            title: "上传失败",
            icon: "error",
            duration: 2000
          });
        }
      },
      fail(res) {
        console.log(res);
      }
    });
  },
  //点击昵称
  clickUserName() {
    this.setData({
      newUsername: this.data.userName,
      setUsernameShow: true
    });
  },
  //修改用户名
  async submitUsername() {
    const app = getApp();
    await promiseRequest({
      method: "PUT",
      url: app.globalData.userUrl + "/app/user/updateUserInfo",
      data: {
        username: this.data.newUsername
      }
    }).then(res => {
      if (res.code == 1) {
        wx.showToast({
          title: "设置成功",
          icon: "success",
          duration: 2000
        });
        this.setData({
          setUsernameShow: false,
          userName: this.data.newUsername
        });
      } else {
        wx.showToast({
          title: "设置失败",
          icon: "error",
          duration: 2000
        });
        this.setData({
          setUsernameShow: false
        });
      }
    });
  },
  //点击手机号
  clickPhone() {
    this.setData({
      setPhoneShow: true,
      newPhone: this.data.phone
    });
  },
  //修改手机号
  async submitPhone() {
    const app = getApp();
    await promiseRequest({
      method: "PUT",
      url: app.globalData.userUrl + "/app/user/updateUserInfo",
      data: {
        phone: this.data.newPhone
      }
    }).then(res => {
      if (res.code == 1) {
        wx.showToast({
          title: "设置成功",
          icon: "success",
          duration: 2000
        });
        this.setData({
          setPhoneShow: false,
          phone: this.data.newPhone
        });
      } else {
        wx.showToast({
          title: "设置失败",
          icon: "error",
          duration: 2000
        });
        this.setData({
          setUsernameShow: false
        });
      }
    });
  },
  //关闭设置用户名
  closeSetUsername() {
    this.setData({
      setUsernameShow: false
    });
  },
  //关闭设置手机号
  closeSetPhone() {
    this.setData({
      setPhoneShow: false
    });
  },
  //获取用户信息
  async getUserInfo() {
    const app = getApp();
    await promiseRequest({
      method: "GET",
      url: app.globalData.userUrl + "/app/user/queryUserInfo"
    }).then(res => {
      if (res.code == 1) {
        this.setData({
          avatarUrl: res.data.avatar,
          userName: res.data.username,
          phone: res.data.phone
        });
      }
    });
  },
  //点击设置头像
  clickSetAvatar() {
    this.setData({
      avatarShow: true
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.getUserInfo();
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
    this.getUserInfo();
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
