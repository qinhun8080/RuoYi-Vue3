package com.ruoyi.book.service;

import java.util.List;
import com.ruoyi.book.domain.BookType;

/**
 * 图书类型Service接口
 *
 * @author 朱奎哲
 * @date 2026-03-19
 */
public interface IBookTypeService
{
    /**
     * 查询图书类型
     *
     * @param typeId 图书类型主键
     * @return 图书类型
     */
    public BookType selectBookTypeByTypeId(Long typeId);

    /**
     * 查询图书类型列表
     *
     * @param bookType 图书类型
     * @return 图书类型集合
     */
    public List<BookType> selectBookTypeList(BookType bookType);

    /**
     * 新增图书类型
     *
     * @param bookType 图书类型
     * @return 结果
     */
    public int insertBookType(BookType bookType);

    /**
     * 修改图书类型
     *
     * @param bookType 图书类型
     * @return 结果
     */
    public int updateBookType(BookType bookType);

    /**
     * 批量删除图书类型
     *
     * @param typeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBookTypeByTypeIds(Long[] typeIds);

    /**
     * 删除图书类型信息
     *
     * @param typeId 图书类型主键
     * @return 结果
     */
    public int deleteBookTypeByTypeId(Long typeId);

    /**
     * 手动同步图书类型到字典
     */
    void syncTypeToDict();
}