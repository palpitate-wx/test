package com.keshe.service;

import com.keshe.dataobject.Admin;
import com.keshe.dataobject.Book;
import com.keshe.dataobject.Borrower;
import com.keshe.pojo.Result;

import java.util.List;

public interface AdminService {

    /**
     * 管理员登录
     * @param adminId
     * @param password
     * @return
     */
    Result<Admin> login(long adminId, String password);

    /**
     * 管理员修改密码
     * @param adminId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    Result<Admin> updatePassword(long adminId,String oldPassword,String newPassword);


    /**
     * 查看全部借阅者
     * @return
     */
    Result<List<Borrower>> getAllBorrower();

    /**
     * 查看全部书籍
     * @return
     */
    Result<List<Book>> getAllBooks();


}
