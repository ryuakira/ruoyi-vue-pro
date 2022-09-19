package cn.iocoder.yudao.module.system.controller.admin.employee.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 社員 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EmployeeRespVO extends EmployeeBaseVO {

    @ApiModelProperty(value = "ID", required = true)
    private Long id;

    @ApiModelProperty(value = "画像URL", required = true)
    private String avatar;

    @ApiModelProperty(value = "在留卡复印件URL", required = true)
    private String resideceCardCopy;

    @ApiModelProperty(value = "新規日付", required = true)
    private Date createTime;

}
