package com.weather.pc.searchengine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestingActivity extends AppCompatActivity {


    private Toolbar toolbar;
    //private MaterialSearchView materialSearchView;
    private ListView listView;
    private String[] sugg=getResources().getStringArray(R.array.query_suggestions);
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);
        toolbar=findViewById(R.id.toolbar_testing);
        //materialSearchView=findViewById(R.id.search_view_testing);
        setSupportActionBar(toolbar);
        listView=findViewById(R.id.list);
        //searchCode();
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,sugg);
        listView.setAdapter(arrayAdapter);

    }
//    private void searchCode(){
//        materialSearchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
//        materialSearchView.setEllipsize(true);
//        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Toast.makeText(TestingActivity.this, query, Toast.LENGTH_SHORT).show();
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//        materialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
//            @Override
//            public void onSearchViewShown() {
//
//            }
//
//            @Override
//            public void onSearchViewClosed() {
//
//            }
//        });
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem item=menu.findItem(R.id.itm_search);
        SearchView searchView= (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(TestingActivity.this, newText, Toast.LENGTH_SHORT).show();
               arrayAdapter.clear();
                if(sugg.equals(newText)){

               }
                return false;
            }
        });
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.itm_search:
//                return true;
//                default:return super.onOptionsItemSelected(item);
//        }
//
//    }
//
//    @Override
//    public void onBackPressed() {
//        if(materialSearchView.isSearchOpen()){
//            materialSearchView.closeSearch();
//        }
//        else
//            super.onBackPressed();
//    }
}
