<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="200px">
        <el-scrollbar height="500px">
          <el-empty description="暂无问诊" v-if="chatList.length == 0" :image-size="100" />
          <div class="chatlist" v-for="(item, index) in chatList" :key="item.id" @click="toChat(item.linkId, index)">
            <div class="block unread">
              <div class="imgbx">
                <el-avatar :size="50" :src="item.toUserPicture" />
              </div>
              <div class="details">
                <div class="listHead">
                  <h4>{{ item.toUserName }}</h4>
                  <p class="time">{{ item.lastTime }}</p>
                </div>
                <div class="message_p">
                  <p style="width: 100px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap">
                    {{ item.lastMessage }}
                  </p>
                  <b v-if="item.unread != 0">{{ item.unread }}</b>
                </div>
              </div>
            </div>
          </div>
        </el-scrollbar>
      </el-aside>
      <el-main>
        <el-empty description="请选择对话" v-if="showEmpty" />
        <router-view @queryList="queryChatList"></router-view>
      </el-main>
    </el-container>
  </div>
</template>

<script setup>
import { inquiryChatList } from "@/api/inquiry/chat_list";
import { ref, onMounted, onBeforeUnmount, onBeforeMount } from "vue";
import { useRouter } from "vue-router";
import webSocket from "@/utils/websocket";
const chatList = ref([]);
const router = useRouter();
const showEmpty = ref(true);
const queryChatList = async () => {
  console.log(2345);
  let result = await inquiryChatList();
  if (result.code == 1) {
    chatList.value = result.data;
  }
};
onBeforeMount(() => {
  webSocket.webSocketInit(); //初始化webSocket
  webSocket.setOpenCallback(res => {
    console.log("连接建立成功", res);
  });
  webSocket.setMessageCallback(res => {
    console.log("接收到回信", res);
    //对接收到的数据进行处理
    queryChatList();
  });
});
onMounted(() => {
  queryChatList();

  if (router.currentRoute.value.path == "/home/inquirymanagement/inprogress/chatframe") showEmpty.value = false;
});
const toChat = (linkId, index) => {
  chatList.value[index].unread = 0;
  showEmpty.value = false;
  router.push({
    path: "/home/inquirymanagement/inprogress/chatframe",
    query: {
      id: linkId
    }
  });
};
onBeforeUnmount(() => {
  console.log("离开inprogress");
  showEmpty.value = true;
});
</script>

<style scoped>
.scrollbar-demo-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  margin: 10px;
  text-align: center;
  border-radius: 4px;
  background: var(--el-color-primary-light-9);
  color: var(--el-color-primary);
}
.chatlist {
  position: relative;
  height: calc(100%-110px);
  overflow-y: auto;
  margin-bottom: 8px;
}
.chatlist .block {
  position: relative;
  width: 100%;
  display: flex;
  align-items: center;

  border-bottom: 1px solid rgba(0, 0, 0, 0.06);
}
.chatlist .block.active {
  background: #ebebeb;
}
.chatlist .block:hover {
  background: #f5f5f5;
}
.chatlist .block .imgbx {
  position: relative;
  min-width: 45px;
  height: 45px;
  overflow: hidden;
  border-radius: 50%;
  margin-right: 10px;
}
.chatlist .block .details {
  position: relative;
  width: 100%;
}
.chatlist .block .details .listHead {
  display: flex;
  justify-content: space-between;
  margin-bottom: 5px;
}
.chatlist .block .details .listHead h4 {
  font-size: 1.1em;
  font-weight: 600;
  color: #111;
  width: 100px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.chatlist .block .details .listHead .time {
  font-size: 0.75em;
  color: #aaa;
}
.chatlist .block .details .listHead .time {
  color: #111;
}

.message_p {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.message_p p {
  color: #aaa;
  display: -webkit-box;
  -webkit-line-break: 1;
  font-size: 0.9em;
  overflow: hidden;
  text-overflow: ellipsis;
}
.message_p b {
  background-color: red;
  color: #fff;
  min-width: 20px;
  height: 20px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 0.75em;
}
</style>
