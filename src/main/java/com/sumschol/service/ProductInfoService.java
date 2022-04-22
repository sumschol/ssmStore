package com.sumschol.service;

import com.github.pagehelper.PageInfo;
import com.sumschol.pojo.ProductInfo;
import com.sumschol.pojo.vo.ProductInfoVO;

import java.util.List;

public interface ProductInfoService {

    //查询所有商品不分页
    List<ProductInfo> getAll();

    //分页功能
    //(当前页，每页取几条)
    PageInfo splitPage(int pageNum, int pageSize);

    PageInfo splitPageCondition(int pageNum, int pageSize,ProductInfoVO productInfoVO);

    ProductInfo getByPId(Integer pId);

    int update(ProductInfo productInfo);

    int del(Integer pId);

    int dels(String[] pIds);

    List<ProductInfo> conditionGet(ProductInfoVO productInfoVO);
}
