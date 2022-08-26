package cn.iocoder.yudao.module.system.dal.mysql.company;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.company.CompanyDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.company.vo.*;

/**
 * 会社 Mapper
 *
 * @author KYO
 */
@Mapper
public interface CompanyMapper extends BaseMapperX<CompanyDO> {

    default PageResult<CompanyDO> selectPage(CompanyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CompanyDO>()
                .eqIfPresent(CompanyDO::getNameJp, reqVO.getNameJp())
                .eqIfPresent(CompanyDO::getNameEn, reqVO.getNameEn())
                .eqIfPresent(CompanyDO::getNameAbrvt, reqVO.getNameAbrvt())
                .eqIfPresent(CompanyDO::getCode, reqVO.getCode())
                .eqIfPresent(CompanyDO::getZipcode, reqVO.getZipcode())
                .eqIfPresent(CompanyDO::getAddress1Jp, reqVO.getAddress1Jp())
                .eqIfPresent(CompanyDO::getAddress2Jp, reqVO.getAddress2Jp())
                .eqIfPresent(CompanyDO::getAddress3Jp, reqVO.getAddress3Jp())
                .eqIfPresent(CompanyDO::getBuildingJp, reqVO.getBuildingJp())
                .eqIfPresent(CompanyDO::getAddress1En, reqVO.getAddress1En())
                .eqIfPresent(CompanyDO::getAddress2En, reqVO.getAddress2En())
                .eqIfPresent(CompanyDO::getAddress3En, reqVO.getAddress3En())
                .eqIfPresent(CompanyDO::getBuildingEn, reqVO.getBuildingEn())
                .eqIfPresent(CompanyDO::getPhone, reqVO.getPhone())
                .eqIfPresent(CompanyDO::getFax, reqVO.getFax())
                .eqIfPresent(CompanyDO::getSite, reqVO.getSite())
                .eqIfPresent(CompanyDO::getBank1, reqVO.getBank1())
                .eqIfPresent(CompanyDO::getBankCode1, reqVO.getBankCode1())
                .eqIfPresent(CompanyDO::getBranch1, reqVO.getBranch1())
                .eqIfPresent(CompanyDO::getBranchCode1, reqVO.getBranchCode1())
                .eqIfPresent(CompanyDO::getSwift1, reqVO.getSwift1())
                .eqIfPresent(CompanyDO::getAccountType1, reqVO.getAccountType1())
                .eqIfPresent(CompanyDO::getAccountNumber1, reqVO.getAccountNumber1())
                .eqIfPresent(CompanyDO::getAccountName1, reqVO.getAccountName1())
                .eqIfPresent(CompanyDO::getCurrency1, reqVO.getCurrency1())
                .eqIfPresent(CompanyDO::getBank2, reqVO.getBank2())
                .eqIfPresent(CompanyDO::getBankCode2, reqVO.getBankCode2())
                .eqIfPresent(CompanyDO::getBranch2, reqVO.getBranch2())
                .eqIfPresent(CompanyDO::getBranchCode2, reqVO.getBranchCode2())
                .eqIfPresent(CompanyDO::getSwift2, reqVO.getSwift2())
                .eqIfPresent(CompanyDO::getAccountType2, reqVO.getAccountType2())
                .eqIfPresent(CompanyDO::getAccountNumber2, reqVO.getAccountNumber2())
                .eqIfPresent(CompanyDO::getAccountName2, reqVO.getAccountName2())
                .eqIfPresent(CompanyDO::getCurrency2, reqVO.getCurrency2())
                .eqIfPresent(CompanyDO::getBank3, reqVO.getBank3())
                .eqIfPresent(CompanyDO::getBankCode3, reqVO.getBankCode3())
                .eqIfPresent(CompanyDO::getBranch3, reqVO.getBranch3())
                .eqIfPresent(CompanyDO::getBranchCode3, reqVO.getBranchCode3())
                .eqIfPresent(CompanyDO::getSwift3, reqVO.getSwift3())
                .eqIfPresent(CompanyDO::getAccountType3, reqVO.getAccountType3())
                .eqIfPresent(CompanyDO::getAccountNumber3, reqVO.getAccountNumber3())
                .eqIfPresent(CompanyDO::getAccountName3, reqVO.getAccountName3())
                .eqIfPresent(CompanyDO::getCurrency3, reqVO.getCurrency3())
                .eqIfPresent(CompanyDO::getBank4, reqVO.getBank4())
                .eqIfPresent(CompanyDO::getBankCode4, reqVO.getBankCode4())
                .eqIfPresent(CompanyDO::getBranch4, reqVO.getBranch4())
                .eqIfPresent(CompanyDO::getBranchCode4, reqVO.getBranchCode4())
                .eqIfPresent(CompanyDO::getSwift4, reqVO.getSwift4())
                .eqIfPresent(CompanyDO::getAccountType4, reqVO.getAccountType4())
                .eqIfPresent(CompanyDO::getAccountNumber4, reqVO.getAccountNumber4())
                .eqIfPresent(CompanyDO::getAccountName4, reqVO.getAccountName4())
                .eqIfPresent(CompanyDO::getCurrency4, reqVO.getCurrency4())
                .eqIfPresent(CompanyDO::getUrl1, reqVO.getUrl1())
                .eqIfPresent(CompanyDO::getUrl2, reqVO.getUrl2())
                .eqIfPresent(CompanyDO::getUrl3, reqVO.getUrl3())
                .betweenIfPresent(CompanyDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(CompanyDO::getId));
    }

