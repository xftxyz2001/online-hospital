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
