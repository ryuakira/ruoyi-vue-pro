package cn.iocoder.yudao.module.system.dal.mysql.worktime;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.worktime.WorktimeDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.worktime.vo.*;

/**
 * 勤怠 Mapper
 *
 * @author 劉
 */
@Mapper
public interface WorktimeMapper extends BaseMapperX<WorktimeDO> {

    default PageResult<WorktimeDO> selectPage(WorktimePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WorktimeDO>()
                .eqIfPresent(WorktimeDO::getEmployeeId, reqVO.getEmployeeId())
                .eqIfPresent(WorktimeDO::getWorkingMonth, reqVO.getWorkingMonth())
                .eqIfPresent(WorktimeDO::getWorkingtimes, reqVO.getWorkingtimes())
                .orderByDesc(WorktimeDO::getId));
    }

    default List<WorktimeDO> selectList(WorktimeExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<WorktimeDO>()
                .eqIfPresent(WorktimeDO::getEmployeeId, reqVO.getEmployeeId())
                .eqIfPresent(WorktimeDO::getWorkingMonth, reqVO.getWorkingMonth())
                .eqIfPresent(WorktimeDO::getWorkingtimes, reqVO.getWorkingtimes())
                .orderByDesc(WorktimeDO::getId));
    }
}
