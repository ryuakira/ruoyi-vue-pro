<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['system:company:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['system:company:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="No." align="center" prop="id" />
      <el-table-column label="会社名" align="center" prop="nameJp" />
      <el-table-column label="郵便" align="center" prop="zipcode" />
      <el-table-column label="住所1" align="center" prop="address1Jp" />
      <el-table-column label="住所2" align="center" prop="address2Jp" />
      <el-table-column label="住所3" align="center" prop="address3Jp" />
      <el-table-column label="建物名" align="center" prop="buildingJp" />
      <el-table-column label="電話番号" align="center" prop="phone" />
      <el-table-column label="FAX" align="center" prop="fax" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['system:company:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['system:company:delete']">删除</el-button>
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
          <el-tab-pane label="会社情報" name="name_jp">
        <el-form-item label="会社名" prop="nameJp">
          <el-input v-model="form.nameJp" placeholder="请输入会社名" />
        </el-form-item>
        <el-form-item label="会社略称" prop="nameAbrvt">
          <el-input v-model="form.nameAbrvt" placeholder="请输入会社略称" />
        </el-form-item>
        <el-form-item label="法人番号" prop="code">
          <el-input v-model="form.code" placeholder="请输入法人番号" />
        </el-form-item>
        <el-form-item label="郵便" prop="zipcode">
          <el-input v-model="form.zipcode" placeholder="请输入郵便" />
        </el-form-item>
        <el-form-item label="住所1" prop="address1Jp">
          <el-input v-model="form.address1Jp" placeholder="请输入住所1" />
        </el-form-item>
        <el-form-item label="住所2" prop="address2Jp">
          <el-input v-model="form.address2Jp" placeholder="请输入住所2" />
        </el-form-item>
        <el-form-item label="住所3" prop="address3Jp">
          <el-input v-model="form.address3Jp" placeholder="请输入住所3" />
        </el-form-item>
        <el-form-item label="建物名" prop="buildingJp">
          <el-input v-model="form.buildingJp" placeholder="请输入建物名" />
        </el-form-item>
            <el-form-item label="電話番号" prop="phone">
              <el-input v-model="form.phone" placeholder="请输入電話番号" />
            </el-form-item>
            <el-form-item label="FAX" prop="fax">
              <el-input v-model="form.fax" placeholder="请输入FAX" />
            </el-form-item>
            <el-form-item label="サイト" prop="site">
              <el-input v-model="form.site" placeholder="请输入サイト" />
            </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="Company Info" name="name_en">
        <el-form-item label="Company Name" prop="nameEn">
          <el-input v-model="form.nameEn" placeholder="请输入Company Name" />
        </el-form-item>
        <el-form-item label="Address1" prop="address1En">
          <el-input v-model="form.address1En" placeholder="请输入Address1" />
        </el-form-item>
        <el-form-item label="Address2" prop="address2En">
          <el-input v-model="form.address2En" placeholder="请输入Address2" />
        </el-form-item>
        <el-form-item label="Address3" prop="address3En">
          <el-input v-model="form.address3En" placeholder="请输入Address3" />
        </el-form-item>
        <el-form-item label="Building" prop="buildingEn">
          <el-input v-model="form.buildingEn" placeholder="请输入Building" />
        </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="銀行口座" name="bank">
            <el-tabs v-model="activeBank">
          <el-tab-pane label="銀行口座1" name="bank1">
        <el-form-item label="銀行1" prop="bank1">
          <el-input v-model="form.bank1" placeholder="请输入銀行1" />
        </el-form-item>
        <el-form-item label="銀行コード" prop="bankCode1">
          <el-input v-model="form.bankCode1" placeholder="请输入銀行コード" />
        </el-form-item>
        <el-form-item label="支店名" prop="branch1">
          <el-input v-model="form.branch1" placeholder="请输入支店名" />
        </el-form-item>
        <el-form-item label="支店コード" prop="branchCode1">
          <el-input v-model="form.branchCode1" placeholder="请输入支店コード" />
        </el-form-item>
        <el-form-item label="swiftcode" prop="swift1">
          <el-input v-model="form.swift1" placeholder="请输入swiftcode" />
        </el-form-item>
        <el-form-item label="口座種類" prop="accountType1">
          <el-input v-model="form.accountType1" placeholder="请输入口座種類" />
        </el-form-item>
        <el-form-item label="口座番号" prop="accountNumber1">
          <el-input v-model="form.accountNumber1" placeholder="请输入口座番号" />
        </el-form-item>
        <el-form-item label="口座名義" prop="accountName1">
          <el-input v-model="form.accountName1" placeholder="请输入口座名義" />
        </el-form-item>
        <el-form-item label="通貨" prop="currency1">
          <el-select v-model="form.currency1" placeholder="请选择通貨">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SYSTEM_CURRENCY)"
                       :key="dict.value" :label="dict.label" :value="(dict.value)" />
          </el-select>
        </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="銀行口座2" name="bank2">
        <el-form-item label="銀行2" prop="bank2">
          <el-input v-model="form.bank2" placeholder="请输入銀行2" />
        </el-form-item>
        <el-form-item label="銀行コード" prop="bankCode2">
          <el-input v-model="form.bankCode2" placeholder="请输入銀行コード" />
        </el-form-item>
        <el-form-item label="支店名" prop="branch2">
          <el-input v-model="form.branch2" placeholder="请输入支店名" />
        </el-form-item>
        <el-form-item label="支店コード" prop="branchCode2">
          <el-input v-model="form.branchCode2" placeholder="请输入支店コード" />
        </el-form-item>
        <el-form-item label="swiftcode" prop="swift2">
          <el-input v-model="form.swift2" placeholder="请输入swiftcode" />
        </el-form-item>
        <el-form-item label="口座種類" prop="accountType2">
          <el-input v-model="form.accountType2" placeholder="请输入口座種類" />
        </el-form-item>
        <el-form-item label="口座番号" prop="accountNumber2">
          <el-input v-model="form.accountNumber2" placeholder="请输入口座番号" />
        </el-form-item>
        <el-form-item label="口座名義" prop="accountName2">
          <el-input v-model="form.accountName2" placeholder="请输入口座名義" />
        </el-form-item>
        <el-form-item label="通貨" prop="currency2">
          <el-select v-model="form.currency2" placeholder="请选择通貨">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SYSTEM_CURRENCY)"
                       :key="dict.value" :label="dict.label" :value="(dict.value)" />
          </el-select>
        </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="銀行口座3" name="bank3">
        <el-form-item label="銀行3" prop="bank3">
          <el-input v-model="form.bank3" placeholder="请输入銀行3" />
        </el-form-item>
        <el-form-item label="銀行コード" prop="bankCode3">
          <el-input v-model="form.bankCode3" placeholder="请输入銀行コード" />
        </el-form-item>
        <el-form-item label="支店名" prop="branch3">
          <el-input v-model="form.branch3" placeholder="请输入支店名" />
        </el-form-item>
        <el-form-item label="支店コード" prop="branchCode3">
          <el-input v-model="form.branchCode3" placeholder="请输入支店コード" />
        </el-form-item>
        <el-form-item label="swiftcode" prop="swift3">
          <el-input v-model="form.swift3" placeholder="请输入swiftcode" />
        </el-form-item>
        <el-form-item label="口座種類" prop="accountType3">
          <el-input v-model="form.accountType3" placeholder="请输入口座種類" />
        </el-form-item>
        <el-form-item label="口座番号" prop="accountNumber3">
          <el-input v-model="form.accountNumber3" placeholder="请输入口座番号" />
        </el-form-item>
        <el-form-item label="口座名義" prop="accountName3">
          <el-input v-model="form.accountName3" placeholder="请输入口座名義" />
        </el-form-item>
        <el-form-item label="通貨" prop="currency3">
          <el-select v-model="form.currency3" placeholder="请选择通貨">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SYSTEM_CURRENCY)"
                       :key="dict.value" :label="dict.label" :value="(dict.value)" />
          </el-select>
        </el-form-item>
          </el-tab-pane>
          <el-tab-pane label="銀行口座4" name="bank4">
        <el-form-item label="銀行4" prop="bank4">
          <el-input v-model="form.bank4" placeholder="请输入銀行4" />
        </el-form-item>
        <el-form-item label="銀行コード" prop="bankCode4">
          <el-input v-model="form.bankCode4" placeholder="请输入銀行コード" />
        </el-form-item>
        <el-form-item label="支店名" prop="branch4">
          <el-input v-model="form.branch4" placeholder="请输入支店名" />
        </el-form-item>
        <el-form-item label="支店コード" prop="branchCode4">
          <el-input v-model="form.branchCode4" placeholder="请输入支店コード" />
        </el-form-item>
        <el-form-item label="swiftcode" prop="swift4">
          <el-input v-model="form.swift4" placeholder="请输入swiftcode" />
        </el-form-item>
        <el-form-item label="口座種類" prop="accountType4">
          <el-input v-model="form.accountType4" placeholder="请输入口座種類" />
        </el-form-item>
        <el-form-item label="口座番号" prop="accountNumber4">
          <el-input v-model="form.accountNumber4" placeholder="请输入口座番号" />
        </el-form-item>
        <el-form-item label="口座名義" prop="accountName4">
          <el-input v-model="form.accountName4" placeholder="请输入口座名義" />
        </el-form-item>
        <el-form-item label="通貨" prop="currency4">
          <el-select v-model="form.currency4" placeholder="请选择通貨">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SYSTEM_CURRENCY)"
                       :key="dict.value" :label="dict.label" :value="(dict.value)" />
          </el-select>
        </el-form-item>
          </el-tab-pane>
            </el-tabs>
          </el-tab-pane>
          <el-tab-pane label="添付ファイル" name="file">
        <el-form-item label="添付1" prop="url1">
          <el-input v-model="form.url1" placeholder="请输入添付1" />
        </el-form-item>
        <el-form-item label="添付2" prop="url2">
          <el-input v-model="form.url2" placeholder="请输入添付2" />
        </el-form-item>
        <el-form-item label="添付3" prop="url3">
          <el-input v-model="form.url3" placeholder="请输入添付3" />
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
import { createCompany, updateCompany, deleteCompany, getCompany, getCompanyPage, exportCompanyExcel } from "@/api/system/company";

