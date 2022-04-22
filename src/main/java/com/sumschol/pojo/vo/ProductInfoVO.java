package com.sumschol.pojo.vo;

import java.io.Serializable;

public class ProductInfoVO implements Serializable {
    //商品名称
    private String pName;
    //商品类型
    private Integer typeId;
    //最低价格
    private Double lPrice;
    //最高价格
    private Double hPrice;

    @Override
    public String toString() {
        return "ProductInfoVO{" +
                "pName='" + pName + '\'' +
                ", typeId=" + typeId +
                ", lPrice=" + lPrice +
                ", hPrice=" + hPrice +
                '}';
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Double getlPrice() {
        return lPrice;
    }

    public void setlPrice(Double lPrice) {
        this.lPrice = lPrice;
    }

    public Double gethPrice() {
        return hPrice;
    }

    public void sethPrice(Double hPrice) {
        this.hPrice = hPrice;
    }

    public ProductInfoVO(String pName, Integer typeId, Double lPrice, Double hPrice) {
        this.pName = pName;
        this.typeId = typeId;
        this.lPrice = lPrice;
        this.hPrice = hPrice;
    }

    public ProductInfoVO() {
    }
}
