const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 3000,
    proxy: {

      '/user': {
        //使用环境变量
        target: process.env.VUE_APP_USER_BASE_URL,
        changeOrigin: true,
        ['/user']: '',

      },
      '/inquiry': {
        target: process.env.VUE_APP_QUIRY_BASE_URL,
        changeOrigin: true,
        ['/inquiry']: ''
      },
      '/registration':{
        target:process.env.VUE_APP_REGISTRATION_URL,
        changeOrigin: true,
        ['/registration']: ''
      },
      '/system':{
        target:process.env.VUE_SYSTEM_BASE_URL,
        changeOrigin: true,
        ['/system']: ''
      },
      '/hospital':{
        target:process.env.VUE_HOSPITAL_BASE_URL,
        changeOrigin: true,
        ['/hospital']: ''
      }
      // '/ws':{
      //   target:process.env.VUE_APP_WS_CHAT,
      //   ws:true,
      //   changeOrigin: true,
      // }
    }
  }
})
