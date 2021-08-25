package com.example.fortalfilm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fortalfilm.model.ResultsItem;

import org.parceler.Parcels;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    public static final String DATA_MOVIE = "datamovie";
    public static final String DATA_EXTRA = "dataextra";
    private Context context;
    private List<ResultsItem> data;

    public MovieAdapter(Context context, List<ResultsItem> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {
        myViewHolder.tvJudul.setText(data.get(position).getTitle());
        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+data.get(position).getPosterPath()).into(myViewHolder.ivPoster);
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(context, DetailMovieActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(DATA_MOVIE, Parcels.wrap(data.get(position)));
                pindah.putExtra(DATA_EXTRA, bundle);
                context.startActivity(pindah);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul;
        ImageView ivPoster;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJudul = itemView.findViewById(R.id.tv_item_judul);
            ivPoster = itemView.findViewById(R.id.iv_item_gambar);
        }
    }
}