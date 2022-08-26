package cn.iocoder.yudao.module.system.controller.admin.employee.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 社員 Excel VO
 *
 * @author 劉
 */
@Data
public class EmployeeExcelVO {

    @ExcelProperty("社員番号")
    private Long id;

    @ExcelProperty("社員名")
    private String familyname;

    @ExcelProperty("社員名")
    private String givenname;

    @ExcelProperty("画像")
    private String avatar;

    @ExcelProperty("社員性别")
    private Integer sex;

    @ExcelProperty("年齢")
    private Integer age;

    @ExcelProperty("就職状態")
    private Integer status;

    @ExcelProperty("携帯番号")
    private String mobile;

    @ExcelProperty("ログインパスワード")
    private String password;

    @ExcelProperty("最新履歴")
    private String resume;

    @ExcelProperty("新規日付")
    private Date createTime;

}
