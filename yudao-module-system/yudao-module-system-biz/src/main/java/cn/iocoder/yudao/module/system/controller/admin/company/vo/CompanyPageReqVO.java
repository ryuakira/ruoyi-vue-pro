package cn.iocoder.yudao.module.system.controller.admin.company.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - 会社分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CompanyPageReqVO extends PageParam {

    @ApiModelProperty(value = "会社名")
    private String nameJp;

    @ApiModelProperty(value = "Company Name")
    private String nameEn;

    @ApiModelProperty(value = "会社略称")
    private String nameAbrvt;

    @ApiModelProperty(value = "法人番号")
    private String code;

    @ApiModelProperty(value = "郵便")
    private String zipcode;

    @ApiModelProperty(value = "住所1")
    private String address1Jp;

    @ApiModelProperty(value = "住所2")
    private String address2Jp;

    @ApiModelProperty(value = "住所3")
    private String address3Jp;

    @ApiModelProperty(value = "建物名")
    private String buildingJp;

    @ApiModelProperty(value = "Address1")
    private String address1En;

    @ApiModelProperty(value = "Address2")
    private String address2En;

    @ApiModelProperty(value = "Address3")
    private String address3En;

    @ApiModelProperty(value = "Building")
    private String buildingEn;

    @ApiModelProperty(value = "電話番号")
    private String phone;

    @ApiModelProperty(value = "FAX")
    private String fax;

    @ApiModelProperty(value = "サイト")
    private String site;

    @ApiModelProperty(value = "銀行1")
    private String bank1;

    @ApiModelProperty(value = "銀行コード")
    private String bankCode1;

    @ApiModelProperty(value = "支店名")
    private String branch1;

    @ApiModelProperty(value = "支店コード")
    private String branchCode1;

    @ApiModelProperty(value = "swiftcode")
    private String swift1;

    @ApiModelProperty(value = "口座種類")
    private String accountType1;

    @ApiModelProperty(value = "口座番号")
    private String accountNumber1;

    @ApiModelProperty(value = "口座名義")
    private String accountName1;

    @ApiModelProperty(value = "通貨")
    private String currency1;

    @ApiModelProperty(value = "銀行2")
    private String bank2;

    @ApiModelProperty(value = "銀行コード")
    private String bankCode2;

    @ApiModelProperty(value = "支店名")
    private String branch2;

    @ApiModelProperty(value = "支店コード")
    private String branchCode2;

    @ApiModelProperty(value = "swiftcode")
    private String swift2;

    @ApiModelProperty(value = "口座種類")
    private String accountType2;

    @ApiModelProperty(value = "口座番号")
    private String accountNumber2;

    @ApiModelProperty(value = "口座名義")
    private String accountName2;

    @ApiModelProperty(value = "通貨")
    private String currency2;

    @ApiModelProperty(value = "銀行3")
    private String bank3;

    @ApiModelProperty(value = "銀行コード")
    private String bankCode3;

    @ApiModelProperty(value = "支店名")
    private String branch3;

    @ApiModelProperty(value = "銀行コード")
    private String branchCode3;

    @ApiModelProperty(value = "swiftcode")
    private String swift3;

    @ApiModelProperty(value = "口座種類")
    private String accountType3;

    @ApiModelProperty(value = "口座番号")
    private String accountNumber3;

    @ApiModelProperty(value = "口座名義")
    private String accountName3;

    @ApiModelProperty(value = "通貨")
    private String currency3;

    @ApiModelProperty(value = "銀行4")
    private String bank4;

    @ApiModelProperty(value = "銀行コード")
    private String bankCode4;

    @ApiModelProperty(value = "支店名")
    private String branch4;

    @ApiModelProperty(value = "銀行コード")
    private String branchCode4;

    @ApiModelProperty(value = "swiftcode")
    private String swift4;

    @ApiModelProperty(value = "口座種類")
    private String accountType4;

    @ApiModelProperty(value = "口座番号")
    private String accountNumber4;

    @ApiModelProperty(value = "口座名義")
    private String accountName4;

    @ApiModelProperty(value = "通貨")
    private String currency4;

    @ApiModelProperty(value = "添付1")
    private String url1;

    @ApiModelProperty(value = "添付2")
    private String url2;

    @ApiModelProperty(value = "添付3")
    private String url3;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "开始创建时间")
    private Date beginCreateTime;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

}
