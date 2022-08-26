package cn.iocoder.yudao.module.system.service.worktime;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.system.controller.admin.worktime.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.worktime.WorktimeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.system.convert.worktime.WorktimeConvert;
import cn.iocoder.yudao.module.system.dal.mysql.worktime.WorktimeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

/**
 * 勤怠 Service 实现类
 *
 * @author 劉
 */
@Service
@Validated
public class WorktimeServiceImpl implements WorktimeService {

    @Resource
    private WorktimeMapper worktimeMapper;

    @Override
    public Long createWorktime(WorktimeCreateReqVO createReqVO) {
        // 插入
        WorktimeDO worktime = WorktimeConvert.INSTANCE.convert(createReqVO);
        worktimeMapper.insert(worktime);
        // 返回
        return worktime.getId();
    }

    @Override
    public void updateWorktime(WorktimeUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateWorktimeExists(updateReqVO.getId());
        // 更新
        WorktimeDO updateObj = WorktimeConvert.INSTANCE.convert(updateReqVO);
        worktimeMapper.updateById(updateObj);
    }

    @Override
    public void deleteWorktime(Long id) {
        // 校验存在
        this.validateWorktimeExists(id);
        // 删除
        worktimeMapper.deleteById(id);
    }

    private void validateWorktimeExists(Long id) {
        if (worktimeMapper.selectById(id) == null) {
            throw exception(WORKTIME_NOT_EXISTS);
        }
    }

    @Override
    public WorktimeDO getWorktime(Long id) {
        return worktimeMapper.selectById(id);
    }

    @Override
    public List<WorktimeDO> getWorktimeList(Collection<Long> ids) {
        return worktimeMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<WorktimeDO> getWorktimePage(WorktimePageReqVO pageReqVO) {
        return worktimeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<WorktimeDO> getWorktimeList(WorktimeExportReqVO exportReqVO) {
        return worktimeMapper.selectList(exportReqVO);
    }

}
