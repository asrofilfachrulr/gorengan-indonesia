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
import java.util.Random;

public class MostViewedRecipeAdapter extends RecyclerView.Adapter<MostViewedRecipeAdapter.ViewHolder>{
    List<Recipe> dataList;
    Context context;

    public MostViewedRecipeAdapter(List<Recipe> dataList, Context context){
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public MostViewedRecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_viewed_recipe_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MostViewedRecipeAdapter.ViewHolder holder, int position) {
        Recipe recipe = dataList.get(holder.getBindingAdapterPosition());

        holder.cvRoot.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("recipe", recipe);

            context.startActivity(intent);
        });

        holder.tvStar.setText(String.valueOf(recipe.getStars()));
        holder.tvTitle.setText(recipe.getTitle());

        // mock implementation
        holder.tvViewCount.setText(String.valueOf(getRandomInt()) + " kali dilihat");

        Glide
                .with(holder.itemView.getContext())
                .load(recipe.getImgUrl())
                .placeholder(R.drawable.solid_grey_landscape)
                .error(R.drawable.img_404_landscape)
                .into(holder.ivRecipe);
    }

    int getRandomInt(){
        Random random = new Random();
        return random.nextInt(21) + 1;
    }

    public void updateData(List<Recipe> updatedDataList){
        this.dataList = updatedDataList;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cvRoot;
        TextView tvTitle, tvViewCount, tvStar;
        ImageView ivRecipe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cvRoot = (CardView) itemView.findViewById(R.id.cv_root_most_viewed_recipe);

            tvStar = (TextView) itemView.findViewById(R.id.tv_star_rating_most_viewed_recipe);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_most_viewed_recipe);
            tvViewCount = (TextView) itemView.findViewById(R.id.tv_view_count_most_viewed_recipe);

            ivRecipe = (ImageView) itemView.findViewById(R.id.iv_most_viewed_recipe);
        }
    }
}
