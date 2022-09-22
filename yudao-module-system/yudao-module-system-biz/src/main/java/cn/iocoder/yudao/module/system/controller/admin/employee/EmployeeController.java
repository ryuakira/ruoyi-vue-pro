package cn.iocoder.yudao.module.system.controller.admin.employee;

import cn.hutool.core.io.IoUtil;
import cn.iocoder.yudao.module.infra.service.file.FileService;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.system.service.dept.DeptService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.*;

import javax.validation.*;
import javax.servlet.http.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;

import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.system.controller.admin.employee.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.employee.EmployeeDO;
import cn.iocoder.yudao.module.system.convert.employee.EmployeeConvert;
import cn.iocoder.yudao.module.system.service.employee.EmployeeService;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "管理后台 - 社員")
@RestController
@RequestMapping("/system/employee")
@Validated
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @Resource
    private DeptService deptService;
    @Resource
    private FileService fileService;
    @PostMapping("/create")
    @ApiOperation("创建社員")
    @PreAuthorize("@ss.hasPermission('system:employee:create')")
    public CommonResult<Long> createEmployee(@Valid @RequestBody EmployeeCreateReqVO createReqVO) {
        // 2022/09/03 社員番号採番処理
        createReqVO.setEmployeeNum(getEmployeeNumbering(createReqVO));
        return success(employeeService.createEmployee(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新社員")
    @PreAuthorize("@ss.hasPermission('system:employee:update')")
    public CommonResult<Boolean> updateEmployee(@Valid @RequestBody EmployeeUpdateReqVO updateReqVO) {
        employeeService.updateEmployee(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除社員")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('system:employee:delete')")
    public CommonResult<Boolean> deleteEmployee(@RequestParam("id") Long id) {
        employeeService.deleteEmployee(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得社員")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('system:employee:query')")
    public CommonResult<EmployeeRespVO> getEmployee(@RequestParam("id") Long id) {
        EmployeeDO employee = employeeService.getEmployee(id);
        return success(EmployeeConvert.INSTANCE.convert(employee));
    }

    @GetMapping("/list")
    @ApiOperation("获得社員列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('system:employee:query')")
    public CommonResult<List<EmployeeRespVO>> getEmployeeList(@RequestParam("ids") Collection<Long> ids) {
        List<EmployeeDO> list = employeeService.getEmployeeList(ids);
        return success(EmployeeConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得社員分页")
    @PreAuthorize("@ss.hasPermission('system:employee:query')")
    public CommonResult<PageResult<EmployeeRespVO>> getEmployeePage(@Valid EmployeePageReqVO pageVO) {
        PageResult<EmployeeDO> pageResult = employeeService.getEmployeePage(pageVO);
        return success(EmployeeConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出社員 Excel")
    @PreAuthorize("@ss.hasPermission('system:employee:export')")
    @OperateLog(type = EXPORT)
    public void exportEmployeeExcel(@Valid EmployeeExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<EmployeeDO> list = employeeService.getEmployeeList(exportReqVO);
        // 导出 Excel
        List<EmployeeExcelVO> datas = EmployeeConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "社員.xls", "数据", EmployeeExcelVO.class, datas);
    }

    @GetMapping("/list-all-simple")
    @ApiOperation(value = "获取在职状态的员工精简信息列表", notes = "只包含被开启的员工信息，主要用于前端的下拉选项")
    public CommonResult<List<EmployeeRespVO>> getSimpleEmployeeList() {
        // 从员工表，获取在职状态的员工信息
        List<EmployeeDO> list = employeeService.getEmployeeListByStatus(0);
        // 排序后，返回给前端
        return success(EmployeeConvert.INSTANCE.convertList(list));
    }

    // 2022/09/03 社員番号の採番処理　開始
    private String getEmployeeNumbering(EmployeeCreateReqVO createReqVO) {
        StringBuffer employeeNum = new StringBuffer();

        // ①部門番号より、会社フラグ（本社／他社の「T」、「G」）を特定する
        DeptDO deptDO = deptService.getDept(createReqVO.getDeptId());
        int flg = deptDO.getStatus();
        employeeNum.append(flg == 0 ? "T" : "G");

        // ②社員テーブルの採番を行う
        long maxId = employeeService.getEmployeeMaxId();
        String strNum = String.format("%04d",maxId);
        employeeNum.append(strNum);

        // ③社員番号を作る：T/G + シーケンスNo + 入社日（yyyymmdd）
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        employeeNum.append(sdf.format(createReqVO.getHireDate()));

        return employeeNum.toString();
    }
    // 2022/09/03 社員番号の採番処理　終了

    @PostMapping("/upload")
    @ApiOperation("上传社員照片/在留卡复印件")
    @PreAuthorize("@ss.hasPermission('system:employee:updownload')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "社員照片/在留卡复印件附件", required = true, dataTypeClass = MultipartFile.class),
            @ApiImplicitParam(name = "path", value = "社員照片/在留卡复印件路径", example = "yudaoyuanma.png", dataTypeClass = String.class)
    })
    @OperateLog(logArgs = false) // 上传文件，没有记录操作日志的必要
    public CommonResult<String> uploadFile(@RequestParam("file") MultipartFile file,
                                                   @RequestParam(value = "path", required = false) String path) throws Exception {

        String fileUrl = fileService.createFile(file.getOriginalFilename(), path, IoUtil.readBytes(file.getInputStream()));
        return success(fileUrl);
    }
}
