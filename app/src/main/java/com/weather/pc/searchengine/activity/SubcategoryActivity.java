package com.weather.pc.searchengine.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.weather.pc.searchengine.R;
import com.weather.pc.searchengine.adapter.SubcategoryAdapter;
import com.weather.pc.searchengine.callback.SubcategoryClickListener;
import com.weather.pc.searchengine.wev_service.api_subcategories_respone.SubCategoriesRespone;
import com.weather.pc.searchengine.wev_service.api_subcategories_respone.Subcategories;
import com.weather.pc.searchengine.wev_service.web_service_retrofit.SearchEngine;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubcategoryActivity extends AppCompatActivity implements SubcategoryClickListener {

    private static final String TAG ="ooo" ;
    @BindView(R.id.recyclerview_subcate)
    RecyclerView recyclerView;
    @BindView(R.id.imv_Keyback)
    ImageView imv_keyback;
    @BindView(R.id.tv_title_subcategory)
    TextView tv_title;
    @BindView(R.id.progressbar_subcat)
    FrameLayout progressbar;
    private SubcategoryAdapter adapter;
    private Retrofit retrofit;
    private SearchEngine searchEngine;
    public static final String BASE_URL="http://110.74.194.125:15000/api/v1/";
    private int position;
    private String catname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);
        ButterKnife.bind(this);
        //set up retrofit
        setUpRetrofit();

        Intent intent=getIntent();
        position=intent.getIntExtra("position",0);
        catname=intent.getStringExtra("catname");
        tv_title.setText(catname);
        //Load sub categories from web service
        LoadSubcategories(position);
        //set up Layout manager Recyclerview
        setupLayoumanager();
        //go back to MainActity
        imv_keyback.setOnClickListener(v->{
            finish();
        });

    }

    private void LoadSubcategories(int position) {
        Call<SubCategoriesRespone> call=searchEngine.getSubcateByMaincateID(position);
        call.enqueue(new Callback<SubCategoriesRespone>() {
            @Override
            public void onResponse(Call<SubCategoriesRespone> call, Response<SubCategoriesRespone> response) {
                //Log.e(TAG, "onResponse: success"+response.body().getSubcategories() );

                List<Subcategories> subcategoriesList=response.body().getSubcategories();
                if(!(subcategoriesList.isEmpty())){
                    Log.e(TAG, "onResponse: yyy" );
                    adapter.AddSubcategories(subcategoriesList);
                    Hideprogressbar();
                }else {
                    Hideprogressbar();
                    Alertdialog();
                    //Toast.makeText(SubcategoryActivity.this, "No Data to Display", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<SubCategoriesRespone> call, Throwable t) {
                Log.e(TAG, "onFailure: " );
            }
        });
    }
    //alert dialog message
    private void Alertdialog() {
       new SweetAlertDialog(this,SweetAlertDialog.WARNING_TYPE)
               .setTitleText("No Data to Display")
               .setConfirmButton("OK", new SweetAlertDialog.OnSweetClickListener() {
                   @Override
                   public void onClick(SweetAlertDialog sweetAlertDialog) {
                       finish();
                   }
               })
               .show();
    }

    private void setUpRetrofit() {
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        searchEngine=retrofit.create(SearchEngine.class);
    }

    private void setupLayoumanager() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new SubcategoryAdapter(this,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void ItemClick(int position,String subcat) {
        Intent intent=new Intent(SubcategoryActivity.this,SearchurlActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("id",position);
        intent.putExtra("subcat",subcat);
        startActivity(intent);
    }

    private void Hideprogressbar(){
        progressbar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }



}

