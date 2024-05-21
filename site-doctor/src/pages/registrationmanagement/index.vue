<template>
  <div class="container">
    <div class="header">
      <div class="homeIcon">
        <el-icon><UserFilled /></el-icon>
      </div>
      <div class="home">挂号管理</div>
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
          <el-table-column fixed prop="username" label="就诊人" width="100">
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <el-icon><User /></el-icon>
                <span style="margin-left: 10px">{{ scope.row.patientName }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="phone" label="预约号" width="180">
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <span style="margin-left: 10px">{{ scope.row.outTradeNo }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="预约时间" width="300">
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <el-icon><timer /></el-icon>
                <span style="margin-left: 10px">{{ scope.row.reserveDate }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="创建时间" width="300">
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <el-icon><timer /></el-icon>
                <span style="margin-left: 10px">{{ scope.row.createTime }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column fixed="right" label="选项" width="120">
            <template #default="scope">
              <el-button link type="primary" size="small" @click="clickSend(scope.row)">发送报告</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="selectpage">
        <div class="demo-pagination-block" style="width: 700px; margin-left: 100px">
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
  <el-dialog v-model="dialogAddVisible" title="发送报告" width="500">
    <el-form-item label="报告名">
      <el-input v-model="fileName" placeholder="请输入报告名" />
    </el-form-item>
    <el-upload
      class="upload-demo"
      drag
      action="/system/upload"
      v-model:file-list="fileList"
      :on-change="handleChange"
      :headers="headerObj"
      name="image"
      :limit="1"
    >
      <el-icon class="el-icon--upload"><upload-filled /></el-icon>
      <div class="el-upload__text">
        Drop file here or
        <em>click to upload</em>
      </div>
      <template #tip>
        <div class="el-upload__tip">jpg/png files with a size less than 500kb</div>
      </template>
    </el-upload>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="cancel">取消</el-button>
        <el-button type="primary" @click="confirmAdd">发送</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { queryRegistrationInfo, addReport } from "@/api/registration/registration.js";
import store from "@/vuex";
import { ElMessage } from "element-plus";
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
const dialogAddVisible = ref(false);
const fileName = ref("");
const fileUrl = ref("");
const userId = ref(0);
const patientId = ref(0);
const patientName = ref("");
const doctorId = ref(0);
const headerObj = ref({ token: store.getters.doctorLoginToken.token });
onMounted(() => {
  queryRegistration();
});
//查询挂号信息
const queryRegistration = async () => {
  let result = await queryRegistrationInfo(currentPage.value, pageSize.value);
  if (result.code == 1) {
    tableData.value = result.data.items;
    totalNum.value = result.data.counts;
  }
};

//页大小改变
const handleSizeChange = () => {
  queryRegistration();
};
//页码改变
const handleCurrentChange = () => {
  queryRegistration();
};
//点击发送报告
const clickSend = e => {
  userId.value = e.userId;
  patientId.value = e.patientId;
  patientName.value = e.patientName;
  doctorId.value = e.doctorId;
  dialogAddVisible.value = true;
};
const cancel = () => {
  fileName.value = "";
  fileUrl.value = "";
};
const confirmAdd = async () => {
  let addReportDto = {
    userId: userId.value,
    patientId: patientId.value,
    patientName: patientName.value,
    doctorId: doctorId.value,
    reportName: fileName.value,
    fileUrl: fileUrl.value
  };
  let res = await addReport(addReportDto);
  if (res.code == 1) {
    ElMessage({
      message: "发送成功",
      type: "success"
    });
    dialogAddVisible.value = false;
  }
};
const fileList = ref([]);

const handleChange = uploadFile => {
  fileUrl.value = uploadFile.response?.data;
};
</script>

<style scoped>
.header {
  width: 100%;
  height: 20px;
  display: flex;

  margin-top: 10px;
}
.homeIcon {
  height: 20px;
}
.home {
  height: 20px;
  margin: 2px 0 0 5px;
  text-align: center;
  font-size: 10px;
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
}
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
.content {
  margin: 25px 0 0 0;
  width: 100%;
  box-shadow: 0px 0px 0.1px 2px #e3ebff;
  background-color: #fff;
  display: flex;
  flex-direction: column;
  align-items: center;
  /* align-items: center; */
}
.table {
  margin: 30px 0 0 0;
}
.selectpage {
  margin: 20px 0 20px 0;
}
.container {
  display: flex;
  flex-direction: column;
  /* align-items: center; */
  width: 100%;
  justify-content: start;
}
</style>
