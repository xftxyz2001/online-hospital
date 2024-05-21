<template>
  <div class="container">
    <div class="header">
      <el-page-header @back="goBack">
        <template #content>
          <span class="text-small font-600 mr-3" style="font-size: 14px">医院详情</span>
        </template>
      </el-page-header>
    </div>
    <div class="hospitalDetail">
      <el-card style="width: 1200px; margin-top: 20px" shadow="always">
        <el-descriptions class="margin-top" title="医院详情" :column="2" :size="size" border>
          <template #extra>
            <el-button type="primary" @click="clickUpdate">修改</el-button>
          </template>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <!-- <el-icon :style="iconStyle">
                  <user />
                </el-icon> -->
                医院名
              </div>
            </template>
            {{ hospitalInfo.name }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <iphone />
                </el-icon>
                电话
              </div>
            </template>
            {{ hospitalInfo.phone }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <location />
                </el-icon>
                地址
              </div>
            </template>
            {{ hospitalInfo.address }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <tickets />
                </el-icon>
                介绍
              </div>
            </template>
            {{ hospitalInfo.introduction }}
          </el-descriptions-item>

          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <!-- <el-icon :style="iconStyle">
                  <office-building />
                </el-icon> -->
                创建时间
              </div>
            </template>
            {{ hospitalInfo.createTime }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <!-- <el-icon :style="iconStyle">
                  <office-building />
                </el-icon> -->
                上次修改时间
              </div>
            </template>
            {{ hospitalInfo.modifiedTime }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">图片</div>
            </template>
            <div class="demo-image__preview">
              <el-image
                style="width: 200px; height: 150px"
                :src="hospitalInfo.picture"
                :zoom-rate="1.2"
                :max-scale="7"
                :min-scale="0.2"
                :initial-index="4"
                :preview-src-list="imgUrlList"
                fit="cover"
              />
            </div>
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
          <div><span style="font-weight: 700">专科</span></div>
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
          <!-- <el-table-column prop="id" label="id" width="120" /> -->
          <el-table-column prop="disc" label="介绍" width="330" />
          <el-table-column prop="createTime" label="创建时间" width="230" />
          <el-table-column prop="modifiedTime" label="上次修改时间" width="230" />
          <el-table-column fixed="right" label="选项" width="120">
            <template #default="scope">
              <el-button link type="primary" size="small" @click="detailClick(scope.row.id)">详情</el-button>
              <!-- <el-button link type="primary" size="small">编辑</el-button> -->
            </template>
          </el-table-column>
        </el-table>
      </el-card>
    </div>
  </div>
  <el-dialog v-model="updateFormVisible" title="修改信息" width="600">
    <el-form>
      <el-form-item label="医院名" label-width="140px">
        <el-input v-model="newName" autocomplete="off" />
      </el-form-item>
      <el-form-item label="电话" label-width="140px">
        <el-input v-model="newPhone" autocomplete="off" />
      </el-form-item>
      <el-form-item label="地址" label-width="140px">
        <el-input v-model="newAddress" autocomplete="off" />
      </el-form-item>
      <el-form-item label="介绍" label-width="140px">
        <el-input
          v-model="newIntroduction"
          style="width: 450px"
          :autosize="{ minRows: 2, maxRows: 4 }"
          type="textarea"
          placeholder="请输入"
        />
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
          <img v-if="newPicture" :src="newPicture" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="updateFormVisible = false">取消</el-button>
        <el-button type="primary" @click="updateConfirm">确定</el-button>
      </div>
    </template>
  </el-dialog>
  <el-dialog v-model="addFormVisible" title="添加专科" width="600">
    <el-form>
      <el-form-item label="专科名" label-width="140px">
        <el-input v-model="specialistName" autocomplete="off" placeholder="请输入" />
      </el-form-item>

      <el-form-item label="介绍" label-width="140px">
        <el-input
          v-model="specialistIntroduction"
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

<script setup lang="ts">
import { ref, onMounted } from "vue";
import { queryHospitalInfoById, updateHospital } from "@/api/hospital/hospital";
import { querySpecialInfoByHospitalId, addSpecialist } from "@/api/hospital/specialist";
import { useRouter, useRoute } from "vue-router";
import store from "@/vuex";
import { Delete, Download, Plus, ZoomIn } from "@element-plus/icons-vue";
import type { UploadFile } from "element-plus";
import { ElMessage } from "element-plus";
const dialogImageUrl = ref("");
const dialogVisible = ref(false);
const disabled = ref(false);
const headerObj = ref({ token: store.getters.managerLoginToken });
const imageUrl = ref("");
const imgUrlList = ref([]);
const addFormVisible = ref(false);
const specialistName = ref("");
const specialistIntroduction = ref("");
const handleAvatarSuccess: UploadProps["onSuccess"] = (response, uploadFile) => {
  newPicture.value = response.data;
};

const beforeAvatarUpload: UploadProps["beforeUpload"] = rawFile => {
  if (rawFile.type !== "image/jpeg") {
    ElMessage.error("Avatar picture must be JPG format!");
    return false;
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error("Avatar picture size can not exceed 2MB!");
    return false;
  }
  return true;
};
//当前页面的医院id
const hospitalId = ref();
//当前医院的信息
const hospitalInfo = ref({});
//专科信息
const tableData = ref([]);
const router = useRouter();
const route = useRoute();
const updateFormVisible = ref(false);
const newName = ref("");
const newPhone = ref("");
const newAddress = ref("");
const newIntroduction = ref("");
const newPicture = ref("");
onMounted(() => {
  hospitalId.value = store.getters.hospitalDetailId;

  queryHospitalInfo();
  querySpecialInfo();
});
//查询医院信息
const queryHospitalInfo = async () => {
  let result = await queryHospitalInfoById(hospitalId.value);
  if (result.code == 1) {
    hospitalInfo.value = result.data;
    imgUrlList.value[0] = result.data.picture;
  }
};
//查询专科信息
const querySpecialInfo = async () => {
  let result = await querySpecialInfoByHospitalId(hospitalId.value);
  if (result.code == 1) {
    tableData.value = result.data;
  }
};
const detailClick = id => {
  store.commit("changeSpecialistDetailId", id);
  router.push("/home/specialistdetail");
};
//点击修改医院信息
const clickUpdate = () => {
  updateFormVisible.value = true;
  newName.value = hospitalInfo.value.name;
  newPhone.value = hospitalInfo.value.phone;
  newAddress.value = hospitalInfo.value.address;
  newIntroduction.value = hospitalInfo.value.introduction;
  newPicture.value = hospitalInfo.value.picture;
};
//提交修改信息
const updateConfirm = async () => {
  let updateHospitalDto = {
    id: hospitalId.value,
    name: newName.value,
    address: newAddress.value,
    phone: newPhone.value,
    introduction: newIntroduction.value,
    picture: newPicture.value
  };
  let result = await updateHospital(updateHospitalDto);
  if (result.code == 1) {
    updateFormVisible.value = false;
    ElMessage({
      message: "修改成功",
      type: "success"
    });
    queryHospitalInfo();
  }
};
const goBack = () => {
  router.back(1);
};
//添加专科
const addConfirm = async () => {
  let addSpecialistDto = {
    hospitalId: hospitalId.value,
    name: specialistName.value,
    disc: specialistIntroduction.value
  };
  let result = await addSpecialist(addSpecialistDto);
  if (result.code == 1) {
    addFormVisible.value = false;
    specialistName.value = "";
    specialistIntroduction.value = "";
    ElMessage({
      message: "添加成功",
      type: "success"
    });
    querySpecialInfo();
  }
};
//取消添加专科
const addCancle = () => {
  addFormVisible.value = false;
  specialistName.value = "";
  specialistIntroduction.value = "";
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
    .homeIcon {
      height: 20px;
    }
    .home {
      height: 20px;
      margin: 2px 0 0 5px;
      text-align: center;
      font-size: 10px;
    }
  }
}
</style>

<style>
.avatar-uploader .avatar {
  width: 200px;
  height: 150px;
  display: block;
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
</style>
