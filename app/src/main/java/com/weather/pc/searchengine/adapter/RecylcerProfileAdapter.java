package com.weather.pc.searchengine.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weather.pc.searchengine.R;

public class RecylcerProfileAdapter extends RecyclerView.Adapter<RecylcerProfileAdapter.ProfleViewHolder> {

    @NonNull
    @Override
    public ProfleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.view_profile_layout,parent,false);
        return new ProfleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfleViewHolder holder, int position) {

    }
    @Override
    public int getItemCount() {
        return 1;
    }

    class ProfleViewHolder extends RecyclerView.ViewHolder{

        public ProfleViewHolder(View itemView) {
            super(itemView);
        }
    }
}
