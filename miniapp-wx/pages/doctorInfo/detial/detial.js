// pages/doctorInfo/index/index.js
import {
  promiseRequest
} from '../../../utils/service'
import {store} from '../../../store/store'
import{createStoreBindings} from 'mobx-miniprogram-bindings'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    doctorInfo:{},
    activeNames: ['1']
  },
  previewImage: function(e) {
    wx.previewImage({
      urls: [e.target.dataset.url],
    })
    },
 async queryDoctorInfo(){
   const app=getApp()
  await promiseRequest({
    url:app.globalData.hospitalUrl+'/app/doctorInfo/queryById?doctorId='+store.doctorInfoId,
    method:'GET',
  }).then((res)=>{
    if(res.code==1){
        this.setData({
          doctorInfo:res.data
        })
        
    }
  })
  },
  Detail(event) {
    this.setData({
      activeNames: event.detail,
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.storeBindings=createStoreBindings(this,{
      store,
      filed:['doctorInfoId']
    })
    this.queryDoctorInfo()
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload() {
    this.storeBindings.destroyStoreBindings()
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {
    this.queryDoctorInfo()
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom() {
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage() {

  }
})