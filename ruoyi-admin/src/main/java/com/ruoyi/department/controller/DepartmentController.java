package com.ruoyi.department.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.department.domain.Department;
import com.ruoyi.department.service.IDepartmentService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 三江学院组织架构Controller
 * 
 * @author ruoyi
 * @date 2026-03-26
 */
@RestController
@RequestMapping("/system/department")
public class DepartmentController extends BaseController
{
    @Autowired
    private IDepartmentService departmentService;

    /**
     * 查询三江学院组织架构列表
     */
    @PreAuthorize("@ss.hasPermi('system:department:list')")
    @GetMapping("/list")
    public AjaxResult list(Department department)
    {
        List<Department> list = departmentService.selectDepartmentList(department);
        return success(list);
    }

    /**
     * 导出三江学院组织架构列表
     */
    @PreAuthorize("@ss.hasPermi('system:department:export')")
    @Log(title = "三江学院组织架构", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Department department)
    {
        List<Department> list = departmentService.selectDepartmentList(department);
        ExcelUtil<Department> util = new ExcelUtil<Department>(Department.class);
        util.exportExcel(response, list, "三江学院组织架构数据");
    }

    /**
     * 获取三江学院组织架构详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:department:query')")
    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable("deptId") Long deptId)
    {
        return success(departmentService.selectDepartmentByDeptId(deptId));
    }

    /**
     * 新增三江学院组织架构
     */
    @PreAuthorize("@ss.hasPermi('system:department:add')")
    @Log(title = "三江学院组织架构", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Department department)
    {
        return toAjax(departmentService.insertDepartment(department));
    }

    /**
     * 修改三江学院组织架构
     */
    @PreAuthorize("@ss.hasPermi('system:department:edit')")
    @Log(title = "三江学院组织架构", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Department department)
    {
        return toAjax(departmentService.updateDepartment(department));
    }

    /**
     * 删除三江学院组织架构
     */
    @PreAuthorize("@ss.hasPermi('system:department:remove')")
    @Log(title = "三江学院组织架构", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deptIds}")
    public AjaxResult remove(@PathVariable Long[] deptIds)
    {
        return toAjax(departmentService.deleteDepartmentByDeptIds(deptIds));
    }
}
