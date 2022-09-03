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
    // 2022/09/01 劉義民　社員番号で社員名前を取得処理　追加開始
    private Employee employee;
    @ApiModel("社員")
    @Data
    public static class Employee {

        @ApiModelProperty(value = "社員主鍵", required = true, example = "シーケンスNo")
        private Long employeeId;

        @ApiModelProperty(value = "社員名前", required = true, example = "田中健一")
        private String employeeName;
    }
    // 2022/09/01 劉義民　社員番号で社員名前を取得処理　追加終了

}
