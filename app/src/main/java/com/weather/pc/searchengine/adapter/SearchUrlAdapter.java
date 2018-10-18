package com.weather.pc.searchengine.adapter;

import android.content.Context;
import android.graphics.ColorSpace;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.weather.pc.searchengine.R;
import com.weather.pc.searchengine.callback.SearchurlClickListener;
import com.weather.pc.searchengine.wev_service.api_search_url_respone.SearchUrl;

import java.util.ArrayList;
import java.util.List;

public class SearchUrlAdapter extends RecyclerView.Adapter<SearchUrlAdapter.SearchUrlViewHolder> {

    private Context context;
    private List<SearchUrl> searchUrlList;
    private SearchurlClickListener listener;

    public SearchUrlAdapter(Context context,SearchurlClickListener listener) {
        this.context = context;
        searchUrlList=new ArrayList<>();
        this.listener=listener;
    }

    public void AddSearchUrl(List<SearchUrl> searchUrls){
        searchUrlList.addAll(searchUrls);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SearchUrlViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.search_url_item_layout,parent,false);
        return new SearchUrlViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchUrlViewHolder holder, int position) {
        SearchUrl searchUrl=searchUrlList.get(position);
        holder.tv_name.setText(searchUrl.getTitle());
        holder.tv_url.setText(searchUrl.getLink());
        holder.tv_email.setText(searchUrl.getEmail());
        holder.tv_phone.setText(searchUrl.getPhone());
        holder.tv_add.setText(searchUrl.getAddress());
        Picasso.get()
                .load(searchUrl.getPicUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.images)
                .into(holder.imv_searchurl);
        Picasso.get()
                .load(searchUrl.getPicUrl())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.images)
                .into(holder.imv_name_searchurl);
    }

    @Override
    public int getItemCount() {
        return searchUrlList.size();
    }

    class SearchUrlViewHolder extends RecyclerView.ViewHolder{

        private ImageView imv_searchurl,imv_name_searchurl;
        private TextView tv_name,tv_phone,tv_add,tv_email,tv_url;
        public SearchUrlViewHolder(View itemView) {
            super(itemView);
            imv_searchurl=itemView.findViewById(R.id.imv_searchurl);
            tv_name=itemView.findViewById(R.id.tv_name_searchurl);
            tv_phone=itemView.findViewById(R.id.tv_phone_searchurl);
            tv_email=itemView.findViewById(R.id.tv_email_searchurl);
            tv_add=itemView.findViewById(R.id.tv_address_searchurl);
            tv_url=itemView.findViewById(R.id.tv_url_searchurl);

            imv_name_searchurl=itemView.findViewById(R.id.imv_name_searchurl);

            itemView.setOnClickListener(v->{
                listener.ItemClick(searchUrlList.get(getAdapterPosition()).getLink());
            });
        }
    }
}
