package com.sumschol.mapper;

import com.sumschol.pojo.ProductInfo;
import com.sumschol.pojo.ProductInfoExample;
import java.util.List;

import com.sumschol.pojo.vo.ProductInfoVO;
import org.apache.ibatis.annotations.Param;

public interface ProductInfoMapper {
    int countByExample(ProductInfoExample example);

    int deleteByExample(ProductInfoExample example);

    int deleteByPrimaryKey(Integer pId);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    List<ProductInfo> selectByExample(ProductInfoExample example);

    ProductInfo selectByPrimaryKey(Integer pId);

    int updateByExampleSelective(@Param("record") ProductInfo record, @Param("example") ProductInfoExample example);

    int updateByExample(@Param("record") ProductInfo record, @Param("example") ProductInfoExample example);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);

    int deleteBatch(String[] ids);

    List<ProductInfo> selectCondition(ProductInfoVO productInfoVO);
}