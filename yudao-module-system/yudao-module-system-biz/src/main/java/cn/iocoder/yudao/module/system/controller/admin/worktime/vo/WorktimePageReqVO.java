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
    private String employeeNum;

    @ApiModelProperty(value = "开始出勤年月")
    private String beginWorkingMonth;

    @ApiModelProperty(value = "结束出勤年月")
    private String endWorkingMonth;

    @ApiModelProperty(value = "社員番号")
    private String employeeName;

    @ApiModelProperty(value = "出勤年月")
    private String workingMonth;

    @ApiModelProperty(value = "稼働時間")
    private Integer workingtimes;

}
