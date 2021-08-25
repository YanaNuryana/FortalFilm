package com.example.fortalfilm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fortalfilm.model.ResponseMovie;
import com.example.fortalfilm.model.ResultsItem;
import com.example.fortalfilm.retrofit.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<ResultsItem> dataMovie = new ArrayList<>();
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = findViewById(R.id.recycleView);
//
//        MovieModel movie1 = new MovieModel();
//        movie1.setJudulFilm("judul Film");
//        movie1.setPosterFilm("https://www.themoviedb.org/t/p/w220_and_h330_face/mzOHg7Q5q9yUmY0b9Esu8Qe6Nnm.jpg");
//
//        for (int i = 0; i < 20 ; i++) {
//            dataMovie.add(movie1);
//        }

        getDataOnline();

        recycler.setAdapter(new MovieAdapter(MainActivity.this, dataMovie));

        recycler.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

    }

    private void getDataOnline() {
        final ProgressDialog progress = new ProgressDialog(MainActivity.this);
        progress.setMessage("Waitting....");
        progress.show();

        Call<ResponseMovie> request = RetrofitConfig.getApiService().ambilDataMovie("ee884a7fa70b9c1dd1b40e0a171170bb");
        request.enqueue(new Callback<ResponseMovie>() {
            @Override
            public void onResponse(Call<ResponseMovie> call, Response<ResponseMovie> response) {
                progress.dismiss();
                if (response.isSuccessful()){
                    dataMovie = response.body().getResults();
                    recycler.setAdapter(new MovieAdapter(MainActivity.this, dataMovie));
                } else {
                    Toast.makeText(MainActivity.this, "Request Not Success", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseMovie> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Request Failure"+t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}