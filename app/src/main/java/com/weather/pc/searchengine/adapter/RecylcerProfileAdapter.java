package com.weather.pc.searchengine.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weather.pc.searchengine.R;

public class RecylcerProfileAdapter extends RecyclerView.Adapter<RecylcerProfileAdapter.ProfleViewHolder> {

    private String n;
    public String Getdata(String name){
        n=name;
        return n;
    }
    @NonNull
    @Override
    public ProfleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.view_profile_layout,parent,false);
        return new ProfleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfleViewHolder holder, int position) {
        holder.tv_name.setText(n);
    }


    @Override
    public int getItemCount() {
        return 1;
    }

    class ProfleViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_name;
        public ProfleViewHolder(View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.tv_name_profile);
        }
    }
}
