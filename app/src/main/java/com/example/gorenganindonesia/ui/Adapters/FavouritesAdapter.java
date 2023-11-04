package com.example.gorenganindonesia.ui.Adapters;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.gorenganindonesia.Activity.DetailActivity;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.R;

import java.util.List;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.ViewHolder> {
    List<Recipe> dataList;
    Context context;

    public FavouritesAdapter(Context context, List<Recipe> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    public void  updateData(List<Recipe> updatedDataList){
        this.dataList = updatedDataList;
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
        holder.tvTitle.setText(dataList.get(position).getTitle().toString());
        holder.tvAuthor.setText("oleh @admin");
        holder.tvPortion.setText(String.valueOf(dataList.get(position).getPortion()) + " porsi");
        holder.tvDifficulty.setText(dataList.get(position).getDifficulty().toString());
        holder.tvMinuteDuration.setText(String.valueOf(dataList.get(position).getMinuteDuration()) + "mnt");

        holder.ivThumb.setImageResource(dataList.get(position).getThumb());

        View[] views = {holder.ivThumb, holder.llFavItem};

        for(View view: views){
            view.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("receipt", dataList.get(position));

                v.getContext().startActivity(intent);
            });
        }

        holder.btnDelete.setOnClickListener(v -> {
            ((GlobalModel) context.getApplicationContext())
                    .getFavouriteViewModel()
                    .removeFavourite(position);
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
        LinearLayout llFavItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_fav_receipt_title);
            tvAuthor = (TextView) itemView.findViewById(R.id.tv_fav_receipt_author);
            tvDifficulty = (TextView) itemView.findViewById(R.id.tv_fav_receipt_difficulty);
            tvMinuteDuration = (TextView) itemView.findViewById(R.id.tv_fav_receipt_duration);
            tvPortion = (TextView) itemView.findViewById(R.id.tv_fav_receipt_portion);

            ivThumb = (ImageView) itemView.findViewById(R.id.iv_fav_receipt_image);

            btnDelete = (ImageButton) itemView.findViewById(R.id.btn_fav_delete);

            llFavItem = (LinearLayout) itemView.findViewById(R.id.ll_fav_item);
        }
    }
}
