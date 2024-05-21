// pages/registration/register/register.js
import {
  promiseRequest
} from '../../../utils/service'
import {
  store
} from '../../../store/store'
import {
  createStoreBindings
} from 'mobx-miniprogram-bindings'
import Notify from '@vant/weapp/notify/notify';
Page({

  /**
   * 页面的初始数据
   */
  data: {
    activeNames: ['1'],
    time: '-1',
    hospitalId: 0,
    OutpatientId: 0,
    date: "",
    half: 0,
    doctorId: 0,
    timeList: [],
    morningTimeString: ["8:00-8:30", "8:30-9:00", "9:00-9:30", "9:30-10:00", "10:00-10:30", "10:30-11:00", "11:00-11:30"],
    afternoonTimeString: ["13:00-13:30", "13:30-14:00", "14:00-14:30", "14:30-115:00", "15:00-15:30", "16:30-16:00", "16:00-16:30"],
    patientId: -1,
    patientList: [],
    scheduleId:0,
    doctorInfo:{}
  },
  Detail(event) {
    this.setData({
      activeNames: event.detail,
    });
  },
  selectTime(event) {
    console.log(event);
    this.setData({
      time: event.detail,
    });
  },
  changePatient(e) {
    this.setData({
      patientId: e.detail
    })
  },
  //获取挂号时间信息
  async getTimeList() {
    const app = getApp()
    await promiseRequest({
      method: 'POST',
      url: app.globalData.registrationUrl + '/schedule/app/doctor',
      data: {
        hospitalId: this.data.hospitalId,
        outpatientId: this.data.OutpatientId,
        workDate: this.data.date,
        workHalf: this.data.half,
        doctorId: this.data.doctorId
      }
    }).then((res) => {
      if (res.code == 1) {
        // this.setData({
        //   hospitalList:res.data
        // })
        this.setData({
          timeList: res.data
        })
        console.log(this.data.timeList);

      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad(options) {
    this.storeBindings = createStoreBindings(this, {
      store,
      fields: ['registrationHospitalId', 'registrationOutpatientId', 'registrationHalf', 'registrationDoctorId', 'registrationDate'],
      actions: []
    })
    this.setData({
      hospitalId: store.registrationHospitalId,
      OutpatientId: store.registrationOutpatientId,
      half: store.registrationHalf,
      doctorId: store.registrationDoctorId,
      date: store.registrationDate
    })
    console.log(this.data.date);
    this.getTimeList()
    this.queryAllPatient()
    this.queryDoctorInfo()
  },
  //获取医生信息
  async queryDoctorInfo(){
const app=getApp()
await promiseRequest({
  method:"GET",
  url:app.globalData.hospitalUrl+"/doctorInfo/queryById?doctorId="+this.data.doctorId
}).then((res)=>{
  if(res.code==1){
    this.setData({
      doctorInfo:res.data
    })
  }
})
  },
  async queryAllPatient() {
    const app = getApp()
    await promiseRequest({
      method: "GET",
      url: app.globalData.userUrl + "/app/patient/queryAll"
    }).then((res) => {
      if (res.code == 1) {
        this.setData({
          patientList: res.data
        })
      }
    })
  },
  submit() {
    if (this.data.time == -1) {
      Notify({ type: 'warning', message: '请选择时间' });
      return
    }
    if (this.data.patientId == -1) {
      Notify({ type: 'warning', message: '请选择就诊人' });
      return
    }
    this.register()
  },

 async register(){
   const app=getApp()
    await promiseRequest({
      method:'POST',
      url:app.globalData.registrationUrl+'/orderInfo/app/add',
      data:{
        amount:0,
        doctorId:this.data.doctorId,
        hospitalId:this.data.hospitalId,
        outpatientId:this.data.OutpatientId,
        patientId:this.data.patientId,
        reserveDate:this.data.date,
        reserveHalf:this.data.half,
        reserveTime:this.data.time,
        scheduleId:this.data.scheduleId
      }
    }).then((res)=>{
      if(res.code==1){
      
        wx.showToast({
          title: '预约成功',
          icon: 'success',
          duration: 2000,
          success: function () {
            setTimeout(function() {
              wx.switchTab({
                url: '/pages/index/index',
              })
            }, 2000);
          }
      });

      }
    })
  },
  changeTime(e){
    this.setData({
      scheduleId:e.currentTarget.dataset.id
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