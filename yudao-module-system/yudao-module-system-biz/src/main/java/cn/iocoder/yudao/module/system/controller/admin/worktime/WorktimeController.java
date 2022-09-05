package cn.iocoder.yudao.module.system.controller.admin.worktime;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.system.controller.admin.employee.vo.EmployeeRespVO;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.user.UserPageItemRespVO;
import cn.iocoder.yudao.module.system.convert.employee.EmployeeConvert;
import cn.iocoder.yudao.module.system.convert.user.UserConvert;
import cn.iocoder.yudao.module.system.dal.dataobject.employee.EmployeeDO;
import cn.iocoder.yudao.module.system.service.employee.EmployeeService;
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
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertSet;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;

import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.system.controller.admin.worktime.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.worktime.WorktimeDO;
import cn.iocoder.yudao.module.system.convert.worktime.WorktimeConvert;
import cn.iocoder.yudao.module.system.service.worktime.WorktimeService;

@Api(tags = "管理后台 - 勤怠")
@RestController
@RequestMapping("/system/worktime")
@Validated
public class WorktimeController {

    @Resource
    private WorktimeService worktimeService;
    @Resource
    private EmployeeService employeeService;

    @PostMapping("/create")
    @ApiOperation("创建勤怠")
    @PreAuthorize("@ss.hasPermission('system:worktime:create')")
    public CommonResult<Long> createWorktime(@Valid @RequestBody WorktimeCreateReqVO createReqVO) {
        return success(worktimeService.createWorktime(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新勤怠")
    @PreAuthorize("@ss.hasPermission('system:worktime:update')")
    public CommonResult<Boolean> updateWorktime(@Valid @RequestBody WorktimeUpdateReqVO updateReqVO) {
        worktimeService.updateWorktime(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除勤怠")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('system:worktime:delete')")
    public CommonResult<Boolean> deleteWorktime(@RequestParam("id") Long id) {
        worktimeService.deleteWorktime(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得勤怠")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('system:worktime:query')")
    public CommonResult<WorktimeRespVO> getWorktime(@RequestParam("id") Long id) {
        WorktimeDO worktime = worktimeService.getWorktime(id);
        return success(WorktimeConvert.INSTANCE.convert(worktime));
    }

    @GetMapping("/list")
    @ApiOperation("获得勤怠列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('system:worktime:query')")
    public CommonResult<List<WorktimeRespVO>> getWorktimeList(@RequestParam("ids") Collection<Long> ids) {
        List<WorktimeDO> list = worktimeService.getWorktimeList(ids);
        return success(WorktimeConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得勤怠分页")
    @PreAuthorize("@ss.hasPermission('system:worktime:query')")
    public CommonResult<PageResult<WorktimeRespVO>> getWorktimePage(@Valid WorktimePageReqVO pageVO) {
        PageResult<WorktimeDO> pageResult = worktimeService.getWorktimePage(pageVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return success(new PageResult<>(pageResult.getTotal())); // 返回空
        }

        // 2022/09/01 劉義民　手動追加　開始
        // 参照开发文档 https://doc.iocoder.cn/mybatis/#_6-mapper-xml "尽量避免数据库的连表（多表）查询"
        // 获得拼接需要的数据
        Collection<Long> employeeIds = convertList(pageResult.getList(), WorktimeDO::getEmployeeId);
        Map<Long, EmployeeDO> employeeMap = employeeService.getEmployeetMap(employeeIds);
        // 拼接结果返回
        List<WorktimeRespVO> worktimeList = new ArrayList<>(pageResult.getList().size());
        pageResult.getList().forEach(worktime -> {
            WorktimeRespVO respVO = WorktimeConvert.INSTANCE.convert(worktime);
            respVO.setEmployee(WorktimeConvert.INSTANCE.convert(employeeMap.get(worktime.getEmployeeId())));
            worktimeList.add(respVO);
        });
        // 2022/09/01 劉義民　手動追加　終了
//        return success(WorktimeConvert.INSTANCE.convertPage(pageResult));
        return success(new PageResult<>(worktimeList, pageResult.getTotal()));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出勤怠 Excel")
    @PreAuthorize("@ss.hasPermission('system:worktime:export')")
    @OperateLog(type = EXPORT)
    public void exportWorktimeExcel(@Valid WorktimeExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<WorktimeDO> list = worktimeService.getWorktimeList(exportReqVO);
        // 导出 Excel
        List<WorktimeExcelVO> datas = WorktimeConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "勤怠.xls", "数据", WorktimeExcelVO.class, datas);
    }

}
