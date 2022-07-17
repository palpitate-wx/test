package com.keshe.service.impl;

import com.keshe.dao.BookDAO;
import com.keshe.dao.BorrowerDAO;
import com.keshe.dataobject.Book;
import com.keshe.dataobject.Borrower;
import com.keshe.pojo.Borrowing;
import com.keshe.pojo.Result;
import com.keshe.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BorrowerServiceImpl implements BorrowerService {

    @Autowired
    private BorrowerDAO borrowerDAO;

    @Autowired
    private BookDAO bookDAO;

    @Override
    public Result<Borrower> login(long borrowId, String password) {
        Result<Borrower> result=new Result<>();
        Borrower borrower=new Borrower();
        borrower=borrowerDAO.getBorrower(borrowId);
        if(borrower==null){
            result.setCode("501");
            result.setMessage("登录失败,没找到该借阅者信息");
            return result;
        }
        if(!borrower.getPassword().equals(password)){
            result.setData(borrower);
            result.setCode("502");
            result.setMessage("登录失败，密码错误!!!");
            return result;
        }
        result.setSuccess(true);
        result.setData(borrower);
        result.setCode("200");
        result.setMessage("登录成功");
        return result;
    }

    @Override
    public Result<Borrower> updatePassword(long borrowId,String oldPassword,String newPassword) {
        Result<Borrower> result=new Result<>();
        Borrower borrower=new Borrower();
        borrower=borrowerDAO.getBorrower(borrowId);
        if(!borrower.getPassword().equals(oldPassword)){
            result.setData(borrower);
            result.setCode("502");
            result.setMessage("修改失败，原密码输入错误!!!");
            return result;
        }
        borrowerDAO.updatePassword(borrower.getBorrowId(),newPassword);
        borrower=borrowerDAO.getBorrower(borrowId);
        result.setSuccess(true);
        result.setData(borrower);
        result.setCode("200");
        result.setMessage("密码修改成功");
        return result;
    }

    @Override
    public Result<Borrowing> borrowBook(long borrowId, long bookId) {
        Result<Borrowing> result=new Result<>();
        Borrower borrower=new Borrower();
        borrower=borrowerDAO.getBorrower(borrowId);
        Book book=new Book();
        book=bookDAO.getBook(bookId);
        //借阅者已借书数量
        int number=borrower.getNumber();
        //库存书的数量
        int amount=book.getAmount();

        //判断有无逾期数目
        boolean flag=true;
        List<Borrowing> borrowings=new ArrayList<>();
        borrowings=borrowerDAO.getAllBorrowing(borrowId);
        for(Borrowing borrowing:borrowings){
            if(borrowing.getDelay()){
                flag=false;
                borrowerDAO.borrowingDelay(borrowing);
            }
        }

        if(!flag){
            result.setCode("500");
            result.setMessage("借书失败，您有逾期的书目未还，不能借书");
            return result;
        }

        if(number==10){
            result.setCode("501");
            result.setMessage("借书失败，借书数量达到上限");
            return result;
        }
        if(amount==0){
            result.setCode("502");
            result.setMessage("借书失败，该书已经被借空");
            return result;
        }

        borrowerDAO.borrowBook(borrowId,bookId);
        //更新借书数量和库存数量

        borrowings=borrowerDAO.getAllBorrowing(borrowId);
        borrowerDAO.updateBorrowNumber(borrowId,borrowings.size());
        borrowerDAO.updateBookAmount(bookId,amount-1);

        Borrowing borrowing=borrowerDAO.getBorrowing(borrowId,bookId);
        result.setData(borrowing);
        result.setSuccess(true);
        result.setCode("200");
        result.setMessage("借书成功");
        return result;
    }

    @Override
    public Result<Borrowing> returnBook(long borrowId, long bookId) {
        Result<Borrowing> result=new Result<>();
        Borrower borrower=new Borrower();
        borrower=borrowerDAO.getBorrower(borrowId);
        Book book=new Book();
        book=bookDAO.getBook(bookId);
        //借阅者已借书数量
        int number=borrower.getNumber();
        //库存书的数量
        int amount=book.getAmount();

        borrowerDAO.returnBook(borrowId,bookId);
        //更新借书数量和库存数量
        List<Borrowing> borrowings=new ArrayList<>();
        borrowings=borrowerDAO.getAllBorrowing(borrowId);
        borrowerDAO.updateBorrowNumber(borrowId,borrowings.size());
        borrowerDAO.updateBookAmount(bookId,amount+1);

        Borrowing borrowing=borrowerDAO.getBorrowing(borrowId,bookId);
        result.setData(borrowing);
        result.setSuccess(true);
        result.setCode("200");
        result.setMessage("还书成功");
        return result;
    }

    @Override
    public Result<List<Book>> getBorrowBooks(long borrowId) {
        Result<List<Book>> result=new Result<>();
        List<Borrowing> borrowings=borrowerDAO.getAllBorrowing(borrowId);
        List<Book> books=new ArrayList<>();
        for(Borrowing borrowing:borrowings){
            Book book=new Book();
            book=bookDAO.getBook(borrowing.getBookId());
            books.add(book);
        }
        result.setSuccess(true);
        result.setData(books);
        result.setCode("200");
        result.setMessage("查询完成");
        return result;
    }
}
