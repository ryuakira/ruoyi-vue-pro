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

// 下载勤怠模板
export function downLoadTemplate() {
  // // 勤怠模版的url（local dev）
  //  return 'http://127.0.0.1:48080/admin-api/infra/file/5/get/88ed67b08cdb0d5f7829f2b7fdeaf0a2f5cd2a69293273a819739521551985e3.xlsx';

  // 简历模版的url（七云牛 11号 dev）
  return 'http://test.yudao.iocoder.cn/4121726fe3e0a68c8d6ab281d027626e167d5077c3e51313d419af2a44c04c4c.xls';
}
