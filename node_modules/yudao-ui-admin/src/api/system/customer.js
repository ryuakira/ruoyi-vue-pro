import request from '@/utils/request'

// 创建顧客
export function createCustomer(data) {
  return request({
    url: '/system/customer/create',
    method: 'post',
    data: data
  })
}

// 更新顧客
export function updateCustomer(data) {
  return request({
    url: '/system/customer/update',
    method: 'put',
    data: data
  })
}

// 删除顧客
export function deleteCustomer(id) {
  return request({
    url: '/system/customer/delete?id=' + id,
    method: 'delete'
  })
}

// 获得顧客
export function getCustomer(id) {
  return request({
    url: '/system/customer/get?id=' + id,
    method: 'get'
  })
}

// 获得顧客分页
export function getCustomerPage(query) {
  return request({
    url: '/system/customer/page',
    method: 'get',
    params: query
  })
}

// 导出顧客 Excel
export function exportCustomerExcel(query) {
  return request({
    url: '/system/customer/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
