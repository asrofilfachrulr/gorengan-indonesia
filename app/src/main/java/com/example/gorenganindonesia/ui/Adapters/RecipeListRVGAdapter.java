package com.example.gorenganindonesia.ui.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gorenganindonesia.Activity.DetailActivity;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.Util.Constants;
import com.example.gorenganindonesia.Util.CustomToast;
import com.example.gorenganindonesia.Util.Logger;
import com.example.gorenganindonesia.Util.RegexHelper;

import java.util.ArrayList;
import java.util.List;

public class RecipeListRVGAdapter extends RecyclerView.Adapter<RecipeListRVGAdapter.ViewHolder> {
    List<Recipe> dataList;
    List<Recipe> originalList;
    String category;
    String title;
    String empty = Constants.EMPTY_STRING;
    RecyclerView recipeRecylerView;

    public RecipeListRVGAdapter(List<Recipe> dataList, RecyclerView recipeRecylerView) {
        this.dataList = dataList;
        this.originalList = dataList;
        this.recipeRecylerView = recipeRecylerView;
        category = "semua";
        title = Constants.EMPTY_STRING;
    }

    public void clearTitle() {
        title = Constants.EMPTY_STRING;
    }

    public void applyFilter(String categoryInput, String titleInput, boolean resetScroll) {
        List<Recipe> filteredList;
        String categoryTarget, titleTarget;

        categoryTarget = categoryInput.equals(empty) ? category : categoryInput;
        titleTarget = titleInput.equals(empty) ? title : titleInput;

        filteredList = filterCategory(categoryTarget, originalList);
        filteredList = filterTitle(titleTarget, filteredList);

        this.dataList = filteredList;

        notifyDataSetChanged();

        if (resetScroll) {
            recipeRecylerView.scrollToPosition(0);
        }
    }

    public void applyFilter(String categoryInput, String titleInput) {
        applyFilter(categoryInput, titleInput, true);
    }

    public List<Recipe> filterCategory(String categoryTarget, List<Recipe> targetList) {
        categoryTarget = categoryTarget.toLowerCase();
        category = categoryTarget.equals(empty) ? "semua" : categoryTarget;

        if (categoryTarget.contains("semua"))
            return originalList;


        List<Recipe> filteredList = new ArrayList<>();

        for (Recipe recipe : targetList) {
            if (recipe.getCategory().toLowerCase().contains(category)) {
                filteredList.add(recipe);
            }
        }

        return filteredList;
    }

    public List<Recipe> filterTitle(String titleTarget, List<Recipe> targetList) {
        Logger.SimpleLog("titleTarget: " + titleTarget);
        titleTarget = titleTarget.toLowerCase();
        RegexHelper regexHelper = new RegexHelper(titleTarget);

        if (regexHelper.isBlank()) {
            title = empty;
            Logger.SimpleLog("reset title");
            return targetList;
        }

        title = titleTarget;

        List<Recipe> filteredList = new ArrayList<>();

        for (Recipe recipe : targetList) {
            if (recipe.getTitle().toLowerCase().contains(titleTarget)) {
                filteredList.add(recipe);
            }
        }

        return filteredList;
    }

    public void updateData(List<Recipe> recipes, String currentCategory) {
        this.originalList = recipes;

        if (currentCategory.isEmpty() || currentCategory.toLowerCase().contains("semua")) {
            applyFilter("semua", Constants.EMPTY_STRING, false);
        } else {
            applyFilter(currentCategory, Constants.EMPTY_STRING, false);
        }
    }


    @NonNull
    @Override
    public RecipeListRVGAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gv_recipe_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeListRVGAdapter.ViewHolder holder, int position) {
        Recipe recipe = dataList.get(position);

        Glide
                .with(holder.itemView.getContext())
                .load(recipe.getImgUrl())
                .placeholder(R.drawable.solid_grey_landscape)
                .error(R.drawable.img_404_landscape)
                .into(holder.ivRecipe);

        holder.tvTitle.setText(recipe.getTitle());
        holder.tvAuthorName.setText("@"+recipe.getAuthorUsername());
        holder.tvStars.setText(String.valueOf(recipe.getStars()));

        holder.cvItem.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), DetailActivity.class);

            intent.putExtra("recipe", recipe);

            int pos = ((GlobalModel) view.getContext().getApplicationContext())
                    .getRecipeViewModel()
                    .getRecipePos(recipe.getId());

            intent.putExtra("position", pos);

            if (pos != -1)
                view.getContext().startActivity(intent);
            else
                new CustomToast("Error mencari resep pada data!", view, false).show();
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivRecipe;
        TextView tvTitle;
        TextView tvAuthorName;
        TextView tvStars;
        CardView cvItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cvItem = itemView.findViewById(R.id.cv_grid_recipe_item);

            ivRecipe = itemView.findViewById(R.id.iv_recipe_thumb_gv);
            tvTitle = itemView.findViewById(R.id.tv_title_list_recipe_gv);
            tvAuthorName = itemView.findViewById(R.id.tv_author_username_gv);
            tvStars = itemView.findViewById(R.id.tv_star_rating_gv);
        }
    }
}
