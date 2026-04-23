package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.Dept1Mapper;
import com.ruoyi.system.domain.Dept1;
import com.ruoyi.system.service.IDept1Service;

/**
 * 测试部门信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-04-16
 */
@Service
public class Dept1ServiceImpl implements IDept1Service 
{
    @Autowired
    private Dept1Mapper dept1Mapper;

    /**
     * 查询测试部门信息
     * 
     * @param deptId 测试部门信息主键
     * @return 测试部门信息
     */
    @Override
    public Dept1 selectDept1ByDeptId(Long deptId)
    {
        return dept1Mapper.selectDept1ByDeptId(deptId);
    }

    /**
     * 查询测试部门信息列表
     * 
     * @param dept1 测试部门信息
     * @return 测试部门信息
     */
    @Override
    public List<Dept1> selectDept1List(Dept1 dept1)
    {
        return dept1Mapper.selectDept1List(dept1);
    }

    /**
     * 新增测试部门信息
     * 
     * @param dept1 测试部门信息
     * @return 结果
     */
    @Override
    public int insertDept1(Dept1 dept1)
    {
        return dept1Mapper.insertDept1(dept1);
    }

    /**
     * 修改测试部门信息
     * 
     * @param dept1 测试部门信息
     * @return 结果
     */
    @Override
    public int updateDept1(Dept1 dept1)
    {
        return dept1Mapper.updateDept1(dept1);
    }

    /**
     * 批量删除测试部门信息
     * 
     * @param deptIds 需要删除的测试部门信息主键
     * @return 结果
     */
    @Override
    public int deleteDept1ByDeptIds(Long[] deptIds)
    {
        return dept1Mapper.deleteDept1ByDeptIds(deptIds);
    }

    /**
     * 删除测试部门信息信息
     * 
     * @param deptId 测试部门信息主键
     * @return 结果
     */
    @Override
    public int deleteDept1ByDeptId(Long deptId)
    {
        return dept1Mapper.deleteDept1ByDeptId(deptId);
    }
}
