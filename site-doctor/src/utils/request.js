//对axios进行二次封装，可以添加请求拦截器和响应拦截器
//请求拦截器可以给每个请求添加token
//响应拦截器可以简化返回的数据
import axios from "axios";
import store from "@/vuex";
//二次封装，设置一些参数
const request = axios.create({
  baseURL: "/", //设置基础路径
  timeout: 10000 //设置超时时间
});
//设置请求拦截器
request.interceptors.request.use(config => {
  const useToken = store.getters.doctorLoginToken.token;
  if (useToken != "") {
    config.headers = {
      token: useToken
    };
  }
  // config => {
  //     //
  //     console.log(2);

  //     //console.log("请求头toekn=====>", useToken.token);
  //     // 设置请求头
  //     // config.headers['token'] = useToken.token;
  //     //   config.headers.token = useToken;
  // };
  // }

  return config;
});
//设置响应拦截器
request.interceptors.response.use(
  response => {
    //响应成功
    // let code = response.data.code;
    // console.log("code="+code);

    // switch(code)
    // {
    //     //请求成功
    //     case 1:
    //         console.log(response.data.data);
    //         console.log("intercepter  "+new Date().getTime());

    //         return response.data.data;
    //         break;
    // }
    return response.data;
  },
  error => {
    //响应失败
    return Promise.reject(new Error(error.message));
  }
);

//对外暴露
export default request;
