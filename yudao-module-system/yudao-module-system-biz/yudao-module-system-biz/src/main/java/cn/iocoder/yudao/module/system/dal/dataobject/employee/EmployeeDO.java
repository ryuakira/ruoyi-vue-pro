package cn.iocoder.yudao.module.system.dal.dataobject.employee;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 社員 DO
 *
 * @author 劉
 */
@TableName("system_tecreas_employee")
@KeySequence("system_tecreas_employee_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDO extends BaseDO {

    /**
     * 社員番号
     */
    @TableId
    private Long id;
    /**
     * 社員名
     */
    private String familyname;
    /**
     * 社員名
     */
    private String givenname;
    /**
     * 画像
     */
    private String avatar;
    /**
     * 社員性别
     */
    private Integer sex;
    /**
     * 年齢
     */
    private Integer age;
    /**
     * 就職状態
     */
    private Integer status;
    /**
     * 携帯番号
     */
    private String mobile;
    /**
     * ログインパスワード
     */
    private String password;
    /**
     * 最新履歴
     */
    private String resume;
    /**
     * 部門番号
     */
    private Long deptId;
}
