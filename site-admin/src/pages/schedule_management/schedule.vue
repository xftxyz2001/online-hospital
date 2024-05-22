<template>
  <div class="container">
    <div class="aside">
      <el-page-header @back="goBack">
        <template #content>
          <span class="text-small font-600 mr-3" style="font-size: 14px">排班</span>
        </template>
      </el-page-header>
      <el-card style="width: 250px; margin-top: 20px" shadow="always">
        <el-tree style="max-width: 600px" :data="treeData" :props="defaultProps" @node-click="handleNodeClick" />
      </el-card>
    </div>
    <div class="body">
      <div class="datelist">
        <el-card style="width: 1000px; margin: 20px 0 0 20px" shadow="always">
          <el-scrollbar>
            <el-radio-group v-model="selectedDate" size="large">
              <div class="scrollbar-flex-content">
                <el-radio-button :label="item" :value="item" v-for="item in datelist" style="margin: 10px 0 15px 0" />
              </div>
            </el-radio-group>

            <!--               
              <p
                v-for="item in datelist"
                :key="item"
                class="scrollbar-demo-item"
                style="width: 150px"
              >
                <el-card
                  style="width: 150px"
                  shadow="hover"
                  @click="datelistClick(item)"
                >
                  <span style="font-weight: 700"> {{ item }}</span>
                </el-card>
              </p> -->
          </el-scrollbar>
        </el-card>
      </div>
      <div class="scheduleInfo">
        <el-card style="width: 1000px; margin: 20px 0 0 20px" shadow="always">
          <span style="font-weight: 700; font-size: 25px">{{ selectedDate }} {{ outpatientName }}</span>
          <el-divider />
          <div class="morning" style="margin-top: 10px">
            <span style="font-weight: 700; font-size: 20px; margin-bottom: 10px; display: inline-block">上午</span>
            <el-collapse>
              <el-collapse-item title="8:00-8:30" name="1">
                <el-table :data="tableData1" style="width: 100%">
                  <el-table-column prop="doctorName" label="姓名" width="180" />
                  <el-table-column prop="professionalTitle" label="职称" width="180" />
                  <el-table-column prop="reservedNumber" label="可预约数" width="180" />
                  <el-table-column prop="availableNumber" label="剩余预约数" width="180" />
                  <!-- <el-table-column prop="amount" label="挂号费" width="180" /> -->
                  <el-table-column fixed="right" label="选项" width="120">
                    <template #default="scoped">
                      <el-button link type="danger" size="small" @click="deleteClick(scoped.row.id)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
                <div class="addtimebutton">
                  <el-button type="success" round @click="addtimeClick(0, 1)">新增</el-button>
                </div>
              </el-collapse-item>
              <el-collapse-item title="8:30-9:00" name="2">
                <el-table :data="tableData2" style="width: 100%">
                  <el-table-column prop="doctorName" label="姓名" width="180" />
                  <el-table-column prop="professionalTitle" label="职称" width="180" />
                  <el-table-column prop="reservedNumber" label="可预约数" width="180" />
                  <el-table-column prop="availableNumber" label="剩余预约数" width="180" />
                  <!-- <el-table-column prop="amount" label="挂号费" width="180" /> -->
                  <el-table-column fixed="right" label="选项" width="120">
                    <template #default="scoped">
                      <el-button link type="danger" size="small" @click="deleteClick(scoped.row.id)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
                <div class="addtimebutton">
                  <el-button type="success" round @click="addtimeClick(0, 2)">新增</el-button>
                </div>
              </el-collapse-item>
              <el-collapse-item title="9:00-9:30" name="3">
                <el-table :data="tableData3" style="width: 100%">
                  <el-table-column prop="doctorName" label="姓名" width="180" />
                  <el-table-column prop="professionalTitle" label="职称" width="180" />
                  <el-table-column prop="reservedNumber" label="可预约数" width="180" />
                  <el-table-column prop="availableNumber" label="剩余预约数" width="180" />
                  <!-- <el-table-column prop="amount" label="挂号费" width="180" /> -->
                  <el-table-column fixed="right" label="选项" width="120">
                    <template #default="scoped">
                      <el-button link type="danger" size="small" @click="deleteClick(scoped.row.id)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
                <div class="addtimebutton">
                  <el-button type="success" round @click="addtimeClick(0, 3)">新增</el-button>
                </div>
              </el-collapse-item>
              <el-collapse-item title="9:30-10:00" name="4">
                <el-table :data="tableData4" style="width: 100%">
                  <el-table-column prop="doctorName" label="姓名" width="180" />
                  <el-table-column prop="professionalTitle" label="职称" width="180" />
                  <el-table-column prop="reservedNumber" label="可预约数" width="180" />
                  <el-table-column prop="availableNumber" label="剩余预约数" width="180" />
                  <!-- <el-table-column prop="amount" label="挂号费" width="180" /> -->
                  <el-table-column fixed="right" label="选项" width="120">
                    <template #default="scoped">
                      <el-button link type="danger" size="small" @click="deleteClick(scoped.row.id)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
                <div class="addtimebutton">
                  <el-button type="success" round @click="addtimeClick(0, 4)">新增</el-button>
                </div>
              </el-collapse-item>
              <el-collapse-item title="10:00-10:30" name="5">
                <el-table :data="tableData5" style="width: 100%">
                  <el-table-column prop="doctorName" label="姓名" width="180" />
                  <el-table-column prop="professionalTitle" label="职称" width="180" />
                  <el-table-column prop="reservedNumber" label="可预约数" width="180" />
                  <el-table-column prop="availableNumber" label="剩余预约数" width="180" />
                  <!-- <el-table-column prop="amount" label="挂号费" width="180" /> -->
                  <el-table-column fixed="right" label="选项" width="120">
                    <template #default="scoped">
                      <el-button link type="danger" size="small" @click="deleteClick(scoped.row.id)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
                <div class="addtimebutton">
                  <el-button type="success" round @click="addtimeClick(0, 5)">新增</el-button>
                </div>
              </el-collapse-item>
              <el-collapse-item title="10:30-11:00" name="6">
                <el-table :data="tableData6" style="width: 100%">
                  <el-table-column prop="doctorName" label="姓名" width="180" />
                  <el-table-column prop="professionalTitle" label="职称" width="180" />
                  <el-table-column prop="reservedNumber" label="可预约数" width="180" />
                  <el-table-column prop="availableNumber" label="剩余预约数" width="180" />
                  <!-- <el-table-column prop="amount" label="挂号费" width="180" /> -->
                  <el-table-column fixed="right" label="选项" width="120">
                    <template #default="scoped">
                      <el-button link type="danger" size="small" @click="deleteClick(scoped.row.id)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
                <div class="addtimebutton">
                  <el-button type="success" round @click="addtimeClick(0, 6)">新增</el-button>
                </div>
              </el-collapse-item>
              <el-collapse-item title="11:00-11:30" name="7">
                <el-table :data="tableData7" style="width: 100%">
                  <el-table-column prop="doctorName" label="姓名" width="180" />
                  <el-table-column prop="professionalTitle" label="职称" width="180" />
                  <el-table-column prop="reservedNumber" label="可预约数" width="180" />
                  <el-table-column prop="availableNumber" label="剩余预约数" width="180" />
                  <!-- <el-table-column prop="amount" label="挂号费" width="180" /> -->
                  <el-table-column fixed="right" label="选项" width="120">
                    <template #default="scoped">
                      <el-button link type="danger" size="small" @click="deleteClick(scoped.row.id)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
                <div class="addtimebutton">
                  <el-button type="success" round @click="addtimeClick(0, 7)">新增</el-button>
                </div>
              </el-collapse-item>
            </el-collapse>
          </div>
          <el-divider />
          <div class="afternoon" style="margin-top: 10px">
            <span style="font-weight: 700; font-size: 20px; margin-bottom: 10px; display: inline-block">下午</span>
            <el-collapse>
              <el-collapse-item title="13:00-13:30" name="1">
                <el-table :data="tableData8" style="width: 100%">
                  <el-table-column prop="doctorName" label="姓名" width="180" />
                  <el-table-column prop="professionalTitle" label="职称" width="180" />
                  <el-table-column prop="reservedNumber" label="可预约数" width="180" />
                  <el-table-column prop="availableNumber" label="剩余预约数" width="180" />
                  <!-- <el-table-column prop="amount" label="挂号费" width="180" /> -->
                  <el-table-column fixed="right" label="选项" width="120">
                    <template #default="scoped">
                      <el-button link type="danger" size="small" @click="deleteClick(scoped.row.id)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
                <div class="addtimebutton">
                  <el-button type="success" round @click="addtimeClick(1, 1)">新增</el-button>
                </div>
              </el-collapse-item>
              <el-collapse-item title="13:30-14:00" name="2">
                <el-table :data="tableData9" style="width: 100%">
                  <el-table-column prop="doctorName" label="姓名" width="180" />
                  <el-table-column prop="professionalTitle" label="职称" width="180" />
                  <el-table-column prop="reservedNumber" label="可预约数" width="180" />
                  <el-table-column prop="availableNumber" label="剩余预约数" width="180" />
                  <!-- <el-table-column prop="amount" label="挂号费" width="180" /> -->
                  <el-table-column fixed="right" label="选项" width="120">
                    <template #default="scoped">
                      <el-button link type="danger" size="small" @click="deleteClick(scoped.row.id)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
                <div class="addtimebutton">
                  <el-button type="success" round @click="addtimeClick(1, 2)">新增</el-button>
                </div>
              </el-collapse-item>
              <el-collapse-item title="14:00-14:30" name="3">
                <el-table :data="tableData10" style="width: 100%">
                  <el-table-column prop="doctorName" label="姓名" width="180" />
                  <el-table-column prop="professionalTitle" label="职称" width="180" />
                  <el-table-column prop="reservedNumber" label="可预约数" width="180" />
                  <el-table-column prop="availableNumber" label="剩余预约数" width="180" />
                  <!-- <el-table-column prop="amount" label="挂号费" width="180" /> -->
                  <el-table-column fixed="right" label="选项" width="120">
                    <template #default="scoped">
                      <el-button link type="danger" size="small" @click="deleteClick(scoped.row.id)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
                <div class="addtimebutton">
                  <el-button type="success" round @click="addtimeClick(1, 3)">新增</el-button>
                </div>
              </el-collapse-item>
              <el-collapse-item title="14:30-15:00" name="4">
                <el-table :data="tableData11" style="width: 100%">
                  <el-table-column prop="doctorName" label="姓名" width="180" />
                  <el-table-column prop="professionalTitle" label="职称" width="180" />
                  <el-table-column prop="reservedNumber" label="可预约数" width="180" />
                  <el-table-column prop="availableNumber" label="剩余预约数" width="180" />
                  <!-- <el-table-column prop="amount" label="挂号费" width="180" /> -->
                  <el-table-column fixed="right" label="选项" width="120">
                    <template #default="scoped">
                      <el-button link type="danger" size="small" @click="deleteClick(scoped.row.id)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
                <div class="addtimebutton">
                  <el-button type="success" round @click="addtimeClick(1, 4)">新增</el-button>
                </div>
              </el-collapse-item>
              <el-collapse-item title="15:00-15:30" name="5">
                <el-table :data="tableData12" style="width: 100%">
                  <el-table-column prop="doctorName" label="姓名" width="180" />
                  <el-table-column prop="professionalTitle" label="职称" width="180" />
                  <el-table-column prop="reservedNumber" label="可预约数" width="180" />
                  <el-table-column prop="availableNumber" label="剩余预约数" width="180" />
                  <!-- <el-table-column prop="amount" label="挂号费" width="180" /> -->
                  <el-table-column fixed="right" label="选项" width="120">
                    <template #default="scoped">
                      <el-button link type="danger" size="small" @click="deleteClick(scoped.row.id)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
                <div class="addtimebutton">
                  <el-button type="success" round @click="addtimeClick(1, 5)">新增</el-button>
                </div>
              </el-collapse-item>
              <el-collapse-item title="15:30-16:00" name="6">
                <el-table :data="tableData13" style="width: 100%">
                  <el-table-column prop="doctorName" label="姓名" width="180" />
                  <el-table-column prop="professionalTitle" label="职称" width="180" />
                  <el-table-column prop="reservedNumber" label="可预约数" width="180" />
                  <el-table-column prop="availableNumber" label="剩余预约数" width="180" />
                  <!-- <el-table-column prop="amount" label="挂号费" width="180" /> -->
                  <el-table-column fixed="right" label="选项" width="120">
                    <template #default="scoped">
                      <el-button link type="danger" size="small" @click="deleteClick(scoped.row.id)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
                <div class="addtimebutton">
                  <el-button type="success" round @click="addtimeClick(1, 6)">新增</el-button>
                </div>
              </el-collapse-item>
              <el-collapse-item title="16:00-16:30" name="7">
                <el-table :data="tableData14" style="width: 100%">
                  <el-table-column prop="doctorName" label="姓名" width="180" />
                  <el-table-column prop="professionalTitle" label="职称" width="180" />
                  <el-table-column prop="reservedNumber" label="可预约数" width="180" />
                  <el-table-column prop="availableNumber" label="剩余预约数" width="180" />
                  <!-- <el-table-column prop="amount" label="挂号费" width="180" /> -->
                  <el-table-column fixed="right" label="选项" width="120">
                    <template #default="scoped">
                      <el-button link type="danger" size="small" @click="deleteClick(scoped.row.id)">删除</el-button>
                    </template>
                  </el-table-column>
                </el-table>
                <div class="addtimebutton">
                  <el-button type="success" round @click="addtimeClick(1, 7)">新增</el-button>
                </div>
              </el-collapse-item>
            </el-collapse>
          </div>
          <el-dialog v-model="addDialogFormVisible" title="新增排班" width="500">
            <el-form :model="addForm">
              <el-form-item label="时间" :label-width="80">
                <el-input v-model="addTime" style="width: 240px" disabled placeholder="Please input" />
              </el-form-item>
              <el-form-item label="医生" :label-width="80">
                <el-select v-model="addDoctor" placeholder="请选择医生">
                  <el-option v-for="item in doctorInfo" :key="item.id" :label="item.name" :value="item.id" />
                </el-select>
              </el-form-item>
              <el-form-item label="可预约数" :label-width="80">
                <el-input v-model="addReservedNumber" style="width: 240px" type="number" placeholder="Please input" />
              </el-form-item>
              <!-- 
              <el-form-item label="预约费" :label-width="80">
                <el-input
                  v-model="addAmount"
                  style="width: 240px"
                  type="number"
                  placeholder="Please input"
                  disabled
                />
              </el-form-item> -->
            </el-form>
            <template #footer>
              <div class="dialog-footer">
                <el-button @click="addDialogFormVisible = false">取消</el-button>
                <el-button type="primary" @click="confirmAddSchedule">确定</el-button>
              </div>
            </template>
          </el-dialog>
        </el-card>
        <el-dialog v-model="deleteDialogVisible" title="警告" width="500" align-center>
          <span>您确定要删除该排班信息吗?</span>
          <template #footer>
            <div class="dialog-footer">
              <el-button @click="deleteDialogVisible = false">取消</el-button>
              <el-button type="primary" @click="confirmDeleteSchedule">确定</el-button>
            </div>
          </template>
        </el-dialog>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import store from "@/vuex";
