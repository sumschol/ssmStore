package com.sumschol.controller;


import com.sumschol.pojo.Admin;
import com.sumschol.pojo.ProductType;
import com.sumschol.service.AdminService;
import com.sumschol.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    ProductTypeService productTypeService;

    @RequestMapping("/")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(String name, String pwd, HttpServletRequest req, HttpSession session) {
        Admin admin = adminService.login(name, pwd);
        if (admin != null) {
            List<ProductType> productTypeList = productTypeService.getall();
            req.setAttribute("admin", admin);
            session.setAttribute("typeList",productTypeList);
            return "main";
        } else {
            req.setAttribute("errmsg", "用户名或密码错误");
            return "login";
        }
    }

    @RequestMapping("/registerpage")
//    int aid, String aName, String aPass
    public String registerPage() {
        return "regist";
    }

    @RequestMapping("/register")
    public String regist(HttpServletRequest req) {
        Admin admin = new Admin(null, req.getParameter("aname"), req.getParameter("apwd"));
        adminService.register(admin);
        return "login";
    }
}
