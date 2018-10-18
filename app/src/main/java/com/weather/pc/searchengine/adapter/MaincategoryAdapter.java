package com.weather.pc.searchengine.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.weather.pc.searchengine.R;
import com.weather.pc.searchengine.callback.MaincategoryClickListener;
import com.weather.pc.searchengine.wev_service.api_maincategories_respone.Maincategories;

import java.util.ArrayList;
import java.util.List;

public class MaincategoryAdapter extends RecyclerView.Adapter<MaincategoryAdapter.MaincategoryViewholder> {

    private Context context;
    private List<Maincategories> maincategoriesList;
    private MaincategoryClickListener listener;

    public MaincategoryAdapter(Context context,MaincategoryClickListener listener) {
        this.context = context;
        maincategoriesList=new ArrayList<>();
        this.listener=listener;
    }

    public void AddMaincategory(List<Maincategories> maincategories){
        maincategoriesList.addAll(maincategories);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public MaincategoryViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.maincategories_item_layout,parent,false);
        return new MaincategoryViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaincategoryViewholder holder, int position) {
        Maincategories maincategories=maincategoriesList.get(position);
        holder.tv_maincategory.setText(maincategories.getCateName());
        holder.tv_desc.setText(maincategories.getDes());
       //get image url from web service

//        String url="";
       if(maincategories.getIconName().contains("https:")){
           //holder.imv_maincategory.setImageResource(R.drawable.noimage);
           Picasso.get()
                   .load(maincategories.getIconName())
                   .placeholder(R.drawable.placeholder)
                   .error(R.drawable.images)
                   .into(holder.imv_maincategory);
       }
       else if(maincategories.getIconName().contains(maincategories.getIconName())){
            String url="http://110.74.194.125:15000"+maincategories.getIconName();
            Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.images)
                    .into(holder.imv_maincategory);
        }
        else{
           holder.imv_maincategory.setImageResource(R.drawable.noimage);
        }

    }

    @Override
    public int getItemCount() {
        return maincategoriesList.size();
    }

    class MaincategoryViewholder extends RecyclerView.ViewHolder{

        private ImageView imv_maincategory;
        private TextView tv_maincategory;
        private TextView tv_desc;
        public MaincategoryViewholder(View itemView) {
            super(itemView);
            imv_maincategory=itemView.findViewById(R.id.imv_category_maincategory);
            tv_maincategory=itemView.findViewById(R.id.tv_category_maincategory);
            tv_desc=itemView.findViewById(R.id.tv_description_maincategory);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.ItemClick(maincategoriesList.get(getAdapterPosition()).getId(),
                            maincategoriesList.get(getAdapterPosition()).getCateName());

                    Log.e("ooo", "onClick: "+maincategoriesList.get(getAdapterPosition()).getCateName() );

                }
            });

        }
    }
}
