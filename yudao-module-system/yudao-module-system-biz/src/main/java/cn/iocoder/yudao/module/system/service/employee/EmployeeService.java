package cn.iocoder.yudao.module.system.service.employee;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.system.controller.admin.employee.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.employee.EmployeeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 社員 Service 接口
 *
 * @author 劉
 */
public interface EmployeeService {

    /**
     * 创建社員
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEmployee(@Valid EmployeeCreateReqVO createReqVO);

    /**
     * 更新社員
     *
     * @param updateReqVO 更新信息
     */
    void updateEmployee(@Valid EmployeeUpdateReqVO updateReqVO);

    /**
     * 删除社員
     *
     * @param id 编号
     */
    void deleteEmployee(Long id);

    /**
     * 获得社員
     *
     * @param id 编号
     * @return 社員
     */
    EmployeeDO getEmployee(Long id);

    /**
     * 获得社員列表
     *
     * @param ids 编号
     * @return 社員列表
     */
    List<EmployeeDO> getEmployeeList(Collection<Long> ids);

    /**
     * 获得社員分页
     *
     * @param pageReqVO 分页查询
     * @return 社員分页
     */
    PageResult<EmployeeDO> getEmployeePage(EmployeePageReqVO pageReqVO);

    /**
     * 获得社員列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 社員列表
     */
    List<EmployeeDO> getEmployeeList(EmployeeExportReqVO exportReqVO);

}
