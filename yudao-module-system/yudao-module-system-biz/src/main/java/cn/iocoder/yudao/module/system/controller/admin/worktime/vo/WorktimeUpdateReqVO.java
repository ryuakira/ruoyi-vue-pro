package cn.iocoder.yudao.module.system.controller.admin.worktime.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 勤怠更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WorktimeUpdateReqVO extends WorktimeBaseVO {

    @ApiModelProperty(value = "勤怠番号", required = true)
    @NotNull(message = "勤怠番号不能为空")
    private Long id;

    @ApiModelProperty(value = "社員id主鍵")
    private Long employeeId;

}
