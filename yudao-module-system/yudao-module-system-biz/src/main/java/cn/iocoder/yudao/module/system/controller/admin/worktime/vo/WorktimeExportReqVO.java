package cn.iocoder.yudao.module.system.controller.admin.worktime.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@ApiModel(value = "管理后台 - 勤怠 Excel 导出 Request VO", description = "参数和 WorktimePageReqVO 是一致的")
@Data
public class WorktimeExportReqVO {

    @ApiModelProperty(value = "社員番号")
    private String employeeNum;

    @ApiModelProperty(value = "开始出勤年月")
    private String beginWorkingMonth;

    @ApiModelProperty(value = "结束出勤年月")
    private String endWorkingMonth;

    @ApiModelProperty(value = "出勤年月")
    private String workingMonth;

}
