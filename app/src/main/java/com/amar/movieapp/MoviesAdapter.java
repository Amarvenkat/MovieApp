package com.amar.movieapp;

import android.content.Context;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private static final String POSITION = "position";

    private List<Movie> movies;
    private int rowLayout;
    private Context context;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView data;
        TextView movieDescription;
        TextView rating;
        ImageView backbg;


        public MovieViewHolder(View view) {
            super(view);

            moviesLayout      = (LinearLayout) view.findViewById(R.id.movies_layout);
            movieTitle        = (TextView) view.findViewById(R.id.title);
            data              = (TextView) view.findViewById(R.id.subtitle);
            movieDescription  = (TextView) view.findViewById(R.id.description);
            rating            = (TextView) view.findViewById(R.id.rating);
            backbg            = (ImageView) view.findViewById(R.id.backbg);
        }
    }

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {

        this.movies    = movies;
        this.rowLayout = rowLayout;
        this.context   = context;
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }
    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {

        holder.movieTitle.      setText( movies.get( position ).getTitle());
        holder.data.            setText( movies.get( position ).getReleaseDate());
        holder.movieDescription.setText( movies.get( position ).getOverview());
        holder.rating.          setText( movies.get( position ).getVoteAverage().toString());
        holder.moviesLayout.setTag(holder);
        holder.moviesLayout.setOnClickListener(clickListner);



        Picasso.with(context).load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + movies.get(position).
                getBackdropPath()).
                resize(150, 150).into(holder.backbg);


    }

    View.OnClickListener clickListner= new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            MovieViewHolder reviewholder = (MovieViewHolder) view.getTag();
            int position = reviewholder.getPosition();


            Intent intent = new Intent(context,MovieDetails.class);
            Bundle bundle1 = new Bundle();
            bundle1.putString(POSITION, String.valueOf(position));

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtras(bundle1);
            context.startActivity(intent);




        }
    };


    @Override
    public int getItemCount() {
        return movies.size();
    }
}


