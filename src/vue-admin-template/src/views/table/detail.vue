<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        添加量表分数
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        添加训练分数
      </el-button>
    </div>
    <el-table
      v-loading="listLoading"
      :data="scaleList"
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
        <template slot-scope="{row}">`
          {{ row.score }}
        </template>
      </el-table-column>
      <el-table-column label="时间" align="center">
        <template slot-scope="{row}">
          <span>{{ row.time }}</span>
        </template>
      </el-table-column>
    </el-table>

    <el-table
      v-loading="listLoading"
      :data="trainList"
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
        <template slot-scope="{row}">`
          {{ row.score }}
        </template>
      </el-table-column>
      <el-table-column label="时间" align="center">
        <template slot-scope="{row}">
          <span>{{ row.time }}</span>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm":model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="姓名" prop="username">
          <el-input v-model="temp.name" />
        </el-form-item>
        <el-form-item label="性别" prop="username">
          <el-input v-model="temp.sex" />
        </el-form-item>
        <el-form-item label="年龄" prop="username">
          <el-input v-model="temp.age" />
        </el-form-item>
        <el-form-item label="看诊医生" prop="type">
          <el-select v-model="temp.doctor_id" class="filter-item" placeholder="Please select">
            <el-option v-for="item,index in doctors" :key="index" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="所患疾病" prop="department">
          <el-input v-model="temp.disease_name" />
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
  import {insert, selectAll, updateByPrimaryKey, selectByPrimaryKey, deleteByPrimaryKey} from '../../api/patient'
  import {selectAll as selectAllDoctor} from '../../api/doctor'

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
        scaleList: null,
        trainList: null,
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
      this.getList()
      this.id = this.$route.query['id']
      selectAllDoctor().then(res=>{
        this.doctors = res.data
      })
    },
    methods: {
      showDetail(row){
        this.$router.push(`/patientDetail/${row.id}`)
      },
      getList() {
        this.listLoading = true
        selectAll().then(res => {
          this.list = res.data
          this.listLoading = false
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
