package com.example.posterlab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder>{

    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PosterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_poster, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        holder.bindPosters(posterList.get(position));
    }

    @Override
    public int getItemCount() {
        return posterList.size();
    }

    private List<Poster> posterList;
    private PostersListener postersListener;

    public List<Poster> getSelectedPosters(){
        List<Poster> selectedPosters = new ArrayList<>();
        for (Poster poster : this.posterList){
            if(poster.isSelected){
                selectedPosters.add(poster);
            }
        }
        return selectedPosters;
    }

    public PosterAdapter(List<Poster> posterList, PostersListener postersListener) {
        this.posterList = posterList;
        this.postersListener = postersListener;
    }

    class PosterViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout layoutPosters;
        View viewBackground;
        RoundedImageView imagePoster;
        TextView textName, textCreator, textStory;
        RatingBar ratings;
        ImageView imageSelected;

        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutPosters = itemView.findViewById(R.id.layoutPosters);
            viewBackground = itemView.findViewById(R.id.viewBackground);
            imagePoster = itemView.findViewById(R.id.imagePosters);
            textName = itemView.findViewById(R.id.textName);
            textCreator = itemView.findViewById(R.id.textCreator);
            textStory = itemView.findViewById(R.id.textStory);
            ratings = itemView.findViewById(R.id.ratings);
            imageSelected = itemView.findViewById(R.id.imageSelected);

        }

        void bindPosters(final Poster poster){
            imagePoster.setImageResource(poster.image);
            textName.setText(poster.name);
            textCreator.setText(poster.creator);
            textStory.setText(poster.story);
            ratings.setRating(poster.rating);

            if (poster.isSelected){
                viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                imageSelected.setVisibility(View.VISIBLE);
            } else {
                viewBackground.setBackgroundResource(R.drawable.poster_background);
                imageSelected.setVisibility(View.GONE);
            }
            layoutPosters.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (poster.isSelected){
                        viewBackground.setBackgroundResource(R.drawable.poster_background);
                        imageSelected.setVisibility(View.GONE);
                        poster.isSelected = false;
                        if (getSelectedPosters().isEmpty()){
                            postersListener.onPosterAction(false);
                        }
                    } else {
                        viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                        imageSelected.setVisibility(View.VISIBLE);
                        poster.isSelected = true;
                        postersListener.onPosterAction(true);
                    }
                }
            });
        }
    }
}



















