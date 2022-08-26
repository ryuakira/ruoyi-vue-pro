package cn.iocoder.yudao.module.system.service.worktime;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.system.controller.admin.worktime.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.worktime.WorktimeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 勤怠 Service 接口
 *
 * @author 劉
 */
public interface WorktimeService {

    /**
     * 创建勤怠
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWorktime(@Valid WorktimeCreateReqVO createReqVO);

    /**
     * 更新勤怠
     *
     * @param updateReqVO 更新信息
     */
    void updateWorktime(@Valid WorktimeUpdateReqVO updateReqVO);

    /**
     * 删除勤怠
     *
     * @param id 编号
     */
    void deleteWorktime(Long id);

    /**
     * 获得勤怠
     *
     * @param id 编号
     * @return 勤怠
     */
    WorktimeDO getWorktime(Long id);

    /**
     * 获得勤怠列表
     *
     * @param ids 编号
     * @return 勤怠列表
     */
    List<WorktimeDO> getWorktimeList(Collection<Long> ids);

    /**
     * 获得勤怠分页
     *
     * @param pageReqVO 分页查询
     * @return 勤怠分页
     */
    PageResult<WorktimeDO> getWorktimePage(WorktimePageReqVO pageReqVO);

    /**
     * 获得勤怠列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 勤怠列表
     */
    List<WorktimeDO> getWorktimeList(WorktimeExportReqVO exportReqVO);

}
