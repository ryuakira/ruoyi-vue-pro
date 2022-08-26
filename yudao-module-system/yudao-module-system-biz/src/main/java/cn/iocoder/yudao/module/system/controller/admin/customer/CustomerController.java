package cn.iocoder.yudao.module.system.controller.admin.customer;

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

import cn.iocoder.yudao.module.system.controller.admin.customer.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.customer.CustomerDO;
import cn.iocoder.yudao.module.system.convert.customer.CustomerConvert;
import cn.iocoder.yudao.module.system.service.customer.CustomerService;

@Api(tags = "管理后台 - 顧客")
@RestController
@RequestMapping("/system/customer")
@Validated
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @PostMapping("/create")
    @ApiOperation("创建顧客")
    @PreAuthorize("@ss.hasPermission('system:customer:create')")
    public CommonResult<Long> createCustomer(@Valid @RequestBody CustomerCreateReqVO createReqVO) {
        return success(customerService.createCustomer(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新顧客")
    @PreAuthorize("@ss.hasPermission('system:customer:update')")
    public CommonResult<Boolean> updateCustomer(@Valid @RequestBody CustomerUpdateReqVO updateReqVO) {
        customerService.updateCustomer(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除顧客")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('system:customer:delete')")
    public CommonResult<Boolean> deleteCustomer(@RequestParam("id") Long id) {
        customerService.deleteCustomer(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得顧客")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('system:customer:query')")
    public CommonResult<CustomerRespVO> getCustomer(@RequestParam("id") Long id) {
        CustomerDO customer = customerService.getCustomer(id);
        return success(CustomerConvert.INSTANCE.convert(customer));
    }

    @GetMapping("/list")
    @ApiOperation("获得顧客列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('system:customer:query')")
    public CommonResult<List<CustomerRespVO>> getCustomerList(@RequestParam("ids") Collection<Long> ids) {
        List<CustomerDO> list = customerService.getCustomerList(ids);
        return success(CustomerConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得顧客分页")
    @PreAuthorize("@ss.hasPermission('system:customer:query')")
    public CommonResult<PageResult<CustomerRespVO>> getCustomerPage(@Valid CustomerPageReqVO pageVO) {
        PageResult<CustomerDO> pageResult = customerService.getCustomerPage(pageVO);
        return success(CustomerConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出顧客 Excel")
    @PreAuthorize("@ss.hasPermission('system:customer:export')")
    @OperateLog(type = EXPORT)
    public void exportCustomerExcel(@Valid CustomerExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<CustomerDO> list = customerService.getCustomerList(exportReqVO);
        // 导出 Excel
        List<CustomerExcelVO> datas = CustomerConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "顧客.xls", "数据", CustomerExcelVO.class, datas);
    }

}
