package com.keshe.control;


import com.keshe.dao.AdminDAO;
import com.keshe.dao.BookDAO;
import com.keshe.dao.BorrowerDAO;
import com.keshe.dataobject.Admin;
import com.keshe.dataobject.Book;
import com.keshe.dataobject.Borrower;
import com.keshe.pojo.Result;
import com.keshe.service.AdminService;

import com.keshe.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminControl {

    @Autowired
    private AdminDAO adminDAO;

    @Autowired
    private AdminService adminService;

    @Autowired
    private BorrowerService borrowerService;

    @Autowired
    private BorrowerDAO borrowerDAO;

    @Autowired
    private BookDAO bookDAO;

    @RequestMapping("/getAdmin")
    @ResponseBody
    public Admin getAdmin(@RequestParam("adminId") long adminId){
        System.out.println(adminDAO.getAdmin(adminId).toString());
        return adminDAO.getAdmin(adminId);
    }


    @RequestMapping ("/borrower")
    public String borrower(){
        return "borrower.html";
    }


    @RequestMapping ("/admin")
    public String admin(){
        return "admin.html";
    }

    @RequestMapping("/alogin")
    public String login(){return "alogin.html";}

    /**
     * 增加借阅者接口
     * @param borrower
     * @return
     */
    @RequestMapping("/admin/addBorrower")
    @ResponseBody
    public Result addBorrower(@RequestBody Borrower borrower){
        Result result=new Result();
        int flag=borrowerDAO.addBorrower(borrower);
        if(flag!=0){
            result.setSuccess(true);
            result.setCode("200");
            result.setMessage("添加借阅者成功");
        }else {
            result.setSuccess(false);
            result.setCode("500");
            result.setMessage("添加借阅者失败");
        }
        return result;
    }

    /**
     * 增加书籍接口
     * @param book
     * @return
     */
    @RequestMapping("/admin/addBook")
    @ResponseBody
    public Result addBook(@RequestBody Book book){
        Result result=new Result();
        int flag=bookDAO.addBook(book);
        if(flag!=0){
            result.setSuccess(true);
            result.setCode("200");
            result.setMessage("添加书籍成功");
        }else {
            result.setSuccess(false);
            result.setCode("500");
            result.setMessage("添加书籍失败");
        }
        return result;
    }


    /**
     * 删除借阅者接口
     * @param borrowId
     * @return
     */
    @RequestMapping("/admin/deleteBorrower")
    @ResponseBody
    public Result deleteBorrower(@RequestParam("borrowId") long borrowId){
        Result result=new Result();
        int flag=borrowerDAO.deleteBorrower(borrowId);
        if(flag!=0){
            result.setSuccess(true);
            result.setCode("200");
            result.setMessage("删除信息成功");
        }else {
            result.setSuccess(false);
            result.setCode("500");
            result.setMessage("删除信息失败");
        }
        return result;
    }


    @RequestMapping("/admin/deleteBook")
    @ResponseBody
    public Result deleteBook(@RequestParam("bookId") long bookId){
        Result result=new Result();
        int flag=bookDAO.deleteBook(bookId);
        if(flag!=0){
            result.setSuccess(true);
            result.setCode("200");
            result.setMessage("删除信息成功");
        }else {
            result.setSuccess(false);
            result.setCode("500");
            result.setMessage("删除信息失败");
        }
        return result;
    }

    /**
     * 修改管理员密码
     * @param adminId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @RequestMapping("/admin/updateAdminPassword")
    @ResponseBody
    public  Result<Admin> updateAdminPassword(@RequestParam("adminId") long adminId,@RequestParam("old") String oldPassword,@RequestParam("new") String newPassword){
        return adminService.updatePassword(adminId,oldPassword,newPassword);
    }

    @RequestMapping("/adminLogin")
    @ResponseBody
    public Result<Admin> adminLogin(@RequestParam("adminId") long adminId, @RequestParam("password") String password, Model model, HttpServletRequest request){
        Result<Admin> result=adminService.login(adminId,password);
        if(result.isSuccess()){
            model.addAttribute("admin", result.getData());
            request.getSession().setAttribute("admin", result.getData());
        }
        return result;
    }




}
