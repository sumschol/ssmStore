package com.sumschol.service.serviceImpl;

import com.sumschol.mapper.ProductTypeMapper;
import com.sumschol.pojo.ProductType;
import com.sumschol.pojo.ProductTypeExample;
import com.sumschol.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProductTypeServiceImpl")
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    ProductTypeMapper productTypeMapper;

    @Override
    public List<ProductType> getall() {
        return productTypeMapper.selectByExample(new ProductTypeExample());
    }
}
