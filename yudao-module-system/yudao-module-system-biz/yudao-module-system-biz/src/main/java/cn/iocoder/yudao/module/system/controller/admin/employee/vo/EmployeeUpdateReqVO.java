package cn.iocoder.yudao.module.system.controller.admin.employee.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 社員更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmployeeUpdateReqVO extends EmployeeBaseVO {

    @ApiModelProperty(value = "社員番号", required = true)
    @NotNull(message = "社員番号不能为空")
    private Long id;

}
