package cn.iocoder.yudao.module.system.dal.mysql.employee;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.employee.EmployeeDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.employee.vo.*;
import org.apache.ibatis.annotations.Select;

/**
 * 社員 Mapper
 *
 * @author 劉
 */
@Mapper
public interface EmployeeMapper extends BaseMapperX<EmployeeDO> {

    default PageResult<EmployeeDO> selectPage(EmployeePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EmployeeDO>()
                .eqIfPresent(EmployeeDO::getEmployeeId, reqVO.getEmployeeId())
                .likeIfPresent(EmployeeDO::getEmployeeName, reqVO.getEmployeeName())
                .eqIfPresent(EmployeeDO::getEmployeeNameKana, reqVO.getEmployeeNameKana())
                .eqIfPresent(EmployeeDO::getSex, reqVO.getSex())
                .eqIfPresent(EmployeeDO::getBirthday, reqVO.getBirthday())
                .eqIfPresent(EmployeeDO::getResideceCardId, reqVO.getResideceCardId())
                .eqIfPresent(EmployeeDO::getMynumberCardId, reqVO.getMynumberCardId())
                .eqIfPresent(EmployeeDO::getEmplyCntrctNumbr, reqVO.getEmplyCntrctNumbr())
                .eqIfPresent(EmployeeDO::getMobile, reqVO.getMobile())
                .eqIfPresent(EmployeeDO::getPostcode, reqVO.getPostcode())
                .eqIfPresent(EmployeeDO::getAddress, reqVO.getAddress())
                .eqIfPresent(EmployeeDO::getDeptId, reqVO.getDeptId())
                .betweenIfPresent(EmployeeDO::getHireDate, reqVO.getBeginHireDate(), reqVO.getEndHireDate())
                .eqIfPresent(EmployeeDO::getStatus, reqVO.getStatus())
                .orderByDesc(EmployeeDO::getId));
    }

    default List<EmployeeDO> selectList(EmployeeExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<EmployeeDO>()
                .eqIfPresent(EmployeeDO::getEmployeeId, reqVO.getEmployeeId())
                .likeIfPresent(EmployeeDO::getEmployeeName, reqVO.getEmployeeName())
                .eqIfPresent(EmployeeDO::getEmployeeNameKana, reqVO.getEmployeeNameKana())
                .eqIfPresent(EmployeeDO::getSex, reqVO.getSex())
                .eqIfPresent(EmployeeDO::getBirthday, reqVO.getBirthday())
                .eqIfPresent(EmployeeDO::getResideceCardId, reqVO.getResideceCardId())
                .eqIfPresent(EmployeeDO::getMynumberCardId, reqVO.getMynumberCardId())
                .eqIfPresent(EmployeeDO::getEmplyCntrctNumbr, reqVO.getEmplyCntrctNumbr())
                .eqIfPresent(EmployeeDO::getMobile, reqVO.getMobile())
                .eqIfPresent(EmployeeDO::getPostcode, reqVO.getPostcode())
                .eqIfPresent(EmployeeDO::getAddress, reqVO.getAddress())
                .eqIfPresent(EmployeeDO::getDeptId, reqVO.getDeptId())
                .betweenIfPresent(EmployeeDO::getHireDate, reqVO.getBeginHireDate(), reqVO.getEndHireDate())
                .eqIfPresent(EmployeeDO::getStatus, reqVO.getStatus())
                .orderByDesc(EmployeeDO::getId));
    }

    default List<EmployeeDO> selectListByStatus(Integer status) {
        // 劉義民 2022/08/31 下面这两种写法都OK
        // return selectList(new LambdaQueryWrapperX<EmployeeDO>().eqIfPresent(EmployeeDO::getStatus, status));
        return selectList(EmployeeDO::getStatus, status);
    }
}
