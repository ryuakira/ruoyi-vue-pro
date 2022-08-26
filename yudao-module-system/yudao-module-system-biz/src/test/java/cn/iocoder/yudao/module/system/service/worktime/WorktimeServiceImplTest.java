package cn.iocoder.yudao.module.system.service.worktime;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.system.controller.admin.worktime.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.worktime.WorktimeDO;
import cn.iocoder.yudao.module.system.dal.mysql.worktime.WorktimeMapper;
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
* {@link WorktimeServiceImpl} 的单元测试类
*
* @author 劉
*/
@Import(WorktimeServiceImpl.class)
public class WorktimeServiceImplTest extends BaseDbUnitTest {

    @Resource
    private WorktimeServiceImpl worktimeService;

    @Resource
    private WorktimeMapper worktimeMapper;

    @Test
    public void testCreateWorktime_success() {
        // 准备参数
        WorktimeCreateReqVO reqVO = randomPojo(WorktimeCreateReqVO.class);

        // 调用
        Long worktimeId = worktimeService.createWorktime(reqVO);
        // 断言
        assertNotNull(worktimeId);
        // 校验记录的属性是否正确
        WorktimeDO worktime = worktimeMapper.selectById(worktimeId);
        assertPojoEquals(reqVO, worktime);
    }

    @Test
    public void testUpdateWorktime_success() {
        // mock 数据
        WorktimeDO dbWorktime = randomPojo(WorktimeDO.class);
        worktimeMapper.insert(dbWorktime);// @Sql: 先插入出一条存在的数据
        // 准备参数
        WorktimeUpdateReqVO reqVO = randomPojo(WorktimeUpdateReqVO.class, o -> {
            o.setId(dbWorktime.getId()); // 设置更新的 ID
        });

        // 调用
        worktimeService.updateWorktime(reqVO);
        // 校验是否更新正确
        WorktimeDO worktime = worktimeMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, worktime);
    }

    @Test
    public void testUpdateWorktime_notExists() {
        // 准备参数
        WorktimeUpdateReqVO reqVO = randomPojo(WorktimeUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> worktimeService.updateWorktime(reqVO), WORKTIME_NOT_EXISTS);
    }

    @Test
    public void testDeleteWorktime_success() {
        // mock 数据
        WorktimeDO dbWorktime = randomPojo(WorktimeDO.class);
        worktimeMapper.insert(dbWorktime);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbWorktime.getId();

        // 调用
        worktimeService.deleteWorktime(id);
       // 校验数据不存在了
       assertNull(worktimeMapper.selectById(id));
    }

    @Test
    public void testDeleteWorktime_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> worktimeService.deleteWorktime(id), WORKTIME_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetWorktimePage() {
       // mock 数据
       WorktimeDO dbWorktime = randomPojo(WorktimeDO.class, o -> { // 等会查询到
           o.setEmpolyeeId(null);
           o.setWorkingyearmonth(null);
           o.setWorkingtime(null);
       });
       worktimeMapper.insert(dbWorktime);
       // 测试 empolyeeId 不匹配
       worktimeMapper.insert(cloneIgnoreId(dbWorktime, o -> o.setEmpolyeeId(null)));
       // 测试 workingyearmonth 不匹配
       worktimeMapper.insert(cloneIgnoreId(dbWorktime, o -> o.setWorkingyearmonth(null)));
       // 测试 workingtime 不匹配
       worktimeMapper.insert(cloneIgnoreId(dbWorktime, o -> o.setWorkingtime(null)));
       // 准备参数
       WorktimePageReqVO reqVO = new WorktimePageReqVO();
       reqVO.setEmpolyeeId(null);
       reqVO.setWorkingyearmonth(null);
       reqVO.setBeginWorkingtime(null);
       reqVO.setEndWorkingtime(null);

       // 调用
       PageResult<WorktimeDO> pageResult = worktimeService.getWorktimePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbWorktime, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetWorktimeList() {
       // mock 数据
       WorktimeDO dbWorktime = randomPojo(WorktimeDO.class, o -> { // 等会查询到
           o.setEmpolyeeId(null);
           o.setWorkingyearmonth(null);
           o.setWorkingtime(null);
       });
       worktimeMapper.insert(dbWorktime);
       // 测试 empolyeeId 不匹配
       worktimeMapper.insert(cloneIgnoreId(dbWorktime, o -> o.setEmpolyeeId(null)));
       // 测试 workingyearmonth 不匹配
       worktimeMapper.insert(cloneIgnoreId(dbWorktime, o -> o.setWorkingyearmonth(null)));
       // 测试 workingtime 不匹配
       worktimeMapper.insert(cloneIgnoreId(dbWorktime, o -> o.setWorkingtime(null)));
       // 准备参数
       WorktimeExportReqVO reqVO = new WorktimeExportReqVO();
       reqVO.setEmpolyeeId(null);
       reqVO.setWorkingyearmonth(null);
       reqVO.setBeginWorkingtime(null);
       reqVO.setEndWorkingtime(null);

       // 调用
       List<WorktimeDO> list = worktimeService.getWorktimeList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbWorktime, list.get(0));
    }

}
