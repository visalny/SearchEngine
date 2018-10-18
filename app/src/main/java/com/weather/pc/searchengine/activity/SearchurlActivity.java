package com.weather.pc.searchengine.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.weather.pc.searchengine.R;
import com.weather.pc.searchengine.adapter.SearchUrlAdapter;
import com.weather.pc.searchengine.callback.SearchurlClickListener;
import com.weather.pc.searchengine.wev_service.api_search_url_respone.SearchUrl;
import com.weather.pc.searchengine.wev_service.api_search_url_respone.SearchUrlRespone;
import com.weather.pc.searchengine.wev_service.web_service_retrofit.SearchEngine;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchurlActivity extends AppCompatActivity implements SearchurlClickListener {

    private static final String TAG ="ooo" ;
    @BindView(R.id.search_url)
    RecyclerView recyclerView;
    @BindView(R.id.imv_Keyback_searchurl)
    ImageView imv_keyback;
    @BindView(R.id.tv_title_searchurl)
    TextView tv_title;
    private Retrofit retrofit;
    private SearchEngine searchEngine;
    public static final String BASE_URL="http://110.74.194.125:15000/api/v1/";
    private int position;
    private SearchUrlAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchurl);
        ButterKnife.bind(this);
        //set up retrofit
        setUpRetrofit();

        Intent intent=getIntent();
        position=intent.getIntExtra("id",0);
        tv_title.setText(intent.getStringExtra("subcat"));

        //Load Search url
        LoadSearchurl(position);

        //set up Recyclerview
        setupRecyclerview();
        //go to SubcategoryActivity
        imv_keyback.setOnClickListener(v->{
            finish();
        });

    }

    private void setupRecyclerview() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new SearchUrlAdapter(this,this);
        recyclerView.setAdapter(adapter);
    }

    private void LoadSearchurl(int position) {
        Call<SearchUrlRespone> call=searchEngine.getSearchurlBycateID("1",position);
        call.enqueue(new Callback<SearchUrlRespone>() {
            @Override
            public void onResponse(Call<SearchUrlRespone> call, Response<SearchUrlRespone> response) {
                Log.e(TAG, "onResponse: gg" );
                if(response.body().getSearchurl()==null){
                    Toast.makeText(SearchurlActivity.this, "No Data to Display!", Toast.LENGTH_SHORT).show();
                }
                List<SearchUrl> searchUrlList=response.body().getSearchurl();
                adapter.AddSearchUrl(searchUrlList);
            }

            @Override
            public void onFailure(Call<SearchUrlRespone> call, Throwable t) {
                Log.e(TAG, "onFailure: " );
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
    public void ItemClick(String url) {
        Intent intent=new Intent(SearchurlActivity.this,DetailActivity.class);
        intent.putExtra("url",url);
        startActivity(intent);
    }
}
