package com.weather.pc.searchengine.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weather.pc.searchengine.R;
import com.weather.pc.searchengine.callback.SubcategoryClickListener;
import com.weather.pc.searchengine.wev_service.api_subcategories_respone.Subcategories;

import java.util.ArrayList;
import java.util.List;

public class SubcategoryAdapter extends RecyclerView.Adapter<SubcategoryAdapter.SubcategoryViewHolder> {

    private Context context;
    private List<Subcategories> subcategoriesList;
    private SubcategoryClickListener listener;

    public SubcategoryAdapter(Context context,SubcategoryClickListener listener) {
        this.context = context;
        subcategoriesList=new ArrayList<>();
        this.listener=listener;
    }
    public void AddSubcategories(List<Subcategories> subcategories){
        subcategoriesList.addAll(subcategories);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SubcategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_categories_item_layout,parent,false);
        return new SubcategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubcategoryViewHolder holder, int position) {
        Subcategories subcategories=subcategoriesList.get(position);
        holder.tv_subcategory.setText(subcategories.getCateName());
    }

    @Override
    public int getItemCount() {
        return subcategoriesList.size();
    }

    class SubcategoryViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_subcategory;
        public SubcategoryViewHolder(View itemView) {
            super(itemView);
            tv_subcategory=itemView.findViewById(R.id.tv_subcategory);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.ItemClick(subcategoriesList.get(getAdapterPosition()).getId(),
                            subcategoriesList.get(getAdapterPosition()).getCateName());
                }
            });
        }
    }
}
