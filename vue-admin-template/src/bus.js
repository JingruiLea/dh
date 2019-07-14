import Vue from "vue";
import {selectAll as allPatients} from "./api/patient";
import {selectAll as allDoctors} from "./api/doctor";

export default new Vue({
  data(){
    return{
      doctors:[],
      patients:[]
    }
  },
  methods:{
    async getDoctors(){
      let res = await allDoctors()
      this.doctors = res.data
      return res
    },
    async getPatients(){
      let res = await allPatients()
      this.patients = res.data
      return res
    }
  },
  created(){
    this.getDoctors()
    this.getPatients()
  }
})
