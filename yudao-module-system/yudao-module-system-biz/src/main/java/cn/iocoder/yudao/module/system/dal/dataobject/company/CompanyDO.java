package cn.iocoder.yudao.module.system.dal.dataobject.company;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 会社 DO
 *
 * @author KYO
 */
@TableName("system_company")
@KeySequence("system_company_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDO extends BaseDO {

    /**
     * No.
     */
    @TableId
    private Long id;
    /**
     * 会社名
     */
    private String nameJp;
    /**
     * Company Name
     */
    private String nameEn;
    /**
     * 会社略称
     */
    private String nameAbrvt;
    /**
     * 法人番号
     */
    private String code;
    /**
     * 郵便
     */
    private String zipcode;
    /**
     * 住所1
     */
    private String address1Jp;
    /**
     * 住所2
     */
    private String address2Jp;
    /**
     * 住所3
     */
    private String address3Jp;
    /**
     * 建物名
     */
    private String buildingJp;
    /**
     * Address1
     */
    private String address1En;
    /**
     * Address2
     */
    private String address2En;
    /**
     * Address3
     */
    private String address3En;
    /**
     * Building
     */
    private String buildingEn;
    /**
     * 電話番号
     */
    private String phone;
    /**
     * FAX
     */
    private String fax;
    /**
     * サイト
     */
    private String site;
    /**
     * 銀行1
     */
    private String bank1;
    /**
     * 銀行コード
     */
    private String bankCode1;
    /**
     * 支店名
     */
    private String branch1;
    /**
     * 支店コード
     */
    private String branchCode1;
    /**
     * swiftcode
     */
    private String swift1;
    /**
     * 口座種類
     */
    private String accountType1;
    /**
     * 口座番号
     */
    private String accountNumber1;
    /**
     * 口座名義
     */
    private String accountName1;
    /**
     * 通貨
     */
    private String currency1;
    /**
     * 銀行2
     */
    private String bank2;
    /**
     * 銀行コード
     */
    private String bankCode2;
    /**
     * 支店名
     */
    private String branch2;
    /**
     * 支店コード
     */
    private String branchCode2;
    /**
     * swiftcode
     */
    private String swift2;
    /**
     * 口座種類
     */
    private String accountType2;
    /**
     * 口座番号
     */
    private String accountNumber2;
    /**
     * 口座名義
     */
    private String accountName2;
    /**
     * 通貨
     */
    private String currency2;
    /**
     * 銀行3
     */
    private String bank3;
    /**
     * 銀行コード
     */
    private String bankCode3;
    /**
     * 支店名
     */
    private String branch3;
    /**
     * 銀行コード
     */
    private String branchCode3;
    /**
     * swiftcode
     */
    private String swift3;
    /**
     * 口座種類
     */
    private String accountType3;
    /**
     * 口座番号
     */
    private String accountNumber3;
    /**
     * 口座名義
     */
    private String accountName3;
    /**
     * 通貨
     */
    private String currency3;
    /**
     * 銀行4
     */
    private String bank4;
    /**
     * 銀行コード
     */
    private String bankCode4;
    /**
     * 支店名
     */
    private String branch4;
    /**
     * 銀行コード
     */
    private String branchCode4;
    /**
     * swiftcode
     */
    private String swift4;
    /**
     * 口座種類
     */
    private String accountType4;
    /**
     * 口座番号
     */
    private String accountNumber4;
    /**
     * 口座名義
     */
    private String accountName4;
    /**
     * 通貨
     */
    private String currency4;
    /**
     * 添付1
     */
    private String url1;
    /**
     * 添付2
     */
    private String url2;
    /**
     * 添付3
     */
    private String url3;

}
