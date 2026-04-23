package com.ruoyi.book.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 图书信息对象 book
 *
 * @author ruoyi
 * @date 2026-03-19
 */
public class Book extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 图书ID */
    private Long bookId;

    /** 图书名 */
    @Excel(name = "图书名")
    private String bookName;

    /** 作者名 */
    @Excel(name = "作者名")
    private String bookAuthor;

    /** 封面 */
    @Excel(name = "封面")
    private String bookImage;

    /** 图书类型ID */
    @Excel(name = "图书类型")
    private Long typeId;

    /** 新增：图书类型名称（前端显示用） */
    @Excel(name = "图书类型名称")
    private String typeName;

    public void setBookId(Long bookId)
    {
        this.bookId = bookId;
    }

    public Long getBookId()
    {
        return bookId;
    }

    public void setBookName(String bookName)
    {
        this.bookName = bookName;
    }

    public String getBookName()
    {
        return bookName;
    }

    public void setBookAuthor(String bookAuthor)
    {
        this.bookAuthor = bookAuthor;
    }

    public String getBookAuthor()
    {
        return bookAuthor;
    }

    public void setBookImage(String bookImage)
    {
        this.bookImage = bookImage;
    }

    public String getBookImage()
    {
        return bookImage;
    }

    public void setTypeId(Long typeId)
    {
        this.typeId = typeId;
    }

    public Long getTypeId()
    {
        return typeId;
    }

    // 新增：typeName 的 getter/setter
    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    public String getTypeName()
    {
        return typeName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("bookId", getBookId())
                .append("bookName", getBookName())
                .append("bookAuthor", getBookAuthor())
                .append("bookImage", getBookImage())
                .append("typeId", getTypeId())
                .append("typeName", getTypeName()) // 新增：toString 包含类型名
                .toString();
    }
}