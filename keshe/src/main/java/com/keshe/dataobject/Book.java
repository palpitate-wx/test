package com.keshe.dataobject;

import lombok.Data;

@Data
public class Book {
    private long bookId;
    private String bookName;
    private String publisher;
    private String author;
    private String introduction;
    private double price;
    private int amount;


}
