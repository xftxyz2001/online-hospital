import { defineConfig, loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'
//引入node内置模块path：可以提供绝对路径，从而给src配置别名
import path from 'path'
export default ({ command, mode }) => {

  return defineConfig({
    plugins: [vue()],
    //给src配置别名
    resolve: {
      alias: {
        "@": path.resolve(__dirname, 'src')
      }
    },
    server: {
      port: 3001,
      proxy: {
        '/system': {
          //使用环境变量
          target: loadEnv(mode, process.cwd()).VITE_SYSTEM_BASE_URL,
          changeOrigin: true,

        },
        '/user': {
          //使用环境变量
          target: loadEnv(mode, process.cwd()).VITE_USER_BASE_URL,
          changeOrigin: true,
        },
        '/hospital': {
          //使用环境变量
          target: loadEnv(mode, process.cwd()).VITE_HOSPITAL_BASE_URL,
          changeOrigin: true,
        },
        '/registration':{
          target: loadEnv(mode, process.cwd()).VITE_REGISTRATION_BASE_URL,
          changeOrigin: true,
        },
        '/inquiry':{
          target: loadEnv(mode, process.cwd()).VITE_INQUIRY_APPLICATION_BASE_URL,
          changeOrigin: true,
        }
        

      }
    },
    
  })


}
