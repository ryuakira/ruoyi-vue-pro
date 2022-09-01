package cn.iocoder.yudao.module.system.dal.dataobject.worktime;

import cn.iocoder.yudao.module.system.dal.dataobject.employee.EmployeeDO;
import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 勤怠 DO
 *
 * @author 劉
 */
@TableName("system_tecreas_worktime")
@KeySequence("system_tecreas_worktime_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorktimeDO extends BaseDO {

    /**
     * 勤怠番号
     */
    @TableId
    private Long id;
    /**
     * 社員番号
     */
    private String employeeId;

//    /**
//     * 社員姓名
//     *
//     * 关联 {@link EmployeeDO#getEmployeeName()}
//     */
//    private String employeeName;
    /**
     * 出勤年月
     */
    private String workingMonth;
    /**
     * 稼働時間
     */
    private Integer workingtimes;

}
