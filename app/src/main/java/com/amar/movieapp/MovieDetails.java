package com.amar.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetails extends AppCompatActivity {
    RecyclerView recyclerView;
    private static final String POSITION = "position";
    private final static String API_KEY = "90ab00085c2a5816e9588e38f01e392a";
    String position;
    TextView title,decs,lang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        final ImageView poster = (ImageView)findViewById(R.id.poster);
         title = (TextView) findViewById(R.id.movie_title);
         decs = (TextView) findViewById(R.id.decs);

        Bundle bundle =getIntent().getExtras();

         position = bundle.getString(POSITION);


        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);


        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Response<MoviesResponse> response) {
                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();

                title.setText(movies.get(Integer.parseInt(position)).getTitle());
                Picasso.with(MovieDetails.this).load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + movies.get(Integer.parseInt(position)).
                        getPosterPath()).fit().into(poster);
                decs.setText(movies.get(Integer.parseInt(position)).getOverview());


            }

            @Override
            public void onFailure(Throwable t) {

            }

        });

    }
}
