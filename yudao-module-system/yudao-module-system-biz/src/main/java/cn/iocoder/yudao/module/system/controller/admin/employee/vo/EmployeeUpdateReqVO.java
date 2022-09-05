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

    @ApiModelProperty(value = "ID", required = true)
    @NotNull(message = "ID不能为空")
    private Long id;

//    @ApiModelProperty(value = "画像URL", required = true)
//    @NotNull(message = "画像URL不能为空")
//    private String avatar;
//
//    @ApiModelProperty(value = "在留カード番号コピー", required = true)
//    @NotNull(message = "在留カード番号コピー不能为空")
//    private String resideceCardCopy;

}
