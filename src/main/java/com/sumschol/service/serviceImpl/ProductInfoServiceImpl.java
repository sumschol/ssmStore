package com.sumschol.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sumschol.mapper.ProductInfoMapper;
import com.sumschol.pojo.ProductInfo;
import com.sumschol.pojo.ProductInfoExample;
import com.sumschol.pojo.vo.ProductInfoVO;
import com.sumschol.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> getAll() {
        return productInfoMapper.selectByExample(new ProductInfoExample());
    }

    @Override
    public PageInfo splitPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        ProductInfoExample productInfoExample = new ProductInfoExample();
        productInfoExample.setOrderByClause("p_id desc");
        List<ProductInfo> productInfoList = productInfoMapper.selectByExample(productInfoExample);
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(productInfoList);
        return pageInfo;
    }

    @Override
    public PageInfo splitPageCondition(int pageNum, int pageSize, ProductInfoVO productInfoVO) {
        PageHelper.startPage(pageNum, pageSize);
//        ProductInfoExample productInfoExample = new ProductInfoExample();
//        productInfoExample.setOrderByClause("p_id desc");
//        List<ProductInfo> productInfoList = productInfoMapper.selectByExample(productInfoExample);
        List<ProductInfo> productInfoList = productInfoMapper.selectCondition(productInfoVO);
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(productInfoList);
        return pageInfo;
    }

    @Override
    public ProductInfo getByPId(Integer pId) {
        ProductInfoExample productInfoExample = new ProductInfoExample();
        productInfoExample.createCriteria().andPIdEqualTo(pId);
        List<ProductInfo> productInfos = productInfoMapper.selectByExample(productInfoExample);
        if (!productInfos.isEmpty()) {
            return productInfos.remove(0);
        }
        return null;
    }

    @Override
    public int update(ProductInfo productInfo) {
        ProductInfoExample productInfoExample = new ProductInfoExample();
        productInfoExample.createCriteria().andPIdEqualTo(productInfo.getpId());
        return productInfoMapper.updateByExample(productInfo, productInfoExample);
    }

    @Override
    public int del(Integer pId) {
        return productInfoMapper.deleteByPrimaryKey(pId);
    }

    @Override
    public int dels(String[] pIds) {
//        int res = 0;
//        for (Integer pId : pIds) {
//            res += productInfoMapper.deleteByPrimaryKey(pId);
//        }
//        return res;
        //上述需要执行多条sql语句 低效 改使用以下方法
        return productInfoMapper.deleteBatch(pIds);
    }

    @Override
    public List<ProductInfo> conditionGet(ProductInfoVO productInfoVO) {
        return productInfoMapper.selectCondition(productInfoVO);
    }
}
