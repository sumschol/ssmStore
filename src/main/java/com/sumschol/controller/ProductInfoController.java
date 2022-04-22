package com.sumschol.controller;


import com.github.pagehelper.PageInfo;
import com.sumschol.pojo.ProductInfo;
import com.sumschol.service.ProductInfoService;
import com.sumschol.utils.PageInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductInfoController {

    public static final int PAGE_SIZE = 5;
    @Autowired
    ProductInfoService productInfoService;

    @RequestMapping("/getall")
    public String getAll(HttpServletRequest req) {
        List<ProductInfo> productInfoList = productInfoService.getAll();
        req.getSession().setAttribute("list", productInfoList);
        return "product";
    }

    //显示第一页数据
    @RequestMapping("/split")
    public String split(HttpServletRequest req) {
        PageInfo pageInfo = productInfoService.splitPage(1, PAGE_SIZE);
        req.setAttribute("info", pageInfo);
        req.setAttribute("pagesList", PageInfoUtil.getPagesList(pageInfo));
        return "product";
    }

    @ResponseBody
    @RequestMapping("/ajaxsplit")
    public ModelAndView ajaxSplit(int page) {
        PageInfo pageInfo = productInfoService.splitPage(page, PAGE_SIZE);
//        session.setAttribute("info", pageInfo);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product_table");
        modelAndView.addObject("info",pageInfo);
        modelAndView.addObject("pagesList",PageInfoUtil.getPagesList(pageInfo));
        return modelAndView;
    }

}