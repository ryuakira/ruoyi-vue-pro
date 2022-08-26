import request from '@/utils/request'

// 创建勤怠
export function createWorktime(data) {
  return request({
    url: '/system/worktime/create',
    method: 'post',
    data: data
  })
}

// 更新勤怠
export function updateWorktime(data) {
  return request({
    url: '/system/worktime/update',
    method: 'put',
    data: data
  })
}

// 删除勤怠
export function deleteWorktime(id) {
  return request({
    url: '/system/worktime/delete?id=' + id,
    method: 'delete'
  })
}

// 获得勤怠
export function getWorktime(id) {
  return request({
    url: '/system/worktime/get?id=' + id,
    method: 'get'
  })
}

// 获得勤怠分页
export function getWorktimePage(query) {
  return request({
    url: '/system/worktime/page',
    method: 'get',
    params: query
  })
}

// 导出勤怠 Excel
export function exportWorktimeExcel(query) {
  return request({
    url: '/system/worktime/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
