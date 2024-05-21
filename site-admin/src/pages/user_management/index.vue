<template>
  <div class="container">
    <div class="header">
      <div class="homeIcon">
        <el-icon><UserFilled /></el-icon>
      </div>
      <div class="home">用户管理</div>
    </div>
    <div class="select">
      <div class="selectbody">
        <div class="name">
          <span>昵称：</span>
          <el-input v-model="username" class="input" placeholder="请输入" clearable />
        </div>
        <div class="phone">
          <span>手机号：</span>
          <el-input v-model="phone" class="input" placeholder="请输入" clearable />
        </div>
        <div class="status">
          <span>状态：</span>
          <el-select v-model="status" placeholder="请选择" style="width: 240px">
            <el-option v-for="item in statusDictionary" :key="item.code" :label="item.disc" :value="item.code" />
          </el-select>
        </div>
      </div>
      <div class="selectbutton">
        <el-button @click="queryUser" round color="#53B9BD">
          <el-icon color="#fff"><Search /></el-icon>
          <span style="color: #fff; font-size: 5px">查询</span>
        </el-button>
      </div>
    </div>
    <div class="content">
      <div class="table">
        <el-table
          :data="tableData"
          style="width: 100%"
          max-height="600"
          stripe
          :header-cell-style="{ background: '#ECF3FE' }"
        >
          <el-table-column fixed prop="username" label="昵称" width="150">
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <el-icon><User /></el-icon>
                <span style="margin-left: 10px">{{ scope.row.username }}</span>
              </div>
            </template>
          </el-table-column>

          <el-table-column prop="phone" label="电话" width="150">
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <el-icon><Iphone /></el-icon>
                <span style="margin-left: 10px">{{ scope.row.phone }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="头像" width="150">
            <template #default="scope">
              <el-avatar :src="scope.row.avatar" />
            </template>
          </el-table-column>
          <el-table-column label="状态" width="120">
            <template #default="scope">
              <el-tag type="success" v-if="scope.row.status == 1">启用</el-tag>
              <el-tag type="danger" v-else>禁用</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="创建时间" width="220">
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <el-icon><timer /></el-icon>
                <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="modifiedTime" label="上次修改时间" width="220">
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <el-icon><timer /></el-icon>
                <span style="margin-left: 10px">{{ scope.row.modifiedTime }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column fixed="right" label="选项" width="80">
            <template #default="scope">
              <el-button link type="primary" size="small" @click="detailClick(scope.row.id)">详情</el-button>
              <!-- <el-button
                link
                type="danger"
                size="small"
                @click.prevent="deleteClick(scope.row.id)"
              >
                删除
              </el-button>
              <el-button
                link
                type="danger"
                size="small"
                @click.prevent="disenableClick(scope.row.id)"
                v-if="scope.row.status == 1"
              >
                禁用
              </el-button> -->
              <!-- <el-button
                link
                type="success"
                size="small"
                @click.prevent="enableClick(scope.row.id)"
                v-else
              >
                启用
              </el-button> -->
            </template>
          </el-table-column>
        </el-table>
        <el-dialog v-model="deleteDialogVisible" title="警告" width="350" align-center>
          <span>您确定要删除该用户吗?</span>
          <template #footer>
            <div class="dialog-footer">
              <el-button @click="cancleDeleteUser">取消</el-button>
              <el-button type="primary" @click="deleteUser">确定</el-button>
            </div>
          </template>
        </el-dialog>
        <el-dialog v-model="enableDialogVisible" title="提示" width="350" align-center>
          <span>您确定要启用该用户吗?</span>
          <template #footer>
            <div class="dialog-footer">
              <el-button @click="cancleEnableUser">取消</el-button>
              <el-button type="primary" @click="enableUser">确定</el-button>
            </div>
          </template>
        </el-dialog>
        <el-dialog v-model="disenableDialogVisible" title="提示" width="350" align-center>
          <span>您确定要禁用该用户吗?</span>
          <template #footer>
            <div class="dialog-footer">
              <el-button @click="cancleDisenableUser">取消</el-button>
              <el-button type="primary" @click="disenableUser">确定</el-button>
            </div>
          </template>
        </el-dialog>
      </div>
      <div class="selectpage">
        <div class="demo-pagination-block">
          <el-pagination
            v-model:current-page="currentPage"
            v-model:page-size="pageSize"
            :page-sizes="pageSizes"
            :disabled="disabled"
            :background="background"
            layout="total, sizes, prev, pager, next, jumper"
            :total="totalNum"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, reactive } from "vue";
import { reqDictionary } from "@/api/system/system";
import { queryUserInfo, deleteUserById, enableUserById, disenableUserById } from "@/api/user/user";
import request from "@/utils/request";
import { ElMessage } from "element-plus";
import { useRouter, useRoute } from "vue-router";
import store from "@/vuex";
//查询用户条件:用户名、电话、账号状态
const username = ref("");
const phone = ref("");
const status = ref("");
//用户状态字典
const statusDictionary = ref([]);
//当前页
const currentPage = ref(1);
//页大小
const pageSize = ref(10);
//可选页大小
const pageSizes = ref([5, 10, 20, 40]);
//用户数据总条数
const totalNum = ref(10);
//用户表格
const tableData = ref([]);
//是否展示删除对话框
const deleteDialogVisible = ref(false);
//要删除用户的id
const deleteUserId = ref(-1);
//是否展示启用对话框
const enableDialogVisible = ref(false);
//要启用的用户id
const enableUserId = ref(-1);
//是否展示禁用对话框
const disenableDialogVisible = ref(false);
//要禁用的用户id
const disenableUserId = ref(-1);
//用户状态数据字典的请求参数
const dictionaryCodeUser = import.meta.env.VITE_DICTIONARY_USER_STATUS;
//路由跳转
const router = useRouter();
const route = useRoute();
onMounted(() => {
  getUserStatusDictionary();
  queryUser();
});
//查询用户状态字典
const getUserStatusDictionary = async () => {
  let result = await reqDictionary(dictionaryCodeUser);
  if (result.code == 1) {
    //将Json数据转换为对象，传给statusDictionary
    statusDictionary.value = JSON.parse(result.data.itemValues);
  }
};
//查询用户信息
const queryUser = async () => {
  //查询数据的查询体
  console.log(status.value);

  const queryUserParam = {
    phone: phone.value,
    status: status.value,
    userName: username.value
  };

  let result = await queryUserInfo(currentPage.value, pageSize.value, queryUserParam);
  if (result.code == 1) {
    tableData.value = result.data.items;
    totalNum.value = result.data.counts;
  }
};

//页大小改变
const handleSizeChange = () => {
  queryUser();
};
//页码改变
const handleCurrentChange = () => {
  queryUser();
};
//点击删除
const deleteClick = id => {
  deleteDialogVisible.value = true;
  deleteUserId.value = id;
};
//取消删除用户
const cancleDeleteUser = () => {
  deleteDialogVisible.value = false;
  deleteUserId.value = -1;
};
//删除用户
const deleteUser = async () => {
  let result = await deleteUserById(deleteUserId.value);
  if (result.code == 1) {
    ElMessage({
      message: "删除成功",
      type: "success"
    });
    queryUser();
  }
  deleteDialogVisible.value = false;
};
//点击启用
const enableClick = id => {
  enableDialogVisible.value = true;
  enableUserId.value = id;
};
//取消启用用户
const cancleEnableUser = () => {
  enableDialogVisible.value = false;
  enableUserId.value = -1;
};
//启用用户
const enableUser = async () => {
  let result = await enableUserById(enableUserId.value);
  if (result.code == 1) {
    ElMessage({
      message: "启用成功",
      type: "success"
    });
    queryUser();
  }
  enableDialogVisible.value = false;
};
//点击禁用
const disenableClick = id => {
  disenableDialogVisible.value = true;
  disenableUserId.value = id;
};
//取消禁用用户
const cancleDisenableUser = () => {
  disenableDialogVisible.value = false;
  disenableUserId.value = -1;
};
//禁用用户
const disenableUser = async () => {
  let result = await disenableUserById(disenableUserId.value);
  if (result.code == 1) {
    ElMessage({
      message: "禁用成功",
      type: "success"
    });
    queryUser();
  }
  disenableDialogVisible.value = false;
};
//点击详情
const detailClick = id => {
  store.commit("changeUserDetailId", id);
  router.push("/home/userdetail");
};
</script>

<style scoped lang="scss">
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  .header {
    width: 100%;
    height: 20px;
    display: flex;

    margin-top: 10px;
    .homeIcon {
      height: 20px;
    }
    .home {
      height: 20px;
      margin: 2px 0 0 5px;
      text-align: center;
      font-size: 10px;
    }
  }
  .select {
    height: 70px;
    width: 100%;
    background-color: white;
    border: 1px solid #dedfe0;
    margin: 25px 0 5px 0;
    border-radius: 4px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    box-shadow: 0px 0px 0.1px 2px #e3ebff;

    .selectbody {
      width: 100%;
      padding: 0px 50px;
      height: 70px;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    .selectbutton {
      margin-right: 40px;
    }
    .selectpage {
      display: flex;
      width: 1100px;
      justify-content: left;
    }
    .input {
      width: 240px;
    }
  }
  .content {
    margin: 25px 0 0 0;
    width: 100%;
    box-shadow: 0px 0px 0.1px 2px #e3ebff;
    background-color: #fff;
    display: flex;
    flex-direction: column;
    align-items: center;
    .table {
      margin: 30px 0 0 0;
      width: 95%;
    }
    .selectpage {
      margin: 20px 0 20px 0;
    }
  }
}

::v-deep .el-input__wrapper {
  // background-color: #f4f4f5;//#f4f4f5  #ebedf0
}
::v-deep .el-select__wrapper {
  //background-color: #ebedf0;
}
</style>
