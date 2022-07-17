package com.keshe.service;

import com.keshe.dataobject.Book;
import com.keshe.dataobject.Borrower;
import com.keshe.pojo.Borrowing;
import com.keshe.pojo.Result;

import java.util.List;

public interface BorrowerService {

    /**
     * 借阅者登录
     * @param borrowId
     * @param password
     * @return
     */
    Result<Borrower> login(long borrowId, String password);

    /**
     * 借阅者修改密码
     * @param borrowId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    Result<Borrower> updatePassword(long borrowId, String oldPassword,String newPassword);

    /**
     * 借书
     * @param borrowId
     * @param bookId
     * @return
     */
    Result<Borrowing> borrowBook(long borrowId,long bookId);

    /**
     * 还书
     * @param borrowId
     * @param bookId
     * @return
     */
    Result<Borrowing> returnBook(long borrowId,long bookId);


    /**
     * 查询自己所借图书信息
     * @param borrowId
     * @return
     */
    Result<List<Book>> getBorrowBooks(long borrowId);


}
