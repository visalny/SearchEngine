package com.weather.pc.searchengine.wev_service.api_search_url_respone;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchUrlRespone {

    @SerializedName("pagination")
    private Pagination pagination;
    @SerializedName("data")
    private List<SearchUrl> searchurl;
    @SerializedName("msg")
    private String msg;
    @SerializedName("status")
    private boolean status;
    @SerializedName("code")
    private String code;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<SearchUrl> getSearchurl() {
        return searchurl;
    }

    public void setSearchurl(List<SearchUrl> searchurl) {
        this.searchurl = searchurl;
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
