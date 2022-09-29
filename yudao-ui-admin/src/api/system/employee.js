import request from '@/utils/request'

// 创建社員
export function createEmployee(data) {
  return request({
    url: '/system/employee/create',
    method: 'post',
    data: data
  })
}

// 更新社員
export function updateEmployee(data) {
  return request({
    url: '/system/employee/update',
    method: 'put',
    data: data
  })
}

// 删除社員
export function deleteEmployee(id) {
  return request({
    url: '/system/employee/delete?id=' + id,
    method: 'delete'
  })
}

// 获得社員
export function getEmployee(id) {
  return request({
    url: '/system/employee/get?id=' + id,
    method: 'get'
  })
}

// 获得社員分页
export function getEmployeePage(query) {
  return request({
    url: '/system/employee/page',
    method: 'get',
    params: query
  })
}

// 导出社員 Excel
export function exportEmployeeExcel(query) {
  return request({
    url: '/system/employee/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}

// 获取在职员工精简信息列表（用于初始化员工名称下拉框）----劉義民 手动追加代码 2022/08/30
export function listSimpleEmployee() {
  return request({
    url: '/system/employee/list-all-simple',
    method: 'get'
  })
}

// 定义员工管理上传文件按钮的枚举值
export const ENUM_BUTTON_FLG = {
  PHOTO_BUTTON: 1, // 上传照片按钮的Flg值
  IDENTITY_BUTTON: 2, // 上传在留卡复印件按钮的Flg值
  RESUME_BUTTON: 3 // 上传最新简历按钮的Flg值
}
