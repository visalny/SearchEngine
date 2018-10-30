package com.weather.pc.searchengine;

import android.app.Application;
import android.content.Intent;
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
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;
import com.weather.pc.searchengine.activity.SearchurlActivity;
import com.weather.pc.searchengine.activity.SubcategoryActivity;
import com.weather.pc.searchengine.adapter.ItemListAdapter;
import com.weather.pc.searchengine.adapter.MaincategoryAdapter;
import com.weather.pc.searchengine.adapter.RecylcerProfileAdapter;
import com.weather.pc.searchengine.callback.ItemListClickListener;
import com.weather.pc.searchengine.callback.MaincategoryClickListener;
import com.weather.pc.searchengine.wev_service.api_get_all_subcategories_respone.Allsubcategories;
import com.weather.pc.searchengine.wev_service.api_get_all_subcategories_respone.GetAllSubCategoriesRespone;
import com.weather.pc.searchengine.wev_service.api_maincategories_respone.Maincategories;
import com.weather.pc.searchengine.wev_service.api_maincategories_respone.MaincategoriesRespone;
import com.weather.pc.searchengine.wev_service.web_service_retrofit.SearchEngine;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements MaincategoryClickListener,ItemListClickListener {

    private static final String TAG = "ooo";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.progressbar_main)
    FrameLayout progressbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.recyclerview_profile)
    RecyclerView recycler_profile;

    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.imv_profile)
    CircleImageView imv_profile;
    @BindView(R.id.item_list)
    RecyclerView item_list;
    private Retrofit retrofit;
    private SearchEngine searchEngine;
    public static final String BASE_URL="http://110.74.194.125:15000/api/v1/";
    private MaincategoryAdapter adapter;
    private List<Maincategories> maincategoriesList;
    private List<Allsubcategories> allsubcategoriesList;
    private ItemListAdapter itemListAdapter;


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
//   Show User Login
         showUserlogin();

 //  Setup Retrofit
        setUpRetrofit();
//    set Layout Manager Recyclerview
        setupRecyclerview();
//    Load main categories from web service
        LoadMaincategories();
        //   get all sub categories
        getAllsubcategories();



//    Recycler view Profile

        RecylcerProfileAdapter profileAdapter=new RecylcerProfileAdapter();
        recycler_profile.setLayoutManager(new LinearLayoutManager(this));
        recycler_profile.setAdapter(profileAdapter);

    }

    private void showUserlogin() {
        Intent intent=getIntent();
        tv_name.setText(intent.getStringExtra("username"));
        Picasso.get()
                .load(intent.getStringExtra("userprofile"))
                .into(imv_profile);

    }

    private void setupRecyclerview() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new MaincategoryAdapter(this,this);
        recyclerView.setAdapter(adapter);
    }
    private void getAllsubcategories(){
        Call<GetAllSubCategoriesRespone> call=searchEngine.getAllSubcategories();
        call.enqueue(new Callback<GetAllSubCategoriesRespone>() {
            @Override
            public void onResponse(Call<GetAllSubCategoriesRespone> call, Response<GetAllSubCategoriesRespone> response) {
                Log.e(TAG, "onResponse: "+response.body().getAllsubcategories() );
                allsubcategoriesList=response.body().getAllsubcategories();
                item_list.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                itemListAdapter=new ItemListAdapter(MainActivity.this,MainActivity.this);
                itemListAdapter.Setdata(allsubcategoriesList);
                item_list.setAdapter(itemListAdapter);

                Hideprogressbar();
            }

            @Override
            public void onFailure(Call<GetAllSubCategoriesRespone> call, Throwable t) {

            }
        });
    }

    private void LoadMaincategories() {
        Call<MaincategoriesRespone> call=searchEngine.getAllMaincategories();
        call.enqueue(new Callback<MaincategoriesRespone>() {
            @Override
            public void onResponse(Call<MaincategoriesRespone> call, Response<MaincategoriesRespone> response) {
                Log.e(TAG, "onResponse: success" );
                if(response.body()!=null){
                    maincategoriesList=response.body().getMaincategories();
                    adapter.AddMaincategory(maincategoriesList);
                }
                Hideprogressbar();
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
                //recyclerView.setVisibility(View.GONE);

                List<Allsubcategories> allsubcate=new ArrayList<>();
                recyclerView.setVisibility(View.GONE);
                itemListAdapter.clearData();
                for(Allsubcategories allsub:allsubcategoriesList){
                    if(allsub.getCateName().equals(newText)||allsub.getCateName().toLowerCase().contains(newText.toLowerCase())){

                        //Toast.makeText(MainActivity.this, ""+newText, Toast.LENGTH_SHORT).show();
                        allsubcate.add(allsub);
                        item_list.setVisibility(View.VISIBLE);
                    }
                }
                //item_list.setVisibility(View.VISIBLE);
                itemListAdapter.Setdata(allsubcate);
                return true;
            }
        });
        return true;
    }


    @Override
    public void ItemClick(int position,String catname) {
        Intent intent=new Intent(this,SubcategoryActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("position",position);
        intent.putExtra("catname",catname);
        startActivity(intent);
    }


    private void Hideprogressbar(){
        progressbar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        //item_list.setVisibility(View.VISIBLE);
    }

    @Override
    public void ListItemClick(int position, String subcate) {
        Intent intent=new Intent(MainActivity.this,SearchurlActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("id",position);
        intent.putExtra("subcat",subcate);
        startActivity(intent);
    }

}
