package com.keshe.control;

import com.keshe.dao.BorrowerDAO;
import com.keshe.dataobject.Book;
import com.keshe.dataobject.Borrower;
import com.keshe.pojo.Borrowing;
import com.keshe.pojo.Result;
import com.keshe.service.AdminService;
import com.keshe.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class borrowerControl {

    @Autowired
    private BorrowerDAO borrowerDAO;

    @Autowired
    private BorrowerService borrowerService;

    @Autowired
    private AdminService adminService;

    @RequestMapping("blogin")
    public String blogin(){
        return "blogin.html";
    }

    @RequestMapping("/getAllBorrowing")
    @ResponseBody
    public List<Borrowing> getBorrowing(@RequestParam("borrowId") long borrowId){
        return borrowerDAO.getAllBorrowing(borrowId);
    }

    @RequestMapping("/borrower/borrowBook")
    @ResponseBody
    public Result<Borrowing> borrowBook(@RequestParam("borrowId") long borrowId,@RequestParam("bookId") long bookId){
        return borrowerService.borrowBook(borrowId,bookId);
    }

    @RequestMapping("getBorrower")
    @ResponseBody
    public Borrower getBorrower(@RequestParam("borrowId") long borrowId){
        return borrowerDAO.getBorrower(borrowId);
    }

    @RequestMapping("/borrower/updatePassword")
    @ResponseBody
    public Result<Borrower> updatePassword(@RequestParam("borrowId") long borrowId,@RequestParam("old") String oldPassword,@RequestParam("new")String newPassword){
        return borrowerService.updatePassword(borrowId,oldPassword,newPassword);
    }

    @RequestMapping("/borrower/returnBook")
    @ResponseBody
    public Result<Borrowing> returnBook(@RequestParam("borrowId") long borrowId,@RequestParam("bookId") long bookId){
        return borrowerService.returnBook(borrowId,bookId);
    }

    @RequestMapping("/borrower/getBorrowBooks")
    @ResponseBody
    public Result<List<Book>> getBorrowBooks(@RequestParam("borrowId") long borrowId){
        return borrowerService.getBorrowBooks(borrowId);
    }

    @RequestMapping(path = {"/getAllBorrowers","/admin/getAllBorrowers"})
    @ResponseBody
    public Result<List<Borrower>> getAllBorrowers(Model model){
        return adminService.getAllBorrower();
    }

    @RequestMapping("/borrowerLogin")
    @ResponseBody
    public Result<Borrower> blogin(@RequestParam("borrowId") long borrowId,@RequestParam("password") String password,Model model, HttpServletRequest request){
        Result<Borrower> result=borrowerService.login(borrowId,password);
        if(result.isSuccess()){
            model.addAttribute("borrower", result.getData());
            request.getSession().setAttribute("borrower", result.getData());
        }
        return result;
    }

    @RequestMapping("borrowerLoginOut")
    @ResponseBody
    public String loginOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("borrower",null);
        return null;
    }

    @RequestMapping("/admin/getBorrower")
    @ResponseBody
    public Result<Borrower> AgetBorrower(@RequestParam("borrowId") long borrowId){
        Result<Borrower> result=new Result<>();
        Borrower borrower=borrowerDAO.getBorrower(borrowId);
        if(borrower!=null){
            result.setCode("200");
            result.setData(borrower);
            result.setSuccess(true);
            return result;
        }
        result.setMessage("500");
        return result;
    }
}
