<template>
  <div class="app-container">
    <div class="filter-container">
<!--      <el-input v-model="listQuery.title" placeholder="Title" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />-->
<!--      <el-select v-model="listQuery.importance" placeholder="Imp" clearable style="width: 90px" class="filter-item">-->
<!--        <el-option v-for="item in importanceOptions" :key="item" :label="item" :value="item" />-->
<!--      </el-select>-->
<!--      <el-select v-model="listQuery.type" placeholder="Type" clearable class="filter-item" style="width: 130px">-->
<!--        <el-option v-for="item in calendarTypeOptions" :key="item.key" :label="item.display_name+'('+item.key+')'" :value="item.key" />-->
<!--      </el-select>-->
<!--      <el-select v-model="listQuery.sort" style="width: 140px" class="filter-item" @change="handleFilter">-->
<!--        <el-option v-for="item in sortOptions" :key="item.key" :label="item.label" :value="item.key" />-->
<!--      </el-select>-->
<!--      <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">-->
<!--        Search-->
<!--      </el-button>-->
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
        添加
      </el-button>
<!--      <el-button v-waves :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">-->
<!--        Export-->
<!--      </el-button>-->
<!--      <el-checkbox v-model="showReviewer" class="filter-item" style="margin-left:15px;" @change="tableKey=tableKey+1">-->
<!--        reviewer-->
<!--      </el-checkbox>-->
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
      <el-table-column label="姓名">
        <template slot-scope="{row}">
          {{ row.name }}
        </template>
      </el-table-column>
      <el-table-column label="登录名" align="center">
        <template slot-scope="{row}">
          <span>{{ row.username }}</span>
        </template>
      </el-table-column>
      <el-table-column label="医院" align="center">
        <template slot-scope="{row}">
          {{ row.hospital_name }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="科室">
        <template slot-scope="{row}">
          <span>{{ row.department_name }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="{row}">
          <el-button type="primary" size="mini" @click="handleUpdate(row)">编辑</el-button>
          <el-button type="danger" size="mini" @click="handleDelete(row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm":model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="姓名" prop="username">
          <el-input v-model="temp.name" />
        </el-form-item>
        <el-form-item label="登录名" prop="username">
          <el-input v-model="temp.username" />
        </el-form-item>
        <el-form-item label="密码" prop="username">
          <el-input v-model="temp.password" />
        </el-form-item>
        <el-form-item label="所在科室" prop="department">
          <el-input v-model="temp.department_name" />
        </el-form-item>
        <el-form-item label="所在医院" prop="department">
          <el-input v-model="temp.hospital_name" />
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
  import {insert, selectAll, updateByPrimaryKey, selectByPrimaryKey, deleteByPrimaryKey} from '../../api/doctor'

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
        temp:{},
        dialogFormVisible:false
      }
    },
    created() {
      this.getList()
    },
    methods: {
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
