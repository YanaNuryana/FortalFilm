package com.example.fortalfilm;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.fortalfilm.model.ResultsItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.parceler.Parcels;

import java.util.Objects;

public class DetailMovieActivity extends AppCompatActivity {

    ResultsItem dataMovie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getBundleExtra(MovieAdapter.DATA_EXTRA);
        assert bundle != null;
        dataMovie = Parcels.unwrap(bundle.getParcelable(MovieAdapter.DATA_MOVIE));

        Objects.requireNonNull(getSupportActionBar()).setTitle(dataMovie.getTitle());

        ImageView ivBackdrop = findViewById(R.id.iv_detail_backdrop);
        TextView tvDeskripsi = findViewById(R.id.tv_detail_description);

        Glide.with(DetailMovieActivity.this).load("https://image.tmdb.org/t/p/w500"+dataMovie.getBackdropPath()).into(ivBackdrop);
        tvDeskripsi.setText(dataMovie.getOverview());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}