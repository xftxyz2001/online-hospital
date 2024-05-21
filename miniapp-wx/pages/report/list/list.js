// pages/report/list/list.js
import {promiseRequest} from '../../../utils/service'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    reportList:[]
  },
// 点击事件
openFile(e) { // item为当前点击的事物对象
  let file = decodeURIComponent(e.currentTarget.dataset.url); // 解码(注意:涉及到文件名中有中文需要转码)
  file = this.getFilePathName(file); // 将文件路径过滤，格式为【文件名+.后缀名】
  // 加载状态
 wx.showToast({
   title: '文件打开中',
 })
  let _this = this;
  // 预览网络文档
  wx.downloadFile({
    url: e.currentTarget.dataset.url, // 文件的本身url
    success: function (res) {
      console.log(res);
      let filePath = res.tempFilePath; // 微信临时文件路径(这里要使用自定义的名字文件名,否则打开的文件名是乱码)
      wx.openDocument({
        filePath: filePath,
        showMenu: true,  // 是否显示右上角菜单按钮 默认为false(看自身需求，可要可不要。后期涉及到右上角分享功能)
        success: function () {
          wx.showToast({
            title: '文件打开成功',
          })
        },
        fail: function() {
        wx.showToast({
          title: '文件打开失败',
        })
        }
      });
      wx.hideLoading();
    },
    fail: function(err) {
      wx.showToast({
        title: '文件下载失败',
        icon:"error"
      })
      console.log(err);
    }
  });
},
// 文件路径过滤【文件名+.后缀名】
// 例如：哈哈.pdf
getFilePathName(path) {
let pos1 = path.lastIndexOf('/');
let pos2 = path.lastIndexOf('\\');
let position  = Math.max(pos1, pos2);
if ( position< 0 ){
return path;
}
else{
return path.substring(position+ 1);
}
},
  async getReportInfos(){
    const app = getApp()
    await promiseRequest({
      method:'GET',
      url:app.globalData.registrationUrl+"/reportInfo/app/queryAll"
    }).then((res)=>{
      if(res.code==1){
        this.setData({
          reportList:res.data
        })
        
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
this.getReportInfos()
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

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh() {

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