package com.weather.pc.searchengine.wev_service.api_maincategories_respone;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MaincategoriesRespone {

    @SerializedName("data")
    private List<Maincategories> maincategories;
    @SerializedName("msg")
    private String msg;
    @SerializedName("status")
    private boolean status;
    @SerializedName("code")
    private String code;

    public List<Maincategories> getMaincategories() {
        return maincategories;
    }

    public void setMaincategories(List<Maincategories> maincategories) {
        this.maincategories = maincategories;
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
