package com.keshe.dao;

import com.keshe.dataobject.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookDAO {
    /**
     * 添加书籍
     * @param book
     * @return
     */
    int addBook(Book book);

    /**
     * 根据书号删除书籍
     * @param bookId
     * @return
     */
    int deleteBook(@Param("bookId") long bookId);

    /**
     * 查询所有书籍
     * @return
     */
    List<Book> getAllBooks();

    /**
     * 根据书号查询图书
     * @param bookId
     * @return
     */
    Book getBook(@Param("bookId") long bookId);

    /**
     * 根据姓名或书名进行模糊查询
     * @param keyWord
     * @return
     */
    List<Book> searchBooks(@Param("keyWord") String keyWord);


}
