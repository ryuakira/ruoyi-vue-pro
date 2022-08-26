package cn.iocoder.yudao.module.system.controller.admin.worktime.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@ApiModel("管理后台 - 勤怠分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WorktimePageReqVO extends PageParam {

    @ApiModelProperty(value = "社員番号")
    private Long empolyeeId;

    @ApiModelProperty(value = "出勤年月")
    private String workingyearmonth;

    @ApiModelProperty(value = "开始稼働時間")
    private Long beginWorkingtime;

    @ApiModelProperty(value = "结束稼働時間")
    private Long endWorkingtime;

}
