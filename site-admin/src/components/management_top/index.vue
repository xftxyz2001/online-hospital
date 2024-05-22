<template>
  <div class="header">
    <div class="content">
      <div class="left">
        <img src="/src/assets/images/管理系统.png" alt="" width="30px" />
        <span style="margin-left: 8px">后台管理平台</span>
      </div>
      <div class="right">
        <el-dropdown>
          <span class="el-dropdown-link" style="color: white; font-size: 10px">
            {{ username }}
            <el-icon class="el-icon--right"><arrow-down /></el-icon>
          </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item icon="SwitchButton" @click="exit">退出</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
  </div>
</template>

<script setup>
import { managerQueryUsername } from "@/api/system/manager";
import { ref, onMounted } from "vue";
import store from "@/vuex";
import { useRouter } from "vue-router";
const router = useRouter();
const username = ref("");
const queryUsername = async () => {
  let result = await managerQueryUsername();
  if (result.code == 1) {
    username.value = result.data.username;
  }
};
//退出
const exit = () => {
  store.commit("changeManagerLoginToken", "");
  router.push("/login");
};
onMounted(() => {
  queryUsername();
});
</script>

<style scoped lang="scss">
.header {
  width: 100%;
  height: 50px;
  display: flex;
  justify-content: center;
  position: fixed;
  z-index: 900;
  background-color: #242f42;

  .content {
    height: 50px;
    width: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    .left {
      margin-left: 2px;
      height: 50px;
      color: #fff;
      display: flex;
      align-items: center;
    }
    .right {
      margin-right: 5px;
      span {
        padding: 0px 5px;
      }
    }
  }
}
</style>
