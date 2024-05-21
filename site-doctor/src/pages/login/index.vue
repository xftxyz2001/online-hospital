<template>
  <div class="container">
    <div class="login-wrapper">
      <div class="header">登录</div>
      <div class="form-wrapper">
        <div class="username">
          <el-input v-model="username" placeholder="用户名" clearable />
        </div>
        <div class="password">
          <el-input v-model="password" placeholder="密码" clearable type="password" />
        </div>

        <div class="btn" @click="login">登录</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import store from "@/vuex";
import { doctorLogin } from "@/api/user/doctor_account.js";
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";
const router = useRouter();

const username = ref("");
const password = ref("");
const login = async () => {
  const doctorLoginDto = ref({
    username: username.value,
    password: password.value
  });
  let result = await doctorLogin(doctorLoginDto.value);
  if (result.code == 1) {
    store.commit("changeDoctorLoginToken", result.data);
    ElMessage({
      message: "登录成功",
      type: "success"
    });
    router.push("/home/inquirymanagement");
  } else {
    ElMessage({
      message: result.msg,
      type: "error"
    });
  }
};
</script>

<style scoped>
.username {
  margin: 30px 0;
}
.container {
  height: 700px;
  background-image: linear-gradient(to right, #fbc2eb, #a6c1ee);
}
.login-wrapper {
  background-color: #fff;
  width: 358px;
  height: 588px;
  border-radius: 15px;
  padding: 0 50px;
  position: relative;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}
.header {
  font-size: 38px;
  font-weight: bold;
  text-align: center;
  line-height: 200px;
}
.input-item {
  display: block;
  width: 100%;
  margin-bottom: 20px;
  border: 0;
  padding: 10px;
  border-bottom: 1px solid rgb(128, 125, 125);
  font-size: 15px;
  outline: none;
}
.input-item:placeholder {
  text-transform: uppercase;
}
.btn {
  text-align: center;
  padding: 10px;
  width: 100%;
  margin-top: 40px;
  background-image: linear-gradient(to right, #a6c1ee, #fbc2eb);
  color: #fff;
}
</style>
