// pages/registration/outpatient/outpatient.js
import {store} from '../../../store/store'
import{createStoreBindings} from 'mobx-miniprogram-bindings'
import {promiseRequest} from '../../../utils/service'

Page({

  /**
   * 页面的初始数据
   */
  data: {
   hospitalId:0,
   selectSpecialistId: 0,
    selectOutpatientId: 0,
    treeInfo:[]

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onClickNav({ detail = {} }) {
    this.setData({
      selectSpecialistId: detail.index || 0,
    });
  },
//选择门诊
  onClickItem({ detail = {} }) {
    // const activeId = this.data.activeId === detail.id ? null : detail.id;

    this.setData({ selectOutpatientId: detail.id});
    this.updateInquiryOutpatientId(detail.id);
  wx.navigateTo({
    url: '/pages/inquiry/doctors/doctors',
  })
  },
//获取所有门诊树状信息
async getSpecialistTree(){
  const app = getApp()
  await promiseRequest({
    method:'GET',
    url:app.globalData.hospitalUrl+"/specialist/queryAppSpecialistTree?hospitalId="+this.data.hospitalId
  }).then((res)=>{
    if(res.code==1){
      this.setData({
        treeInfo:res.data
      })
    }
  })
},
  onLoad(options) {
    this.storeBindings=createStoreBindings(this,{
      store,
      fields:['inquiryHospitalId'],
      actions:['updateInquiryOutpatientId']
    })
    this.setData({
      hospitalId:store.inquiryHospitalId
    })
    this.getSpecialistTree()
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
    this.getSpecialistTree()
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