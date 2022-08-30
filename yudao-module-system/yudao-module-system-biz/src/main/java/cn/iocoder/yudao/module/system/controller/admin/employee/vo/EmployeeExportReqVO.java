package cn.iocoder.yudao.module.system.controller.admin.employee.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 社員 Excel 导出 Request VO", description = "参数和 EmployeePageReqVO 是一致的")
@Data
public class EmployeeExportReqVO {

    @ApiModelProperty(value = "社員番号")
    private String employeeId;

    @ApiModelProperty(value = "姓名")
    private String employeeName;

    @ApiModelProperty(value = "姓名カナ")
    private String employeeNameKana;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "年月日")
    private Date birthday;

    @ApiModelProperty(value = "在留カード番号")
    private String resideceCardId;

    @ApiModelProperty(value = "マイナンバーカード番号")
    private String mynumberCardId;

    @ApiModelProperty(value = "雇用契約番号")
    private String emplyCntrctNumbr;

    @ApiModelProperty(value = "携帯番号")
    private String mobile;

    @ApiModelProperty(value = "郵便番号")
    private String postcode;

    @ApiModelProperty(value = "住所")
    private String address;

    @ApiModelProperty(value = "部門番号")
    private Long deptId;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始入社日")
    private Date beginHireDate;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束入社日")
    private Date endHireDate;

    @ApiModelProperty(value = "就職状態")
    private Integer status;

}
