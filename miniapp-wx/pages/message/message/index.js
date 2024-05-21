// pages/message/message/index.js
import {
  promiseRequest
} from '../../../utils/service'
import {
  store
} from '../../../store/store'
import {
  createStoreBindings
} from 'mobx-miniprogram-bindings'


Page({

  /**
   * 页面的初始数据
   */
  data: {
    chatList:[]
  },
  //获取聊天列表
  async queryChatList(){
    const app = getApp()
   let res= await promiseRequest({
      url:app.globalData.inquiryUrl+'/app/chat-list/query-chat-list',
      method:'GET'
    })
    if(res.code==1){
      this.setData({
        chatList:res.data
      })
    }
  },
  toDetail(e){
    this.updateInquiryLinkId(e.currentTarget.dataset.id)
    this.updateMessageShowInput(true)
    wx.navigateTo({
      url: '/pages/message/detial/detial',
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.storeBindings=createStoreBindings(this,{
      store,
      actions:['updateInquiryLinkId','updateMessageShowInput']
    })
    this.queryChatList()
    this.hasUnread()
    let that=this
    console.log(0);
    wx.onSocketMessage(function (res) {
      that.queryChatList()
    that.hasUnread()
    });
    console.log(2);
  },
  async hasUnread(){
    const app=getApp()
    await promiseRequest({
      method: "GET",
      url: app.globalData.inquiryUrl + "/app/chat-list/allUnread"
    }).then((res) => {
      if (res.code == 1) {
       this.setData({
         unread:res.data.unread
       })
       if(res.data.unread!=0){
       wx.setTabBarBadge({//tabbar右上角添加文本
        index: 1,   
        text: res.data.unread+''
      })
    }
      else{
      wx.removeTabBarBadge({
        index: 1,
      })
    }
    }})
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
    this.queryChatList()
    this.hasUnread()
    
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

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {
    this.queryChatList()
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