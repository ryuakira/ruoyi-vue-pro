package cn.iocoder.yudao.module.system.convert.worktime;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.system.dal.dataobject.employee.EmployeeDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.system.controller.admin.worktime.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.worktime.WorktimeDO;

/**
 * 勤怠 Convert
 *
 * @author 劉
 */
@Mapper
public interface WorktimeConvert {

    WorktimeConvert INSTANCE = Mappers.getMapper(WorktimeConvert.class);

    WorktimeDO convert(WorktimeCreateReqVO bean);

    WorktimeDO convert(WorktimeUpdateReqVO bean);

    WorktimeRespVO convert(WorktimeDO bean);
    // 2022/09/01 劉義民　社員番号で社員名前を取得処理　追加開始
    WorktimeRespVO.Employee convert(EmployeeDO bean);
    // 2022/09/01 劉義民　社員番号で社員名前を取得処理　追加終了
    List<WorktimeRespVO> convertList(List<WorktimeDO> list);

    PageResult<WorktimeRespVO> convertPage(PageResult<WorktimeDO> page);

    List<WorktimeExcelVO> convertList02(List<WorktimeDO> list);

}
