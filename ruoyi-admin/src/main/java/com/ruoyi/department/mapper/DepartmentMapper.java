package com.ruoyi.department.mapper;

import java.util.List;
import com.ruoyi.department.domain.Department;

/**
 * 三江学院组织架构Mapper接口
 * 
 * @author ruoyi
 * @date 2026-03-26
 */
public interface DepartmentMapper 
{
    /**
     * 查询三江学院组织架构
     * 
     * @param deptId 三江学院组织架构主键
     * @return 三江学院组织架构
     */
    public Department selectDepartmentByDeptId(Long deptId);

    /**
     * 查询三江学院组织架构列表
     * 
     * @param department 三江学院组织架构
     * @return 三江学院组织架构集合
     */
    public List<Department> selectDepartmentList(Department department);

    /**
     * 新增三江学院组织架构
     * 
     * @param department 三江学院组织架构
     * @return 结果
     */
    public int insertDepartment(Department department);

    /**
     * 修改三江学院组织架构
     * 
     * @param department 三江学院组织架构
     * @return 结果
     */
    public int updateDepartment(Department department);

    /**
     * 删除三江学院组织架构
     * 
     * @param deptId 三江学院组织架构主键
     * @return 结果
     */
    public int deleteDepartmentByDeptId(Long deptId);

    /**
     * 批量删除三江学院组织架构
     * 
     * @param deptIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDepartmentByDeptIds(Long[] deptIds);
}
