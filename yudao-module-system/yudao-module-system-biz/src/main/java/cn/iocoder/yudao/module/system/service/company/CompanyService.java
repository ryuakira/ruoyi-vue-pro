package cn.iocoder.yudao.module.system.service.company;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.system.controller.admin.company.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.company.CompanyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 会社 Service 接口
 *
 * @author KYO
 */
public interface CompanyService {

    /**
     * 创建会社
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCompany(@Valid CompanyCreateReqVO createReqVO);

    /**
     * 更新会社
     *
     * @param updateReqVO 更新信息
     */
    void updateCompany(@Valid CompanyUpdateReqVO updateReqVO);

    /**
     * 删除会社
     *
     * @param id 编号
     */
    void deleteCompany(Long id);

    /**
     * 获得会社
     *
     * @param id 编号
     * @return 会社
     */
    CompanyDO getCompany(Long id);

    /**
     * 获得会社列表
     *
     * @param ids 编号
     * @return 会社列表
     */
    List<CompanyDO> getCompanyList(Collection<Long> ids);

    /**
     * 获得会社分页
     *
     * @param pageReqVO 分页查询
     * @return 会社分页
     */
    PageResult<CompanyDO> getCompanyPage(CompanyPageReqVO pageReqVO);

    /**
     * 获得会社列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 会社列表
     */
    List<CompanyDO> getCompanyList(CompanyExportReqVO exportReqVO);

}
