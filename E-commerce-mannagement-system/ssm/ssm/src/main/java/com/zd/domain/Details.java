package com.zd.domain;

import java.util.Date;

public class Details {
    private Integer id;

    private String productId;

    private String shopId;

    private String commodityId;

    private String couponValue;

    private String expireMsg;

    private Date expireDate;

    private String url;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId == null ? null : commodityId.trim();
    }

    public String getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(String couponValue) {
        this.couponValue = couponValue == null ? null : couponValue.trim();
    }

    public String getExpireMsg() {
        return expireMsg;
    }

    public void setExpireMsg(String expireMsg) {
        this.expireMsg = expireMsg == null ? null : expireMsg.trim();
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}