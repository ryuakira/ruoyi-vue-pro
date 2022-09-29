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

    // 2022/09/26 劉義民　部門idで部門名称を取得処理　追加開始
    private EmplyDept department;
    @ApiModel("部門")
    @Data
    public static class EmplyDept {

        @ApiModelProperty(value = "部門主鍵", required = true, example = "シーケンスNo")
        private Long id;

        @ApiModelProperty(value = "部門名称", required = true, example = "開発一部")
        private String name;
    }
    // 2022/09/26 劉義民　部門idで部門名称を取得処理　追加終了

}
