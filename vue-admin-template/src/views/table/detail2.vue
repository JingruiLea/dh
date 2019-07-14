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
          {{ row.id }}
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
    <el-button class="filter-item" style="margin-left: 10px;margin-top: 30px;" type="primary" icon="el-icon-edit" @click="handleCreate">
    添加量表分数
    </el-button>


    <el-dialog title="添加量表分数" :visible.sync="dialogFormVisible">
      <el-form :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="测量时间" prop="username">
          <el-input v-model="temp.time" />
        </el-form-item>
        <el-form-item label="测量分数" prop="username">
          <el-input v-model="temp.score" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
          确定
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {insert, selectAll, updateByPrimaryKey, selectByPrimaryKey, deleteByPrimaryKey} from '../../api/scaleScore'
  import {selectAll as selectAllDoctor} from '../../api/doctor'
  import {selectByPatientId} from "../../api/scaleScore";
  import bus from '../../bus'


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
        this.temp.patient_id = this.id
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
