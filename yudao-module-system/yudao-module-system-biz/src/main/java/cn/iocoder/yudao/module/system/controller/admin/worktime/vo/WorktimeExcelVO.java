package cn.iocoder.yudao.module.system.controller.admin.worktime.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 勤怠 Excel VO
 *
 * @author 劉
 */
@Data
public class WorktimeExcelVO {

    @ExcelProperty("勤怠番号")
    private Long id;

    @ExcelProperty("社員番号")
    private String employeeNum;

    @ExcelProperty("出勤年月")
    private String workingMonth;

    @ExcelProperty("稼働時間")
    private Float workingtimes;

    @ExcelProperty("新規日付")
    private Date createTime;

}
