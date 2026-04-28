import request from '@/utils/request'

// 查询三江学院组织架构列表
export function listDepartment(query) {
  return request({
    url: '/system/department/list',
    method: 'get',
    params: query
  })
}

// 查询三江学院组织架构详细
export function getDepartment(deptId) {
  return request({
    url: '/system/department/' + deptId,
    method: 'get'
  })
}

// 新增三江学院组织架构
export function addDepartment(data) {
  return request({
    url: '/system/department',
    method: 'post',
    data: data
  })
}

// 修改三江学院组织架构
export function updateDepartment(data) {
  return request({
    url: '/system/department',
    method: 'put',
    data: data
  })
}

// 删除三江学院组织架构
export function delDepartment(deptId) {
  return request({
    url: '/system/department/' + deptId,
    method: 'delete'
  })
}
