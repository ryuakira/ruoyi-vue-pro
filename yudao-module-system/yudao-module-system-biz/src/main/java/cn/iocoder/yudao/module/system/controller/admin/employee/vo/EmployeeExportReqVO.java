package cn.iocoder.yudao.module.system.controller.admin.employee.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

@ApiModel(value = "管理后台 - 社員 Excel 导出 Request VO", description = "参数和 EmployeePageReqVO 是一致的")
@Data
public class EmployeeExportReqVO {

    @ApiModelProperty(value = "社員番号")
    private Long id;

    @ApiModelProperty(value = "社員名")
    private String familyname;

    @ApiModelProperty(value = "社員名")
    private String givenname;

    @ApiModelProperty(value = "社員性别")
    private Integer sex;

    @ApiModelProperty(value = "年齢")
    private Integer age;

    @ApiModelProperty(value = "就職状態")
    private Integer status;

    @ApiModelProperty(value = "携帯番号")
    private String mobile;

    @ApiModelProperty(value = "ログインパスワード")
    private String password;

    @ApiModelProperty(value = "最新履歴")
    private String resume;

}
