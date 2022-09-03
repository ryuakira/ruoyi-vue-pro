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
     * ID
     */
    @TableId
    private Long id;
    /**
     * 社員番号
     */
    private String employeeNum;
    /**
     * 姓名
     */
    private String employeeName;
    /**
     * 姓名カナ
     */
    private String employeeNameKana;
    /**
     * 画像URL
     */
    private String avatar;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 年月日
     */
    private Date birthday;
    /**
     * 在留カード番号
     */
    private String resideceCardId;
    /**
     * 在留カード番号コピー
     */
    private String resideceCardCopy;
    /**
     * マイナンバーカード番号
     */
    private String mynumberCardId;
    /**
     * 雇用契約番号
     */
    private String emplyCntrctNumbr;
    /**
     * 携帯番号
     */
    private String mobile;
    /**
     * 郵便番号
     */
    private String postcode;
    /**
     * 住所
     */
    private String address;
    /**
     * 最新履歴
     */
    private String resume;
    /**
     * 部門番号
     */
    private Long deptId;
    /**
     * 入社日
     */
    private Date hireDate;
    /**
     * 就職状態
     */
    private Integer status;

}
