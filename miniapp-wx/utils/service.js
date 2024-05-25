import WebsocketHeartbeat from "websocket-heartbeat-miniprogram";

export const promiseRequest = ({ method, url, data }) => {
  return getStorage("token")
    .then(token => {
      return wxRequest({
        method,
        url,
        data,
        header: {
          "content-type": "application/json",
          token: token
        }
      });
    })
    .then(res => res.data)
    .catch(err => {
      wx.switchTab({
        url: "/pages/login/index"
      });
      throw err;
    });
};

export const promiseUploadFile = ({ filePath, name, url }) => {
  return getStorage("token")
    .then(token => {
      return wxUploadFile({
        filePath,
        name,
        url,
        header: {
          token: token
        }
      });
    })
    .then(res => res.data)
    .catch(err => {
      wx.switchTab({
        url: "/pages/login/index"
      });
      throw err;
    });
};

export const promiseWebsocket = () => {
  const app = getApp();
  return getStorage("token").then(token => {
    return WebsocketHeartbeat({
      miniprogram: wx,
      connectSocketParams: {
        url: `ws://${app.globalData.ONLINE_HOSPITAL_GATEWAY_HOST}/inquiry/chat/${token}`
      },
      pingTimeout: 2000, //心跳时间
      pongTimeout: 3000, //自动关闭时间
      reconnectTimeout: 2000, // 重连时间
      pingMsg: "heart"
    });
  });
};

function getStorage(key) {
  return new Promise((resolve, reject) => {
    wx.getStorage({
      key: key,
      success: res => resolve(res.data),
      fail: reject
    });
  });
}

function wxRequest(options) {
  return new Promise((resolve, reject) => {
    wx.request({
      ...options,
      success: res => resolve(res),
      fail: reject
    });
  });
}

function wxUploadFile(options) {
  return new Promise((resolve, reject) => {
    wx.uploadFile({
      ...options,
      success: res => resolve(res),
      fail: reject
    });
  });
}
