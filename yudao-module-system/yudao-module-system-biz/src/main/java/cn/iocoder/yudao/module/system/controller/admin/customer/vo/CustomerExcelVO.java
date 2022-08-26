package cn.iocoder.yudao.module.system.controller.admin.customer.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;


/**
 * 顧客 Excel VO
 *
 * @author KYO
 */
@Data
public class CustomerExcelVO {

    @ExcelProperty("编号")
    private Long id;

    @ExcelProperty("公司名1")
    private String name1;

    @ExcelProperty("公司名2")
    private String name2;

    @ExcelProperty("公司略称")
    private String name3;

    @ExcelProperty("公司代码")
    private String code;

    @ExcelProperty("公司描述")
    private String description;

    @ExcelProperty("邮编")
    private String zipcode;

    @ExcelProperty("地址1")
    private String address1;

    @ExcelProperty("地址2")
    private String address2;

    @ExcelProperty("地址3")
    private String address3;

    @ExcelProperty("大楼名")
    private String building;

    @ExcelProperty("电话")
    private String phone;

    @ExcelProperty("传真")
    private String fax;

    @ExcelProperty("网址")
    private String site;

    @ExcelProperty(value = "取引关系", converter = DictConvert.class)
    @DictFormat("system_business_flag") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer businessFlag;

    @ExcelProperty("创建时间")
    private Date createTime;

}
