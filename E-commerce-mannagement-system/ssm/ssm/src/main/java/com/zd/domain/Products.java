package com.zd.domain;

import com.zd.dao.ProductsDao;
import org.springframework.beans.factory.annotation.Autowired;

public class Products {
    private String productId;

    private String title;

    private String commodityId;

    private String tabId;

    private Float originPrice;

    private Float price;

    private Integer saleNum;

    private String saleNumStr;

    private String url;

    private String image;

    private Integer isFreePostage;

    private Integer state;

    public String getSaleNumStr() {

        return saleNum+"";
    }

    public void setSaleNumStr(String saleNumStr) {
        this.saleNumStr = saleNumStr;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId == null ? null : commodityId.trim();
    }

    public String getTabId() {
        return tabId;
    }

    public void setTabId(String tabId) {
        this.tabId = tabId == null ? null : tabId.trim();
    }

    public Float getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(Float originPrice) {
        this.originPrice = originPrice;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Integer getIsFreePostage() {
        return isFreePostage;
    }

    public void setIsFreePostage(Integer isFreePostage) {
        this.isFreePostage = isFreePostage;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}