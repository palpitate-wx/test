package com.demo.dao;

import com.demo.model.Person;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonDAO {

    int insert(Person person);
}
