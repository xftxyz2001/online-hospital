export const promiseRequest = (parmas) => {
  // 返回一个promise对象
  return new Promise((resolve, reject) => {
    let token="111"
  wx.getStorage({
    key:'token',
    success:function(res){
     
      token=res.data
        wx.request({
          method:parmas.method,
          url: parmas.url, //仅为示例，并非真实的接口地址
          data:parmas.data,
          header: {
              'content-type': 'application/json', // 默认值
              'token':token,
          },
          success: (res)=> {
               // 请求成功，就将成功的数据返回出去
              resolve(res.data)
          },
          fail: (err) => {
              reject(err)
          },
      })
      

    },
    fail:function(res){
      wx.switchTab({
        url: '/pages/login/index',
      })
    }
   
    })
      
  })
}