import { querySpecialistTree, querySchedule, addSchedule, deleteScheduleById } from "@/api/hospital/schedule";
import { queryDoctorInfoByHospitalAndOutpatientId } from "@/api/hospital/doctor";
import dayjs from "dayjs"; // ES 2015
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";
const router = useRouter();
//当前页面的医院id
const hospitalId = ref();
//日期
const datelist = ref([]);
//选择的门诊id
const outpatientId = ref();
const outpatientName = ref("");
//选择的日期
const selectedDate = ref("");
//选择的上午、下午
const workHalf = ref();
//选择的时间段
const workTime = ref();
//新增的就诊数
const addReservedNumber = ref();
//新增的就诊费
const addAmount = ref(0);
//新增的医生
const addDoctor = ref();
//存储14个时间段排班信息的数组
const tableData1 = ref([]);
const tableData2 = ref([]);
const tableData3 = ref([]);
const tableData4 = ref([]);
const tableData5 = ref([]);
const tableData6 = ref([]);
const tableData7 = ref([]);
const tableData8 = ref([]);
const tableData9 = ref([]);
const tableData10 = ref([]);
const tableData11 = ref([]);
const tableData12 = ref([]);
const tableData13 = ref([]);
const tableData14 = ref([]);
//新增的时间段字符串
const addTime = ref("");
//是否显示新增表单
const addDialogFormVisible = ref(false);
//是否显示删除提示框
const deleteDialogVisible = ref(false);
//要删除的排班id
const deleteScheduleId = ref();
onMounted(() => {
  hospitalId.value = store.getters.scheduleHospitalId;
  queryTreeInfo().then(() => {
    //默认选择日期
    selectedDate.value = dayjs().add(1, "day").format("YYYY-MM-DD");
    for (let i = 0; i < 30; i++) {
      let date = dayjs()
        .add(i + 1, "day")
        .format("YYYY-MM-DD");
      datelist.value[i] = date;
    }
    queryScheduleInfo();
  });
});