    default List<CompanyDO> selectList(CompanyExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CompanyDO>()
                .eqIfPresent(CompanyDO::getNameJp, reqVO.getNameJp())
                .eqIfPresent(CompanyDO::getNameEn, reqVO.getNameEn())
                .eqIfPresent(CompanyDO::getNameAbrvt, reqVO.getNameAbrvt())
                .eqIfPresent(CompanyDO::getCode, reqVO.getCode())
                .eqIfPresent(CompanyDO::getZipcode, reqVO.getZipcode())
                .eqIfPresent(CompanyDO::getAddress1Jp, reqVO.getAddress1Jp())
                .eqIfPresent(CompanyDO::getAddress2Jp, reqVO.getAddress2Jp())
                .eqIfPresent(CompanyDO::getAddress3Jp, reqVO.getAddress3Jp())
                .eqIfPresent(CompanyDO::getBuildingJp, reqVO.getBuildingJp())
                .eqIfPresent(CompanyDO::getAddress1En, reqVO.getAddress1En())
                .eqIfPresent(CompanyDO::getAddress2En, reqVO.getAddress2En())
                .eqIfPresent(CompanyDO::getAddress3En, reqVO.getAddress3En())
                .eqIfPresent(CompanyDO::getBuildingEn, reqVO.getBuildingEn())
                .eqIfPresent(CompanyDO::getPhone, reqVO.getPhone())
                .eqIfPresent(CompanyDO::getFax, reqVO.getFax())
                .eqIfPresent(CompanyDO::getSite, reqVO.getSite())
                .eqIfPresent(CompanyDO::getBank1, reqVO.getBank1())
                .eqIfPresent(CompanyDO::getBankCode1, reqVO.getBankCode1())
                .eqIfPresent(CompanyDO::getBranch1, reqVO.getBranch1())
                .eqIfPresent(CompanyDO::getBranchCode1, reqVO.getBranchCode1())
                .eqIfPresent(CompanyDO::getSwift1, reqVO.getSwift1())
                .eqIfPresent(CompanyDO::getAccountType1, reqVO.getAccountType1())
                .eqIfPresent(CompanyDO::getAccountNumber1, reqVO.getAccountNumber1())
                .eqIfPresent(CompanyDO::getAccountName1, reqVO.getAccountName1())
                .eqIfPresent(CompanyDO::getCurrency1, reqVO.getCurrency1())
                .eqIfPresent(CompanyDO::getBank2, reqVO.getBank2())
                .eqIfPresent(CompanyDO::getBankCode2, reqVO.getBankCode2())
                .eqIfPresent(CompanyDO::getBranch2, reqVO.getBranch2())
                .eqIfPresent(CompanyDO::getBranchCode2, reqVO.getBranchCode2())
                .eqIfPresent(CompanyDO::getSwift2, reqVO.getSwift2())
                .eqIfPresent(CompanyDO::getAccountType2, reqVO.getAccountType2())
                .eqIfPresent(CompanyDO::getAccountNumber2, reqVO.getAccountNumber2())
                .eqIfPresent(CompanyDO::getAccountName2, reqVO.getAccountName2())
                .eqIfPresent(CompanyDO::getCurrency2, reqVO.getCurrency2())
                .eqIfPresent(CompanyDO::getBank3, reqVO.getBank3())
                .eqIfPresent(CompanyDO::getBankCode3, reqVO.getBankCode3())
                .eqIfPresent(CompanyDO::getBranch3, reqVO.getBranch3())
                .eqIfPresent(CompanyDO::getBranchCode3, reqVO.getBranchCode3())
                .eqIfPresent(CompanyDO::getSwift3, reqVO.getSwift3())
                .eqIfPresent(CompanyDO::getAccountType3, reqVO.getAccountType3())
                .eqIfPresent(CompanyDO::getAccountNumber3, reqVO.getAccountNumber3())
                .eqIfPresent(CompanyDO::getAccountName3, reqVO.getAccountName3())
                .eqIfPresent(CompanyDO::getCurrency3, reqVO.getCurrency3())
                .eqIfPresent(CompanyDO::getBank4, reqVO.getBank4())
                .eqIfPresent(CompanyDO::getBankCode4, reqVO.getBankCode4())
                .eqIfPresent(CompanyDO::getBranch4, reqVO.getBranch4())
                .eqIfPresent(CompanyDO::getBranchCode4, reqVO.getBranchCode4())
                .eqIfPresent(CompanyDO::getSwift4, reqVO.getSwift4())
                .eqIfPresent(CompanyDO::getAccountType4, reqVO.getAccountType4())
                .eqIfPresent(CompanyDO::getAccountNumber4, reqVO.getAccountNumber4())
                .eqIfPresent(CompanyDO::getAccountName4, reqVO.getAccountName4())
                .eqIfPresent(CompanyDO::getCurrency4, reqVO.getCurrency4())
                .eqIfPresent(CompanyDO::getUrl1, reqVO.getUrl1())
                .eqIfPresent(CompanyDO::getUrl2, reqVO.getUrl2())
                .eqIfPresent(CompanyDO::getUrl3, reqVO.getUrl3())
                .betweenIfPresent(CompanyDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(CompanyDO::getId));
    }

}
