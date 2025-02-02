package cn.iocoder.yudao.module.system.controller.admin.worktime.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 勤怠 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class WorktimeBaseVO {

    @ApiModelProperty(value = "社員番号", required = true)
    @NotNull(message = "社員番号不能为空")
    private String employeeNum;

    @ApiModelProperty(value = "出勤年月", required = true)
    @NotNull(message = "出勤年月不能为空")
    private String workingMonth;

    @ApiModelProperty(value = "稼働時間")
    private Float workingtimes;

}
