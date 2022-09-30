<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="社員番号" prop="id">
        <el-input v-model="queryParams.EmployeeNum" placeholder="请输入社員番号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="姓名" prop="employeeName">
        <el-input v-model="queryParams.employeeName" placeholder="请输入姓名" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="姓名カナ" prop="employeeNameKana">
        <el-input v-model="queryParams.employeeNameKana" placeholder="请输入姓名カナ" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-select v-model="queryParams.sex" placeholder="请选择社員性别" clearable size="small">
          <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SYSTEM_USER_SEX)"
                     :key="dict.value" :label="dict.label" :value="parseInt(dict.value)"/>
        </el-select>
      </el-form-item>
      <el-form-item label="生年月日" prop="birthday">
        <el-date-picker clearable v-model="queryParams.birthday" type="date" placeholder="选择年月日" />
      </el-form-item>
<!--      <el-form-item label="在留カード番号" prop="resideceCardId">-->
<!--        <el-input v-model="queryParams.resideceCardId" placeholder="请输入在留カード番号" clearable @keyup.enter.native="handleQuery"/>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="マイナンバーカード番号" prop="mynumberCardId">-->
<!--        <el-input v-model="queryParams.mynumberCardId" placeholder="请输入マイナンバーカード番号" clearable @keyup.enter.native="handleQuery"/>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="雇用契約番号" prop="emplyCntrctNumbr">-->
<!--        <el-input v-model="queryParams.emplyCntrctNumbr" placeholder="请输入雇用契約番号" clearable @keyup.enter.native="handleQuery"/>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="携帯番号" prop="mobile">-->
<!--        <el-input v-model="queryParams.mobile" placeholder="请输入携帯番号" clearable @keyup.enter.native="handleQuery"/>-->
<!--      </el-form-item>-->
      <el-form-item label="郵便番号" prop="postcode">
        <el-input v-model="queryParams.postcode" placeholder="请输入郵便番号" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
<!--      <el-form-item label="住所" prop="address">-->
<!--        <el-input v-model="queryParams.address" placeholder="请输入住所" clearable @keyup.enter.native="handleQuery"/>-->
<!--      </el-form-item>-->
      <el-form-item label="所属部门" prop="deptId" >
        <treeselect v-model="queryParams.deptId" :options="deptOptions" :show-count="true" :clearable="true"
                    placeholder="请选择所属部门" :normalizer="normalizer"/>
      </el-form-item>
      <el-form-item label="入社日">
        <el-date-picker v-model="dateRangeHireDate" style="width: 240px"
                        type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" />
      </el-form-item>
      <el-form-item label="就職状態" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择就職状態" clearable size="small">
            <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SYSTEM_EMPLOYEE_STATUS)"
                       :key="dict.value" :label="dict.label" :value="parseInt(dict.value)"/>
        </el-select>
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
                   v-hasPermi="['system:employee:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['system:employee:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
