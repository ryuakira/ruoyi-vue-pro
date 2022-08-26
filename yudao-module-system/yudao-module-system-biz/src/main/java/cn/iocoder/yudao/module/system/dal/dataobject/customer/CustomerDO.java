package cn.iocoder.yudao.module.system.dal.dataobject.customer;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 顧客 DO
 *
 * @author KYO
 */
@TableName("system_customer")
@KeySequence("system_customer_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 公司名1
     */
    private String name1;
    /**
     * 公司名2
     */
    private String name2;
    /**
     * 公司略称
     */
    private String name3;
    /**
     * 公司代码
     */
    private String code;
    /**
     * 公司描述
     */
    private String description;
    /**
     * 邮编
     */
    private String zipcode;
    /**
     * 地址1
     */
    private String address1;
    /**
     * 地址2
     */
    private String address2;
    /**
     * 地址3
     */
    private String address3;
    /**
     * 大楼名
     */
    private String building;
    /**
     * 电话
     */
    private String phone;
    /**
     * 传真
     */
    private String fax;
    /**
     * 网址
     */
    private String site;
    /**
     * 取引关系
     *
     * 枚举 {@link TODO system_business_flag 对应的类}
     */
    private Integer businessFlag;

}
