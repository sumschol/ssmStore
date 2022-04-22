package com.sumschol.controller;

import com.github.pagehelper.PageInfo;
import com.sumschol.pojo.ProductInfo;
import com.sumschol.pojo.vo.ProductInfoVO;
import com.sumschol.service.ProductInfoService;
import com.sumschol.utils.PageInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.sumschol.controller.ProductInfoController.PAGE_SIZE;

@Controller
@RequestMapping("/query")
public class ProductInfoQueryController {

    @Autowired
    ProductInfoService productInfoService;

    @RequestMapping("/condition")
    public ModelAndView conditionQuery(ProductInfoVO productInfoVO, Integer pageNum) {
        PageInfo pageInfo = productInfoService.splitPageCondition(pageNum, PAGE_SIZE, productInfoVO);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product_table");
        modelAndView.addObject("info", pageInfo);
        modelAndView.addObject("pagesList", PageInfoUtil.getPagesList(pageInfo));
        modelAndView.addObject("queryFlag", 1);
        return modelAndView;
    }
}