package com.example.thejavagrotto;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thejavagrotto.pojo.ReviewItem;

import java.util.ArrayList;

public class CustomReviewAdapter extends RecyclerView.Adapter<CustomReviewAdapter.CustomViewHolder> {

    private ArrayList<ReviewItem> reviews;
    private Context context;

    public CustomReviewAdapter(ArrayList<ReviewItem> reviews, Context context){
        this.reviews = reviews;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        ReviewItem reviewItem = reviews.get(position);
        holder.reviewTitle.setText(reviewItem.getReviewTitle());
        holder.reviewText.setText(reviewItem.getReviewText());
        holder.web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.canstarblue.co.nz/food-drink/coffee-types-explained/"));
                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {

        protected TextView reviewTitle;
        protected TextView reviewText;
        protected ImageView web;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.reviewTitle = itemView.findViewById(R.id.reviewTitle);
            this.reviewText = itemView.findViewById(R.id.reviewDescription);
            this.web = itemView.findViewById(R.id.web);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            CoffeeDB coffeeDB = new CoffeeDB(context);
            coffeeDB.deleteReview(reviews.get(getLayoutPosition()).getReviewID());
            reviews.remove(getLayoutPosition());
            notifyItemRemoved(getAdapterPosition());
            coffeeDB.close();
        }

        @Override
        public boolean onLongClick(View view) {
            new AlertDialog.Builder(context).setTitle("View Page").setMessage("Would you like to see coffee shops near you?")
                    .setIcon(R.drawable.ic_baseline_format_list_bulleted_24)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("https://www.tripadvisor.ca/Restaurants-g155021-c8-Windsor_Ontario.html"));
                            if (intent.resolveActivity(context.getPackageManager()) != null) {
                                context.startActivity(intent);
                            }
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
            return false;
        }
    }

}
