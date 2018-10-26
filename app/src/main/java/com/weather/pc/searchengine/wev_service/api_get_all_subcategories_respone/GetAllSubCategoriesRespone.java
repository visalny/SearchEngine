package com.weather.pc.searchengine.wev_service.api_get_all_subcategories_respone;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class GetAllSubCategoriesRespone {

    @SerializedName("data")
    private List<Allsubcategories> allsubcategories;
    @SerializedName("msg")
    private String msg;
    @SerializedName("status")
    private boolean status;
    @SerializedName("code")
    private String code;

    public List<Allsubcategories> getAllsubcategories() {
        return allsubcategories;
    }

    public void setAllsubcategories(List<Allsubcategories> allsubcategories) {
        this.allsubcategories = allsubcategories;
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
