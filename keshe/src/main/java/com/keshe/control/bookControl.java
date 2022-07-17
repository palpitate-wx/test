package com.keshe.control;

import com.keshe.dao.BookDAO;
import com.keshe.dataobject.Admin;
import com.keshe.dataobject.Book;
import com.keshe.pojo.Result;
import com.keshe.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class bookControl {

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private AdminService adminService;

    @RequestMapping(path = {"/borrower/getAllBooks","/admin/getAllBooks"})
    @ResponseBody
    public Result<List<Book>> getAllBooks(){
        return adminService.getAllBooks();
    }

    @RequestMapping(path = {"/borrower/searchBooks","/admin/searchBooks"})
    @ResponseBody
    public List<Book> searchBooks(@RequestParam("keyWord") String keyWord){
        return bookDAO.searchBooks(keyWord);
    }


    @RequestMapping("/admin/getBook")
    @ResponseBody
    public Result<Book> getBook(@RequestParam("bookId") long bookId){
        Result<Book> result=new Result<>();
        Book book=bookDAO.getBook(bookId);
        if(book!=null){
        result.setData(bookDAO.getBook(bookId));
        result.setSuccess(true);
        result.setCode("200");
        return result;
        }
        result.setCode("500");
        return result;
    }

}
