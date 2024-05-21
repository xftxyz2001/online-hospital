// pages/patient/update/update.js
import {store} from '../../../store/store'
import{createStoreBindings} from 'mobx-miniprogram-bindings'
import{promiseRequest}from '../../../utils/service'
import { areaList } from '@vant/area-data/index.js';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    areaList,
    patientId:0,
    patientInfo:{},
    newAreaCode:"110101",
    newProvinceName:"北京市",
    newCityName:"北京市",
    newDistrictName:"东城区",
    areaDialogVisible:false,
    newAreaDetail:"",
    newPhone:""
  },
  async getPatientInfo(){
    const app = getApp()
    await promiseRequest({
      method:'GET',
      url:app.globalData.userUrl+"/app/patient/queryOne?patientId="+this.data.patientId
    }).then((res)=>{
      if(res.code==1){
        this.setData({
          patientInfo:res.data,
          newAreaCode:res.data.areaCode,
          newProvinceName:res.data.provinceName,
          newCityName:res.data.cityName,
          newDistrictName:res.data.districtName,
          newAreaDetail:res.data.address,
          newPhone:res.data.phone
        })
        
      }
    })
  },
  selectDistract(){
    this.setData({
      areaDialogVisible:true
    })
  },
  cancelArea(){
    this.setData({
      areaDialogVisible:false
    })
  },
  confirmArea(){
    this.setData({
      areaDialogVisible:false
    })
  },
  changeArea(event){
    this.setData({
      newProvinceName:event.detail.values[0].name,
      newCityName:event.detail.values[1].name,
      newDistrictName:event.detail.values[2].name,
      newAreaCode:event.detail.values[2].code
    })
 
  },
  //提交
  async confirm(){
    const app = getApp()
    await promiseRequest({
      method:'PUT',
      url:app.globalData.userUrl+'/app/patient/update',
      data:{
        id:this.data.patientId,
        phone:this.data.newPhone,
        provinceName:this.data.newProvinceName,
        cityName:this.data.newCityName,
        districtName:this.data.newDistrictName,
        areaCode:this.data.newAreaCode,
        address:this.data.newAreaDetail
      }
    }).then((res)=>{
      if(res.code==1){
       
        wx.navigateBack({
          delta: 2
        });    
        wx.showToast({
          title: '修改成功',
          icon:'success',
          duration:2000
        })  
      }
      else{
        wx.showToast({
          title: '修改失败',
          icon:'error',
          duration:2000
        })
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.storeBindings=createStoreBindings(this,{
      store,
      fields:['patientUpdateId']
    })
    this.setData({
      patientId:store.patientUpdateId
    })
    this.getPatientInfo()
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
    this.getPatientInfo()
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