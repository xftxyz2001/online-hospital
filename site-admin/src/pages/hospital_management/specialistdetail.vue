<template>
  <div class="container">
    <div class="header">
      <el-page-header @back="goBack">
        <template #content>
          <span class="text-small font-600 mr-3" style="font-size: 14px">专科详情</span>
        </template>
      </el-page-header>
    </div>
    <div class="specialistDetail">
      <el-card style="width: 1200px; margin-top: 20px" shadow="always">
        <el-descriptions class="margin-top" title="专科详情" :column="1" :size="size" border>
          <template #extra>
            <el-button type="primary" @click="clickUpdate">修改</el-button>
          </template>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <user />
                </el-icon>
                专科名
              </div>
            </template>
            {{ specialistInfo.name }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <iphone />
                </el-icon>
                介绍
              </div>
            </template>
            {{ specialistInfo.disc }}
          </el-descriptions-item>

          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <office-building />
                </el-icon>
                创建时间
              </div>
            </template>
            {{ specialistInfo.createTime }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <office-building />
                </el-icon>
                上次修改时间
              </div>
            </template>
            {{ specialistInfo.modifiedTime }}
          </el-descriptions-item>
        </el-descriptions>
      </el-card>
    </div>
    <div class="specialist">
      <el-card
        style="width: 1200px; margin-top: 20px; display: flex; flex-direction: column; align-items: center"
        shadow="always"
      >
        <div style="display: flex; align-items: center; justify-content: space-between; width: 100%">
          <div><span style="font-weight: 700">门诊</span></div>
          <div>
            <el-button type="primary" @click="addFormVisible = true">添加</el-button>
          </div>
        </div>
        <el-table
          :data="tableData"
          style="width: 1100px; margin-top: 20px"
          :header-cell-style="{ background: '#ECF3FE' }"
        >
          <el-table-column fixed prop="name" label="名称" width="150" />
          <el-table-column prop="disc" label="介绍" width="330" />
          <el-table-column prop="createTime" label="创建时间" width="230" />
          <el-table-column prop="modifiedTime" label="上次修改时间" width="200" />
          <el-table-column fixed="right" label="选项" width="120">
            <template #default="scope">
              <el-button link type="primary" size="small" @click="detailClick(scope.row.id)">详情</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </div>
  </div>
  <el-dialog v-model="updateFormVisible" title="修改信息" width="600">
    <el-form>
      <el-form-item label="专科名" label-width="140px">
        <el-input v-model="newSpecialistName" autocomplete="off" placeholder="请输入" />
      </el-form-item>

      <el-form-item label="介绍" label-width="140px">
        <el-input
          v-model="newSpecialistIntroduction"
          style="width: 450px"
          :autosize="{ minRows: 2, maxRows: 4 }"
          type="textarea"
          placeholder="请输入"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="updateFormVisible = false">取消</el-button>
        <el-button type="primary" @click="updateConfirm">确定</el-button>
      </div>
    </template>
  </el-dialog>
  <el-dialog v-model="addFormVisible" title="添加门诊" width="600">
    <el-form>
      <el-form-item label="门诊名" label-width="140px">
        <el-input v-model="outpatientName" autocomplete="off" placeholder="请输入" />
      </el-form-item>

      <el-form-item label="介绍" label-width="140px">
        <el-input
          v-model="outpatientDisc"
          style="width: 450px"
          :autosize="{ minRows: 2, maxRows: 4 }"
          type="textarea"
          placeholder="请输入"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="addCancle">取消</el-button>
        <el-button type="primary" @click="addConfirm">确定</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, onMounted } from "vue";
import store from "@/vuex";
import { querySpecialistInfoById, updateSpecialist } from "@/api/hospital/specialist";
import { queryOutpatientInfoByHospitalAndSpecialistId, addOutpatient } from "@/api/hospital/outpatient";
import { useRouter } from "vue-router";
import { ElMessage } from "element-plus";
const router = useRouter();
//当前页面的医院id
const hospitalId = ref();
//当前页面的专科id
const specialistId = ref();
//当前专科的信息
const specialistInfo = ref({});
//门诊信息
const tableData = ref([]);
const updateFormVisible = ref(false);
const newSpecialistName = ref("");
const newSpecialistIntroduction = ref("");
const addFormVisible = ref(false);
const outpatientName = ref("");
const outpatientDisc = ref("");
onMounted(() => {
  hospitalId.value = store.getters.hospitalDetailId;
  specialistId.value = store.getters.specialistDetailId;
  querySpecialistInfo();
  queryOutpatientInfo();
});
//查询专科信息
const querySpecialistInfo = async () => {
  let result = await querySpecialistInfoById(specialistId.value);
  if (result.code == 1) {
    specialistInfo.value = result.data;
  }
};
//查询门诊信息
const queryOutpatientInfo = async () => {
  let result = await queryOutpatientInfoByHospitalAndSpecialistId(hospitalId.value, specialistId.value);
  if (result.code == 1) {
    tableData.value = result.data;
  }
};
const goBack = () => {
  router.back(1);
};
//点击更新
const clickUpdate = () => {
  newSpecialistName.value = specialistInfo.value.name;
  newSpecialistIntroduction.value = specialistInfo.value.disc;
  updateFormVisible.value = true;
};
//确定更新
const updateConfirm = async () => {
  let updateSpecialistDto = {
    id: specialistId.value,
    name: newSpecialistName.value,
    disc: newSpecialistIntroduction.value
  };
  let result = await updateSpecialist(updateSpecialistDto);
  if (result.code == 1) {
    updateFormVisible.value = false;
    ElMessage({
      message: "修改成功",
      type: "success"
    });
    querySpecialistInfo();
  }
};

//添加门诊
const addConfirm = async () => {
  let addOutpatientDto = {
    hospitalId: hospitalId.value,
    specialistId: specialistId.value,
    name: outpatientName.value,
    disc: outpatientDisc.value
  };
  let result = await addOutpatient(addOutpatientDto);
  if (result.code == 1) {
    addFormVisible.value = false;
    outpatientName.value = "";
    outpatientDisc.value = "";
    ElMessage({
      message: "添加成功",
      type: "success"
    });
    queryOutpatientInfo();
  }
};
//取消添加门诊
const addCancle = () => {
  addFormVisible.value = false;
  outpatientName.value = "";
  outpatientDisc.value = "";
};
//门诊详情
const detailClick = id => {
  store.commit("changeOutpatientDetailId", id);
  router.push("/home/outpatientdetail");
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
  }
}
</style>
