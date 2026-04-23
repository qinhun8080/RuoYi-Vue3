package com.ruoyi.department.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.department.mapper.DepartmentMapper;
import com.ruoyi.department.domain.Department;
import com.ruoyi.department.service.IDepartmentService;

/**
 * 三江学院组织架构Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-26
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService 
{
    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 查询三江学院组织架构
     * 
     * @param deptId 三江学院组织架构主键
     * @return 三江学院组织架构
     */
    @Override
    public Department selectDepartmentByDeptId(Long deptId)
    {
        return departmentMapper.selectDepartmentByDeptId(deptId);
    }

    /**
     * 查询三江学院组织架构列表
     * 
     * @param department 三江学院组织架构
     * @return 三江学院组织架构
     */
    @Override
    public List<Department> selectDepartmentList(Department department)
    {
        return departmentMapper.selectDepartmentList(department);
    }

    /**
     * 新增三江学院组织架构
     * 
     * @param department 三江学院组织架构
     * @return 结果
     */
    @Override
    public int insertDepartment(Department department)
    {
        return departmentMapper.insertDepartment(department);
    }

    /**
     * 修改三江学院组织架构
     * 
     * @param department 三江学院组织架构
     * @return 结果
     */
    @Override
    public int updateDepartment(Department department)
    {
        return departmentMapper.updateDepartment(department);
    }

    /**
     * 批量删除三江学院组织架构
     * 
     * @param deptIds 需要删除的三江学院组织架构主键
     * @return 结果
     */
    @Override
    public int deleteDepartmentByDeptIds(Long[] deptIds)
    {
        return departmentMapper.deleteDepartmentByDeptIds(deptIds);
    }

    /**
     * 删除三江学院组织架构信息
     * 
     * @param deptId 三江学院组织架构主键
     * @return 结果
     */
    @Override
    public int deleteDepartmentByDeptId(Long deptId)
    {
        return departmentMapper.deleteDepartmentByDeptId(deptId);
    }
}