<!--      <el-table-column label="ID" align="center" prop="id" />-->
      <el-table-column label="社員番号" align="center" prop="employeeNum" />
      <el-table-column label="姓名" align="center" prop="employeeName" />
      <el-table-column label="姓名カナ" align="center" prop="employeeNameKana" width="100"/>
      <el-table-column label="性别" align="center" prop="sex" width="60">
        <template slot-scope="scope">
        <dict-tag :type="DICT_TYPE.SYSTEM_USER_SEX" :value="scope.row.sex" />
        </template>
      </el-table-column>
      <el-table-column label="生年月日" align="center" prop="birthday" :formatter="dateFormat" width="100" >
      </el-table-column>
      <el-table-column label="在留カード番号" align="center" prop="resideceCardId" />
      <el-table-column label="证件下载" align="center" class-name="small-padding fixed-width" >
        <template slot-scope="scope">
          <!--   TODO 暂时忽略权限问题 v-hasPermi="['system:employee:updownload']" -->
          <el-button v-if="scope.row.avatar != null" size="mini" type="text" icon="el-icon-download"
                     @click="downloadFile(scope.row.avatar, scope.row.employeeName + '_写真')" >照片</el-button>
          <el-button v-if="scope.row.resideceCardCopy != null" size="mini" type="text" icon="el-icon-download"
                     @click="downloadFile(scope.row.resideceCardCopy, scope.row.employeeName + '_在留カード')" >在留卡</el-button>
        </template>
      </el-table-column>
      <el-table-column label="雇用契約番号" align="center" prop="emplyCntrctNumbr" />
      <el-table-column label="携帯番号" align="center" prop="mobile" />
      <el-table-column label="郵便番号" align="center" prop="postcode" />
      <el-table-column label="住所" align="center" prop="address" />
      <el-table-column label="最新履歴下载" align="center" prop="resume" width="120">
        <template slot-scope="scope">
          <!--   TODO 暂时忽略权限问题 v-hasPermi="['system:employee:updownload']" -->
          <el-button v-if="scope.row.resume != null" size="mini" type="text" icon="el-icon-download"
                     @click="downloadFile(scope.row.resume, scope.row.employeeName + '_履歴')" >下载履歴</el-button>
        </template>
      </el-table-column>
      <el-table-column label="勤怠情報" align="center" >
        <el-button size="mini" type="text" slot-scope="scope" @click="handleWorkTime(scope.row)"
                   v-hasPermi="['system:employee:update']">勤怠詳細
        </el-button>
      </el-table-column>
      <el-table-column label="所属部門" align="center" prop="department.name" />
      <el-table-column label="入社日"  :formatter="dateFormat" align="center" prop="hireDate" width="100"/>
      <el-table-column label="就職状態" align="center" prop="status" width="80">
        <template slot-scope="scope">
          <dict-tag :type="DICT_TYPE.SYSTEM_EMPLOYEE_STATUS" :value="scope.row.status" />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['system:employee:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['system:employee:delete']">删除</el-button>
        </template>
      </el-table-column>
      <el-table-column label="新規日付" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="180px">
        <el-tabs v-model="activeName" @tab-click="handleClick">
          <el-tab-pane label="個人情報" name="first">
            <el-form-item label="社員番号" prop="employeeNum">
              <el-input v-model="form.employeeNum" placeholder="自動採番" disabled />
            </el-form-item>
            <el-form-item label="姓名" prop="employeeName">
              <el-input v-model="form.employeeName" placeholder="请输入姓名" />
            </el-form-item>
            <el-form-item label="姓名カナ" prop="employeeNameKana">
              <el-input v-model="form.employeeNameKana" placeholder="请输入姓名カナ" />
            </el-form-item>
            <el-form-item label="照片" prop="">
                <el-image v-if="form.avatar != null" :src="form.avatar" style="width:110px; height:150px"/>
                <tr></tr>
                <el-button type="primary" @click="handleUpload(1)">上传照片</el-button>
                <el-input v-model="form.avatar" placeholder="点击下面按钮上传照片" type="hidden" />
            </el-form-item>
            <el-form-item label="性别" prop="sex">
              <el-select v-model="form.sex" placeholder="请选择">
                <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SYSTEM_USER_SEX)"
                           :key="parseInt(dict.value)" :label="dict.label" :value="parseInt(dict.value)"/>
              </el-select>
            </el-form-item>å
            <el-form-item label="生年月日" prop="birthday">
              <el-date-picker clearable v-model="form.birthday" type="date"  placeholder="选择年月日" />
            </el-form-item>
            <el-form-item label="携帯番号" prop="mobile">
              <el-input v-model="form.mobile" placeholder="请输入携帯番号" />
            </el-form-item>
            <el-form-item label="郵便番号" prop="postcode">
              <el-input v-model="form.postcode" placeholder="请输入郵便番号" />
            </el-form-item>
            <el-form-item label="住所" prop="address">
              <el-input v-model="form.address" placeholder="请输入住所" />
            </el-form-item>
          </el-tab-pane>

          <el-tab-pane label="入社情報" name="second">
            <el-form-item label="在留カード番号" prop="resideceCardId">
              <el-input v-model="form.resideceCardId" placeholder="请输入在留カード番号" />
            </el-form-item>
            <el-form-item label="在留カードコピー" prop="resideceCardCopy">
              <el-image v-if="form.resideceCardCopy != null" :src="form.resideceCardCopy" />
              <el-button type="primary" @click="handleUpload(2)">上传在留卡复印件</el-button>
              <el-input v-model="form.resideceCardCopy" placeholder="点击下面按钮上传在留卡复印件" type="hidden"/>
            </el-form-item>
            <el-form-item label="マイナンバーカード番号" prop="mynumberCardId">
              <el-input v-model="form.mynumberCardId" placeholder="请输入マイナンバーカード番号" />
            </el-form-item>
            <el-form-item label="雇用契約番号" prop="emplyCntrctNumbr">
              <el-input v-model="form.emplyCntrctNumbr" placeholder="请输入雇用契約番号" />
            </el-form-item>
            <el-form-item label="最新履歴" prop="resume">
              <el-button type="primary" @click="handleUpload(3)">上传最新履歴</el-button>
              <el-input v-model="form.resume" placeholder="点击下面按钮上传最新履歴" type="hidden"/>
            </el-form-item>
            <el-form-item label="所属部门" prop="deptId">
              <treeselect v-model="form.deptId" :options="deptOptions" :show-count="true" :clearable="false"
                          placeholder="请选择所属部门" :normalizer="normalizer"/>
            </el-form-item>
            <el-form-item label="入社日" prop="hireDate">
              <el-date-picker clearable v-model="form.hireDate" type="date"  placeholder="选择入社日"/>
            </el-form-item>
            <el-form-item label="就職状態" prop="status">
              <el-select v-model="form.status" placeholder="请选择就職状態">
                <el-option v-for="dict in this.getDictDatas(DICT_TYPE.SYSTEM_EMPLOYEE_STATUS)"
                           :key="dict.value" :label="dict.label" :value="parseInt(dict.value)"/>
              </el-select>
            </el-form-item>
          </el-tab-pane>
        </el-tabs>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

