package com.weather.pc.searchengine.wev_service.api_maincategories_respone;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Maincategories {
    @SerializedName("sub_cate")
    private List<SubCate> subCate;
    @SerializedName("icon_name")
    private String iconName;
    @SerializedName("des")
    private String des;
    @SerializedName("cate_name")
    private String cateName;
    @SerializedName("status")
    private boolean status;
    @SerializedName("id")
    private int id;

    public List<SubCate> getSubCate() {
        return subCate;
    }

    public void setSubCate(List<SubCate> subCate) {
        this.subCate = subCate;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
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
