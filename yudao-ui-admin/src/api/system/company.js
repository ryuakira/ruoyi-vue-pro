import request from '@/utils/request'

// 查询会社列表
export function listCompany() {
  return request({
    url: '/system/company/list-all-simple',
    method: 'get'
  })
}

// 创建会社
export function createCompany(data) {
  return request({
    url: '/system/company/create',
    method: 'post',
    data: data
  })
}

// 更新会社
export function updateCompany(data) {
  return request({
    url: '/system/company/update',
    method: 'put',
    data: data
  })
}

// 删除会社
export function deleteCompany(id) {
  return request({
    url: '/system/company/delete?id=' + id,
    method: 'delete'
  })
}

// 获得会社
export function getCompany(id) {
  return request({
    url: '/system/company/get?id=' + id,
    method: 'get'
  })
}

// 获得会社分页
export function getCompanyPage(query) {
  return request({
    url: '/system/company/page',
    method: 'get',
    params: query
  })
}

// 导出会社 Excel
export function exportCompanyExcel(query) {
  return request({
    url: '/system/company/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
