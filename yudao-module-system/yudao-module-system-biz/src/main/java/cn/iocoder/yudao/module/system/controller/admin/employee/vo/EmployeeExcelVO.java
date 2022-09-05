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

    @ExcelProperty("ID")
    private Long id;

    @ExcelProperty("社員番号")
    private String employeeNum;

    @ExcelProperty("姓名")
    private String employeeName;

    @ExcelProperty("姓名カナ")
    private String employeeNameKana;

    @ExcelProperty("性别")
    private Integer sex;

    @ExcelProperty("年月日")
    private Date birthday;

    @ExcelProperty("在留カード番号")
    private String resideceCardId;

    @ExcelProperty("マイナンバーカード番号")
    private String mynumberCardId;

    @ExcelProperty("雇用契約番号")
    private String emplyCntrctNumbr;

    @ExcelProperty("携帯番号")
    private String mobile;

    @ExcelProperty("郵便番号")
    private String postcode;

    @ExcelProperty("住所")
    private String address;

    @ExcelProperty("最新履歴")
    private String resume;

    @ExcelProperty("部門番号")
    private Long deptId;

    @ExcelProperty("入社日")
    private Date hireDate;

    @ExcelProperty("就職状態")
    private Integer status;

    @ExcelProperty("新規日付")
    private Date createTime;

}