const handleNodeClick = treeData => {
  if (treeData.value != undefined) {
    outpatientName.value = treeData.label;
    outpatientId.value = treeData.value;
  }
  selectedDate.value = dayjs().add(1, "day").format("YYYY-MM-DD");
  if (outpatientId.value != undefined) queryScheduleInfo();
};
const treeData = ref([]);
//新增排版时查询医生信息
const doctorInfo = ref([]);
const defaultProps = {
  children: "children",
  label: "label",
  value: "value"
};
//获取专科树状信息
const queryTreeInfo = async () => {
  let result = await querySpecialistTree(hospitalId.value);
  if (result.code == 1) {
    treeData.value = result.data;
    outpatientId.value = treeData.value[0].children[0].value;
  }
};
//点击日期列表
const datelistClick = item => {
  selectedDate.value = item;
  queryScheduleInfo();
};
//清空排班信息
const cleartableData = () => {
  tableData1.value = [];
  tableData2.value = [];
  tableData3.value = [];
  tableData4.value = [];
  tableData5.value = [];
  tableData6.value = [];
  tableData7.value = [];
  tableData8.value = [];
  tableData9.value = [];
  tableData10.value = [];
  tableData11.value = [];
  tableData12.value = [];
  tableData13.value = [];
  tableData14.value = [];
};
//获取排班信息
const queryScheduleInfo = async () => {
  cleartableData();
  const queryScheduleDto = {
    hospitalId: hospitalId.value,
    outpatientId: outpatientId.value,
    workDate: selectedDate.value
  };
  let result = await querySchedule(queryScheduleDto);
  if (result.code == 1) {
    result.data.forEach(element => {
      switch (element.workHalf) {
        case 0:
          switch (element.workTime) {
            case 1:
              tableData1.value.push(element);
              break;
            case 2:
              tableData2.value.push(element);
              break;
            case 3:
              tableData3.value.push(element);
              break;
            case 4:
              tableData4.value.push(element);
              break;
            case 5:
              tableData5.value.push(element);
              break;
            case 6:
              tableData6.value.push(element);
              break;
            case 7:
              tableData7.value.push(element);
              break;
          }
          break;
        case 1:
          switch (element.workTime) {
            case 1:
              tableData8.value.push(element);
              break;
            case 2:
              tableData9.value.push(element);
              break;
            case 3:
              tableData10.value.push(element);
              break;
            case 4:
              tableData11.value.push(element);
              break;
            case 5:
              tableData12.value.push(element);
              break;
            case 6:
              tableData13.value.push(element);
              break;
            case 7:
              tableData14.value.push(element);
              break;
          }
          break;
      }
    });
  }
};
//点击新增排班
const addtimeClick = (workhalf, worktime) => {
  addTime.value = returnTimeString(workhalf, worktime);
  workHalf.value = workhalf;
  workTime.value = worktime;
  addDialogFormVisible.value = true;
  queryDoctorInfo();
};

