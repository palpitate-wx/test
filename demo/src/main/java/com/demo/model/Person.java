package com.demo.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Person {
    @NotNull(message = "编号不能为 null")
    private long id;

    @NotEmpty(message = "姓名不能为 null")
    private String name;

    @Length(min=11,max = 11,message = "请输入正确的电话号码")
    private String tel;

    @NotEmpty(message = "地址必须输入")
    private String address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String toString(){
        return "编号:"+this.getId()+"\n姓名:"+this.getName()+"\n电话"+this.getTel()+"\n地址:"+this.getAddress();
    }

    public String toStringWeb(){
        return "编号:"+this.getId()+"<br>姓名:"+this.getName()+"<br>电话"+this.getTel()+"<br>地址:"+this.getAddress();
    }
}
