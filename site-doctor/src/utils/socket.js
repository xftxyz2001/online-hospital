import store from "@/vuex";
let websock = null;
let global_callback = null;
let wsuri = "";
//连接标识 避免重复连接
let isConnect = false;
//断线重连后，延迟5秒重新创建WebSocket连接  rec用来存储延迟请求的代码
let rec = null;
// 心跳发送/返回的信息
const checkMsg = { hhh: "heart" };
//每段时间发送一次心跳包 这里设置为20s
const timeout = 20000;
//延时发送消息对象（启动心跳新建这个对象，收到消息后重置对象）
let timeoutObj = null;
let useToken = store.getters.doctorLoginToken.token;
let webSocketUrl = process.env.VUE_APP_WS_CHAT;
function createWebSocket() {
  if (websock == null || typeof websock !== WebSocket) {
    wsuri = webSocketUrl + "/" + useToken;
    initWebSocket(callback);
  }
}
function initWebSocket(callback) {
  global_callback = callback;
  // 初始化websocket

  websock = new WebSocket(wsuri);
  websock.onmessage = function (e) {
    websocketonmessage(e);
  };
  // 连接发生错误的回调方法
  websock.onerror = function () {
    console.log("WebSocket连接发生错误");
    isConnect = false;
    //连接错误 需要重连
    reConnect();
    //createWebSocket();啊，发现这样写会创建多个连接，加延时也不行
  };
  websock.onclose = function (e) {
    websocketclose(e);
  };
  websock.onopen = function () {
    websocketOpen();
  };
}
// 实际调用的方法
function sendSock(agentData) {
  if (websock.readyState === websock.OPEN) {
    // 若是ws开启状态
    websocketsend(agentData);
  } else if (websock.readyState === websock.CONNECTING) {
    // 若是 正在开启状态，则等待1s后重新调用
    setTimeout(function () {
      sendSock(agentData);
    }, 1000);
  } else {
    // 若未开启 ，则等待1s后重新调用
    setTimeout(function () {
      sendSock(agentData);
    }, 1000);
  }
}
function closeSock() {
  websock.close();
}
// 数据接收
function websocketonmessage(msg) {
  // console.log("收到数据："+JSON.parse(e.data));
  // console.log("收到数据："+msg);
  // global_callback(JSON.parse(msg.data));
  //获取消息后 重置心跳
  clearTimeout(timeoutObj);
  timeoutObj = setTimeout(function () {
    if (isConnect) websock.send(checkMsg);
  }, timeout);

  // 收到信息为Blob类型时
  let result = null;
  // debugger
  if (msg.data instanceof Blob) {
    const reader = new FileReader();
    reader.readAsText(msg.data, "UTF-8");
    reader.onload = e => {
      console.log(e);
      if (typeof reader.result === "string") {
        result = JSON.parse(reader.result);
      }
      //console.log("websocket收到", result);
      global_callback(result);
    };
  } else {
    result = JSON.parse(msg.data);
    //console.log("websocket收到", result);
    global_callback(result);
  }
}
// 数据发送
function websocketsend(agentData) {
  console.log("发送数据：" + agentData);
  websock.send(agentData);
}
// 关闭
function websocketclose(e) {
  isConnect = false;
  //连接错误 需要重连
  reConnect();
  console.log("connection closed (" + e.code + ")");
}
function websocketOpen() {
  isConnect = true;
  timeoutObj = setTimeout(function () {
    if (isConnect) websock.send(checkMsg);
  }, timeout);
}
function reConnect() {
  console.log("尝试重新连接");
  //如果已经连上就不在重连了
  if (isConnect) {
    return;
  }

  clearTimeout(rec);
  // 延迟5秒重连  避免过多次过频繁请求重连
  rec = setTimeout(function () {
    createWebSocket();
  }, 5000);
}
function callback(msg) {
  console.log("websocket的回调函数收到服务器信息：" + JSON.stringify(msg));
  store.dispatch("setActionData", msg);
}
export { sendSock, createWebSocket, closeSock };
