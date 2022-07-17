package com.keshe.dao;

import com.keshe.dataobject.Borrower;
import com.keshe.pojo.Borrowing;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BorrowerDAO {

    /**
     * 增加借阅者
     * @param borrower
     * @return
     */
    int addBorrower(Borrower borrower);

    /**
     * 根据借阅证号删除借阅者
     * @param borrowId
     * @return
     */
    int deleteBorrower(@Param("borrowId") long borrowId);

    /**
     * 根据借阅证号查询借阅者
     * @param borrowId
     * @return
     */
    Borrower getBorrower(@Param("borrowId") long borrowId);

    /**
     * 查询全部借阅者信息
     * @return
     */
    List<Borrower> getAllBorrower();

    /**
     * 查询某个借阅者的借阅信息
     * @param borrowId
     * @return
     */
    List<Borrowing> getAllBorrowing(@Param("borrowId") long borrowId);

    /**
     * 借阅书籍
     * @param borrowId
     * @param bookId
     * @return
     */
    int borrowBook(@Param("borrowId") long borrowId,@Param("bookId") long bookId);

    /**
     * 更新借阅者的借阅数量
     * @param number
     * @param borrowId
     * @return
     */
    int updateBorrowNumber(@Param("borrowId") long borrowId,@Param("number") int number);


    /**
     * 更新书籍数量
     * @param bookId
     * @param amount
     * @return
     */
    int updateBookAmount(@Param("bookId") long bookId,@Param("amount") int amount);


    /**
     * 借阅者更改密码
     * @param borrowId
     * @param newPassword
     * @return
     */
    int updatePassword(@Param("borrowId") long borrowId,@Param("newPassword") String newPassword);


    /**
     * 还书
     * @param borrowId
     * @param bookId
     * @return
     */
    int returnBook(@Param("borrowId") long borrowId,@Param("bookId") long bookId);


    /**
     * 查找借书记录
     * @param borrowId
     * @param bookId
     * @return
     */
    Borrowing getBorrowing(@Param("borrowId") long borrowId,@Param("bookId") long bookId);

    /**
     * 逾期更新
     * @param borrowing
     * @return
     */
    int borrowingDelay(Borrowing borrowing);
}
