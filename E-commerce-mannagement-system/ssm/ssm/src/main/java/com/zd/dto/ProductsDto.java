package com.zd.dto;

import com.zd.domain.Products;

public class ProductsDto extends Products{
    private String tabName;

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }
}
