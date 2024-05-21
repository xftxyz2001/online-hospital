<template>
  <div class="common-layout">
    <el-container style="min-height: 1000px; background-color: #f4f4f5">
      <el-header style="background-color: #242f42; position: fixed; width: 100%; height: 50px; z-index: 900">
        <Top></Top>
      </el-header>
      <el-container style="margin-top: 50px">
        <el-aside width="200px" style="position: fixed"><Aside></Aside></el-aside>
        <el-main style="margin-left: 200px"><router-view></router-view></el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import Aside from "@/components/aside/index.vue";
import Top from "@/components/top/index.vue";
</script>

<style></style>
