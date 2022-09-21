<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="公司名1" prop="name1">
        <el-input v-model="queryParams.name1" placeholder="请输入公司名1" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="公司名2" prop="name2">
        <el-input v-model="queryParams.name2" placeholder="请输入公司名2" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="公司略称" prop="name3">
        <el-input v-model="queryParams.name3" placeholder="请输入公司略称" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="公司代码" prop="code">
        <el-input v-model="queryParams.code" placeholder="请输入公司代码" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="邮编" prop="zipcode">
        <el-input v-model="queryParams.zipcode" placeholder="请输入邮编" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="地址1" prop="address1">
        <el-input v-model="queryParams.address1" placeholder="请输入地址1" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="地址2" prop="address2">
        <el-input v-model="queryParams.address2" placeholder="请输入地址2" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="地址3" prop="address3">
        <el-input v-model="queryParams.address3" placeholder="请输入地址3" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="大楼名" prop="building">
        <el-input v-model="queryParams.building" placeholder="请输入大楼名" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="电话" prop="phone">
        <el-input v-model="queryParams.phone" placeholder="请输入电话" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="传真" prop="fax">
        <el-input v-model="queryParams.fax" placeholder="请输入传真" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="网址" prop="site">
        <el-input v-model="queryParams.site" placeholder="请输入网址" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="取引关系" prop="businessFlag">
        <el-select v-model="queryParams.businessFlag" placeholder="请选择取引关系" clearable size="small">
          <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SYSTEM_BUSINESS_FLAG)"
                       :key="dict.value" :label="dict.label" :value="dict.value"/>
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker v-model="dateRangeCreateTime" style="width: 240px" value-format="yyyy-MM-dd"
                        type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" />
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
                   v-hasPermi="['system:customer:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['system:customer:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="公司名1" align="center" prop="name1" />
      <el-table-column label="公司名2" align="center" prop="name2" />
      <el-table-column label="公司略称" align="center" prop="name3" />
      <el-table-column label="公司代码" align="center" prop="code" />
      <el-table-column label="公司描述" align="center" prop="description" />
      <el-table-column label="邮编" align="center" prop="zipcode" />
      <el-table-column label="地址1" align="center" prop="address1" />
      <el-table-column label="地址2" align="center" prop="address2" />
      <el-table-column label="地址3" align="center" prop="address3" />
      <el-table-column label="大楼名" align="center" prop="building" />
      <el-table-column label="电话" align="center" prop="phone" />
      <el-table-column label="传真" align="center" prop="fax" />
      <el-table-column label="网址" align="center" prop="site" />
      <el-table-column label="取引关系" align="center" prop="businessFlag">
        <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.SYSTEM_BUSINESS_FLAG" :value="scope.row.businessFlag" />
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['system:customer:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['system:customer:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="基本情報" name="basic_info">
        <el-form-item label="公司名1" prop="name1">
          <el-input v-model="form.name1" placeholder="请输入公司名1" />
        </el-form-item>
        <el-form-item label="公司名2" prop="name2">
          <el-input v-model="form.name2" placeholder="请输入公司名2" />
        </el-form-item>
        <el-form-item label="公司略称" prop="name3">
          <el-input v-model="form.name3" placeholder="请输入公司略称" />
        </el-form-item>
        <el-form-item label="公司代码" prop="code">
          <el-input v-model="form.code" placeholder="请输入公司代码" />
        </el-form-item>
        <el-form-item label="公司描述">
          <editor v-model="form.description" :min-height="100"/>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="传真" prop="fax">
          <el-input v-model="form.fax" placeholder="请输入传真" />
        </el-form-item>
        <el-form-item label="网址" prop="site">
          <el-input v-model="form.site" placeholder="请输入网址" />
        </el-form-item>
        <el-form-item label="取引关系" prop="businessFlag">
          <el-select v-model="form.businessFlag" placeholder="请选择取引关系">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SYSTEM_BUSINESS_FLAG)"
                       :key="dict.value" :label="dict.label" :value="parseInt(dict.value)" />
          </el-select>
        </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="アドレス" name="address">
        <el-form-item label="邮编" prop="zipcode">
          <el-input v-model="form.zipcode" placeholder="请输入邮编" />
        </el-form-item>
        <el-form-item label="地址1" prop="address1">
          <el-input v-model="form.address1" placeholder="请输入地址1" />
        </el-form-item>
        <el-form-item label="地址2" prop="address2">
          <el-input v-model="form.address2" placeholder="请输入地址2" />
        </el-form-item>
        <el-form-item label="地址3" prop="address3">
          <el-input v-model="form.address3" placeholder="请输入地址3" />
        </el-form-item>
        <el-form-item label="大楼名" prop="building">
          <el-input v-model="form.building" placeholder="请输入大楼名" />
        </el-form-item>
          </el-tab-pane>
        </el-tabs>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { createCustomer, updateCustomer, deleteCustomer, getCustomer, getCustomerPage, exportCustomerExcel } from "@/api/system/customer";
import Editor from '@/components/Editor';

export default {
  name: "Customer",
  components: {
    Editor,
  },
  data() {
    return {
      // el-tab
      activeName: 'basic_info',
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 顧客列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      dateRangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        name1: null,
        name2: null,
        name3: null,
        code: null,
        description: null,
        zipcode: null,
        address1: null,
        address2: null,
        address3: null,
        building: null,
        phone: null,
        fax: null,
        site: null,
        businessFlag: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name1: [{ required: true, message: "公司名1不能为空", trigger: "blur" }],
        code: [{ required: true, message: "公司代码不能为空", trigger: "blur" }],
        zipcode: [{ required: true, message: "邮编不能为空", trigger: "blur" }],
        address1: [{ required: true, message: "地址1不能为空", trigger: "blur" }],
        phone: [{ required: true, message: "电话不能为空", trigger: "blur" }],
        businessFlag: [{ required: true, message: "取引关系不能为空", trigger: "change" }],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    },
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 处理查询参数
      let params = {...this.queryParams};
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行查询
      getCustomerPage(params).then(response => {
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
        name1: undefined,
        name2: undefined,
        name3: undefined,
        code: undefined,
        description: undefined,
        zipcode: undefined,
        address1: undefined,
        address2: undefined,
        address3: undefined,
        building: undefined,
        phone: undefined,
        fax: undefined,
        site: undefined,
        businessFlag: undefined,
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
      this.dateRangeCreateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加顧客";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getCustomer(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改顧客";
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
          updateCustomer(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createCustomer(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除顧客编号为"' + id + '"的数据项?').then(function() {
          return deleteCustomer(id);
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
      this.addBeginAndEndTime(params, this.dateRangeCreateTime, 'createTime');
      // 执行导出
      this.$modal.confirm('是否确认导出所有顧客数据项?').then(() => {
          this.exportLoading = true;
          return exportCustomerExcel(params);
        }).then(response => {
          this.$download.excel(response, '顧客.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
