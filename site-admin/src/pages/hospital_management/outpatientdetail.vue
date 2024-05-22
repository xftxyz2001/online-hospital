<template>
  <div class="container">
    <div class="header">
      <el-page-header @back="goBack">
        <template #content>
          <span class="text-small font-600 mr-3" style="font-size: 14px">门诊详情</span>
        </template>
      </el-page-header>
    </div>
    <div class="outpatientDetail">
      <el-card style="width: 1200px; margin-top: 20px" shadow="always">
        <el-descriptions class="margin-top" title="门诊详情" :column="1" :size="size" border>
          <template #extra>
            <el-button type="primary" @click="clickUpdate">修改</el-button>
          </template>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <user />
                </el-icon>
                门诊名
              </div>
            </template>
            {{ outpatientInfo.name }}
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
            {{ outpatientInfo.disc }}
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
            {{ outpatientInfo.createTime }}
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
            {{ outpatientInfo.modifiedTime }}
          </el-descriptions-item>
        </el-descriptions>
      </el-card>
    </div>
    <div class="doctor">
      <el-card
        style="width: 1200px; margin-top: 20px; display: flex; flex-direction: column; align-items: center"
        shadow="always"
      >
        <div style="display: flex; align-items: center; justify-content: space-between; width: 100%">
          <div><span style="font-weight: 700">医生</span></div>
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
          <el-table-column prop="professionalTitle" label="职称" width="330" />
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
      <el-form-item label="门诊名" label-width="140px">
        <el-input v-model="newOutpatientName" autocomplete="off" placeholder="请输入" />
      </el-form-item>

      <el-form-item label="介绍" label-width="140px">
        <el-input
          v-model="newOutpatientDisc"
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
  <el-dialog v-model="addFormVisible" title="添加医生" width="600">
    <el-form>
      <el-form-item label="医生名" label-width="140px">
        <el-input v-model="doctorName" autocomplete="off" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="性别" label-width="140px">
        <el-select v-model="gender" placeholder="请选择" style="width: 240px">
          <el-option v-for="item in genders" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="职称" label-width="140px">
        <el-input v-model="professionalTitle" autocomplete="off" placeholder="请输入" />
      </el-form-item>
      <el-form-item label="介绍" label-width="140px">
        <el-input
          v-model="introduce"
          style="width: 450px"
          :autosize="{ minRows: 2, maxRows: 4 }"
          type="textarea"
          placeholder="请输入"
        />
      </el-form-item>
      <el-form-item label="在线问诊" label-width="140px">
        <el-select v-model="isInquiry" placeholder="请选择" style="width: 240px">
          <el-option v-for="item in isInquirys" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="图片" label-width="140px">
        <el-upload
          class="avatar-uploader"
          action="/system/upload"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :headers="headerObj"
          name="image"
        >
          <img v-if="picture" :src="picture" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
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
import { queryOutpatientById, updateOutpatient } from "@/api/hospital/outpatient";
import { addDoctor, queryDoctorInfoByHospitalAndOutpatientId } from "@/api/hospital/doctor";
import { useRouter } from "vue-router";
import { ElMessage } from "element-plus";
const headerObj = ref({ token: store.getters.managerLoginToken });
const router = useRouter();
//当前页面的医院id
const hospitalId = ref();
//当前页面的专科id
const outpatientId = ref();
//当前门诊的信息
const outpatientInfo = ref({});
//医生信息
const tableData = ref([]);
const updateFormVisible = ref(false);
const newOutpatientName = ref("");
const newOutpatientDisc = ref("");
const addFormVisible = ref(false);
//新增医生
const doctorName = ref("");
const professionalTitle = ref("");
const introduce = ref("");
const gender = ref();
const picture = ref("");
const isInquiry = ref();
const genders = [
  {
    value: 1,
    label: "男"
  },
  {
    value: 0,
    label: "女"
  }
];
const isInquirys = [
  {
    value: 1,
    label: "是"
  },
  {
    value: 0,
    label: "否"
  }
];
const handleAvatarSuccess = (response, uploadFile) => {
  picture.value = response.data;
};

const beforeAvatarUpload = rawFile => {
  if (rawFile.type !== "image/jpeg") {
    ElMessage.error("Avatar picture must be JPG format!");
    return false;
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error("Avatar picture size can not exceed 2MB!");
    return false;
  }
  return true;
};
onMounted(() => {
  hospitalId.value = store.getters.hospitalDetailId;
  outpatientId.value = store.getters.outpatientDetailId;
  queryOutpatientInfo();
  queryDoctorInfo();
});
//查询门诊信息
const queryOutpatientInfo = async () => {
  let result = await queryOutpatientById(outpatientId.value);
  if (result.code == 1) {
    outpatientInfo.value = result.data;
  }
};
//查询医生信息
const queryDoctorInfo = async () => {
  let result = await queryDoctorInfoByHospitalAndOutpatientId(hospitalId.value, outpatientId.value);
  if (result.code == 1) {
    tableData.value = result.data;
  }
};
const goBack = () => {
  router.back(1);
};
//点击更新
const clickUpdate = () => {
  newOutpatientName.value = outpatientInfo.value.name;
  newOutpatientDisc.value = outpatientInfo.value.disc;
  updateFormVisible.value = true;
};
//确定更新
const updateConfirm = async () => {
  let updateOutpatientDto = {
    id: outpatientId.value,
    name: newOutpatientName.value,
    disc: newOutpatientDisc.value
  };
  let result = await updateOutpatient(updateOutpatientDto);
  if (result.code == 1) {
    updateFormVisible.value = false;
    ElMessage({
      message: "修改成功",
      type: "success"
    });
    queryOutpatientInfo();
  }
};

//添加医生
const addConfirm = async () => {
  let addDoctorDto = {
    hospitalId: hospitalId.value,
    outpatientId: outpatientId.value,
    name: doctorName.value,
    professionalTitle: professionalTitle.value,
    picture: picture.value,
    introduce: introduce.value,
    gender: gender.value,
    isInquiry: isInquiry.value
  };
  let result = await addDoctor(addDoctorDto);
  if (result.code == 1) {
    addFormVisible.value = false;
    doctorName.value = "";
    professionalTitle.value = "";
    ElMessage({
      message: "添加成功",
      type: "success"
    });
    queryDoctorInfo();
  }
};

//取消添加医生
const addCancle = () => {
  addFormVisible.value = false;
  doctorName.value = "";
  professionalTitle.value = "";
  picture.value = "";
  introduce.value = "";
  gender.value = "";
  isInquiry.value = "";
};
//医生详情
const detailClick = id => {
  store.commit("changeDoctorDetailId", id);
  router.push("/home/doctordetail");
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
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 200px;
  height: 150px;
  text-align: center;
}
.demo-image__error .image-slot {
  font-size: 30px;
}
.demo-image__error .image-slot .el-icon {
  font-size: 30px;
}
.demo-image__error .el-image {
  width: 100%;
  height: 200px;
}
.avatar-uploader .avatar {
  width: 200px;
  height: 150px;
  display: block;
}
</style>
