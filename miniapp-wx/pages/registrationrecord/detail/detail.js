// pages/registrationrecord/detail/detail.js
import { promiseRequest } from "../../../utils/service";
import { store } from "../../../store/store";
import { createStoreBindings } from "mobx-miniprogram-bindings";
import Dialog from "@vant/weapp/dialog/dialog";
Page({
  /**
   * 页面的初始数据
   */
  data: {
    orderDetailId: 0,
    orderDetail: {},
    canCancel: false,
    morningList: ["8:00~8:30", "8:30~9:00", "9:00~9:30", "9:30~10:00", "10:00~10:30", "10:30~11:00", "11:00~11:30"],
    afternoonList: [
      "13:00~13:30",
      "13:30~14:00",
      "14:00~14:30",
      "14:30~15:00",
      "15:00~15:30",
      "15:30~16:00",
      "16:00~16:30"
    ],
    orderStatusList: ["未完成", "已完成", "已超时", "已取消"]
  },
  //获取单个订单信息
  async getOneOrder() {
    const app = getApp();
    await promiseRequest({
      method: "GET",
      url: app.globalData.registrationUrl + "/orderInfo/app/queryOne?id=" + this.data.orderDetailId
    }).then(res => {
      if (res.code == 1) {
        this.setData({
          orderDetail: res.data
        });
        let time = new Date(res.data.reserveDate);
        let timeNow = new Date();
        if (timeNow < time && res.data.orderStatus == 0)
          this.setData({
            canCancel: true
          });
      }
    });
  },
  //点击取消预约
  clickCancel() {
    Dialog.confirm({
      title: "提示",
      message: "确定取消预约吗？"
    })
      .then(() => {
        this.cancelRegistration();
      })
      .catch(() => {
        // on cancel
      });
  },
  //取消预约
  async cancelRegistration() {
    const app = getApp();
    await promiseRequest({
      url: app.globalData.registrationUrl + "/orderInfo/app/update",
      method: "PUT",
      data: {
        id: this.data.orderDetailId,
        orderStatus: 3
      }
    }).then(res => {
      if (res.code == 1) {
        wx.navigateBack(1);
        wx.showToast({
          title: "取消成功",
          icon: "success"
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
      field: ["registrationRecordDetailId"]
    });
    this.setData({
      orderDetailId: store.registrationRecordDetailId
    });
    this.getOneOrder();
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
    this.getOneOrder();
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
