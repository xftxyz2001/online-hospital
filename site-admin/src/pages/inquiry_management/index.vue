<template>
  <div class="container">
    <div class="header">
      <div class="homeIcon">
        <el-icon><UserFilled /></el-icon>
      </div>
      <div class="home">问诊管理</div>
    </div>
    <div class="content">
      <div class="table">
        <el-table
          :data="tableData"
          style="width: 1100px"
          max-height="600"
          stripe
          :header-cell-style="{ background: '#ECF3FE' }"
        >
          <el-table-column fixed prop="username" label="用户" width="200">
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <el-icon><User /></el-icon>
                <span style="margin-left: 10px">{{ scope.row.userName }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="username" label="就诊人" width="200">
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <el-icon><User /></el-icon>
                <span style="margin-left: 10px">{{ scope.row.patientName }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="username" label="院区" width="100">
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <span style="margin-left: 10px">{{ scope.row.hospitalName }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="username" label="门诊" width="150">
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <span style="margin-left: 10px">{{ scope.row.outpatientName }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="username" label="医生" width="100">
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <span style="margin-left: 10px">{{ scope.row.doctorName }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="病情描述" width="300">
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <span style="margin-left: 10px">{{ scope.row.description }}</span>
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
          <el-table-column label="状态" width="100">
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <el-tag type="primary" v-if="scope.row.status == 1">进行中</el-tag>
                <el-tag type="success" v-if="scope.row.status == 2">已完成</el-tag>
                <el-tag type="warning" v-if="scope.row.status == 0">排队中</el-tag>
                <el-tag type="danger" v-if="scope.row.status == 3">已取消</el-tag>
              </div>
            </template>
          </el-table-column>
        </el-table>
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

<script setup>
import { ref, onMounted } from "vue";
import { queryInquiryApplication } from "@/api/hospital/inquiryapplication";
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

onMounted(() => {
  queryInquirys();
});
//查询挂号信息
const queryInquirys = async () => {
  let result = await queryInquiryApplication(currentPage.value, pageSize.value);
  if (result.code == 1) {
    tableData.value = result.data.items;
    totalNum.value = result.data.counts;
  }
};

//页大小改变
const handleSizeChange = () => {
  queryInquirys();
};
//页码改变
const handleCurrentChange = () => {
  queryInquirys();
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
