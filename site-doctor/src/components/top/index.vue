<template>
  <div class="container">
    <div class="left" style="color: white; font-size: 17px; display: flex; align-items: center">
      <img src="@/assets/接诊.png" alt="" width="30px" />
      医生问诊
    </div>
    <div class="right" @click="logout" style="cursor: pointer;">退出</div>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";
const router = useRouter();
const logout = () => {
  router.push({
    path: "/login"
  });
};
</script>

<style>
.container {
  width: 100%;
  height: 100%;
  justify-content: space-between;
  display: flex;
  align-items: center;
}
</style>
