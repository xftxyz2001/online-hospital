<template>
  <div class="container">
    <div class="header">
      <div class="homeIcon">
        <el-icon><UserFilled /></el-icon>
      </div>
      <div class="home">医院管理</div>
    </div>
    <el-card
      style="width: 1200px; margin-top: 20px; display: flex; flex-direction: column; align-items: center"
      shadow="always"
    >
      <el-table :data="tableData" style="width: 1100px" :header-cell-style="{ background: '#ECF3FE' }">
        <el-table-column fixed prop="name" label="名称" width="150" />
        <!-- <el-table-column prop="id" label="id" width="120" /> -->
        <el-table-column prop="phone" label="电话" width="120" />
        <el-table-column prop="address" label="地址" width="300" />
        <el-table-column prop="createTime" label="创建时间" width="200" />
        <el-table-column prop="modifiedTime" label="修改时间" width="200" />
        <el-table-column fixed="right" label="操作" width="100">
          <template #default="scope">
            <el-button link type="primary" size="small" @click="detailClick(scope.row.id)">详情</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import { queryHospitalInfo } from "@/api/hospital/hospital";
import store from "@/vuex";
import { useRouter, useRoute } from "vue-router";
const router = useRouter();
const route = useRoute();
const tableData = ref([]);
onMounted(() => {
  queryAllHospitalInfo();
});
//获取医院信息
const queryAllHospitalInfo = async () => {
  let result = await queryHospitalInfo();
  if (result.code == 1) {
    tableData.value = result.data;
  }
};
//点击详情
const detailClick = id => {
  console.log("this" + id);

  store.commit("changeHospitalDetailId", id);
  router.push("/home/hospitaldetail");
};
</script>

<style lang="scss" scoped>
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
}
</style>
