<template>
  <div class="container">
    <div class="header">
      <el-page-header @back="goBack">
        <template #content>
          <span class="text-small font-600 mr-3" style="font-size: 14px">就诊人详情</span>
        </template>
      </el-page-header>
    </div>
    <div class="patientInfo">
      <el-card style="width: 1200px; margin-top: 30px" shadow="always">
        <el-descriptions class="margin-top" title="就诊人信息" :column="2" :size="size" border>
          <!-- <template #extra>
            <div class="selectbutton">
              <el-button @click="editPatientClick" round color="#53B9BD"
                ><el-icon color="white"><Edit /></el-icon
                ><span style="color: #fff; font-size: 5px"
                  >修改</span
                ></el-button
              >
            </div>
          </template> -->
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <patient />
                </el-icon>
                姓名
              </div>
            </template>
            {{ patientInfo.name }}
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
            {{ patientInfo.sex == 1 ? "男" : "女" }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <iphone />
                </el-icon>
                出生日期
              </div>
            </template>
            {{ patientInfo.birthdate }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <iphone />
                </el-icon>
                婚否
              </div>
            </template>
            {{ patientInfo.isMarry == 0 ? "否" : "是" }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <iphone />
                </el-icon>
                省
              </div>
            </template>
            {{ patientInfo.provinceName }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <iphone />
                </el-icon>
                市
              </div>
            </template>
            {{ patientInfo.cityName }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <iphone />
                </el-icon>
                区
              </div>
            </template>
            {{ patientInfo.districtName }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <iphone />
                </el-icon>
                电话
              </div>
            </template>
            {{ patientInfo.phone }}
          </el-descriptions-item>
          <!-- <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <iphone />
                </el-icon>
                联系人
              </div>
            </template>
            {{ patientInfo.contactsName }}
          </el-descriptions-item>
           <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <iphone />
                </el-icon>
                联系人电话
              </div>
            </template>
            {{ patientInfo.contactsPhone }}
          </el-descriptions-item> -->
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <iphone />
                </el-icon>
                就诊卡号
              </div>
            </template>
            {{ patientInfo.cardNo }}
          </el-descriptions-item>

          <!-- <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <tickets />
                </el-icon>
                状态
              </div>
            </template>
            <el-tag type="success" v-if="patientInfo.status == 1">已认证</el-tag>
            <el-tag type="danger" v-else>未认证</el-tag>
          </el-descriptions-item> -->
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <office-building />
                </el-icon>
                创建时间
              </div>
            </template>
            {{ patientInfo.createTime }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <office-building />
                </el-icon>
                上次修改时间
              </div>
            </template>
            {{ patientInfo.updateTime }}
          </el-descriptions-item>
        </el-descriptions>
        <el-dialog v-model="editPatientFormVisible" title="修改就诊人" width="500">
          <el-form :model="editForm">
            <el-form-item label="姓名" :label-width="formLabelWidth">
              <el-input v-model="editForm.name" autocomplete="off" />
            </el-form-item>
            <el-form-item label="性别" :label-width="formLabelWidth">
              <el-input v-model="editForm.sex" autocomplete="off" />
            </el-form-item>
            <el-form-item label="出生日期" :label-width="formLabelWidth">
              <el-input v-model="editForm.birthdate" autocomplete="off" />
            </el-form-item>
            <el-form-item label="婚否" :label-width="formLabelWidth">
              <el-input v-model="editForm.isMarry" autocomplete="off" />
            </el-form-item>
            <el-form-item label="省" :label-width="formLabelWidth">
              <el-input v-model="editForm.provinceName" autocomplete="off" />
            </el-form-item>
            <el-form-item label="市" :label-width="formLabelWidth">
              <el-input v-model="editForm.cityName" autocomplete="off" />
            </el-form-item>
            <el-form-item label="区" :label-width="formLabelWidth">
              <el-input v-model="editForm.districtName" autocomplete="off" />
            </el-form-item>
            <el-form-item label="电话" :label-width="formLabelWidth">
              <el-input v-model="editForm.phone" autocomplete="off" />
            </el-form-item>
            <el-form-item label="联系人" :label-width="formLabelWidth">
              <el-input v-model="editForm.contactsName" autocomplete="off" />
            </el-form-item>
            <el-form-item label="联系人电话" :label-width="formLabelWidth">
              <el-input v-model="editForm.contactsPhone" autocomplete="off" />
            </el-form-item>
          </el-form>
          <template #footer>
            <div class="dialog-footer">
              <el-button @click="editPatientFormVisible = false">取消</el-button>
              <el-button type="primary" @click="confirmEditpatient">确定</el-button>
            </div>
          </template>
        </el-dialog>
      </el-card>
    </div>
  </div>
</template>

<script setup lang="ts">
import store from "@/vuex";
import { ref, onMounted, reactive, computed } from "vue";
import { queryPatientInfoById, editPatientInfo } from "@/api/user/patient";
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";
//路由跳转
const router = useRouter();
//当前页就诊人id
const patientId = ref();
//就诊人信息
const patientInfo = ref({});
//是否展示修改就诊人表单
const editPatientFormVisible = ref(false);
//修改就诊人表单数据
const editForm = ref({});
onMounted(() => {
  patientId.value = store.getters.patientDetailId;
  queryPatientInfo();
});
//查询就诊人信息
const queryPatientInfo = async () => {
  let result = await queryPatientInfoById(patientId.value);
  if (result.code == 1) {
    patientInfo.value = result.data;
  }
};
//点击修改就诊人
const editPatientClick = () => {
  editPatientFormVisible.value = true;
  editForm.value = { ...patientInfo.value };
};
//确认修改就诊人
const confirmEditpatient = async () => {
  let result = await editPatientInfo(editForm.value);
  if (result.code == 1) {
    ElMessage({
      message: "修改成功",
      type: "success"
    });
    editPatientFormVisible.value = false;
    queryPatientInfo();
  }
};
//返回用户管理
const goBack = () => {
  router.push("/home/patientmanagement");
};
</script>

<style lang="scss" scoped>
.container {
  display: flex;
  flex-direction: column;
  min-height: 800px;
  .header {
    margin: 10px 0 0 10px;
    width: 1200px;
  }
}
</style>
