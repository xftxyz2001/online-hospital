/**
 * 参数说明：
 *  webSocketURL：String    webSocket服务地址    eg: ws://127.0.0.1:8088/websocket (后端接口若为restful风格可以带参数)
 *  callback：为带一个参数的回调函数
 *  message：String 要传递的参数值（不是一个必要的参数）
 */
import store from "@/vuex";
export default {
  // 初始化webSocket
  webSocketInit() {
    const webSocketUrl = "/inquiry/chat";
    const useToken = store.getters.doctorLoginToken.token;
    if (useToken != "") {
      this.webSocket = new WebSocket(webSocketUrl + "/" + useToken);
      this.webSocket.onopen = this.onOpenCellback;
      this.webSocket.onmessage = this.onMessageCallback;
      this.webSocket.onerror = this.onErrorCallback;
      this.webSocket.onclose = this.onCloseCallback;
    }
  },

  // 自定义回调函数
  setOpenCallback(callback) {
    //  与服务端连接打开回调函数
    this.webSocket.onopen = callback;
  },
  setMessageCallback(callback) {
    //  与服务端发送消息回调函数
    this.webSocket.onmessage = callback;
  },
  setErrorCallback(callback) {
    //  与服务端连接异常回调函数
    this.webSocket.onerror = callback;
    this.webSocketInit();
  },
  setCloseCallback(callback) {
    //  与服务端连接关闭回调函数
    this.webSocket.onclose = callback;
    this.webSocketInit();
  },

  close() {
    this.webSocket.close();
  },
  sendMessage(message) {
    this.webSocket.send(message);
  }
};
