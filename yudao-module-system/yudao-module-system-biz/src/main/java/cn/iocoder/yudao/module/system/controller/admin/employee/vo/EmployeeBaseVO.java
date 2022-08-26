package cn.iocoder.yudao.module.system.controller.admin.employee.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 社員 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class EmployeeBaseVO {

    @ApiModelProperty(value = "社員名", required = true)
    @NotNull(message = "社員名不能为空")
    private String familyname;

    @ApiModelProperty(value = "社員名", required = true)
    @NotNull(message = "社員名不能为空")
    private String givenname;

    @ApiModelProperty(value = "画像", required = true)
    @NotNull(message = "画像不能为空")
    private String avatar;

    @ApiModelProperty(value = "社員性别", required = true)
    @NotNull(message = "社員性别不能为空")
    private Integer sex;

    @ApiModelProperty(value = "年齢", required = true)
    @NotNull(message = "年齢不能为空")
    private Integer age;

    @ApiModelProperty(value = "就職状態")
    private Integer status;

    @ApiModelProperty(value = "携帯番号", required = true)
    @NotNull(message = "携帯番号不能为空")
    private String mobile;

    @ApiModelProperty(value = "ログインパスワード", required = true)
    @NotNull(message = "ログインパスワード不能为空")
    private String password;

    @ApiModelProperty(value = "最新履歴")
    private String resume;

}
