package cn.iocoder.yudao.module.system.controller.admin.employee.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
* 社員 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class EmployeeBaseVO {

    @ApiModelProperty(value = "社員番号", required = true)
    @NotNull(message = "社員番号不能为空")
    private String employeeNum;

    @ApiModelProperty(value = "姓名", required = true)
    @NotNull(message = "姓名不能为空")
    private String employeeName;

    @ApiModelProperty(value = "姓名カナ", required = true)
    @NotNull(message = "姓名カナ不能为空")
    private String employeeNameKana;

    @ApiModelProperty(value = "性别", required = true)
    @NotNull(message = "性别不能为空")
    private Integer sex;

    @ApiModelProperty(value = "年月日", required = true)
    @NotNull(message = "年月日不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date birthday;

    @ApiModelProperty(value = "在留カード番号", required = true)
    @NotNull(message = "在留カード番号不能为空")
    private String resideceCardId;

    @ApiModelProperty(value = "マイナンバーカード番号")
    private String mynumberCardId;

    @ApiModelProperty(value = "雇用契約番号")
    private String emplyCntrctNumbr;

    @ApiModelProperty(value = "携帯番号", required = true)
    @NotNull(message = "携帯番号不能为空")
    private String mobile;

    @ApiModelProperty(value = "郵便番号")
    private String postcode;

    @ApiModelProperty(value = "住所", required = true)
    @NotNull(message = "住所不能为空")
    private String address;

    @ApiModelProperty(value = "最新履歴")
    private String resume;

    @ApiModelProperty(value = "部門番号")
    private Long deptId;

    @ApiModelProperty(value = "入社日", required = true)
    @NotNull(message = "入社日不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date hireDate;

    @ApiModelProperty(value = "就職状態")
    private Integer status;

}
