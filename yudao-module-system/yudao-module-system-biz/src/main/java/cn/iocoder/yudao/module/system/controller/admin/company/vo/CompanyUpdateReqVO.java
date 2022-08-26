package cn.iocoder.yudao.module.system.controller.admin.company.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 会社更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CompanyUpdateReqVO extends CompanyBaseVO {

    @ApiModelProperty(value = "No.", required = true)
    @NotNull(message = "No.不能为空")
    private Long id;

}
