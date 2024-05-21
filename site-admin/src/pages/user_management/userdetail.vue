<template>
  <div class="container">
    <div class="header">
      <el-page-header @back="goBack">
        <template #content>
          <span class="text-small font-600 mr-3" style="font-size: 14px">用户详情</span>
        </template>
      </el-page-header>
    </div>

    <div class="userInfo">
      <el-card style="width: 1200px; margin-top: 30px; height: 240px" shadow="always">
        <el-descriptions class="margin-top" title="用户信息" :column="3" :size="size" border>
          <!-- <template #extra>
            <div class="selectbutton">
              <el-button @click="editUserClick" round color="#53B9BD"
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
                  <user />
                </el-icon>
                用户名
              </div>
            </template>
            {{ userInfo.username }}
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
            {{ userInfo.phone }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <location />
                </el-icon>
                头像
              </div>
            </template>
            <el-avatar :src="userInfo.avatar" :size="35" />
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <tickets />
                </el-icon>
                状态
              </div>
            </template>
            <el-tag type="success" v-if="userInfo.status == 1">启用</el-tag>
            <el-tag type="danger" v-else>禁用</el-tag>
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <office-building />
                </el-icon>
                创建时间
              </div>
            </template>
            {{ userInfo.createTime }}
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
            {{ userInfo.modifiedTime }}
          </el-descriptions-item>
        </el-descriptions>
        <el-dialog v-model="editUserFormVisible" title="修改用户" width="500">
          <el-form :model="editForm">
            <el-form-item label="用户名" :label-width="formLabelWidth">
              <el-input v-model="editForm.username" autocomplete="off" />
            </el-form-item>
            <el-form-item label="电话" :label-width="formLabelWidth">
              <el-input v-model="editForm.phone" autocomplete="off" />
            </el-form-item>
            <el-form-item label="头像" :label-width="formLabelWidth">
              <el-input v-model="editForm.avatar" autocomplete="off" />
            </el-form-item>
          </el-form>
          <template #footer>
            <div class="dialog-footer">
              <el-button @click="editUserFormVisible = false">取消</el-button>
              <el-button type="primary" @click="confirmEditUser">确定</el-button>
            </div>
          </template>
        </el-dialog>
      </el-card>
    </div>
    <div class="patientInfo">
      <el-card style="width: 1200px; margin-top: 30px" shadow="always">
        <div class="header" style="font-weight: 800; margin-left: -3px">就诊人</div>
        <div class="content">
          <div class="table" style="width: 100%">
            <el-table :data="patientInfo" stripe style="width: 100%" :header-cell-style="{ background: '#ECF3FE' }">
              <el-table-column fixed prop="name" label="姓名" width="150" />
              <el-table-column prop="certificatesType" label="证件类型" width="120" />
              <el-table-column label="性别" width="120">
                <template #default="scope">
                  {{ scope.row.sex == 0 ? "女" : "男" }}
                </template>
              </el-table-column>
              <el-table-column prop="birthdate" label="出生日期" width="120" />
              <el-table-column prop="isMarry" label="婚否" width="120">
                <template #default="scope">
                  {{ scope.row.isMarry == 0 ? "否" : "是" }}
                </template>
              </el-table-column>
              <el-table-column prop="provinceName" label="省" width="120" />
              <el-table-column prop="cityName" label="市" width="120" />
              <el-table-column prop="districtName" label="区" width="120" />
              <!-- <el-table-column prop="contactsName" label="联系人" width="120" />
              <el-table-column
                prop="contactsPhone"
                label="联系人电话"
                width="120"
              /> -->
              <el-table-column prop="cardNo" label="就诊卡号" width="200" />
              <!-- <el-table-column prop="status" label="认证状态" width="120">
                <template #default="scope">
                  <el-tag type="success" v-if="scope.row.status == 1"
                    >已认证</el-tag
                  >
                  <el-tag type="danger" v-else>未认证</el-tag>
                </template>
              </el-table-column> -->
              <el-table-column prop="createTime" label="创建时间" width="200" />
              <el-table-column prop="updateTime" label="上次修改时间" width="200" />

              <!-- <el-table-column fixed="right" label="Operations" width="120">
                <template #default="scope">
                  <el-button
                    link
                    type="primary"
                    size="small"
                    @click="detailClick(scope.row.id)"
                    >详情</el-button
                  >
                  <el-button link type="danger" size="small" @click="deleteClick(scope.row.id)">删除</el-button>
                </template>
              </el-table-column> -->
            </el-table>
          </div>
          <el-dialog v-model="deleteDialogVisible" title="警告" width="350" align-center>
            <span>您确定要删除该就诊人吗?</span>
            <template #footer>
              <div class="dialog-footer">
                <el-button @click="cancleDeletePatient">取消</el-button>
                <el-button type="primary" @click="confirmDeletePatient">确定</el-button>
              </div>
            </template>
          </el-dialog>
          <div class="pageSelect">
            <div class="demo-pagination-block">
              <el-pagination
                v-model:current-page="currentPage"
                v-model:page-size="pageSize"
                :page-sizes="pageSizes"
                :small="true"
                layout="total, sizes, prev, pager, next, jumper"
                :total="totalNum"
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
              />
            </div>
          </div>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script setup lang="ts">
import store from "@/vuex";
import { ref, onMounted, reactive, computed } from "vue";
import { queryUserInfoById, editUserInfo } from "@/api/user/user";
import { queryPatientListByUserId, deletePatientById } from "@/api/user/patient";
import { ElMessage } from "element-plus";
import { useRouter, useRoute } from "vue-router";
//路由跳转
const router = useRouter();
const route = useRoute();
//当前页用户id
const userId = ref();
//用户信息
const userInfo = ref({});
//当前页
const currentPage = ref(1);
//页大小
const pageSize = ref(10);
//可选页大小
const pageSizes = ref([5, 10, 20]);
//用户数据总条数
const totalNum = ref(10);
//就诊人信息
const patientInfo = ref([
  {
    date: "2016-05-03",
    name: "Tom",
    address: "No. 189, Grove St, Los Angeles"
  }
]);
//信息elementplus
const size = ref("default");
//是否显示修改用户表单
const editUserFormVisible = ref(false);
//修改用户表单
const editForm = ref({});
//要删除的就诊人的id
const deletePatientId = ref(-1);
//是否展示删除就诊人对话框
const deleteDialogVisible = ref(false);
const iconStyle = computed(() => {
  const marginMap = {
    large: "8px",
    default: "6px",
    small: "4px"
  };
  return {
    marginRight: marginMap[size.value] || marginMap.default
  };
});

onMounted(() => {
  userId.value = store.getters.userDetailId;

  queryUserInfo();
  queryPatientInfo();
});
//查询用户信息
const queryUserInfo = async () => {
  let result = await queryUserInfoById(userId.value);
  if (result.code == 1) {
    userInfo.value = result.data;
  }
};
//查询就诊人信息
const queryPatientInfo = async () => {
  let result = await queryPatientListByUserId(currentPage.value, pageSize.value, userId.value);
  if (result.code == 1) {
    patientInfo.value = result.data.items;
    console.log(result.data.items);
    totalNum.value = result.data.counts;
  }
};
//点击修改用户
const editUserClick = () => {
  //拷贝，而不是直接指向
  editForm.value = { ...userInfo.value };
  editUserFormVisible.value = true;
};
//确认修改用户
const confirmEditUser = async () => {
  let result = await editUserInfo(editForm.value);
  if (result.code == 1) {
    ElMessage({
      message: "修改成功",
      type: "success"
    });
    editUserFormVisible.value = false;
    queryUserInfo();
  }
};
//点击删除就诊人
const deleteClick = id => {
  deletePatientId.value = id;
  deleteDialogVisible.value = true;
};
//取消删除就诊人
const cancleDeletePatient = () => {
  deletePatientId.value = -1;
  deleteDialogVisible.value = false;
};
//确定删除就诊人
const confirmDeletePatient = async () => {
  let result = await deletePatientById(deletePatientId.value);
  if (result.code == 1) {
    ElMessage({
      message: "删除成功",
      type: "success"
    });
    deleteDialogVisible.value = false;
    queryPatientInfo();
  }
};
//点击就诊人详情
const detailClick = id => {
  store.commit("changePatientDetailId", id);
  router.push("/home/patientdetail");
};
//返回用户管理
const goBack = () => {
  router.push("/home/usermanagement");
};
</script>

<style scoped lang="scss">
.container {
  display: flex;
  flex-direction: column;
  min-height: 800px;
  .header {
    margin: 10px 0 0 10px;
    width: 1200px;
  }
  .patientInfo {
    .content {
      margin-top: 20px;
      display: flex;
      flex-direction: column;
      align-items: center;
      .pageSelect {
        margin-top: 20px;
        width: 100%;
        display: flex;
        justify-content: right;
      }
    }
  }
}
.el-descriptions {
  margin-top: 30px;
}
.cell-item {
  display: flex;
  align-items: center;
}
.margin-top {
  margin-top: 20px;
}
</style>
