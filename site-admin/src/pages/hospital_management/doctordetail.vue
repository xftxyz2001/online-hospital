<template>
  <div class="container">
    <div class="header">
      <el-page-header @back="goBack">
        <template #content>
          <span class="text-small font-600 mr-3" style="font-size: 14px">医生详情</span>
        </template>
      </el-page-header>
    </div>
    <div class="doctorDetail">
      <el-card style="width: 1200px; margin-top: 20px" shadow="always">
        <el-descriptions class="margin-top" title="医生详情" :column="1" :size="size" border>
          <template #extra>
            <el-button type="primary" @click="clickUpdate">修改</el-button>
          </template>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <!-- <el-icon :style="iconStyle">
                  <user />
                </el-icon> -->
                姓名
              </div>
            </template>
            {{ doctorInfo.name }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <iphone />
                </el-icon>
                性别
              </div>
            </template>
            {{ doctorInfo.gender == 0 ? "女" : "男" }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <location />
                </el-icon>
                职称
              </div>
            </template>
            {{ doctorInfo.professionalTitle }}
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
            {{ doctorInfo.introduce }}
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
            {{ doctorInfo.createTime }}
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
            {{ doctorInfo.modifiedTime }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">图片</div>
            </template>
            <div class="demo-image__preview">
              <el-image
                style="width: 200px; height: 150px"
                :src="doctorInfo.picture"
                :zoom-rate="1.2"
                :max-scale="7"
                :min-scale="0.2"
                :initial-index="4"
                :preview-src-list="imgUrlList"
                fit="cover"
              />
            </div>
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <el-icon :style="iconStyle">
                  <iphone />
                </el-icon>
                在线问诊
              </div>
            </template>
            <el-tag type="success" v-if="doctorInfo.isInquiry == 1">是</el-tag>
            <el-tag type="info" v-else>否</el-tag>
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <!-- <el-icon :style="iconStyle">
                  <office-building />
                </el-icon> -->
                用户名
              </div>
            </template>
            {{ doctorInfo.username }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">
                <!-- <el-icon :style="iconStyle">
                  <office-building />
                </el-icon> -->
                注意
              </div>
            </template>
            在线问诊账号的账号为上述用户名，初始密码为123456
          </el-descriptions-item>
        </el-descriptions>
      </el-card>
    </div>
  </div>
  <el-dialog v-model="updateFormVisible" title="修改信息" width="600">
    <el-form>
      <el-form-item label="姓名" label-width="140px">
        <el-input v-model="newName" autocomplete="off" />
      </el-form-item>
      <el-form-item label="性别" label-width="140px">
        <el-select v-model="newGender" placeholder="请选择" style="width: 240px">
          <el-option v-for="item in genders" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="职称" label-width="140px">
        <el-input v-model="newProfessionalTitle" autocomplete="off" />
      </el-form-item>
      <el-form-item label="介绍" label-width="140px">
        <el-input
          v-model="newIntroduce"
          style="width: 450px"
          :autosize="{ minRows: 2, maxRows: 4 }"
          type="textarea"
          placeholder="请输入"
        />
      </el-form-item>
      <el-form-item label="在线问诊" label-width="140px">
        <el-select v-model="newInquiry" placeholder="请选择" style="width: 240px">
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
import { updateDoctor, queryDoctorById } from "@/api/hospital/doctor";
import { useRouter, useRoute } from "vue-router";
import store from "@/vuex";
import { Delete, Download, Plus, ZoomIn } from "@element-plus/icons-vue";
import type { UploadFile } from "element-plus";
import { ElMessage } from "element-plus";
const dialogImageUrl = ref("");
const dialogVisible = ref(false);
const disabled = ref(false);
const uploadUrl = ref("");
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
//当前页面的医生id
const doctorId = ref();
//当前医生的信息
const doctorInfo = ref({});

const router = useRouter();
const route = useRoute();
const updateFormVisible = ref(false);
const newName = ref("");
const newGender = ref("");
const newAddress = ref("");
const newProfessionalTitle = ref("");
const newIntroduce = ref("");
const newPicture = ref("");
const newInquiry = ref();
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
onMounted(() => {
  doctorId.value = store.getters.doctorDetailId;

  queryDoctorInfo();
  uploadUrl.value = import.meta.env.VITE_SYSTEM_BASE_URL;
});
//查询医生信息
const queryDoctorInfo = async () => {
  let result = await queryDoctorById(doctorId.value);
  if (result.code == 1) {
    doctorInfo.value = result.data;
    imgUrlList.value[0] = result.data.picture;
  }
};

//点击修改医生信息
const clickUpdate = () => {
  updateFormVisible.value = true;
  newName.value = doctorInfo.value.name;
  newGender.value = doctorInfo.value.gender;
  newProfessionalTitle.value = doctorInfo.value.professionalTitle;
  newIntroduce.value = doctorInfo.value.introduce;
  newPicture.value = doctorInfo.value.picture;
  newInquiry.value = doctorInfo.value.isInquiry;
};
//提交修改信息
const updateConfirm = async () => {
  let updateDoctorDto = {
    id: doctorId.value,
    name: newName.value,
    gender: newGender.value,
    professionalTitle: newProfessionalTitle.value,
    introduce: newIntroduce.value,
    picture: newPicture.value,
    isInquiry: newInquiry.value
  };
  let result = await updateDoctor(updateDoctorDto);
  if (result.code == 1) {
    updateFormVisible.value = false;
    ElMessage({
      message: "修改成功",
      type: "success"
    });
    queryDoctorInfo();
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
