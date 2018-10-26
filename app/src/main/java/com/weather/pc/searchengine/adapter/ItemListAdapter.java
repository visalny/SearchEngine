package com.weather.pc.searchengine.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.weather.pc.searchengine.R;
import com.weather.pc.searchengine.callback.ItemListClickListener;
import com.weather.pc.searchengine.callback.SubcategoryClickListener;
import com.weather.pc.searchengine.wev_service.api_get_all_subcategories_respone.Allsubcategories;

import java.util.ArrayList;
import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemlistViewHolder> {

    private List<Allsubcategories> allsubcategoriesList;
    private Context context;
    private ItemListClickListener listener;

    public ItemListAdapter(Context context,ItemListClickListener listener) {
        this.context = context;
        allsubcategoriesList=new ArrayList<>();
        this.listener=listener;
    }
    public void Setdata(List<Allsubcategories> allsubcategories){
        allsubcategoriesList.addAll(allsubcategories);
        notifyDataSetChanged();
    }
    public void clearData(){
        this.allsubcategoriesList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_layout,parent,false);
        return new ItemlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemlistViewHolder holder, int position) {
        Allsubcategories allsubcategories=allsubcategoriesList.get(position);
        holder.textView.setText(allsubcategories.getCateName());
    }

    @Override
    public int getItemCount() {
        return allsubcategoriesList.size();
    }

    class ItemlistViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        public ItemlistViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.tv_item_name);
            itemView.setOnClickListener(v->{
                Log.e("ooo", "ItemlistViewHolder: "+allsubcategoriesList.get(getAdapterPosition()).getId() );
                listener.ListItemClick(allsubcategoriesList.get(getAdapterPosition()).getId(),allsubcategoriesList.get(getAdapterPosition()).getCateName());
            });
        }
    }
}