export default {
  name: "Company",
  components: {
  },
  data() {
    return {
      // el-tab
      activeName: 'name_jp',
      activeBank: 'bank1',
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 会社列表
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
        nameJp: null,
        nameEn: null,
        nameAbrvt: null,
        code: null,
        zipcode: null,
        address1Jp: null,
        address2Jp: null,
        address3Jp: null,
        buildingJp: null,
        address1En: null,
        address2En: null,
        address3En: null,
        buildingEn: null,
        phone: null,
        fax: null,
        site: null,
        bank1: null,
        bankCode1: null,
        branch1: null,
        branchCode1: null,
        swift1: null,
        accountType1: null,
        accountNumber1: null,
        accountName1: null,
        currency1: null,
        bank2: null,
        bankCode2: null,
        branch2: null,
        branchCode2: null,
        swift2: null,
        accountType2: null,
        accountNumber2: null,
        accountName2: null,
        currency2: null,
        bank3: null,
        bankCode3: null,
        branch3: null,
        branchCode3: null,
        swift3: null,
        accountType3: null,
        accountNumber3: null,
        accountName3: null,
        currency3: null,
        bank4: null,
        bankCode4: null,
        branch4: null,
        branchCode4: null,
        swift4: null,
        accountType4: null,
        accountNumber4: null,
        accountName4: null,
        currency4: null,
        url1: null,
        url2: null,
        url3: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        nameJp: [{ required: true, message: "会社名不能为空", trigger: "blur" }],
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
      getCompanyPage(params).then(response => {
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
        nameJp: undefined,
        nameEn: undefined,
        nameAbrvt: undefined,
        code: undefined,
        zipcode: undefined,
        address1Jp: undefined,
        address2Jp: undefined,
        address3Jp: undefined,
        buildingJp: undefined,
        address1En: undefined,
        address2En: undefined,
        address3En: undefined,
        buildingEn: undefined,
        phone: undefined,
        fax: undefined,
        site: undefined,
        bank1: undefined,
        bankCode1: undefined,
        branch1: undefined,
        branchCode1: undefined,
        swift1: undefined,
        accountType1: undefined,
        accountNumber1: undefined,
        accountName1: undefined,
        currency1: undefined,
        bank2: undefined,
        bankCode2: undefined,
        branch2: undefined,
        branchCode2: undefined,
        swift2: undefined,
        accountType2: undefined,
        accountNumber2: undefined,
        accountName2: undefined,
        currency2: undefined,
        bank3: undefined,
        bankCode3: undefined,
        branch3: undefined,
        branchCode3: undefined,
        swift3: undefined,
        accountType3: undefined,
        accountNumber3: undefined,
        accountName3: undefined,
        currency3: undefined,
        bank4: undefined,
        bankCode4: undefined,
        branch4: undefined,
        branchCode4: undefined,
        swift4: undefined,
        accountType4: undefined,
        accountNumber4: undefined,
        accountName4: undefined,
        currency4: undefined,
        url1: undefined,
        url2: undefined,
        url3: undefined,
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
      this.title = "添加会社";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getCompany(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改会社";
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
          updateCompany(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createCompany(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除会社编号为"' + id + '"的数据项?').then(function() {
          return deleteCompany(id);
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
      this.$modal.confirm('是否确认导出所有会社数据项?').then(() => {
          this.exportLoading = true;
          return exportCompanyExcel(params);
        }).then(response => {
          this.$download.excel(response, '会社.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