//根据医院id和门诊id获取医生列表
const queryDoctorInfo = async () => {
  let result = await queryDoctorInfoByHospitalAndOutpatientId(hospitalId.value, outpatientId.value);
  if (result.code == 1) {
    doctorInfo.value = result.data;
  }
};
//根据输入值返回时间段字符串
const returnTimeString = (workhalf, worktime) => {
  switch (workhalf) {
    case -1:
      return "8:00~11:30  13:00~16:30";
      break;
    case 0:
      switch (worktime) {
        case 1:
          return "8:00~8:30";
          break;
        case 2:
          return "8:30~9:00";
          break;
        case 3:
          return "9:00~9:30";
          break;
        case 4:
          return "9:30~10:00";
          break;
        case 5:
          return "10:00~10:30";
          break;
        case 6:
          return "10:30~11:00";
          break;
        case 7:
          return "11:00~11:30";
          break;
      }
    case 1:
      switch (worktime) {
        case 1:
          return "13:00~13:30";
          break;
        case 2:
          return "13:30~14:00";
          break;
        case 3:
          return "14:00~14:30";
          break;
        case 4:
          return "14:30~15:00";
          break;
        case 5:
          return "15:00~15:30";
          break;
        case 6:
          return "15:30~16:00";
          break;
        case 7:
          return "16:00~16:30";
          break;
      }
    default:
      break;
  }
};
//确认增加排班
const confirmAddSchedule = () => {
  switch (workHalf.value) {
    case -1:
      for (i = 0; i <= 1; i++) {
        for (j = 1; j <= 7; j++) {
          addScheduleItem(i, j);
        }
      }
      break;
    case 0:
    case 1:
      switch (workTime.value) {
        case -1:
          for (j = 1; j <= 7; j++) {
            addScheduleItem(workHalf.value, j);
          }
          break;
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
          addScheduleItem(workHalf.value, workTime.value);
          break;
        default:
          break;
      }
      break;
    default:
      break;
  }
};
//增加半个小时的排班
const addScheduleItem = async (workhalf, worktime) => {
  const addScheduleDto = ref({
    amount: addAmount.value,
    doctorId: addDoctor.value,
    hospitalId: hospitalId.value,
    outpatientId: outpatientId.value,
    reservedNumber: addReservedNumber.value,
    status: 1,
    workDate: selectedDate.value,
    workHalf: workhalf,
    workTime: worktime
  });
  let result = await addSchedule(addScheduleDto.value);
  if (result.code == 1) {
    ElMessage({
      message: "添加成功",
      type: "success"
    });
    queryScheduleInfo();
    addDialogFormVisible.value = false;
  }
};
//点击删除排班
const deleteClick = scheduleId => {
  deleteDialogVisible.value = true;
  deleteScheduleId.value = scheduleId;
};
//确定删除排班
const confirmDeleteSchedule = async () => {
  let result = await deleteScheduleById(deleteScheduleId.value);

  if (result.code == 1) {
    ElMessage({
      message: "删除成功",
      type: "success"
    });
    queryScheduleInfo();
    deleteDialogVisible.value = false;
  }
};
const goBack = () => {
  router.back(1);
};
</script>

<style scoped lang="scss">
.container {
  width: 100%;
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  margin-left: -70px;

  .aside {
    position: fixed;
    margin-top: 20px;
  }
  .body {
    margin-left: 240px;
    margin-top: 45px;
  }
}

[class^="morning-item"] {
  margin-top: 5px;
}
[class^="afternoon-item"] {
  margin-top: 5px;
}
.addtimebutton {
  display: flex;
  justify-content: end;
  margin-top: 10px;
  width: 100%;
}
.scrollbar-flex-content {
  display: flex;
}
.scrollbar-demo-item {
  flex-shrink: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100px;
  height: 50px;
  margin: 10px;
  text-align: center;
  border-radius: 4px;
  background: var(--el-color-danger-light-9);
  color: var(--el-color-danger);
}
</style>
