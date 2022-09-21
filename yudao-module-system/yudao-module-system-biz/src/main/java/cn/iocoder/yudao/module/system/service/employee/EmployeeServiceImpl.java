package cn.iocoder.yudao.module.system.service.employee;

import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.system.controller.admin.employee.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.employee.EmployeeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.system.convert.employee.EmployeeConvert;
import cn.iocoder.yudao.module.system.dal.mysql.employee.EmployeeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

/**
 * 社員 Service 实现类
 *
 * @author 劉
 */
@Service
@Validated
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public Long createEmployee(EmployeeCreateReqVO createReqVO) {
        // 插入
        EmployeeDO employee = EmployeeConvert.INSTANCE.convert(createReqVO);

        // 去执行我在EmployeeMapper.xml里指定的INSERT文 ----2022/09/03
        //employeeMapper.insertRetrunId(employee);
        //long intId = employee.getId();
        employeeMapper.insert(employee);
        // 返回
        return employee.getId();
    }

    @Override
    public void updateEmployee(EmployeeUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateEmployeeExists(updateReqVO.getId());
        // 更新
        EmployeeDO updateObj = EmployeeConvert.INSTANCE.convert(updateReqVO);
        employeeMapper.updateById(updateObj);
    }

    @Override
    public void deleteEmployee(Long id) {
        // 校验存在
        this.validateEmployeeExists(id);
        // 删除
        employeeMapper.deleteById(id);
    }

    private void validateEmployeeExists(Long id) {
        if (employeeMapper.selectById(id) == null) {
            throw exception(EMPLOYEE_NOT_EXISTS);
        }
    }

    @Override
    public EmployeeDO getEmployee(Long id) {
        return employeeMapper.selectById(id);
    }

    @Override
    public List<EmployeeDO> getEmployeeList(Collection<Long> ids) {
        return employeeMapper.selectBatchIds(ids);
    }
    // 2022/09/01 劉義民　社員番号で社員名前を取得処理　追加開始
    @Override
    public List<EmployeeDO> getEmployeeInfoList(Collection<Long> ids) {
        return employeeMapper.selectBatchIds(ids);
    }
    @Override
    public List<EmployeeDO> getEmployeeInfoList2(Map<String, Object> colMap) {
        return employeeMapper.selectByMap(colMap);
    }


    // 2022/09/01 劉義民　社員番号で社員名前を取得処理　追加終了
    @Override
    public PageResult<EmployeeDO> getEmployeePage(EmployeePageReqVO pageReqVO) {
        return employeeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<EmployeeDO> getEmployeeList(EmployeeExportReqVO exportReqVO) {
        return employeeMapper.selectList(exportReqVO);
    }

    @Override
    public List<EmployeeDO> getEmployeeListByStatus(Integer status) {
        return employeeMapper.selectListByStatus(status);
    }

    @Override
    public Long getEmployeeMaxId() {
        long maxId;
        try {
            maxId = employeeMapper.returnMaxId() + 1;
        } catch (Exception e) {
            maxId = 1;
        }
        return maxId;
    }
}