<!--  员工照片，在留卡复印件上传  -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload ref="upload" :limit="1" accept=".jpg, .png, .gif, .jpeg" :auto-upload="false" drag v-if="this.uploadFlg === 1 || this.uploadFlg === 2"
                 :headers="upload.headers"
                 :action="upload.url"
                 :data="upload.data"
                 :disabled="upload.isUploading"
                 :on-change="handleFileChange"
                 :on-progress="handleFileUploadProgress"
                 :on-success="handleFileSuccess">
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或 <em>点击上传</em>
        </div>
        <div class="el-upload__tip" style="color:red" slot="tip">提示：仅允许导入 jpg、png、gif 格式文件！</div>
      </el-upload>
<!--  员工最新学历上传  -->
      <el-upload ref="upload" :limit="1" accept=".xlsx, .xls" :auto-upload="false" drag v-if="this.uploadFlg === 3"
                 :headers="upload.headers"
                 :action="upload.url"
                 :data="upload.data"
                 :disabled="upload.isUploading"
                 :on-progress="handleFileUploadProgress"
                 :on-success="handleFileSuccess">
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip text-center" slot="tip">
          <div class="el-upload__tip" slot="tip">
            <el-checkbox v-model="upload.updateSupport" /> 是否更新已经存在的考勤数据
          </div>
          <span>仅允许导入xls、xlsx格式文件。</span>
          <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="downLoadTemplate">下载简历模板</el-link>
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  createEmployee,
  updateEmployee,
  deleteEmployee,
  getEmployee,
  getEmployeePage,
  exportEmployeeExcel,
  downLoadTemplate,
  ENUM_BUTTON_FLG,
} from "@/api/system/employee";
import {listSimpleDepts} from "@/api/system/dept";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import moment from "moment";
import {getAccessToken} from "@/utils/auth";
// import {getDictDatas, getDictDataLabel} from "@/api//src/utils/dict"
export default {
  name: "Employee",
  components: { Treeselect
  },
  data() {
    return {
      // 追加／修正画面のTags設定
      activeName: 'first',
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 社員列表
      list: [],
      // 部门树选项
      deptOptions: undefined,
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      dateRangeHireDate: [],
      // 上传按钮flg（上传员工照片：1， 上传在留卡复印件：2）
      uploadFlg: null,
      fileList:[],
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        EmployeeNum: null,
        employeeName: null,
        employeeNameKana: null,
        sex: null,
        birthday: null,
        resideceCardId: null,
        mynumberCardId: null,
        emplyCntrctNumbr: null,
        mobile: null,
        postcode: null,
        address: null,
        deptId: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        // EmployeeNum: [{ required: true, message: "社員番号不能为空", trigger: "blur" }],
        employeeName: [{ required: true, message: "姓名不能为空", trigger: "blur" }],
        employeeNameKana: [{ required: true, message: "姓名カナ不能为空", trigger: "blur" }],
        // avatar: [{ required: true, message: "画像URL不能为空", trigger: "blur" }],
        sex: [{ required: true, message: "性别不能为空", trigger: "change" }],
        birthday: [{ required: true, message: "年月日不能为空", trigger: "blur" }],
        resideceCardId: [{ required: true, message: "在留カード番号不能为空", trigger: "blur" }],
        // resideceCardCopy: [{ required: true, message: "在留カード番号コピー不能为空", trigger: "blur" }],
        mobile: [{ required: true, message: "携帯番号不能为空", trigger: "blur" }],
        address: [{ required: true, message: "住所不能为空", trigger: "blur" }],
        deptId: [{ required: true, message: "所属部門不能为空", trigger: "change" }],
        hireDate: [{ required: true, message: "入社日不能为空", trigger: "blur" }],
        status: [{ required: true, message: "就只状态不能为空", trigger: "change" }],
      },
      upload: {
        open: false, // 是否显示弹出层
        title: "", // 弹出层标题
        isUploading: false, // 是否禁用上传
        url: process.env.VUE_APP_BASE_API + "/admin-api/system/employee/upload", // 请求地址
        headers: { Authorization: "Bearer " + getAccessToken() }, // 设置上传的请求头部
        data: {} // 上传的额外数据，用于文件名
      },
    };
  },
  created() {
    this.getList();
    this.getTreeselect();
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
      this.addBeginAndEndTime(params, this.dateRangeHireDate, 'hireDate');
      // 执行查询
      getEmployeePage(params).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    /** 查询部门下拉树结构 */
    getTreeselect() {
      listSimpleDepts().then(response => {
        // 处理 deptOptions 参数
        this.deptOptions = [];
        this.deptOptions.push(...this.handleTree(response.data, "id"));
        // console.log("depOptions.size = ====" + this.deptOptions.length);
      });
    },
    /** 取消按钮 */
    cancel() {
      this.open = false;
      this.reset();
    },
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.name.indexOf(value) !== -1;
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.queryParams.deptId = data.id;
      this.getList();
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        employeeNum: undefined,
        employeeName: undefined,
        employeeNameKana: undefined,
        avatar: undefined,
        sex: undefined,
        birthday: undefined,
        resideceCardId: undefined,
        resideceCardCopy: undefined,
        mynumberCardId: undefined,
        emplyCntrctNumbr: undefined,
        mobile: undefined,
        postcode: undefined,
        address: undefined,
        resume: undefined,
        deptId: undefined,
        hireDate: undefined,
        status: undefined,
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
      this.dateRangeHireDate = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加社員";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getEmployee(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改社員";
      });
    },
    /** 跳转至勤怠管理画面 */
    handleWorkTime(row) {
      if (row.EmployeeNum != '') {
        this.$router.push({ path: "/system/worktime", query: {id: row.EmployeeNum} });
      } else {
        this.$router.push({ path: "/system/worktime" });
      }
      // console.log(row.EmployeeNum + "------");
      // this.$router.push({ path: "/system/worktime" });
    },
    /** 2022/09/05 日期时间格式化处理  刘义民 手动追加
     *  format日付　YYYY-MM-DD
     *　时间格式化使用moent.js
     *　如果开发环境里没有moent.js，需要依次执行下列命令↓
     *                      「cd yudao-ui-admin」
     *                      「npm install moment --save」
     *                      「yarn add moment」
     *  同时在该index.vue里import moment from "moment";
     * */
    dateFormat:function (row,column){
      var date = row[column.property];
      if (date === undefined) {
        return "";
      }
      return moment(date).format("YYYY-MM-DD");
    },

    // ********文件上传处理 开始*********************************************************************************
    /** 上传按钮操作 */
    handleUpload(flg) {
      // 上传按钮flg（上传员工照片：1， 上传在留卡复印件：2）
      this.uploadFlg = flg;
      this.upload.open = true;
      this.upload.title = "上传文件";
    },
    /** 处理上传的文件发生变化 */
    handleFileChange(file, fileList) {

    },
    /** 处理文件上传中 */
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true; // 禁止修改
    },
    /** 发起文件上传 */
    submitFileForm() {
      this.$refs.upload.submit();
    },
    /** 文件上传成功处理 */
    handleFileSuccess(response, file, fileList) {
      // 清理
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      // 提示成功，并刷新
      // this.$modal.msgSuccess("上传成功");
      // 上传照片按钮按下时，赋值照片的url
      if (this.uploadFlg == ENUM_BUTTON_FLG.PHOTO_BUTTON) this.form.avatar = response.data;
      // 上传在留卡复印件按钮按下时，赋值在留卡复印件的url
      if (this.uploadFlg == ENUM_BUTTON_FLG.IDENTITY_BUTTON) this.form.resideceCardCopy = response.data;
      // 上传在留卡复印件按钮按下时，赋值在留卡复印件的url
      if (this.uploadFlg == ENUM_BUTTON_FLG.RESUME_BUTTON) this.form.resume = response.data;
    },
    // ********文件上传处理 终了*********************************************************************************
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updateEmployee(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createEmployee(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const employeeName = row.employeeName;
      const id = row.id;
      this.$modal.confirm('是否确认删除社員名为"' + employeeName + '"的数据项?').then(function() {
        return deleteEmployee(id);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    // ********文件下载处理 开始*********************************************************************************
    /** 下载简历模板操作 劉義民　手動追加*/
    downLoadTemplate() {
      let url = downLoadTemplate();
      console.log("file url is :" + url);
      this.downloadFile(url,'名前_履歴_yyyyMMdd.xls');
    },
    /** 员工照片/在留卡复印件/最新履歴 下载 */
    downloadFile(url, filename) {
      // 从url中获取文件的扩展名
      let index = url.lastIndexOf(".");
      let extension = url.substr(index);
      　this.getBlob(url).then(blob => {
        this.saveAs(blob, filename + extension)
      })
    },
    //通过文件下载url拿到对应的blob对象
    getBlob(url) {
      return new Promise(resolve => {
        const xhr = new XMLHttpRequest()
        xhr.open('GET', url, true)
        xhr.responseType = 'blob'
        xhr.onload = () => {
          if (xhr.status === 200) {
            resolve(xhr.response)
          }
        }
        xhr.send()
      })
    },
    //下载文件(js模拟点击a标签进行下载)
    saveAs(blob, filename) {
      let link = document.createElement('a')
      link.href = window.URL.createObjectURL(blob)
      link.download = filename
      link.click()
    },
    // ********文件下载处理 终了*********************************************************************************
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.addBeginAndEndTime(params, this.dateRangeHireDate, 'hireDate');
      // 执行导出
      this.$modal.confirm('是否确认导出所有社員数据项?').then(() => {
        this.exportLoading = true;
        return exportEmployeeExcel(params);
      }).then(response => {
        this.$download.excel(response, '社員.xls');
        this.exportLoading = false;
      }).catch(() => {});
    },
    // 格式化部门的下拉框
    normalizer(node) {
      return {
        id: node.id,
        label: node.name,
        children: node.children
      }
    }
  }
};
</script>
