package cn.iocoder.yudao.module.system.controller.admin.customer.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - 顧客分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CustomerPageReqVO extends PageParam {

    @ApiModelProperty(value = "公司名1")
    private String name1;

    @ApiModelProperty(value = "公司名2")
    private String name2;

    @ApiModelProperty(value = "公司略称")
    private String name3;

    @ApiModelProperty(value = "公司代码")
    private String code;

    @ApiModelProperty(value = "公司描述")
    private String description;

    @ApiModelProperty(value = "邮编")
    private String zipcode;

    @ApiModelProperty(value = "地址1")
    private String address1;

    @ApiModelProperty(value = "地址2")
    private String address2;

    @ApiModelProperty(value = "地址3")
    private String address3;

    @ApiModelProperty(value = "大楼名")
    private String building;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "传真")
    private String fax;

    @ApiModelProperty(value = "网址")
    private String site;

    @ApiModelProperty(value = "取引关系", example = "0")
    private Integer businessFlag;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

}
