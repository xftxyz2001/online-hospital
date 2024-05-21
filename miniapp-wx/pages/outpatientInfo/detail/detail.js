// pages/hospitalInfo/detail/detail.js
import {promiseRequest} from '../../../utils/service'
import {store} from '../../../store/store'
import{createStoreBindings} from 'mobx-miniprogram-bindings'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    outpatientInfo:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.storeBindings=createStoreBindings(this,{
      store,
      field:['outpatientInfoId']
    })
   this.getOutpatientInfo()
  },
//查询医院信息
async getOutpatientInfo(){
 const app=getApp()
 await promiseRequest({
   url:app.globalData.hospitalUrl+'/outpatientInfo/queryById?id='+store.outpatientInfoId,
   method:'GET'
 }).then((res)=>{
   if(res.code==1){
     this.setData({
       outpatientInfo:res.data
     })
   }
 }) 
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
    this.getOutpatientInfo()
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