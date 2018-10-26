package com.weather.pc.searchengine.wev_service.web_service_retrofit;

import com.weather.pc.searchengine.wev_service.api_get_all_subcategories_respone.GetAllSubCategoriesRespone;
import com.weather.pc.searchengine.wev_service.api_maincategories_respone.MaincategoriesRespone;
import com.weather.pc.searchengine.wev_service.api_search_url_respone.SearchUrlRespone;
import com.weather.pc.searchengine.wev_service.api_subcategories_respone.SubCategoriesRespone;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SearchEngine {

    //Get all Main categories from web service
    @GET("categories")
    Call<MaincategoriesRespone> getAllMaincategories();
    //Get sub categories from web service by main categories Id
    @GET("categories/sub-by-main-id/{id}")
    Call<SubCategoriesRespone> getSubcateByMaincateID(@Path("id") int id);
    //Get search url from web service by cate_id
    @GET("search")
    Call<SearchUrlRespone> getSearchurlBycateID(@Query ("page") String page, @Query("cate_id") int id);

    //Get All sub categories from web service
    @GET("categories/sub")
    Call<GetAllSubCategoriesRespone> getAllSubcategories();


}
