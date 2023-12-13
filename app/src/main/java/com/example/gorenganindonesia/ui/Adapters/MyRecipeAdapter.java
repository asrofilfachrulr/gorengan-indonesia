package com.example.gorenganindonesia.ui.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gorenganindonesia.API.Handlers.RecipeHandler;
import com.example.gorenganindonesia.Activity.DetailActivity;
import com.example.gorenganindonesia.Model.DTO.APIHandlerDTO;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.Util.ToastUseCase;

import java.util.List;

public class MyRecipeAdapter extends RecyclerView.Adapter<MyRecipeAdapter.ViewHolder> {
    List<Recipe> dataList;
    Context context;
    APIHandlerDTO dto;

    public MyRecipeAdapter(List<Recipe> recipes, Context context, APIHandlerDTO dto){
        this.dataList = recipes;
        this.context = context;
        this.dto = dto;
    }

    @NonNull
    @Override
    public MyRecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_recipe_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecipeAdapter.ViewHolder holder, int position) {
        Recipe recipe = dataList.get(position);

        Glide
            .with(holder.itemView.getContext())
            .load(recipe.getImgUrl())
            .placeholder(R.drawable.solid_grey_landscape)
            .error(R.drawable.img_404_landscape)
            .into(holder.ivImage);

        holder.tvTitle.setText(recipe.getTitle());
        holder.tvDifficulty.setText(recipe.getDifficulty());
        holder.tvPortion.setText(String.valueOf(recipe.getPortion()) + " Porsi");
        holder.tvMinuteDuration.setText(String.valueOf(recipe.getMinuteDuration()) + "mnt");
        holder.tvAuthorUsername.setText("oleh @" + recipe.getAuthorUsername());

        holder.ibDelete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder
                    .setMessage("Anda yakin ingin menghapus Resep \"" + recipe.getTitle() + "\"? Aktivitas ini tidak dapat dipulihkan")
                    .setPositiveButton("Hapus", (dialog, which) -> {
                        dto.setCallback(() -> {
                            APIHandlerDTO getDTO = dto;
                            getDTO.setCallback(null);
                            RecipeHandler getHandler = new RecipeHandler(getDTO);
                            getHandler.getAllRecipes();
                        });
                        RecipeHandler deleteHandler = new RecipeHandler(dto);
                        deleteHandler.deleteRecipe(recipe.getId());
                    })
                    .setNegativeButton("Batal", (dialog, which) -> {dialog.dismiss();})
                    .show();
        });

        View[] targetViews = {holder.clMyReceipt, holder.ivImage};

        for(View _: targetViews)
            _.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("recipe", recipe);

                int pos = ((GlobalModel) v.getContext().getApplicationContext()).getRecipeViewModel()
                                .getRecipePos(recipe.getId());

                intent.putExtra("position", pos);

                v.getContext().startActivity(intent);
            });
    }

    public void updateData(List<Recipe> recipes){
        this.dataList = recipes;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvTitle, tvDifficulty, tvPortion, tvMinuteDuration, tvAuthorUsername;
        ImageButton ibDelete;
        ConstraintLayout clMyReceipt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivImage = (ImageView) itemView.findViewById(R.id.iv_my_receipt_image);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_my_receipt_title);
            tvDifficulty = (TextView) itemView.findViewById(R.id.tv_my_receipt_difficulty);
            tvPortion = (TextView) itemView.findViewById(R.id.tv_my_receipt_portion);
            tvMinuteDuration = (TextView) itemView.findViewById(R.id.tv_my_receipt_duration);
            tvAuthorUsername = (TextView) itemView.findViewById(R.id.tv_my_receipt_author);

            ibDelete = (ImageButton) itemView.findViewById(R.id.ib_delete_my_receipt);

            clMyReceipt = (ConstraintLayout) itemView.findViewById(R.id.cl_my_receipt_item);
        }
    }
}
