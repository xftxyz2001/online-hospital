// app.js
// import {promisifyAll} from 'miniprogram-api-promise'
const wxp=wx.p={}
// promisifyAll(wx,wxp)
App({

  onLaunch:function(){
    // 调用
 },
 onPullDownRefresh:function(){
  this.onLaunch();
},
onRefresh:function(){
  //导航条加载动画
  wx.showNavigationBarLoading();
  setTimeout(function () {
    wx.hideNavigationBarLoading();
    //停止下拉刷新
    wx.stopPullDownRefresh();
  }, 2000);
},


  globalData: {
    hospitalUrl:"http://localhost/hospital",
    registrationUrl:"http://localhost/registration",
    userUrl:"http://localhost/user",
    inquiryUrl:"http://localhost/inquiry",
    systemUrl:"http://localhost/system"
  }
})
