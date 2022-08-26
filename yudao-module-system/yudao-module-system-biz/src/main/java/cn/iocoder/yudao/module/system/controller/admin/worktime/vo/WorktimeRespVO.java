package cn.iocoder.yudao.module.system.controller.admin.worktime.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 勤怠 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WorktimeRespVO extends WorktimeBaseVO {

    @ApiModelProperty(value = "勤怠番号", required = true)
    private Long id;

    @ApiModelProperty(value = "新規日付", required = true)
    private Date createTime;

}
