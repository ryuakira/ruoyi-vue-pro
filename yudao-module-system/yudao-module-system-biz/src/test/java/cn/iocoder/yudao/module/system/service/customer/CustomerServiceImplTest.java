package cn.iocoder.yudao.module.system.service.customer;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.system.controller.admin.customer.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.customer.CustomerDO;
import cn.iocoder.yudao.module.system.dal.mysql.customer.CustomerMapper;
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
* {@link CustomerServiceImpl} 的单元测试类
*
* @author KYO
*/
@Import(CustomerServiceImpl.class)
public class CustomerServiceImplTest extends BaseDbUnitTest {

    @Resource
    private CustomerServiceImpl customerService;

    @Resource
    private CustomerMapper customerMapper;

    @Test
    public void testCreateCustomer_success() {
        // 准备参数
        CustomerCreateReqVO reqVO = randomPojo(CustomerCreateReqVO.class);

        // 调用
        Long customerId = customerService.createCustomer(reqVO);
        // 断言
        assertNotNull(customerId);
        // 校验记录的属性是否正确
        CustomerDO customer = customerMapper.selectById(customerId);
        assertPojoEquals(reqVO, customer);
    }

    @Test
    public void testUpdateCustomer_success() {
        // mock 数据
        CustomerDO dbCustomer = randomPojo(CustomerDO.class);
        customerMapper.insert(dbCustomer);// @Sql: 先插入出一条存在的数据
        // 准备参数
        CustomerUpdateReqVO reqVO = randomPojo(CustomerUpdateReqVO.class, o -> {
            o.setId(dbCustomer.getId()); // 设置更新的 ID
        });

        // 调用
        customerService.updateCustomer(reqVO);
        // 校验是否更新正确
        CustomerDO customer = customerMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, customer);
    }

    @Test
    public void testUpdateCustomer_notExists() {
        // 准备参数
        CustomerUpdateReqVO reqVO = randomPojo(CustomerUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> customerService.updateCustomer(reqVO), CUSTOMER_NOT_EXISTS);
    }

    @Test
    public void testDeleteCustomer_success() {
        // mock 数据
        CustomerDO dbCustomer = randomPojo(CustomerDO.class);
        customerMapper.insert(dbCustomer);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbCustomer.getId();

        // 调用
        customerService.deleteCustomer(id);
       // 校验数据不存在了
       assertNull(customerMapper.selectById(id));
    }

    @Test
    public void testDeleteCustomer_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> customerService.deleteCustomer(id), CUSTOMER_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCustomerPage() {
       // mock 数据
       CustomerDO dbCustomer = randomPojo(CustomerDO.class, o -> { // 等会查询到
           o.setName1(null);
           o.setName2(null);
           o.setName3(null);
           o.setCode(null);
           o.setDescription(null);
           o.setZipcode(null);
           o.setAddress1(null);
           o.setAddress2(null);
           o.setAddress3(null);
           o.setBuilding(null);
           o.setPhone(null);
           o.setFax(null);
           o.setSite(null);
           o.setBusinessFlag(null);
           o.setCreateTime(null);
       });
       customerMapper.insert(dbCustomer);
       // 测试 name1 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setName1(null)));
       // 测试 name2 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setName2(null)));
       // 测试 name3 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setName3(null)));
       // 测试 code 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setCode(null)));
       // 测试 description 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setDescription(null)));
       // 测试 zipcode 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setZipcode(null)));
       // 测试 address1 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setAddress1(null)));
       // 测试 address2 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setAddress2(null)));
       // 测试 address3 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setAddress3(null)));
       // 测试 building 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setBuilding(null)));
       // 测试 phone 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setPhone(null)));
       // 测试 fax 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setFax(null)));
       // 测试 site 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setSite(null)));
       // 测试 businessFlag 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setBusinessFlag(null)));
       // 测试 createTime 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setCreateTime(null)));
       // 准备参数
       CustomerPageReqVO reqVO = new CustomerPageReqVO();
       reqVO.setName1(null);
       reqVO.setName2(null);
       reqVO.setName3(null);
       reqVO.setCode(null);
       reqVO.setDescription(null);
       reqVO.setZipcode(null);
       reqVO.setAddress1(null);
       reqVO.setAddress2(null);
       reqVO.setAddress3(null);
       reqVO.setBuilding(null);
       reqVO.setPhone(null);
       reqVO.setFax(null);
       reqVO.setSite(null);
       reqVO.setBusinessFlag(null);
       reqVO.setBeginCreateTime(null);
       reqVO.setEndCreateTime(null);

       // 调用
       PageResult<CustomerDO> pageResult = customerService.getCustomerPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbCustomer, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCustomerList() {
       // mock 数据
       CustomerDO dbCustomer = randomPojo(CustomerDO.class, o -> { // 等会查询到
           o.setName1(null);
           o.setName2(null);
           o.setName3(null);
           o.setCode(null);
           o.setDescription(null);
           o.setZipcode(null);
           o.setAddress1(null);
           o.setAddress2(null);
           o.setAddress3(null);
           o.setBuilding(null);
           o.setPhone(null);
           o.setFax(null);
           o.setSite(null);
           o.setBusinessFlag(null);
           o.setCreateTime(null);
       });
       customerMapper.insert(dbCustomer);
       // 测试 name1 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setName1(null)));
       // 测试 name2 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setName2(null)));
       // 测试 name3 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setName3(null)));
       // 测试 code 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setCode(null)));
       // 测试 description 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setDescription(null)));
       // 测试 zipcode 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setZipcode(null)));
       // 测试 address1 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setAddress1(null)));
       // 测试 address2 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setAddress2(null)));
       // 测试 address3 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setAddress3(null)));
       // 测试 building 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setBuilding(null)));
       // 测试 phone 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setPhone(null)));
       // 测试 fax 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setFax(null)));
       // 测试 site 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setSite(null)));
       // 测试 businessFlag 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setBusinessFlag(null)));
       // 测试 createTime 不匹配
       customerMapper.insert(cloneIgnoreId(dbCustomer, o -> o.setCreateTime(null)));
       // 准备参数
       CustomerExportReqVO reqVO = new CustomerExportReqVO();
       reqVO.setName1(null);
       reqVO.setName2(null);
       reqVO.setName3(null);
       reqVO.setCode(null);
       reqVO.setDescription(null);
       reqVO.setZipcode(null);
       reqVO.setAddress1(null);
       reqVO.setAddress2(null);
       reqVO.setAddress3(null);
       reqVO.setBuilding(null);
       reqVO.setPhone(null);
       reqVO.setFax(null);
       reqVO.setSite(null);
       reqVO.setBusinessFlag(null);
       reqVO.setBeginCreateTime(null);
       reqVO.setEndCreateTime(null);

       // 调用
       List<CustomerDO> list = customerService.getCustomerList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbCustomer, list.get(0));
    }

}
