package com.zd.domain;

public class Tabs {
    private Integer tabId;

    private String name;

    private Integer state;

    public Integer getTabId() {
        return tabId;
    }

    public void setTabId(Integer tabId) {
        this.tabId = tabId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}