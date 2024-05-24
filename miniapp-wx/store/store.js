//创建store实例对象
import { action, observable } from "mobx-miniprogram";
export const store = observable({
  registrationHospitalId: 0,
  registrationOutpatientId: 0,
  registrationDate: "",
  registrationDoctorId: 0,
  registrationHalf: 0,
  patientDetailId: 0,
  patientUpdateId: 0,
  registrationRecordDetailId: 0,
  inquiryHospitalId: 0,
  inquiryOutpatientId: 0,
  inquiryDoctorId: 0,
  inquiryLinkId: 0,
  hospitalInfoId: 0,
  outpatientInfoOfHospitalId: 0,
  outpatientInfoId: 0,
  doctorInfoId: 0,
  inquiryDetailId: 0,
  finishedLinkId: 0,
  messageShowInput: true,
  updateRegistrationHospitalId: action(function (hospitalId) {
    this.registrationHospitalId = hospitalId;
  }),
  updateRegistrationOutpatientId: action(function (outpatientId) {
    this.registrationOutpatientId = outpatientId;
  }),
  updateRegistrationDate: action(function (date) {
    this.registrationDate = date;
  }),
  updateRegistrationDoctorId: action(function (doctorId) {
    this.registrationDoctorId = doctorId;
  }),
  updateRegistrationHalf: action(function (half) {
    this.registrationHalf = half;
  }),
  updatePatientDetailId: action(function (id) {
    this.patientDetailId = id;
  }),
  updatePatientUpdateId: action(function (id) {
    this.patientUpdateId = id;
  }),
  updateRegistrationRecordDetailId: action(function (id) {
    this.registrationRecordDetailId = id;
  }),
  updateInquiryHospitalId: action(function (id) {
    this.inquiryHospitalId = id;
  }),
  updateInquiryOutpatientId: action(function (id) {
    this.inquiryOutpatientId = id;
  }),
  updateInquiryDoctorId: action(function (id) {
    this.inquiryDoctorId = id;
  }),
  updateInquiryLinkId: action(function (id) {
    this.inquiryLinkId = id;
  }),
  updateHospitalInfoId: action(function (id) {
    this.hospitalInfoId = id;
  }),
  updateOutpatientInfoOfHospitalId: action(function (id) {
    this.outpatientInfoOfHospitalId = id;
  }),
  updateOutpatientInfoId: action(function (id) {
    this.outpatientInfoId = id;
  }),
  updateDoctorInfoId: action(function (id) {
    this.doctorInfoId = id;
  }),
  updateInquiryDetailId: action(function (id) {
    this.inquiryDetailId = id;
  }),
  updateFinishedLinkId: action(function (id) {
    this.finishedLinkId = id;
  }),
  updateMessageShowInput: action(function (isShow) {
    this.messageShowInput = isShow;
  })
});
