<template>
  <div class="rightside">
    <div class="header">
      <div class="imgText">
        <div class="userimg"></div>
        <h4>
          {{ chatMessage.toUserName }}
          <br />
        </h4>
      </div>
      <div>
        <el-button type="primary" @click="detail">详情</el-button>
      </div>
    </div>
    <el-scrollbar height="400px" ref="scrollbarRef" style="padding: 20px">
      <div ref="innerRef">
        <!-- recordContent 聊天记录数组-->
        <div v-for="(itemc, indexc) in chatMessage.messageVos" :key="indexc">
          <!-- 对方 -->
          <div class="word" v-if="!itemc.identity == 1">
            <img class="avatar" :src="chatMessage.toUserPicture" />
            <div class="info">
              <p class="time">{{ chatMessage.toUserName }} {{ itemc.sendTime }}</p>
              <div class="info-content">
                <div v-if="itemc.type == 0">{{ itemc.content }}</div>
                <div v-if="itemc.type == 1">
                  <el-image
                    style="width: 100px; height: 100px"
                    :src="itemc.content"
                    :zoom-rate="1.2"
                    :max-scale="7"
                    :min-scale="0.2"
                    :preview-src-list="[itemc.content]"
                    fit="cover"
                  />
                </div>
              </div>
            </div>
          </div>
          <!-- 我的 -->
          <div class="word-my" v-else>
            <div class="info">
              <p class="time">{{ itemc.nickName }} {{ itemc.sendTime }}</p>
              <div class="info-content">
                <div v-if="itemc.type == 0">{{ itemc.content }}</div>
                <div v-if="itemc.type == 1">
                  <el-image
                    style="width: 100px; height: 100px"
                    :src="itemc.content"
                    :zoom-rate="1.2"
                    :max-scale="7"
                    :min-scale="0.2"
                    :preview-src-list="[itemc.content]"
                    fit="cover"
                  />
                </div>
              </div>
            </div>
            <img class="avatar" :src="chatMessage.fromUserPicture" />
          </div>
        </div>
      </div>
    </el-scrollbar>
    <div class="divider"></div>
    <div class="chatbox_input">
      <div>
        <el-input v-model="message" style="width: 100%" autosize type="textarea" placeholder="请输入" resize="none" />
      </div>
      <div style="display: flex; width: 100%; justify-content: flex-end; margin-top: 5px">
        <el-button type="primary" @click="send">发送</el-button>
      </div>
    </div>
  </div>
  <el-dialog v-model="dialogDetailVisible" title="详情" width="1000">
    <el-descriptions class="margin-top" :column="3" size="default" border>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon :style="iconStyle">
              <user />
            </el-icon>
            就诊人
          </div>
        </template>
        {{ applicationDetail.patientName }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon :style="iconStyle">
              <iphone />
            </el-icon>
            性别
          </div>
        </template>
        {{ applicationDetail.sex == 0 ? "女" : "男" }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon :style="iconStyle">
              <location />
            </el-icon>
            出生日期
          </div>
        </template>
        {{ applicationDetail.birthdate }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon :style="iconStyle">
              <tickets />
            </el-icon>
            婚否
          </div>
        </template>
        <el-tag size="small">{{ applicationDetail.isMarry == 0 ? "否" : "是" }}</el-tag>
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon :style="iconStyle">
              <office-building />
            </el-icon>
            申请时间
          </div>
        </template>
        {{ applicationDetail.createTime }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon :style="iconStyle">
              <office-building />
            </el-icon>
            就诊卡号
          </div>
        </template>
        {{ applicationDetail.cardNo }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon :style="iconStyle">
              <office-building />
            </el-icon>
            病情描述
          </div>
        </template>
        <div style="height: auto; width: 800px">
          {{ applicationDetail.description }}
        </div>

        <div class="demo-image__preview">
          <el-image
            style="width: 150px; height: 150px"
            :src="applicationDetail.image"
            :zoom-rate="1.2"
            :max-scale="7"
            :min-scale="0.2"
            :preview-src-list="[applicationDetail.image]"
            :initial-index="0"
            fit="cover"
            v-if="applicationDetail.image != ''"
          />
        </div>
      </el-descriptions-item>
    </el-descriptions>
  </el-dialog>
</template>
<script setup>
import { inquiryChatMessage, sendMessage } from "@/api/inquiry/chat_message";
import { ref, onMounted, onBeforeUnmount, onBeforeMount } from "vue";
import { clearUnread, inWindow, outWindow } from "@/api/inquiry/chat_list";
import { onBeforeRouteUpdate, useRoute, onBeforeRouteLeave } from "vue-router";
import webSocket from "@/utils/websocket";
import { inquiryWaitingApplicationDetail } from "@/api/inquiry/inquiry_application";
const dialogDetailVisible = ref(false);
const route = useRoute();
const linkId = ref(route.query.id);
const chatMessage = ref({});
const message = ref("");
const innerRef = ref();
const scrollbarRef = ref({});
const applicationDetail = ref({});
const srcList = ref([]);
import { defineEmits } from "vue";
const emit = defineEmits(["queryList"]);
const queryChatMessage = async () => {
  let result = await inquiryChatMessage(linkId.value);
  if (result.code == 1) {
    chatMessage.value = result.data;
  }
  setTimeout(() => {
    scrollbarRef.value.setScrollTop(innerRef.value.clientHeight);
  }, 20);
};
const detail = async () => {
  let result = await inquiryWaitingApplicationDetail(chatMessage.value.inquiryApplicationId);
  if (result.code == 1) {
    applicationDetail.value = result.data;
    srcList.value[0] = result.data.image;
  }
  dialogDetailVisible.value = true;
};
const inWindowInitial = async () => {
  await clearUnread(linkId.value);
  await inWindow(linkId.value);
};
onBeforeMount(() => {
  const webSocketUrl = process.env.VUE_APP_WS_CHAT;
  webSocket.webSocketInit(webSocketUrl); //初始化webSocket
  webSocket.setOpenCallback(res => {
    console.log("连接建立成功", res);
  });
  webSocket.setMessageCallback(res => {
    console.log("接收到回信1", res);
    //对接收到的数据进行处理
    console.log(1);
    queryChatMessage();
    emit("queryList");
    console.log(123);
  });
});
onMounted(() => {
  inWindowInitial();
  queryChatMessage();
});
const send = async () => {
  const addChatMessageDto = {
    linkId: linkId.value,
    inquiryApplicationId: chatMessage.value.inquiryApplicationId,
    toUserId: chatMessage.value.toUserId,
    content: message.value,
    type: 0
  };
  await sendMessage(addChatMessageDto);
  message.value = "";
  queryChatMessage();
  const wsMessage = {
    linkId: linkId.value,
    inquiryApplicationId: chatMessage.value.inquiryApplicationId,
    toUserId: chatMessage.value.toUserId,
    toUserIdentity: 0
  };
  webSocket.sendMessage(JSON.stringify(wsMessage));
};
onBeforeRouteUpdate(async (to, from, next) => {
  const id = from.query.id;
  if (id != undefined) {
    await outWindow(id);

    linkId.value = to.query.id;
  }
  inWindowInitial();
  queryChatMessage();
  next();
});
onBeforeRouteLeave(async (to, from, next) => {
  const id = from.query.id;
  if (id != undefined) {
    await outWindow(id);
  }
  next();
});
onBeforeUnmount(() => {
  console.log("离开chat");
});
</script>

<style scoped>
.rightside {
  position: relative;
  flex: 70%;
  background: white;
  width: 100%;
  border-radius: 15px 15px 0% 0%;
}
/* .rightside::before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  opacity: 0.06;
} */

.header {
  position: relative;
  width: 100%;
  height: 60px;
  background: #2b3d63;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 15px;
  box-sizing: border-box;
  border-radius: 15px 15px 0% 0%;
  color: white;
}

.chatbox_input {
  position: relative;
  width: 100%;
  min-height: 50px;
  background: white;
  padding: 15px;
  justify-content: space-between;
  align-items: center;
  box-sizing: border-box;
}

/* :hover {
    overflow-y: auto;
}
::-webkit-scrollbar {
    width: 10px;
    background-color: #F5F5F5;
}

::-webkit-scrollbar-thumb {
    border-radius: 10px;
    
}

::-webkit-scrollbar-track {
    background-color: #F5F5F5;
    border-radius: 10px;
} */
.word {
  display: flex;
  margin-bottom: 20px;
}
.avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
}
.word .info {
  margin-left: 10px;
}
.word .info .time {
  font-size: 12px;
  color: rgba(51, 51, 51, 0.8);
  margin: 0;
  height: 20px;
  line-height: 20px;
  margin-top: -5px;
}
.word .info .info-content {
  padding: 10px;
  font-size: 14px;
  background: #e8e8e8;
  position: relative;
  margin-top: 8px;
  table-layout: fixed;
  word-break: break-all;
}
.word .info .info-content::before {
  position: absolute;
  left: -8px;
  top: 8px;
  content: "";
  border-right: 10px solid #e8e8e8;
  border-top: 8px solid transparent;
  border-bottom: 8px solid transparent;
}
.word-my {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 20px;
}

.word-my .info {
  width: 90%;
  margin-left: 10px;
  text-align: right;
}
.word-my .info .time {
  font-size: 12px;
  color: rgba(51, 51, 51, 0.8);
  margin: 0;
  height: 20px;
  line-height: 20px;
  margin-top: -5px;
  margin-right: 10px;
}
.word-my .info .info-content {
  max-width: 70%;
  padding: 10px;
  font-size: 14px;
  float: right;
  margin-right: 10px;
  position: relative;
  margin-top: 8px;
  background: #a3c3f6;
  text-align: left;
  table-layout: fixed;
  word-break: break-all;
}
.word-my .info .info-content::after {
  position: absolute;
  right: -8px;
  top: 8px;
  content: "";
  border-left: 10px solid #a3c3f6;
  border-top: 8px solid transparent;
  border-bottom: 8px solid transparent;
}
.divider {
  width: 100%;
  height: 1px;
  background-color: rgb(214, 213, 213);
}
:deep(.el-textarea__inner) {
  box-shadow: 0 0 0 0px;
}
:deep(.el-textarea__inner:hover) {
  box-shadow: 0 0 0 0px;
}
:deep(.el-textarea__inner:focus) {
  box-shadow: 0 0 0 0px;
}
</style>
