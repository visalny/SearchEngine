package com.weather.pc.searchengine;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.weather.pc.searchengine.adapter.MaincategoryAdapter;
import com.weather.pc.searchengine.wev_service.api_maincategories_respone.Maincategories;
import com.weather.pc.searchengine.wev_service.api_maincategories_respone.MaincategoriesRespone;
import com.weather.pc.searchengine.wev_service.web_service_retrofit.SearchEngine;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ooo";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigationview)
    NavigationView navigationView;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private Retrofit retrofit;
    private SearchEngine searchEngine;
    public static final String BASE_URL="http://110.74.194.125:15000/api/v1/";
    private MaincategoryAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//      ........set up Navigation button on Toolbar
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(
                this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
//      ........end set up Navigation button on Toolbar

        //Setup Retrofit
        setUpRetrofit();
        //set Layout Manager Recyclerview
        setupRecyclerview();
        //Load main categories from web service
        LoadMaincategories();


    }

    private void setupRecyclerview() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new MaincategoryAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void LoadMaincategories() {
        Call<MaincategoriesRespone> call=searchEngine.getAllMaincategories();
        call.enqueue(new Callback<MaincategoriesRespone>() {
            @Override
            public void onResponse(Call<MaincategoriesRespone> call, Response<MaincategoriesRespone> response) {
                Log.e(TAG, "onResponse: success" );
                if(response.body()!=null){
                    List<Maincategories> maincategoriesList=response.body().getMaincategories();
                    adapter.AddMaincategory(maincategoriesList);
                }
            }

            @Override
            public void onFailure(Call<MaincategoriesRespone> call, Throwable t) {
                Log.e(TAG, "onFailure: Error" );
            }
        });
    }

    private void setUpRetrofit() {
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        searchEngine=retrofit.create(SearchEngine.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);

        MenuItem item = menu.findItem(R.id.itm_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        return true;
    }
}
