package com.weather.pc.searchengine.wev_service.api_subcategories_respone;

import com.google.gson.annotations.SerializedName;

public class Subcategories {
    @SerializedName("total_url")
    private int totalUrl;
    @SerializedName("des")
    private String des;
    @SerializedName("cate_name")
    private String cateName;
    @SerializedName("status")
    private boolean status;
    @SerializedName("id")
    private int id;

    public int getTotalUrl() {
        return totalUrl;
    }

    public void setTotalUrl(int totalUrl) {
        this.totalUrl = totalUrl;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
