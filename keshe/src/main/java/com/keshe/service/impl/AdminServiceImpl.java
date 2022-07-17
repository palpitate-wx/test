package com.keshe.service.impl;

import com.keshe.dao.AdminDAO;
import com.keshe.dao.BookDAO;
import com.keshe.dao.BorrowerDAO;
import com.keshe.dataobject.Admin;
import com.keshe.dataobject.Book;
import com.keshe.dataobject.Borrower;
import com.keshe.pojo.Result;
import com.keshe.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Autowired
    private BorrowerDAO borrowerDAO;

    @Autowired
    private BookDAO bookDAO;

    @Override
    public Result<Admin> login(long adminId, String password) {
        Result<Admin> result=new Result<>();
        Admin admin=new Admin();
        admin=adminDAO.getAdmin(adminId);
        if(admin==null){
            result.setCode("501");
            result.setMessage("登录失败,没找到该管理员信息");
            return result;
        }
        if(!admin.getAdminPassword().equals(password)){
            result.setData(admin);
            result.setCode("502");
            result.setMessage("登录失败，密码错误!!!");
            return result;
        }
        result.setSuccess(true);
        result.setData(admin);
        result.setCode("200");
        result.setMessage("登录成功");
        return result;
    }

    @Override
    public Result<Admin> updatePassword(long adminId, String oldPassword, String newPassword) {
        Result<Admin> result=new Result<>();
        Admin admin=new Admin();
        admin=adminDAO.getAdmin(adminId);
        if(!admin.getAdminPassword().equals(oldPassword)){
            result.setData(admin);
            result.setCode("502");
            result.setMessage("修改失败，原密码输入错误!!!");
            return result;
        }
        adminDAO.updatePassword(adminId,newPassword);
        admin=adminDAO.getAdmin(adminId);
        result.setSuccess(true);
        result.setData(admin);
        result.setCode("200");
        result.setMessage("密码修改成功");
        return result;
    }

    @Override
    public Result<List<Borrower>> getAllBorrower() {
        Result<List<Borrower>> result=new Result<>();
        List<Borrower> borrowers=new ArrayList<>();
        borrowers=borrowerDAO.getAllBorrower();
        result.setSuccess(true);
        result.setData(borrowers);
        result.setCode("200");
        result.setMessage("查询成功");
        return result;
    }

    @Override
    public Result<List<Book>> getAllBooks() {
        Result<List<Book>> result=new Result<>();
        List<Book> books=new ArrayList<>();
        books=bookDAO.getAllBooks();
        result.setSuccess(true);
        result.setData(books);
        result.setCode("200");
        result.setMessage("查询成功");
        return result;
    }
}
