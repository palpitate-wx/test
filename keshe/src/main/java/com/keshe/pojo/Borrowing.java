package com.keshe.pojo;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class Borrowing {
    private long borrowId;
    private String name;
    private long bookId;
    private String bookName;
    private LocalDateTime borrowTime;
    private LocalDateTime returnTime;
    private boolean delay;

    public boolean getDelay(){
        LocalDateTime ontime=this.borrowTime.plusDays(10);
        if(LocalDateTime.now().isBefore(ontime)){
            return false;
        }else {
            return true;
        }
    }
}
