package com.weather.pc.searchengine.wev_service.api_search_url_respone;

import com.google.gson.annotations.SerializedName;

public class Pagination {
    @SerializedName("total_page")
    private int totalPage;
    @SerializedName("total_record")
    private int totalRecord;
    @SerializedName("current_page")
    private int currentPage;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
