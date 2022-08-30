package cn.iocoder.yudao.module.system.service.employee;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.system.controller.admin.employee.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.employee.EmployeeDO;
import cn.iocoder.yudao.module.system.dal.mysql.employee.EmployeeMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
* {@link EmployeeServiceImpl} 的单元测试类
*
* @author 劉
*/
@Import(EmployeeServiceImpl.class)
public class EmployeeServiceImplTest extends BaseDbUnitTest {

    @Resource
    private EmployeeServiceImpl employeeService;

    @Resource
    private EmployeeMapper employeeMapper;

    @Test
    public void testCreateEmployee_success() {
        // 准备参数
        EmployeeCreateReqVO reqVO = randomPojo(EmployeeCreateReqVO.class);

        // 调用
        Long employeeId = employeeService.createEmployee(reqVO);
        // 断言
        assertNotNull(employeeId);
        // 校验记录的属性是否正确
        EmployeeDO employee = employeeMapper.selectById(employeeId);
        assertPojoEquals(reqVO, employee);
    }

    @Test
    public void testUpdateEmployee_success() {
        // mock 数据
        EmployeeDO dbEmployee = randomPojo(EmployeeDO.class);
        employeeMapper.insert(dbEmployee);// @Sql: 先插入出一条存在的数据
        // 准备参数
        EmployeeUpdateReqVO reqVO = randomPojo(EmployeeUpdateReqVO.class, o -> {
            o.setId(dbEmployee.getId()); // 设置更新的 ID
        });

        // 调用
        employeeService.updateEmployee(reqVO);
        // 校验是否更新正确
        EmployeeDO employee = employeeMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, employee);
    }

    @Test
    public void testUpdateEmployee_notExists() {
        // 准备参数
        EmployeeUpdateReqVO reqVO = randomPojo(EmployeeUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> employeeService.updateEmployee(reqVO), EMPLOYEE_NOT_EXISTS);
    }

    @Test
    public void testDeleteEmployee_success() {
        // mock 数据
        EmployeeDO dbEmployee = randomPojo(EmployeeDO.class);
        employeeMapper.insert(dbEmployee);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbEmployee.getId();

        // 调用
        employeeService.deleteEmployee(id);
       // 校验数据不存在了
       assertNull(employeeMapper.selectById(id));
    }

    @Test
    public void testDeleteEmployee_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> employeeService.deleteEmployee(id), EMPLOYEE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetEmployeePage() {
       // mock 数据
       EmployeeDO dbEmployee = randomPojo(EmployeeDO.class, o -> { // 等会查询到
           o.setEmployeeId(null);
           o.setEmployeeName(null);
           o.setEmployeeNameKana(null);
           o.setSex(null);
           o.setBirthday(null);
           o.setResideceCardId(null);
           o.setMynumberCardId(null);
           o.setEmplyCntrctNumbr(null);
           o.setMobile(null);
           o.setPostcode(null);
           o.setAddress(null);
           o.setDeptId(null);
           o.setHireDate(null);
           o.setStatus(null);
       });
       employeeMapper.insert(dbEmployee);
       // 测试 employeeId 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setEmployeeId(null)));
       // 测试 employeeName 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setEmployeeName(null)));
       // 测试 employeeNameKana 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setEmployeeNameKana(null)));
       // 测试 sex 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setSex(null)));
       // 测试 birthday 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setBirthday(null)));
       // 测试 resideceCardId 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setResideceCardId(null)));
       // 测试 mynumberCardId 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setMynumberCardId(null)));
       // 测试 emplyCntrctNumbr 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setEmplyCntrctNumbr(null)));
       // 测试 mobile 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setMobile(null)));
       // 测试 postcode 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setPostcode(null)));
       // 测试 address 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setAddress(null)));
       // 测试 deptId 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setDeptId(null)));
       // 测试 hireDate 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setHireDate(null)));
       // 测试 status 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setStatus(null)));
       // 准备参数
       EmployeePageReqVO reqVO = new EmployeePageReqVO();
       reqVO.setEmployeeId(null);
       reqVO.setEmployeeName(null);
       reqVO.setEmployeeNameKana(null);
       reqVO.setSex(null);
       reqVO.setBirthday(null);
       reqVO.setResideceCardId(null);
       reqVO.setMynumberCardId(null);
       reqVO.setEmplyCntrctNumbr(null);
       reqVO.setMobile(null);
       reqVO.setPostcode(null);
       reqVO.setAddress(null);
       reqVO.setDeptId(null);
       reqVO.setBeginHireDate(null);
       reqVO.setEndHireDate(null);
       reqVO.setStatus(null);

       // 调用
       PageResult<EmployeeDO> pageResult = employeeService.getEmployeePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbEmployee, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetEmployeeList() {
       // mock 数据
       EmployeeDO dbEmployee = randomPojo(EmployeeDO.class, o -> { // 等会查询到
           o.setEmployeeId(null);
           o.setEmployeeName(null);
           o.setEmployeeNameKana(null);
           o.setSex(null);
           o.setBirthday(null);
           o.setResideceCardId(null);
           o.setMynumberCardId(null);
           o.setEmplyCntrctNumbr(null);
           o.setMobile(null);
           o.setPostcode(null);
           o.setAddress(null);
           o.setDeptId(null);
           o.setHireDate(null);
           o.setStatus(null);
       });
       employeeMapper.insert(dbEmployee);
       // 测试 employeeId 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setEmployeeId(null)));
       // 测试 employeeName 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setEmployeeName(null)));
       // 测试 employeeNameKana 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setEmployeeNameKana(null)));
       // 测试 sex 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setSex(null)));
       // 测试 birthday 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setBirthday(null)));
       // 测试 resideceCardId 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setResideceCardId(null)));
       // 测试 mynumberCardId 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setMynumberCardId(null)));
       // 测试 emplyCntrctNumbr 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setEmplyCntrctNumbr(null)));
       // 测试 mobile 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setMobile(null)));
       // 测试 postcode 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setPostcode(null)));
       // 测试 address 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setAddress(null)));
       // 测试 deptId 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setDeptId(null)));
       // 测试 hireDate 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setHireDate(null)));
       // 测试 status 不匹配
       employeeMapper.insert(cloneIgnoreId(dbEmployee, o -> o.setStatus(null)));
       // 准备参数
       EmployeeExportReqVO reqVO = new EmployeeExportReqVO();
       reqVO.setEmployeeId(null);
       reqVO.setEmployeeName(null);
       reqVO.setEmployeeNameKana(null);
       reqVO.setSex(null);
       reqVO.setBirthday(null);
       reqVO.setResideceCardId(null);
       reqVO.setMynumberCardId(null);
       reqVO.setEmplyCntrctNumbr(null);
       reqVO.setMobile(null);
       reqVO.setPostcode(null);
       reqVO.setAddress(null);
       reqVO.setDeptId(null);
       reqVO.setBeginHireDate(null);
       reqVO.setEndHireDate(null);
       reqVO.setStatus(null);

       // 调用
       List<EmployeeDO> list = employeeService.getEmployeeList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbEmployee, list.get(0));
    }

}
