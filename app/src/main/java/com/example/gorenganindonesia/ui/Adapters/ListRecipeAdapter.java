package com.example.gorenganindonesia.ui.Adapters;

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
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.Util.Constants;
import com.example.gorenganindonesia.Util.CustomToast;
import com.example.gorenganindonesia.Util.Logger;
import com.example.gorenganindonesia.Util.RegexHelper;

import java.util.ArrayList;
import java.util.List;

public class ListRecipeAdapter extends RecyclerView.Adapter<ListRecipeAdapter.ViewHolder> {
    List<Recipe> dataList;
    List<Recipe> originalList;
    String category;
    String title;
    String empty = Constants.EMPTY_STRING;
    RecyclerView recipeRecylerView;

    public ListRecipeAdapter(List<Recipe> dataList, RecyclerView recipeRecylerView) {
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
    public ListRecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        ListRecipeAdapter.ViewHolder viewHolder = new ListRecipeAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListRecipeAdapter.ViewHolder holder, int position) {
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
