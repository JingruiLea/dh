<template>
  <div class="app-container">
    <div class="filter-container">
      <!--<el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">-->
        <!--添加量表分数-->
      <!--</el-button>-->
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-back" @click="back">
        返回
      </el-button>
    </div>
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="ID" width="95">
        <template slot-scope="{row}">
          {{ row.patient_id }}
        </template>
      </el-table-column>
      <el-table-column label="分数">
        <template slot-scope="{row}">
          {{ row.score }}
        </template>
      </el-table-column>
      <el-table-column label="时间" align="center">
        <template slot-scope="{row}">
          <span>{{ row.time }}</span>
        </template>
      </el-table-column>
    </el-table>
    <div ref="chart" style="width: 300px;height: 300px"></div>
  </div>
</template>

<script>
  import {insert, selectAll, updateByPrimaryKey, selectByPrimaryKey, deleteByPrimaryKey} from '../../api/patient'
  import {selectAll as selectAllDoctor} from '../../api/doctor'
  import {selectByPatientId} from "../../api/trainScore";
  import bus from '../../bus'
  import echarts from 'echarts'

  export default {
    filters: {
      statusFilter(status) {
        const statusMap = {
          published: 'success',
          draft: 'gray',
          deleted: 'danger'
        }
        return statusMap[status]
      }
    },
    data() {
      return {
        list: null,
        listLoading: true,
        dialogStatus:null,
        textMap:{
          create:'新建',
          update:'修改'
        },
        id:null,
        doctors:[],
        temp:{},
        dialogFormVisible:false
      }
    },
    created() {
      this.id = this.$route.query['id']
      this.getList()
    },
    mounted(){
      let orgOptions = {
        xAxis: {
          type: 'category',
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: [820, 932, 901, 934, 1290, 1330, 1320],
          type: 'line',
          smooth: true
        }]
      }
      let chart = echarts.init(this.$refs['chart'])
      chart.setOption(orgOptions)
    },
    methods: {
      back(){
        this.$router.go(-1)
      },
      showDetail(row){
        this.$router.push(`/patientDetail/${row.id}`)
      },
      getList() {
        this.listLoading = true
        selectByPatientId(this.id).then(res=>{
          this.list = res.data
          this.listLoading = false
          console.log(res.data)
        })
      },
      handleDelete(row){
        deleteByPrimaryKey(row.id).then(res=>{
          this.$notify({
            title: 'Success',
            message: '删除成功!',
            type: 'success',
            duration: 2000
          })
          this.getList()
        })
      },
      handleUpdate(row){
        this.temp = Object.assign({}, row) // copy obj
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
      },
      handleCreate(){
        this.temp = {}
        this.dialogFormVisible = true
        this.dialogStatus = 'create'
      },
      updateData(){
        updateByPrimaryKey(this.temp).then(res=>{
          this.dialogFormVisible = false
          this.$notify({
            title: 'Success',
            message: '更新成功!',
            type: 'success',
            duration: 2000
          })
          this.getList()
        })
      },
      createData(){
        insert(this.temp).then(res=>{
          this.dialogFormVisible = false
          this.$notify({
            title: 'Success',
            message: '添加成功!',
            type: 'success',
            duration: 2000
          })
          this.getList()
        })
      }
    }
  }
</script>
