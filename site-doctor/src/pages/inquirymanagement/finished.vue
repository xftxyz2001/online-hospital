<template>
  <div style="display: flex; flex-direction: column">
    <el-button style="margin-left: auto; margin-bottom: 10px">刷新</el-button>
    <el-empty description="暂无问诊" v-if="inquiryList.length == 0" image-size="200"></el-empty>
    <el-scrollbar height="500px">
      <el-card style="width: 95%; margin-bottom: 20px" shadow="hover" v-for="item in inquiryList" :key="item.id">
        <el-descriptions class="margin-top" title="问诊记录" :column="1" border size="default">
          <template #extra>
            <el-button type="primary" @click="clickDetail(item.id)">详情</el-button>
          </template>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <user />
                </el-icon>
                就诊人
              </div>
            </template>
            {{ item.patientName }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <tickets />
                </el-icon>
                创建时间
              </div>
            </template>
            {{ item.createTime }}
          </el-descriptions-item>

          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <location />
                </el-icon>
                状态
              </div>
            </template>
            <el-tag type="success">已完成</el-tag>
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <iphone />
                </el-icon>
                病情描述
              </div>
            </template>
            <div
              style="
                overflow: hidden;
                white-space: nowrap;
                text-overflow: ellipsis;
                -o-text-overflow: ellipsis;
                width: 300px;
              "
            >
              {{ item.description }}
            </div>
          </el-descriptions-item>
        </el-descriptions>
      </el-card>
    </el-scrollbar>
  </div>
  <el-dialog v-model="dialogdetailVisible" title="问诊详情" width="1000">
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
    <el-card shadow="never" style="margin-top: 20px">
      <template #header>
        <div class="card-header">
          <span>详情</span>
        </div>
        <el-scrollbar height="400px" ref="scrollbarRef" style="padding: 30px 20px; margin-top: 20px">
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
      </template>
    </el-card>
  </el-dialog>
</template>
<script setup>
import { onMounted, ref } from "vue";
import { inquiryAllFinished } from "@/api/inquiry/inquiry_application.js";
import { inquiryFinishedById } from "@/api/inquiry/inquiry_application";
import { inquiryChatMessage } from "@/api/inquiry/chat_message";
const inquiryList = ref([]);
const dialogdetailVisible = ref(false);
const applicationDetail = ref({});
const chatMessage = ref({});
//请求所有完成的问诊
const queryAllInquiry = async () => {
  let res = await inquiryAllFinished();
  if (res.code == 1) {
    inquiryList.value = res.data;
  }
};
const clickDetail = id => {
  dialogdetailVisible.value = true;
  detail(id);
};
const queryChatMessage = async id => {
  let result = await inquiryChatMessage(id);
  if (result.code == 1) {
    chatMessage.value = result.data;
  }
};
onMounted(() => {
  queryAllInquiry();
});
const detail = async id => {
  let result = await inquiryFinishedById(id);
  if (result.code == 1) {
    applicationDetail.value = result.data;

    queryChatMessage(result.data.linkId);
  }
  dialogdetailVisible.value = true;
};
</script>

<style scoped>
.el-descriptions {
  margin-top: 20px;
}
.cell-item {
  display: flex;
  align-items: center;
}
.margin-top {
  margin-top: 20px;
}
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
