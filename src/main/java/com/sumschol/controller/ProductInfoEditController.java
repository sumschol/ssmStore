package com.sumschol.controller;


import com.github.pagehelper.PageInfo;
import com.sumschol.pojo.ProductInfo;
import com.sumschol.pojo.ProductType;
import com.sumschol.service.ProductInfoService;
import com.sumschol.service.ProductTypeService;
import com.sumschol.utils.PageInfoUtil;
import org.apache.commons.io.LineIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.sumschol.controller.ProductInfoController.PAGE_SIZE;

@Controller
@RequestMapping("/edit")
public class ProductInfoEditController {

    @Autowired
    ProductInfoService productInfoService;

    @Autowired
    ProductTypeService productTypeService;

    @RequestMapping("")
    public ModelAndView editPage(Integer pId, Integer pageNum) {
        ProductInfo productInfo = productInfoService.getByPId(pId);
        List<ProductType> productTypeList = productTypeService.getall();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("prod", productInfo);
        modelAndView.addObject("page", pageNum);
        modelAndView.addObject("typeList", productTypeList);
        modelAndView.setViewName("update");
        return modelAndView;
    }

    @RequestMapping("/submitupdate")
    public String submitUpdate(ProductInfo productInfo) {
        productInfoService.update(productInfo);
        return "redirect:/product/getall";
    }

    @RequestMapping("/del")
    public ModelAndView del(Integer pId) {
        productInfoService.del(pId);
        PageInfo pageInfo = productInfoService.splitPage(1, PAGE_SIZE);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product_table");
        modelAndView.addObject("info", pageInfo);
        modelAndView.addObject("pagesList", PageInfoUtil.getPagesList(pageInfo));
        return modelAndView;
    }

    @RequestMapping("/dels")
    public ModelAndView dels(String pIdsStr) {
        String[] pIdStr = pIdsStr.split(",");
        //Integer[] ids
        int res = productInfoService.dels(pIdStr);
        PageInfo pageInfo = productInfoService.splitPage(1, PAGE_SIZE);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product_table");
        modelAndView.addObject("info", pageInfo);
        modelAndView.addObject("pagesList", PageInfoUtil.getPagesList(pageInfo));
//        if (res > 0) {
//            modelAndView.addObject("delsMsg", res + "条数据删除成功")
//        }else {
//            modelAndView.addObject("delsMsg","删除失败");
//        }
        return modelAndView;
    }
}