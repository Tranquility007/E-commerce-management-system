package com.zd.domain;

import java.util.Date;

public class Shops {
    private String shopId;

    private Float goodRatePercentage;

    private String serviceScore;

    private String deliveryScore;

    private String title;

    private Integer allItemCount;

    private Integer fansNum;

    private String picUrl;

    private Integer state;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public Float getGoodRatePercentage() {
        return goodRatePercentage;
    }

    public void setGoodRatePercentage(Float goodRatePercentage) {
        this.goodRatePercentage = goodRatePercentage;
    }

    public String getServiceScore() {
        return serviceScore;
    }

    public void setServiceScore(String serviceScore) {
        this.serviceScore = serviceScore;
    }

    public String getDeliveryScore() {
        return deliveryScore;
    }

    public void setDeliveryScore(String deliveryScore) {
        this.deliveryScore = deliveryScore;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAllItemCount() {
        return allItemCount;
    }

    public void setAllItemCount(Integer allItemCount) {
        this.allItemCount = allItemCount;
    }

    public Integer getFansNum() {
        return fansNum;
    }

    public void setFansNum(Integer fansNum) {
        this.fansNum = fansNum;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }



}