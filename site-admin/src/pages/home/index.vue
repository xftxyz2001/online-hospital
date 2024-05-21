<template>
  <div class="container">
    <ManagementTop />
    <div class="body">
      <div class="aside"><ManagementAside /></div>
      <div class="content">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts"></script>

<style scoped lang="scss">
.container {
  background-color: #ffffff;
  display: flex;
  flex-direction: column;
  align-items: center;
  .body {
    display: flex;
    margin-top: 40px;
    width: 100%;
    min-height: 700px;
    background-color: FFFFFF;

    .aside {
      position: fixed;
    }
    .content {
      margin: 10px 0 0 220px;
      width: 1200px;
    }
  }
}
</style>
