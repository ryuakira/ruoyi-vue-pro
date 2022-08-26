package cn.iocoder.yudao.module.system.service.company;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.system.controller.admin.company.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.company.CompanyDO;
import cn.iocoder.yudao.module.system.dal.mysql.company.CompanyMapper;
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
* {@link CompanyServiceImpl} 的单元测试类
*
* @author KYO
*/
@Import(CompanyServiceImpl.class)
public class CompanyServiceImplTest extends BaseDbUnitTest {

    @Resource
    private CompanyServiceImpl companyService;

    @Resource
    private CompanyMapper companyMapper;

    @Test
    public void testCreateCompany_success() {
        // 准备参数
        CompanyCreateReqVO reqVO = randomPojo(CompanyCreateReqVO.class);

        // 调用
        Long companyId = companyService.createCompany(reqVO);
        // 断言
        assertNotNull(companyId);
        // 校验记录的属性是否正确
        CompanyDO company = companyMapper.selectById(companyId);
        assertPojoEquals(reqVO, company);
    }

    @Test
    public void testUpdateCompany_success() {
        // mock 数据
        CompanyDO dbCompany = randomPojo(CompanyDO.class);
        companyMapper.insert(dbCompany);// @Sql: 先插入出一条存在的数据
        // 准备参数
        CompanyUpdateReqVO reqVO = randomPojo(CompanyUpdateReqVO.class, o -> {
            o.setId(dbCompany.getId()); // 设置更新的 ID
        });

        // 调用
        companyService.updateCompany(reqVO);
        // 校验是否更新正确
        CompanyDO company = companyMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, company);
    }

    @Test
    public void testUpdateCompany_notExists() {
        // 准备参数
        CompanyUpdateReqVO reqVO = randomPojo(CompanyUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> companyService.updateCompany(reqVO), COMPANY_NOT_EXISTS);
    }

    @Test
    public void testDeleteCompany_success() {
        // mock 数据
        CompanyDO dbCompany = randomPojo(CompanyDO.class);
        companyMapper.insert(dbCompany);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbCompany.getId();

        // 调用
        companyService.deleteCompany(id);
       // 校验数据不存在了
       assertNull(companyMapper.selectById(id));
    }

    @Test
    public void testDeleteCompany_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> companyService.deleteCompany(id), COMPANY_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCompanyPage() {
       // mock 数据
       CompanyDO dbCompany = randomPojo(CompanyDO.class, o -> { // 等会查询到
           o.setNameJp(null);
           o.setNameEn(null);
           o.setNameAbrvt(null);
           o.setCode(null);
           o.setZipcode(null);
           o.setAddress1Jp(null);
           o.setAddress2Jp(null);
           o.setAddress3Jp(null);
           o.setBuildingJp(null);
           o.setAddress1En(null);
           o.setAddress2En(null);
           o.setAddress3En(null);
           o.setBuildingEn(null);
           o.setPhone(null);
           o.setFax(null);
           o.setSite(null);
           o.setBank1(null);
           o.setBankCode1(null);
           o.setBranch1(null);
           o.setBranchCode1(null);
           o.setSwift1(null);
           o.setAccountType1(null);
           o.setAccountNumber1(null);
           o.setAccountName1(null);
           o.setCurrency1(null);
           o.setBank2(null);
           o.setBankCode2(null);
           o.setBranch2(null);
           o.setBranchCode2(null);
           o.setSwift2(null);
           o.setAccountType2(null);
           o.setAccountNumber2(null);
           o.setAccountName2(null);
           o.setCurrency2(null);
           o.setBank3(null);
           o.setBankCode3(null);
           o.setBranch3(null);
           o.setBranchCode3(null);
           o.setSwift3(null);
           o.setAccountType3(null);
           o.setAccountNumber3(null);
           o.setAccountName3(null);
           o.setCurrency3(null);
           o.setBank4(null);
           o.setBankCode4(null);
           o.setBranch4(null);
           o.setBranchCode4(null);
           o.setSwift4(null);
           o.setAccountType4(null);
           o.setAccountNumber4(null);
           o.setAccountName4(null);
           o.setCurrency4(null);
           o.setUrl1(null);
           o.setUrl2(null);
           o.setUrl3(null);
           o.setCreateTime(null);
       });
       companyMapper.insert(dbCompany);
       // 测试 nameJp 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setNameJp(null)));
       // 测试 nameEn 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setNameEn(null)));
       // 测试 nameAbrvt 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setNameAbrvt(null)));
       // 测试 code 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setCode(null)));
       // 测试 zipcode 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setZipcode(null)));
       // 测试 address1Jp 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAddress1Jp(null)));
       // 测试 address2Jp 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAddress2Jp(null)));
       // 测试 address3Jp 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAddress3Jp(null)));
       // 测试 buildingJp 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBuildingJp(null)));
       // 测试 address1En 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAddress1En(null)));
       // 测试 address2En 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAddress2En(null)));
       // 测试 address3En 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAddress3En(null)));
       // 测试 buildingEn 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBuildingEn(null)));
       // 测试 phone 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setPhone(null)));
       // 测试 fax 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setFax(null)));
       // 测试 site 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setSite(null)));
       // 测试 bank1 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBank1(null)));
       // 测试 bankCode1 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBankCode1(null)));
       // 测试 branch1 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBranch1(null)));
       // 测试 branchCode1 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBranchCode1(null)));
       // 测试 swift1 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setSwift1(null)));
       // 测试 accountType1 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAccountType1(null)));
       // 测试 accountNumber1 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAccountNumber1(null)));
       // 测试 accountName1 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAccountName1(null)));
       // 测试 currency1 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setCurrency1(null)));
       // 测试 bank2 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBank2(null)));
       // 测试 bankCode2 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBankCode2(null)));
       // 测试 branch2 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBranch2(null)));
       // 测试 branchCode2 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBranchCode2(null)));
       // 测试 swift2 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setSwift2(null)));
       // 测试 accountType2 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAccountType2(null)));
       // 测试 accountNumber2 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAccountNumber2(null)));
       // 测试 accountName2 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAccountName2(null)));
       // 测试 currency2 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setCurrency2(null)));
       // 测试 bank3 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBank3(null)));
       // 测试 bankCode3 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBankCode3(null)));
       // 测试 branch3 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBranch3(null)));
       // 测试 branchCode3 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBranchCode3(null)));
       // 测试 swift3 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setSwift3(null)));
       // 测试 accountType3 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAccountType3(null)));
       // 测试 accountNumber3 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAccountNumber3(null)));
       // 测试 accountName3 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAccountName3(null)));
       // 测试 currency3 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setCurrency3(null)));
       // 测试 bank4 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBank4(null)));
       // 测试 bankCode4 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBankCode4(null)));
       // 测试 branch4 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBranch4(null)));
       // 测试 branchCode4 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBranchCode4(null)));
       // 测试 swift4 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setSwift4(null)));
       // 测试 accountType4 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAccountType4(null)));
       // 测试 accountNumber4 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAccountNumber4(null)));
       // 测试 accountName4 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAccountName4(null)));
       // 测试 currency4 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setCurrency4(null)));
       // 测试 url1 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setUrl1(null)));
       // 测试 url2 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setUrl2(null)));
       // 测试 url3 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setUrl3(null)));
       // 测试 createTime 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setCreateTime(null)));
       // 准备参数
       CompanyPageReqVO reqVO = new CompanyPageReqVO();
       reqVO.setNameJp(null);
       reqVO.setNameEn(null);
       reqVO.setNameAbrvt(null);
       reqVO.setCode(null);
       reqVO.setZipcode(null);
       reqVO.setAddress1Jp(null);
       reqVO.setAddress2Jp(null);
       reqVO.setAddress3Jp(null);
       reqVO.setBuildingJp(null);
       reqVO.setAddress1En(null);
       reqVO.setAddress2En(null);
       reqVO.setAddress3En(null);
       reqVO.setBuildingEn(null);
       reqVO.setPhone(null);
       reqVO.setFax(null);
       reqVO.setSite(null);
       reqVO.setBank1(null);
       reqVO.setBankCode1(null);
       reqVO.setBranch1(null);
       reqVO.setBranchCode1(null);
       reqVO.setSwift1(null);
       reqVO.setAccountType1(null);
       reqVO.setAccountNumber1(null);
       reqVO.setAccountName1(null);
       reqVO.setCurrency1(null);
       reqVO.setBank2(null);
       reqVO.setBankCode2(null);
       reqVO.setBranch2(null);
       reqVO.setBranchCode2(null);
       reqVO.setSwift2(null);
       reqVO.setAccountType2(null);
       reqVO.setAccountNumber2(null);
       reqVO.setAccountName2(null);
       reqVO.setCurrency2(null);
       reqVO.setBank3(null);
       reqVO.setBankCode3(null);
       reqVO.setBranch3(null);
       reqVO.setBranchCode3(null);
       reqVO.setSwift3(null);
       reqVO.setAccountType3(null);
       reqVO.setAccountNumber3(null);
       reqVO.setAccountName3(null);
       reqVO.setCurrency3(null);
       reqVO.setBank4(null);
       reqVO.setBankCode4(null);
       reqVO.setBranch4(null);
       reqVO.setBranchCode4(null);
       reqVO.setSwift4(null);
       reqVO.setAccountType4(null);
       reqVO.setAccountNumber4(null);
       reqVO.setAccountName4(null);
       reqVO.setCurrency4(null);
       reqVO.setUrl1(null);
       reqVO.setUrl2(null);
       reqVO.setUrl3(null);
       reqVO.setBeginCreateTime(null);
       reqVO.setEndCreateTime(null);

       // 调用
       PageResult<CompanyDO> pageResult = companyService.getCompanyPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbCompany, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCompanyList() {
       // mock 数据
       CompanyDO dbCompany = randomPojo(CompanyDO.class, o -> { // 等会查询到
           o.setNameJp(null);
           o.setNameEn(null);
           o.setNameAbrvt(null);
           o.setCode(null);
           o.setZipcode(null);
           o.setAddress1Jp(null);
           o.setAddress2Jp(null);
           o.setAddress3Jp(null);
           o.setBuildingJp(null);
           o.setAddress1En(null);
           o.setAddress2En(null);
           o.setAddress3En(null);
           o.setBuildingEn(null);
           o.setPhone(null);
           o.setFax(null);
           o.setSite(null);
           o.setBank1(null);
           o.setBankCode1(null);
           o.setBranch1(null);
           o.setBranchCode1(null);
           o.setSwift1(null);
           o.setAccountType1(null);
           o.setAccountNumber1(null);
           o.setAccountName1(null);
           o.setCurrency1(null);
           o.setBank2(null);
           o.setBankCode2(null);
           o.setBranch2(null);
           o.setBranchCode2(null);
           o.setSwift2(null);
           o.setAccountType2(null);
           o.setAccountNumber2(null);
           o.setAccountName2(null);
           o.setCurrency2(null);
           o.setBank3(null);
           o.setBankCode3(null);
           o.setBranch3(null);
           o.setBranchCode3(null);
           o.setSwift3(null);
           o.setAccountType3(null);
           o.setAccountNumber3(null);
           o.setAccountName3(null);
           o.setCurrency3(null);
           o.setBank4(null);
           o.setBankCode4(null);
           o.setBranch4(null);
           o.setBranchCode4(null);
           o.setSwift4(null);
           o.setAccountType4(null);
           o.setAccountNumber4(null);
           o.setAccountName4(null);
           o.setCurrency4(null);
           o.setUrl1(null);
           o.setUrl2(null);
           o.setUrl3(null);
           o.setCreateTime(null);
       });
       companyMapper.insert(dbCompany);
       // 测试 nameJp 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setNameJp(null)));
       // 测试 nameEn 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setNameEn(null)));
       // 测试 nameAbrvt 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setNameAbrvt(null)));
       // 测试 code 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setCode(null)));
       // 测试 zipcode 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setZipcode(null)));
       // 测试 address1Jp 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAddress1Jp(null)));
       // 测试 address2Jp 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAddress2Jp(null)));
       // 测试 address3Jp 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAddress3Jp(null)));
       // 测试 buildingJp 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBuildingJp(null)));
       // 测试 address1En 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAddress1En(null)));
       // 测试 address2En 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAddress2En(null)));
       // 测试 address3En 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAddress3En(null)));
       // 测试 buildingEn 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBuildingEn(null)));
       // 测试 phone 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setPhone(null)));
       // 测试 fax 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setFax(null)));
       // 测试 site 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setSite(null)));
       // 测试 bank1 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBank1(null)));
       // 测试 bankCode1 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBankCode1(null)));
       // 测试 branch1 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBranch1(null)));
       // 测试 branchCode1 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBranchCode1(null)));
       // 测试 swift1 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setSwift1(null)));
       // 测试 accountType1 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAccountType1(null)));
       // 测试 accountNumber1 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAccountNumber1(null)));
       // 测试 accountName1 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAccountName1(null)));
       // 测试 currency1 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setCurrency1(null)));
       // 测试 bank2 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBank2(null)));
       // 测试 bankCode2 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBankCode2(null)));
       // 测试 branch2 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBranch2(null)));
       // 测试 branchCode2 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBranchCode2(null)));
       // 测试 swift2 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setSwift2(null)));
       // 测试 accountType2 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAccountType2(null)));
       // 测试 accountNumber2 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAccountNumber2(null)));
       // 测试 accountName2 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAccountName2(null)));
       // 测试 currency2 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setCurrency2(null)));
       // 测试 bank3 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBank3(null)));
       // 测试 bankCode3 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBankCode3(null)));
       // 测试 branch3 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBranch3(null)));
       // 测试 branchCode3 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBranchCode3(null)));
       // 测试 swift3 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setSwift3(null)));
       // 测试 accountType3 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAccountType3(null)));
       // 测试 accountNumber3 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAccountNumber3(null)));
       // 测试 accountName3 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAccountName3(null)));
       // 测试 currency3 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setCurrency3(null)));
       // 测试 bank4 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBank4(null)));
       // 测试 bankCode4 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBankCode4(null)));
       // 测试 branch4 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBranch4(null)));
       // 测试 branchCode4 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setBranchCode4(null)));
       // 测试 swift4 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setSwift4(null)));
       // 测试 accountType4 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAccountType4(null)));
       // 测试 accountNumber4 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAccountNumber4(null)));
       // 测试 accountName4 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setAccountName4(null)));
       // 测试 currency4 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setCurrency4(null)));
       // 测试 url1 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setUrl1(null)));
       // 测试 url2 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setUrl2(null)));
       // 测试 url3 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setUrl3(null)));
       // 测试 createTime 不匹配
       companyMapper.insert(cloneIgnoreId(dbCompany, o -> o.setCreateTime(null)));
       // 准备参数
       CompanyExportReqVO reqVO = new CompanyExportReqVO();
       reqVO.setNameJp(null);
       reqVO.setNameEn(null);
       reqVO.setNameAbrvt(null);
       reqVO.setCode(null);
       reqVO.setZipcode(null);
       reqVO.setAddress1Jp(null);
       reqVO.setAddress2Jp(null);
       reqVO.setAddress3Jp(null);
       reqVO.setBuildingJp(null);
       reqVO.setAddress1En(null);
       reqVO.setAddress2En(null);
       reqVO.setAddress3En(null);
       reqVO.setBuildingEn(null);
       reqVO.setPhone(null);
       reqVO.setFax(null);
       reqVO.setSite(null);
       reqVO.setBank1(null);
       reqVO.setBankCode1(null);
       reqVO.setBranch1(null);
       reqVO.setBranchCode1(null);
       reqVO.setSwift1(null);
       reqVO.setAccountType1(null);
       reqVO.setAccountNumber1(null);
       reqVO.setAccountName1(null);
       reqVO.setCurrency1(null);
       reqVO.setBank2(null);
       reqVO.setBankCode2(null);
       reqVO.setBranch2(null);
       reqVO.setBranchCode2(null);
       reqVO.setSwift2(null);
       reqVO.setAccountType2(null);
       reqVO.setAccountNumber2(null);
       reqVO.setAccountName2(null);
       reqVO.setCurrency2(null);
       reqVO.setBank3(null);
       reqVO.setBankCode3(null);
       reqVO.setBranch3(null);
       reqVO.setBranchCode3(null);
       reqVO.setSwift3(null);
       reqVO.setAccountType3(null);
       reqVO.setAccountNumber3(null);
       reqVO.setAccountName3(null);
       reqVO.setCurrency3(null);
       reqVO.setBank4(null);
       reqVO.setBankCode4(null);
       reqVO.setBranch4(null);
       reqVO.setBranchCode4(null);
       reqVO.setSwift4(null);
       reqVO.setAccountType4(null);
       reqVO.setAccountNumber4(null);
       reqVO.setAccountName4(null);
       reqVO.setCurrency4(null);
       reqVO.setUrl1(null);
       reqVO.setUrl2(null);
       reqVO.setUrl3(null);
       reqVO.setBeginCreateTime(null);
       reqVO.setEndCreateTime(null);

       // 调用
       List<CompanyDO> list = companyService.getCompanyList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbCompany, list.get(0));
    }

}
