package com.sumschol.service.serviceImpl;

import com.sumschol.mapper.ProductInfoMapper;
import com.sumschol.pojo.ProductInfo;
import com.sumschol.service.ProductInfoAddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Date;

@Service
public class ProductInfoAddServiceImpl implements ProductInfoAddService {

    @Autowired
    ProductInfoMapper productInfoMapper;



    @Override
    public int addProductInfo(ProductInfo productInfo) {
        return productInfoMapper.insert(productInfo);
    }

//    @Override
//    public int addProductInfo(String pName, String pContent, Integer pPrice, Integer pNumber) {
//        ProductInfo productInfo = new ProductInfo();
//        productInfo.setpName(pName);
//        productInfo.setpContent(pContent);
//        productInfo.setpPrice(pPrice);
//        productInfo.setpNumber(pNumber);
//        productInfo.setpImage();
//        return productInfoMapper.insert(productInfo);
//    }

}
