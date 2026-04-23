package com.ruoyi.book.service.impl;

import com.ruoyi.book.domain.Book;
import com.ruoyi.book.domain.BookType;
import com.ruoyi.book.mapper.BookTypeMapper;
import com.ruoyi.book.service.IBookTypeService;
import com.ruoyi.common.core.domain.entity.SysDictData;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.mapper.SysDictDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 图书类型Service业务层处理
 *
 * @author 朱奎哲
 * @date 2026-03-19
 */
@Service
public class BookTypeServiceImpl implements IBookTypeService
{
    @Autowired
    private BookTypeMapper bookTypeMapper;

    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    @Override
    public BookType selectBookTypeByTypeId(Long typeId) {
        return bookTypeMapper.selectBookTypeByTypeId(typeId);
    }

    @Override
    public List<BookType> selectBookTypeList(BookType bookType) {
        return bookTypeMapper.selectBookTypeList(bookType);
    }

    @Transactional
    @Override
    public int insertBookType(BookType bookType) {
        int rows = bookTypeMapper.insertBookType(bookType);
        insertBook(bookType);
        syncTypeToDict();
        return rows;
    }

    @Transactional
    @Override
    public int updateBookType(BookType bookType) {
        bookTypeMapper.deleteBookByTypeId(bookType.getTypeId());
        insertBook(bookType);
        syncTypeToDict();
        return bookTypeMapper.updateBookType(bookType);
    }

    @Transactional
    @Override
    public int deleteBookTypeByTypeIds(Long[] typeIds) {
        bookTypeMapper.deleteBookByTypeIds(typeIds);
        int rows = bookTypeMapper.deleteBookTypeByTypeIds(typeIds);
        syncTypeToDict();
        return rows;
    }

    @Transactional
    @Override
    public int deleteBookTypeByTypeId(Long typeId) {
        bookTypeMapper.deleteBookByTypeId(typeId);
        int rows = bookTypeMapper.deleteBookTypeByTypeId(typeId);
        syncTypeToDict();
        return rows;
    }

    public void insertBook(BookType bookType) {
        List<Book> bookList = bookType.getBookList();
        Long typeId = bookType.getTypeId();
        if (StringUtils.isNotNull(bookList)) {
            List<Book> list = new ArrayList<>();
            for (Book book : bookList) {
                book.setTypeId(typeId);
                list.add(book);
            }
            if (!list.isEmpty()) {
                bookTypeMapper.batchBook(list);
            }
        }
    }

    // 👇👇👇 这里改成 public，给外部调用
    @Override
    public void syncTypeToDict() {
        List<BookType> bookTypeList = bookTypeMapper.selectBookTypeList(null);
        sysDictDataMapper.deleteAllDataByType("book_type");

        if (bookTypeList != null && !bookTypeList.isEmpty()) {
            for (BookType type : bookTypeList) {
                SysDictData dict = new SysDictData();
                dict.setDictLabel(type.getTypeName());
                dict.setDictValue(type.getTypeId().toString());
                dict.setDictType("book_type");
                dict.setStatus("0");
                dict.setDictSort(type.getTypeId());
                sysDictDataMapper.insertDictData(dict);
            }
        }
    }
}