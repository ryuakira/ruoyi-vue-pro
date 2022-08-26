package cn.iocoder.yudao.module.system.dal.mysql.employee;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.employee.EmployeeDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.employee.vo.*;

/**
 * 社員 Mapper
 *
 * @author 劉
 */
@Mapper
public interface EmployeeMapper extends BaseMapperX<EmployeeDO> {

    default PageResult<EmployeeDO> selectPage(EmployeePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EmployeeDO>()
                .eqIfPresent(EmployeeDO::getId, reqVO.getId())
                .likeIfPresent(EmployeeDO::getFamilyname, reqVO.getFamilyname())
                .likeIfPresent(EmployeeDO::getGivenname, reqVO.getGivenname())
                .eqIfPresent(EmployeeDO::getSex, reqVO.getSex())
                .eqIfPresent(EmployeeDO::getAge, reqVO.getAge())
                .eqIfPresent(EmployeeDO::getStatus, reqVO.getStatus())
                .eqIfPresent(EmployeeDO::getMobile, reqVO.getMobile())
                .eqIfPresent(EmployeeDO::getDeptId, reqVO.getDeptId())
                .orderByDesc(EmployeeDO::getId));
    }

    default List<EmployeeDO> selectList(EmployeeExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<EmployeeDO>()
                .eqIfPresent(EmployeeDO::getId, reqVO.getId())
                .likeIfPresent(EmployeeDO::getFamilyname, reqVO.getFamilyname())
                .likeIfPresent(EmployeeDO::getGivenname, reqVO.getGivenname())
                .eqIfPresent(EmployeeDO::getSex, reqVO.getSex())
                .eqIfPresent(EmployeeDO::getAge, reqVO.getAge())
                .eqIfPresent(EmployeeDO::getStatus, reqVO.getStatus())
                .eqIfPresent(EmployeeDO::getMobile, reqVO.getMobile())
                .eqIfPresent(EmployeeDO::getDeptId, reqVO.getDeptId())
                .orderByDesc(EmployeeDO::getId));
    }

}
