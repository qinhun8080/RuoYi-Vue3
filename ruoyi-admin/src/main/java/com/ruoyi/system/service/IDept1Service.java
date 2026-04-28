package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Dept1;

/**
 * 测试部门信息Service接口
 * 
 * @author ruoyi
 * @date 2026-04-16
 */
public interface IDept1Service 
{
    /**
     * 查询测试部门信息
     * 
     * @param deptId 测试部门信息主键
     * @return 测试部门信息
     */
    public Dept1 selectDept1ByDeptId(Long deptId);

    /**
     * 查询测试部门信息列表
     * 
     * @param dept1 测试部门信息
     * @return 测试部门信息集合
     */
    public List<Dept1> selectDept1List(Dept1 dept1);

    /**
     * 新增测试部门信息
     * 
     * @param dept1 测试部门信息
     * @return 结果
     */
    public int insertDept1(Dept1 dept1);

    /**
     * 修改测试部门信息
     * 
     * @param dept1 测试部门信息
     * @return 结果
     */
    public int updateDept1(Dept1 dept1);

    /**
     * 批量删除测试部门信息
     * 
     * @param deptIds 需要删除的测试部门信息主键集合
     * @return 结果
     */
    public int deleteDept1ByDeptIds(Long[] deptIds);

    /**
     * 删除测试部门信息信息
     * 
     * @param deptId 测试部门信息主键
     * @return 结果
     */
    public int deleteDept1ByDeptId(Long deptId);
}
