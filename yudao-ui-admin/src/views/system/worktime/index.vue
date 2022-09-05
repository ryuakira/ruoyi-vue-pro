<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="社員名" prop="employeeName">
        <el-select v-model="queryParams.employeeNum"
                   placeholder="请选择社員名"
                   clearable
                   size="small">
          <el-option v-for="item in employeeList"
                  :value="item.employeeNum"
                  :key="item.id"
                  :label="item.employeeName"/>
        </el-select>
      </el-form-item>
      <el-form-item label="出勤年月" prop="workingMonth">
        <el-input v-model="queryParams.workingMonth" placeholder="请输入出勤年月" type="month" dataformatas="yyyy-MM" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['system:worktime:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['system:worktime:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="社員番号" align="center" prop="employeeNum" />
      <el-table-column label="社員名前" align="center" prop="employee.employeeName" />
      <el-table-column label="出勤年月" align="center" prop="workingMonth" width="180"/>
      <el-table-column label="稼働時間" align="center" prop="workingtimes" />
      <el-table-column label="新規日付" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['system:worktime:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['system:worktime:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="社員番号" prop="employeeNum" >
          <el-input v-model="form.employeeNum" disabled />
        </el-form-item>
        <el-form-item label="社員名前" prop="employeeId">
          <el-select v-model="form.employeeName"
                     placeholder="请选择社員"
                     clearable style="width: 100%" @change="selectedChange">
            <!--   {value:item.id, label:item.employeeNum} ←--⭐️選んでる社員主鍵で社員番号を特定する （画面・社員番号が連動的に表示できるため）-->
            <el-option v-for="item in employeeList"
                       :value="{value:item.id, label:item.employeeNum}"
                       :key="item.id"
                       :label="item.employeeName"/>
          </el-select>
        </el-form-item>
        <el-form-item label="出勤年月" prop="workingMonth">
          <el-date-picker clearable v-model="form.workingMonth" type="month" placeholder="选择出勤年月" value-format="yyyy-MM" />
        </el-form-item>
        <el-form-item label="稼働時間" prop="workingtimes">
          <el-input v-model="form.workingtimes" placeholder="请输入稼働時間" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { createWorktime, updateWorktime, deleteWorktime, getWorktime, getWorktimePage, exportWorktimeExcel } from "@/api/system/worktime";
import { listSimpleEmployee } from "@/api/system/employee";

export default {
  name: "Worktime",
  components: {
  },
  data() {
    return {

      // 手动追加 2022/08/30 start ---社员姓名下拉框 ----//
      employeeList: [],
      getEmplyNum:null,
      // 手动追加 2022/08/30 end ----社员姓名下拉框 ----//
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 勤怠列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        employeeId: null,
        employeeNum: null,
        workingMonth: null,
        workingtimes: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        employeeId: [{ required: true, message: "社員不能为空", trigger: "change" }],
        workingMonth: [{ required: true, message: "勤怠年月不能为空", trigger: "change" }],
        workingtimes: [{ required: true, message: "稼働時間不能为空", trigger: "change" }],
      }
    };
  },
  created() {
    this.getList();
    /** 获取在职员工下拉框列表 */
    listSimpleEmployee().then(response => {
      this.employeeList = response.data;
    });
  },
  methods: {
    // 手動追加 start -------//
    // selectBlur(e) {
    //     if (e.target.value != '') {
    //       // this.value = e.target.value + '(Others)';
    //       this.value = e.target.value +'';
    //       this.$forceUpdate();
    //     }
    //   },
    // selectClear() {
    //    this.value = '';
    //    this.$forceUpdate();
    //  },
    selectedChange(val) {
       const{value,label} = val
       // 选中下拉框的社員名，自动显示社員番号
       this.form.employeeNum = label
       // 将当前员工的主键值赋值给勤怠表的employee_id字段
       this.form.employeeId = value
       this.$forceUpdate();
     },
    // 手動追加 end -------//
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 处理查询参数
      let params = {...this.queryParams};
      // 执行查询
      getWorktimePage(params).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    /** 取消按钮 */
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        employeeNum: undefined,
        employeeId: undefined,
        employeeName:undefined,
        workingMonth: undefined,
        workingtimes: undefined,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNo = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.queryParams.employeeNum = undefined;
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加勤怠";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getWorktime(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改勤怠";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updateWorktime(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createWorktime(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除勤怠编号为"' + id + '"的数据项?').then(function() {
          return deleteWorktime(id);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      // 执行导出
      this.$modal.confirm('是否确认导出所有勤怠数据项?').then(() => {
          this.exportLoading = true;
          return exportWorktimeExcel(params);
        }).then(response => {
          this.$download.excel(response, '勤怠.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
