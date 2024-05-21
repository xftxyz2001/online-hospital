<template>
  <div>
    <el-row class="tac">
      <el-col :span="24">
        <el-menu
          default-active="1"
          background-color="#324157"
          class="el-menu-vertical-demo"
          text-color="#fff"
          @open="handleOpen"
          @close="handleClose"
          active-text-color="#4FACFE"
        >
          <el-menu-item class="el-menu-item" index="1" @click="userManagement">
            <el-icon><icon-menu /></el-icon>
            <span>用户管理</span>
          </el-menu-item>
          <el-menu-item class="el-menu-item" index="2" @click="patientManagement">
            <el-icon><document /></el-icon>
            <span>就诊人管理</span>
          </el-menu-item>
          <el-menu-item class="el-menu-item" index="3" @click="hospitalManagement">
            <el-icon><setting /></el-icon>
            <span>医院管理</span>
          </el-menu-item>
          <el-menu-item class="el-menu-item" index="4" @click="scheduleManagement">
            <el-icon><setting /></el-icon>
            <span>排班管理</span>
          </el-menu-item>
          <el-menu-item class="el-menu-item" index="5" @click="orderInfoManagement">
            <el-icon><setting /></el-icon>
            <span>挂号管理</span>
          </el-menu-item>
          <el-menu-item class="el-menu-item" index="6" @click="inquiryManagement">
            <el-icon><setting /></el-icon>
            <span>问诊管理</span>
          </el-menu-item>
        </el-menu>
      </el-col>
    </el-row>
  </div>
</template>

<script setup lang="ts">
import { Document, Menu as IconMenu, Location, Setting } from "@element-plus/icons-vue";
import { useRouter, useRoute } from "vue-router";
const router = useRouter();
const route = useRoute();
const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath);
};
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath);
};
const userManagement = () => {
  router.push("/home/usermanagement");
};
const patientManagement = () => {
  router.push("/home/patientmanagement");
};
const hospitalManagement = () => {
  router.push("/home/hospitalmanagement");
};
const scheduleManagement = () => {
  router.push("/home/scheduleindex");
};
const orderInfoManagement = () => {
  router.push("/home/orderInfo");
};
const inquiryManagement = () => {
  router.push("/home/inquiry");
};
</script>

<style scoped lang="scss">
.el-menu-vertical-demo {
  min-height: 700px;
  .el-menu-item {
    margin: 10px 0px;
  }
}
</style>
