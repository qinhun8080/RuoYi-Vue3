import request from '@/utils/request'

// 查询测试部门信息列表
export function listDept1(query) {
  return request({
    url: '/system/dept1/list',
    method: 'get',
    params: query
  })
}

// 查询测试部门信息详细
export function getDept1(deptId) {
  return request({
    url: '/system/dept1/' + deptId,
    method: 'get'
  })
}

// 新增测试部门信息
export function addDept1(data) {
  return request({
    url: '/system/dept1',
    method: 'post',
    data: data
  })
}

// 修改测试部门信息
export function updateDept1(data) {
  return request({
    url: '/system/dept1',
    method: 'put',
    data: data
  })
}

// 删除测试部门信息
export function delDept1(deptId) {
  return request({
    url: '/system/dept1/' + deptId,
    method: 'delete'
  })
}
