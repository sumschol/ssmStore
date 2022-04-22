package com.sumschol.controller;

import com.sumschol.pojo.ProductInfo;
import com.sumschol.pojo.ProductType;
import com.sumschol.service.ProductInfoAddService;
import com.sumschol.service.ProductInfoService;
import com.sumschol.service.ProductTypeService;
import com.sumschol.utils.FileNameUtil;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/add")
public class ProductInfoAddController {

    @Autowired
    ProductTypeService productTypeService;

    @Autowired
    ProductInfoAddService productInfoAddService;

    String saveFileName = "";

//    @RequestMapping("")
//    public ModelAndView addPage() {
//        List<ProductType> productTypeList = productTypeService.getall();
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("typeList", productTypeList);
//        modelAndView.setViewName("addproduct");
//        return modelAndView;
//    }

    @RequestMapping("")
    public String addPage() {
        return "addproduct";
    }

    @ResponseBody
    @RequestMapping("/ajaxImg")
    public Object ajaxImg(MultipartFile pImage, HttpServletRequest req) {
        String UUID = FileNameUtil.getUUIDFileName();
        String UUIDPath = UUID + FileNameUtil.getFileType(pImage.getOriginalFilename());
        String path = req.getSession().getServletContext().getRealPath("image_big");
        try {
//            pImage.transferTo(new File(path + File.separator + UUIDPath));
            FileUtils.copyInputStreamToFile(pImage.getInputStream(), new File(path + File.separator + UUIDPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveFileName = UUIDPath;
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("imgurl", UUIDPath);
        return jsonObj.toString();
    }

    @RequestMapping("/submitadd")
    public String submintAdd(ProductInfo productInfo, HttpServletRequest req) {
        productInfo.setpImage(saveFileName);
        saveFileName = "";
        productInfo.setpDate(new Date());
        int res = productInfoAddService.addProductInfo(productInfo);
        if (res > 0) {
            req.setAttribute("addmsg", "添加成功");
        }
        return "redirect:/product/getall";
    }
}
