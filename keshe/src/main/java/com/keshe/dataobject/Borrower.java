package com.keshe.dataobject;

import lombok.Data;

@Data
public class Borrower {
    private long borrowId;
    private String name;
    private String password;
    private String sex;
    private String academy;
    private String major;
    private int number;
}
