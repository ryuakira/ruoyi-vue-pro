package cn.iocoder.yudao.module.system.convert.employee;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.system.controller.admin.employee.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.employee.EmployeeDO;

/**
 * 社員 Convert
 *
 * @author 劉
 */
@Mapper
public interface EmployeeConvert {

    EmployeeConvert INSTANCE = Mappers.getMapper(EmployeeConvert.class);

    EmployeeDO convert(EmployeeCreateReqVO bean);

    EmployeeDO convert(EmployeeUpdateReqVO bean);

    EmployeeRespVO convert(EmployeeDO bean);

    List<EmployeeRespVO> convertList(List<EmployeeDO> list);

    PageResult<EmployeeRespVO> convertPage(PageResult<EmployeeDO> page);

    List<EmployeeExcelVO> convertList02(List<EmployeeDO> list);

    // 2022/09/26 劉義民　部門番号で部門名称を取得処理　追加開始
    EmployeeRespVO.EmplyDept convert(DeptDO bean);
    // 2022/09/26 劉義民　部門番号で部門名称を取得処理　追加終了

}
