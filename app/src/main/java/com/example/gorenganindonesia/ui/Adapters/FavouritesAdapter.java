package com.example.gorenganindonesia.ui.Adapters;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
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
import com.example.gorenganindonesia.API.Handlers.FavouriteHandler;
import com.example.gorenganindonesia.Activity.DetailActivity;
import com.example.gorenganindonesia.Model.DTO.APIHandlerDTO;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.Util.CustomToast;

import java.util.ArrayList;
import java.util.List;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.ViewHolder> {
    List<Recipe> dataList;
    List<Recipe> originalList;
    Context context;
    FavouriteHandler favouriteHandler;

    public FavouritesAdapter(Context context, List<Recipe> dataList, FavouriteHandler favouriteHandler){
        this.context = context;
        this.dataList = dataList;
        this.originalList = dataList;
        this.favouriteHandler = favouriteHandler;
    }

    public void applyFiler(String target){
        target = target.toLowerCase();
        List<Recipe> filteredDataList = new ArrayList<>();

        if(TextUtils.isEmpty(target)){
            dataList = originalList;
            notifyDataSetChanged();
        } else {
            for(Recipe recipe: originalList){
                if(recipe.getTitle().toLowerCase().contains(target))
                    filteredDataList.add(recipe);
            }
            dataList = filteredDataList;
        }

        notifyDataSetChanged();
    }

    public void  updateData(List<Recipe> updatedDataList){
        this.dataList = updatedDataList;
        this.originalList = updatedDataList;
        notifyDataSetChanged();
    }

    public void toggleEmptyView() {

    }

    @NonNull
    @Override
    public FavouritesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouritesAdapter.ViewHolder holder, int position) {
        Recipe recipe = dataList.get(position);
        holder.tvTitle.setText(recipe.getTitle().toString());
        holder.tvAuthor.setText("oleh @" + recipe.getAuthorUsername());
        holder.tvPortion.setText(String.valueOf(recipe.getPortion()) + " porsi");
        holder.tvDifficulty.setText(recipe.getDifficulty().toString());
        holder.tvMinuteDuration.setText(String.valueOf(recipe.getMinuteDuration()) + "mnt");

        Glide
            .with(holder.itemView.getContext())
            .load(recipe.getImgUrl())
            .placeholder(R.drawable.solid_grey_landscape)
            .error(R.drawable.img_404_landscape)
            .into(holder.ivThumb);

        View[] views = {holder.ivThumb, holder.clFavItem};

        for(View view: views){
            view.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("recipe", recipe);

                int pos = ((GlobalModel) v.getContext().getApplicationContext()).getRecipeViewModel()
                                .getRecipePos(recipe.getId());

                intent.putExtra("position", pos);

                v.getContext().startActivity(intent);
            });
        }

        holder.btnDelete.setOnClickListener(v -> {
            APIHandlerDTO tempDAO = favouriteHandler.getDto();
            tempDAO.setCallback(() -> favouriteHandler.getFavourites());
            favouriteHandler.setDto(tempDAO);

            favouriteHandler.deleteFavourite(recipe.getId());

            ((GlobalModel) context.getApplicationContext())
                    .getFavouriteViewModel()
                    .removeFavourite(position);

            new CustomToast("Berhasil menghapus dari favorit!", v, false).show();
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvAuthor, tvDifficulty, tvMinuteDuration, tvPortion;
        ImageView ivThumb;
        ImageButton btnDelete;
        ConstraintLayout clFavItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_fav_receipt_title);
            tvAuthor = (TextView) itemView.findViewById(R.id.tv_fav_receipt_author);
            tvDifficulty = (TextView) itemView.findViewById(R.id.tv_fav_receipt_difficulty);
            tvMinuteDuration = (TextView) itemView.findViewById(R.id.tv_fav_receipt_duration);
            tvPortion = (TextView) itemView.findViewById(R.id.tv_fav_receipt_portion);

            ivThumb = (ImageView) itemView.findViewById(R.id.iv_fav_receipt_image);

            btnDelete = (ImageButton) itemView.findViewById(R.id.btn_fav_delete);

            clFavItem = (ConstraintLayout) itemView.findViewById(R.id.cl_fav_item);
        }
    }
}
