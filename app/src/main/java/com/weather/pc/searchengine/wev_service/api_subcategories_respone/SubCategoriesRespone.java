package com.weather.pc.searchengine.wev_service.api_subcategories_respone;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SubCategoriesRespone {

    @SerializedName("data")
    private List<Subcategories> subcategories;
    @SerializedName("msg")
    private String msg;
    @SerializedName("status")
    private boolean status;
    @SerializedName("code")
    private String code;

    public List<Subcategories> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<Subcategories> subcategories) {
        this.subcategories = subcategories;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
