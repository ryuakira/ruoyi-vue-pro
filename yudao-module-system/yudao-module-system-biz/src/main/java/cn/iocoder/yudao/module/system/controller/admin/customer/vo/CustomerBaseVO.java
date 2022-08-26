package cn.iocoder.yudao.module.system.controller.admin.customer.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 顧客 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class CustomerBaseVO {

    @ApiModelProperty(value = "公司名1", required = true)
    @NotNull(message = "公司名1不能为空")
    private String name1;

    @ApiModelProperty(value = "公司名2")
    private String name2;

    @ApiModelProperty(value = "公司略称")
    private String name3;

    @ApiModelProperty(value = "公司代码", required = true)
    @NotNull(message = "公司代码不能为空")
    private String code;

    @ApiModelProperty(value = "公司描述")
    private String description;

    @ApiModelProperty(value = "邮编", required = true)
    @NotNull(message = "邮编不能为空")
    private String zipcode;

    @ApiModelProperty(value = "地址1", required = true)
    @NotNull(message = "地址1不能为空")
    private String address1;

    @ApiModelProperty(value = "地址2")
    private String address2;

    @ApiModelProperty(value = "地址3")
    private String address3;

    @ApiModelProperty(value = "大楼名")
    private String building;

    @ApiModelProperty(value = "电话", required = true)
    @NotNull(message = "电话不能为空")
    private String phone;

    @ApiModelProperty(value = "传真")
    private String fax;

    @ApiModelProperty(value = "网址")
    private String site;

    @ApiModelProperty(value = "取引关系", required = true, example = "0")
    @NotNull(message = "取引关系不能为空")
    private Integer businessFlag;

}
