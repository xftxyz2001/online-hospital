

// pages/registration/index/index.js
import {promiseRequest} from '../../../utils/service'
import {store} from '../../../store/store'
import{createStoreBindings} from 'mobx-miniprogram-bindings'
Page({
  /**
   * 页面的初始数据
   */
  data: {
    hospitalList:[],

  },
//获取所有医院信息
async getHospitalInfo(){
  const app = getApp()
  await promiseRequest({
    method:'GET',
    url:app.globalData.hospitalUrl+"/hospital"
  }).then((res)=>{
    if(res.code==1){
      this.setData({
        hospitalList:res.data
      })
      
    }
  })
},
//选择医院
selectHospital(e){
  this.updateRegistrationHospitalId(e.target.dataset.hospitalid)
  wx.navigateTo({
    url: '/pages/registration/outpatient/outpatient',
  })
},
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.storeBindings=createStoreBindings(this,{
      store,
      fields:['registrationHospitalId','getRegistrationHospitalId'],
      actions:['updateRegistrationHospitalId']
    })
   this.getHospitalInfo()
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
    this.getHospitalInfo()
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {
    this.onLoad()
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