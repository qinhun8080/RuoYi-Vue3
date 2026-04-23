package com.ruoyi.system.controller;

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
import com.ruoyi.system.domain.Dept1;
import com.ruoyi.system.service.IDept1Service;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 测试部门信息Controller
 * 
 * @author ruoyi
 * @date 2026-04-16
 */
@RestController
@RequestMapping("/system/dept1")
public class Dept1Controller extends BaseController
{
    @Autowired
    private IDept1Service dept1Service;

    /**
     * 查询测试部门信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:dept1:list')")
    @GetMapping("/list")
    public TableDataInfo list(Dept1 dept1)
    {
        startPage();
        List<Dept1> list = dept1Service.selectDept1List(dept1);
        return getDataTable(list);
    }

    /**
     * 导出测试部门信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:dept1:export')")
    @Log(title = "测试部门信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Dept1 dept1)
    {
        List<Dept1> list = dept1Service.selectDept1List(dept1);
        ExcelUtil<Dept1> util = new ExcelUtil<Dept1>(Dept1.class);
        util.exportExcel(response, list, "测试部门信息数据");
    }

    /**
     * 获取测试部门信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:dept1:query')")
    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable("deptId") Long deptId)
    {
        return success(dept1Service.selectDept1ByDeptId(deptId));
    }

    /**
     * 新增测试部门信息
     */
    @PreAuthorize("@ss.hasPermi('system:dept1:add')")
    @Log(title = "测试部门信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Dept1 dept1)
    {
        return toAjax(dept1Service.insertDept1(dept1));
    }

    /**
     * 修改测试部门信息
     */
    @PreAuthorize("@ss.hasPermi('system:dept1:edit')")
    @Log(title = "测试部门信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Dept1 dept1)
    {
        return toAjax(dept1Service.updateDept1(dept1));
    }

    /**
     * 删除测试部门信息
     */
    @PreAuthorize("@ss.hasPermi('system:dept1:remove')")
    @Log(title = "测试部门信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deptIds}")
    public AjaxResult remove(@PathVariable Long[] deptIds)
    {
        return toAjax(dept1Service.deleteDept1ByDeptIds(deptIds));
    }
}
