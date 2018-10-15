package com.weather.pc.searchengine.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.weather.pc.searchengine.R;
import com.weather.pc.searchengine.wev_service.api_maincategories_respone.Maincategories;

import java.util.ArrayList;
import java.util.List;

public class MaincategoryAdapter extends RecyclerView.Adapter<MaincategoryAdapter.MaincategoryViewholder> {

    private Context context;
    private List<Maincategories> maincategoriesList;

    public MaincategoryAdapter(Context context) {
        this.context = context;
        maincategoriesList=new ArrayList<>();
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
//       if(maincategories.getIconName().contains(null)){
//           holder.imv_maincategory.setImageResource(R.drawable.noimage);
//       }
//       else if(maincategories.getIconName().contains("http")){
//           url=maincategories.getCateName();
//       }else {
//           url="http://110.74.194.125:15000/"+maincategories.getIconName();
//       }
//            Picasso.get()
//                .load(url)
//                .placeholder(R.drawable.ic_launcher_background)
//                .error(R.drawable.ic_launcher_foreground)
//                .into(holder.imv_maincategory);



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

        }
    }
}
