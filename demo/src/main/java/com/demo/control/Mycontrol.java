package com.demo.control;

import com.demo.dao.PersonDAO;
import com.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class Mycontrol {

    @Autowired
    PersonDAO personDAO;

    @RequestMapping("/show")
    public String show(Model model){
        Person person=new Person();
        model.addAttribute("person",person);
        return "add.html";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request, HttpServletResponse response, Model model,@Valid Person person, BindingResult errors) throws IOException {
        if (errors.hasErrors()) {
            return "add.html";
        }
        Person person1=new Person();
        long id=Long.parseLong(request.getParameter("id"));
        person1.setId(id);
        person1.setName(request.getParameter("name"));
        person1.setTel(request.getParameter("tel"));
        person1.setAddress(request.getParameter("address"));
        personDAO.insert(person1);
        response.setContentType("text/html;charset=UTF-8");
        System.out.println(person1.toString());
        response.getWriter().print(person1.toStringWeb());
        return null;
    }
}
