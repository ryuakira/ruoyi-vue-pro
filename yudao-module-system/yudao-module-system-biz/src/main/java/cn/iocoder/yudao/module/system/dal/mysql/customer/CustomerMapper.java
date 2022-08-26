package cn.iocoder.yudao.module.system.dal.mysql.customer;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.customer.CustomerDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.customer.vo.*;

/**
 * 顧客 Mapper
 *
 * @author KYO
 */
@Mapper
public interface CustomerMapper extends BaseMapperX<CustomerDO> {

    default PageResult<CustomerDO> selectPage(CustomerPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerDO>()
                .eqIfPresent(CustomerDO::getName1, reqVO.getName1())
                .eqIfPresent(CustomerDO::getName2, reqVO.getName2())
                .eqIfPresent(CustomerDO::getName3, reqVO.getName3())
                .eqIfPresent(CustomerDO::getCode, reqVO.getCode())
                .eqIfPresent(CustomerDO::getDescription, reqVO.getDescription())
                .eqIfPresent(CustomerDO::getZipcode, reqVO.getZipcode())
                .eqIfPresent(CustomerDO::getAddress1, reqVO.getAddress1())
                .eqIfPresent(CustomerDO::getAddress2, reqVO.getAddress2())
                .eqIfPresent(CustomerDO::getAddress3, reqVO.getAddress3())
                .eqIfPresent(CustomerDO::getBuilding, reqVO.getBuilding())
                .eqIfPresent(CustomerDO::getPhone, reqVO.getPhone())
                .eqIfPresent(CustomerDO::getFax, reqVO.getFax())
                .eqIfPresent(CustomerDO::getSite, reqVO.getSite())
                .eqIfPresent(CustomerDO::getBusinessFlag, reqVO.getBusinessFlag())
                .betweenIfPresent(CustomerDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(CustomerDO::getId));
    }

    default List<CustomerDO> selectList(CustomerExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CustomerDO>()
                .eqIfPresent(CustomerDO::getName1, reqVO.getName1())
                .eqIfPresent(CustomerDO::getName2, reqVO.getName2())
                .eqIfPresent(CustomerDO::getName3, reqVO.getName3())
                .eqIfPresent(CustomerDO::getCode, reqVO.getCode())
                .eqIfPresent(CustomerDO::getDescription, reqVO.getDescription())
                .eqIfPresent(CustomerDO::getZipcode, reqVO.getZipcode())
                .eqIfPresent(CustomerDO::getAddress1, reqVO.getAddress1())
                .eqIfPresent(CustomerDO::getAddress2, reqVO.getAddress2())
                .eqIfPresent(CustomerDO::getAddress3, reqVO.getAddress3())
                .eqIfPresent(CustomerDO::getBuilding, reqVO.getBuilding())
                .eqIfPresent(CustomerDO::getPhone, reqVO.getPhone())
                .eqIfPresent(CustomerDO::getFax, reqVO.getFax())
                .eqIfPresent(CustomerDO::getSite, reqVO.getSite())
                .eqIfPresent(CustomerDO::getBusinessFlag, reqVO.getBusinessFlag())
                .betweenIfPresent(CustomerDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(CustomerDO::getId));
    }

}
