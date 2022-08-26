package cn.iocoder.yudao.module.system.controller.admin.employee.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 社員 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmployeeRespVO extends EmployeeBaseVO {

    @ApiModelProperty(value = "社員番号", required = true)
    private Long id;

    @ApiModelProperty(value = "新規日付", required = true)
    private Date createTime;

}
