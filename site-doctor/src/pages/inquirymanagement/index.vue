<template>
  <div>
    <el-container style="background-color: white">
      <el-header>
        <el-menu ellipsis class="el-menu-popper-demo" mode="horizontal" :popper-offset="16" style="max-width: 600px">
          <router-link to="/home/inquirymanagement/inprogress">
            <el-menu-item index="1">进行中</el-menu-item>
          </router-link>
          <router-link to="/home/inquirymanagement/waiting"><el-menu-item index="2">等待中</el-menu-item></router-link>
          <router-link to="/home/inquirymanagement/finished"><el-menu-item index="3">已完成</el-menu-item></router-link>
        </el-menu>
      </el-header>
      <el-main><router-view></router-view></el-main>
    </el-container>
  </div>
</template>

<script setup></script>

<style scoped>
a {
  text-decoration: none;
}

.router-link-active {
  text-decoration: none;
}
</style>
