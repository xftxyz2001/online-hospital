import { promiseRequest, promiseUploadFile } from "../utils/service";

const app = getApp();
const systemUrl = `http://${app.globalData.ONLINE_HOSPITAL_GATEWAY_HOST}/system`;

export default {
  uploadImage(filePath) {
    return promiseUploadFile({
      url: `${systemUrl}/upload`,
      filePath,
      name: "image"
    });
  }
};
