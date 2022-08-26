package cn.iocoder.yudao.module.system.controller.admin.company.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 会社 Excel VO
 *
 * @author KYO
 */
@Data
public class CompanyExcelVO {

    @ExcelProperty("No.")
    private Long id;

    @ExcelProperty("会社名")
    private String nameJp;

    @ExcelProperty("Company Name")
    private String nameEn;

    @ExcelProperty("会社略称")
    private String nameAbrvt;

    @ExcelProperty("法人番号")
    private String code;

    @ExcelProperty("郵便")
    private String zipcode;

    @ExcelProperty("住所1")
    private String address1Jp;

    @ExcelProperty("住所2")
    private String address2Jp;

    @ExcelProperty("住所3")
    private String address3Jp;

    @ExcelProperty("建物名")
    private String buildingJp;

    @ExcelProperty("Address1")
    private String address1En;

    @ExcelProperty("Address2")
    private String address2En;

    @ExcelProperty("Address3")
    private String address3En;

    @ExcelProperty("Building")
    private String buildingEn;

    @ExcelProperty("電話番号")
    private String phone;

    @ExcelProperty("FAX")
    private String fax;

    @ExcelProperty("サイト")
    private String site;

    @ExcelProperty("銀行1")
    private String bank1;

    @ExcelProperty("銀行コード")
    private String bankCode1;

    @ExcelProperty("支店名")
    private String branch1;

    @ExcelProperty("支店コード")
    private String branchCode1;

    @ExcelProperty("swiftcode")
    private String swift1;

    @ExcelProperty("口座種類")
    private String accountType1;

    @ExcelProperty("口座番号")
    private String accountNumber1;

    @ExcelProperty("口座名義")
    private String accountName1;

    @ExcelProperty("通貨")
    private String currency1;

    @ExcelProperty("銀行2")
    private String bank2;

    @ExcelProperty("銀行コード")
    private String bankCode2;

    @ExcelProperty("支店名")
    private String branch2;

    @ExcelProperty("支店コード")
    private String branchCode2;

    @ExcelProperty("swiftcode")
    private String swift2;

    @ExcelProperty("口座種類")
    private String accountType2;

    @ExcelProperty("口座番号")
    private String accountNumber2;

    @ExcelProperty("口座名義")
    private String accountName2;

    @ExcelProperty("通貨")
    private String currency2;

    @ExcelProperty("銀行3")
    private String bank3;

    @ExcelProperty("銀行コード")
    private String bankCode3;

    @ExcelProperty("支店名")
    private String branch3;

    @ExcelProperty("銀行コード")
    private String branchCode3;

    @ExcelProperty("swiftcode")
    private String swift3;

    @ExcelProperty("口座種類")
    private String accountType3;

    @ExcelProperty("口座番号")
    private String accountNumber3;

    @ExcelProperty("口座名義")
    private String accountName3;

    @ExcelProperty("通貨")
    private String currency3;

    @ExcelProperty("銀行4")
    private String bank4;

    @ExcelProperty("銀行コード")
    private String bankCode4;

    @ExcelProperty("支店名")
    private String branch4;

    @ExcelProperty("銀行コード")
    private String branchCode4;

    @ExcelProperty("swiftcode")
    private String swift4;

    @ExcelProperty("口座種類")
    private String accountType4;

    @ExcelProperty("口座番号")
    private String accountNumber4;

    @ExcelProperty("口座名義")
    private String accountName4;

    @ExcelProperty("通貨")
    private String currency4;

    @ExcelProperty("添付1")
    private String url1;

    @ExcelProperty("添付2")
    private String url2;

    @ExcelProperty("添付3")
    private String url3;

    @ExcelProperty("创建时间")
    private Date createTime;

}
