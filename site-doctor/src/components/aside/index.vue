<template>
  <el-col :span="24">
    <el-menu
      active-text-color="#ffd04b"
      background-color="#324157"
      class="el-menu-vertical-demo"
      default-active="2"
      text-color="#fff"
      style="height: 700px"
    >
      <el-menu-item index="1" @click="gotoInquiry">
        <el-icon><ChatDotSquare /></el-icon>
        <span>问诊管理</span>
      </el-menu-item>
      <el-menu-item index="2" @click="gotoRegistration">
        <el-icon><Check /></el-icon>
        <span>挂号管理</span>
      </el-menu-item>
    </el-menu>
  </el-col>
</template>

<script setup>
import { useRouter } from "vue-router";
const router = useRouter();
const gotoInquiry = () => {
  router.push({
    path: "/home/inquirymanagement/inprogress"
  });
};
const gotoRegistration = () => {
  router.push({
    path: "/home/registrationmanagement"
  });
};
</script>

<style></style>
