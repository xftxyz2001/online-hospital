<template>
  <div style="display: flex; flex-direction: column">
    <el-button style="margin-left: auto; margin-bottom: 10px" @click="refresh">刷新</el-button>
    <el-empty description="暂无信息" v-if="applicationList.length == 0" image-size="200"></el-empty>
    <el-scrollbar height="500px">
      <p v-for="item in applicationList" :key="item.id" class="scrollbar-demo-item">
        <span style="display: online-block; margin-left: 10px">就诊人：{{ item.patientName }}</span>
        <span>挂号时间：{{ item.createTime }}</span>
        <span>
          <el-button type="primary" @click="detail(item.id)">详情</el-button>
          <el-button type="success" @click="receptClick(item.id)">接诊</el-button>
        </span>
      </p>
    </el-scrollbar>
  </div>
  <el-dialog v-model="detailDialogVisible" title="详情" width="1000" align-center>
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
        <div style="height: auto; width: 800px">{{ applicationDetail.description }}</div>

        <div class="demo-image__preview">
          <el-image
            style="width: 150px; height: 150px"
            :src="applicationDetail.image"
            :zoom-rate="1.2"
            :max-scale="7"
            :min-scale="0.2"
            :preview-src-list="srcList"
            :initial-index="0"
            fit="cover"
          />
        </div>
      </el-descriptions-item>
    </el-descriptions>
  </el-dialog>
  <el-dialog v-model="receptDialogVisible" title="提示" width="500" align-center>
    <span>确定要接诊吗？</span>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="confirmRecept">确定</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import {
  inquiryWaitingApplicationList,
  inquiryWaitingApplicationDetail,
  receptById
} from "@/api/inquiry/inquiry_application";
import { ref, onMounted } from "vue";
import { ElMessage } from "element-plus";
//import store from "@/vuex";

const applicationList = ref([]);
const applicationDetail = ref({});
const detailDialogVisible = ref(false);
const receptDialogVisible = ref(false);
const receptId = ref(0);
const srcList = ref([]);

const queryInquiryApplicationList = async () => {
  let result = await inquiryWaitingApplicationList();
  if (result.code == 1) {
    applicationList.value = result.data;
  }
};
const detail = async id => {
  let result = await inquiryWaitingApplicationDetail(id);
  if (result.code == 1) {
    applicationDetail.value = result.data;
    srcList.value[0] = result.data.image;
  }
  detailDialogVisible.value = true;
};
const receptClick = id => {
  receptDialogVisible.value = true;
  receptId.value = id;
};
const confirmRecept = async () => {
  let result = await receptById(receptId.value);
  if (result.code == 1) {
    ElMessage({
      message: "接诊成功",
      type: "success"
    });
    receptDialogVisible.value = false;
    queryInquiryApplicationList();
  }
};
onMounted(() => {
  queryInquiryApplicationList();
});
const refresh = () => {
  queryInquiryApplicationList();
};
</script>

<style scoped>
.scrollbar-demo-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 50px;
  margin: 10px;
  text-align: center;
  border-radius: 4px;
  background: var(--el-color-primary-light-9);
  color: var(--el-color-primary);
}
.demo-image__error .image-slot {
  font-size: 30px;
}
.demo-image__error .image-slot .el-icon {
  font-size: 30px;
}
.demo-image__error .el-image {
  width: 100%;
  height: 200px;
}
</style>
