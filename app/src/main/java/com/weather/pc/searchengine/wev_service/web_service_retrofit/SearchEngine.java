package com.weather.pc.searchengine.wev_service.web_service_retrofit;

import com.weather.pc.searchengine.wev_service.api_maincategories_respone.MaincategoriesRespone;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SearchEngine {

    //Get all Main categories from web service
    @GET("categories")
    Call<MaincategoriesRespone> getAllMaincategories();
}
