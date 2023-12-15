package com.example.gorenganindonesia.ui.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gorenganindonesia.Activity.DetailActivity;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.R;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    List<Recipe> dataList;
    Context context;

    public RecipeAdapter(List<Recipe> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    public void updateData(List<Recipe> recipes) {
        this.dataList = recipes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.ViewHolder holder, int position) {
        Recipe recipe = dataList.get(position);

        Glide
                .with(holder.itemView.getContext())
                .load(recipe.getImgUrl())
                .placeholder(R.drawable.solid_grey_landscape)
                .error(R.drawable.img_404_landscape)
                .into(holder.ivReceiptThumb);

        holder.tvReceiptTitle.setText(recipe.getTitle().toString());
        holder.tvDifficulty.setText(recipe.getDifficulty().toString());
        holder.tvPortion.setText(String.valueOf(recipe.getPortion()) + " Porsi");
        holder.tvDuration.setText(String.valueOf(recipe.getMinuteDuration()) + "mnt");
        holder.tvAuthorUsername.setText("@" + recipe.getAuthorUsername().toString());
        holder.tvRatingStar.setText(String.valueOf(recipe.getStars()));

        holder.cvReceipt.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), DetailActivity.class);
            intent.putExtra("recipe", recipe);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cvReceipt;
        ImageView ivReceiptThumb;
        TextView tvReceiptTitle, tvDifficulty, tvPortion, tvDuration, tvAuthorUsername, tvRatingStar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cvReceipt = itemView.findViewById(R.id.cv_receipt);
            ivReceiptThumb = itemView.findViewById(R.id.iv_receipt_thumb);
            tvReceiptTitle = itemView.findViewById(R.id.tv_receipt_title);
            tvDifficulty = itemView.findViewById(R.id.tv_difficulty);
            tvPortion = itemView.findViewById(R.id.tv_portion);
            tvDuration = itemView.findViewById(R.id.tv_duration);
            tvAuthorUsername = itemView.findViewById(R.id.tv_author_username);
            tvRatingStar = itemView.findViewById(R.id.tv_star_rating);
        }
    }
}