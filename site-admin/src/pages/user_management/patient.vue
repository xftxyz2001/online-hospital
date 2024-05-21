<template>
  <div class="container">
    <div class="header">
      <div class="homeIcon">
        <el-icon><UserFilled /></el-icon>
      </div>
      <div class="home">就诊人管理</div>
    </div>
    <div class="select">
      <div class="selectbody">
        <div class="name">
          <span>姓名：</span>
          <el-input v-model="name" class="input" placeholder="请输入" clearable />
        </div>
        <div class="phone">
          <span>手机号：</span>
          <el-input v-model="phone" class="input" placeholder="请输入" clearable />
        </div>
        <div>
          <span>性别：</span>
          <el-select v-model="sex" placeholder="请选择" style="width: 240px">
            <el-option v-for="item in sexDictionary" :key="item.code" :label="item.disc" :value="item.code" />
          </el-select>
        </div>
      </div>
      <div class="selectbutton">
        <el-button @click="queryPatient" round color="#53B9BD">
          <el-icon color="#fff"><Search /></el-icon>
          <span style="color: #fff; font-size: 5px">查询</span>
        </el-button>
      </div>
    </div>
    <div class="content">
      <div class="table" style="width: 100%">
        <el-table :data="tableData" stripe style="width: 100%" :header-cell-style="{ background: '#ECF3FE' }">
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

          <el-table-column fixed="right" label="选项" width="80">
            <template #default="scope">
              <el-button link type="primary" size="small" @click="detailClick(scope.row.id)">详情</el-button>
              <!-- <el-button link type="danger" size="small" @click="deleteClick(scope.row.id)">删除</el-button> -->
            </template>
          </el-table-column>
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
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, reactive } from "vue";
import { reqUserStatusDictionary } from "@/api/system/system";
import { queryPatientInfo, deletePatientById } from "@/api/user/patient";
import { ElMessage } from "element-plus";
import { useRouter, useRoute } from "vue-router";
import store from "@/vuex";
//查询就诊人条件:就诊人名、电话、账号状态
const name = ref("");
const phone = ref("");
const sex = ref("");
//性别字典
const sexDictionary = ref([
  {
    disc: "男",
    code: 1
  },
  {
    disc: "女",
    code: 0
  }
]);
//当前页
const currentPage = ref(1);
//页大小
const pageSize = ref(10);
//可选页大小
const pageSizes = ref([5, 10, 20, 40]);
//就诊人数据总条数
const totalNum = ref(10);
//就诊人表格
const tableData = ref([]);
//是否展示删除对话框
const deleteDialogVisible = ref(false);
//要删除就诊人的id
const deletePatientId = ref(-1);
//路由跳转
const router = useRouter();
const route = useRoute();
onMounted(() => {
  getPatientStatusDictionary();
  queryPatient();
});
//查询就诊人状态字典
const getPatientStatusDictionary = async () => {
  let result = await reqUserStatusDictionary();
  if (result.code == 1) {
    //将Json数据转换为对象，传给statusDictionary
    statusDictionary.value = JSON.parse(result.data.itemValues);
  }
};
//查询就诊人信息
const queryPatient = async () => {
  //查询数据的查询体
  console.log(status.value);

  const queryPatientParam = {
    phone: phone.value,
    sex: sex.value,
    name: name.value
  };

  let result = await queryPatientInfo(currentPage.value, pageSize.value, queryPatientParam);
  if (result.code == 1) {
    tableData.value = result.data.items;
    totalNum.value = result.data.counts;
  }
};

//页大小改变
const handleSizeChange = () => {
  queryPatient();
};
//页码改变
const handleCurrentChange = () => {
  queryPatient();
};
//点击删除
const deleteClick = id => {
  deleteDialogVisible.value = true;
  deletePatientId.value = id;
};
//取消删除就诊人
const cancleDeletePatient = () => {
  deleteDialogVisible.value = false;
  deletePatientId.value = -1;
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
    queryPatient();
  }
};
//点击就诊人详情
const detailClick = id => {
  store.commit("changePatientDetailId", id);
  router.push("/home/patientdetail");
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
