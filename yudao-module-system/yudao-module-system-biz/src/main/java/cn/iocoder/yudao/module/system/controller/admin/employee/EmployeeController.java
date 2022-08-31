package cn.iocoder.yudao.module.system.controller.admin.employee;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.*;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.system.controller.admin.employee.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.employee.EmployeeDO;
import cn.iocoder.yudao.module.system.convert.employee.EmployeeConvert;
import cn.iocoder.yudao.module.system.service.employee.EmployeeService;

@Api(tags = "管理后台 - 社員")
@RestController
@RequestMapping("/system/employee")
@Validated
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @PostMapping("/create")
    @ApiOperation("创建社員")
    @PreAuthorize("@ss.hasPermission('system:employee:create')")
    public CommonResult<Long> createEmployee(@Valid @RequestBody EmployeeCreateReqVO createReqVO) {
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
}
