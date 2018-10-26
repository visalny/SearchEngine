package com.weather.pc.searchengine.wev_service.api_get_all_subcategories_respone;

import com.google.gson.annotations.SerializedName;

public class Allsubcategories {
    @SerializedName("des")
    private String des;
    @SerializedName("cate_name")
    private String cateName;
    @SerializedName("status")
    private boolean status;
    @SerializedName("id")
    private int id;

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
