package com.example.gorenganindonesia.ui.Adapters;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gorenganindonesia.Model.data.Receipt.Receipt;
import com.example.gorenganindonesia.R;

import java.util.List;

public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.ViewHolder> {
    List<Receipt> dataList;

    public FavouritesAdapter(List<Receipt> dataList){
        this.dataList = dataList;
    }

    public void updateData(List<Receipt> updatedDataList){
        this.dataList = updatedDataList;
        notifyDataSetChanged();
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
        holder.tvPortion.setText(Integer.valueOf(dataList.get(position).getPortion()));
        holder.tvDifficulty.setText(dataList.get(position).getDifficulty().toString());
        holder.tvMinuteDuration.setText(Integer.valueOf(dataList.get(position).getMinuteDuration()));

        holder.ivThumb.setImageResource(dataList.get(position).getThumb());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvAuthor, tvDifficulty, tvMinuteDuration, tvPortion;
        ImageView ivThumb;
        ImageButton btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_fav_receipt_title);
            tvAuthor = (TextView) itemView.findViewById(R.id.tv_fav_receipt_author);
            tvDifficulty = (TextView) itemView.findViewById(R.id.tv_fav_receipt_difficulty);
            tvMinuteDuration = (TextView) itemView.findViewById(R.id.tv_fav_receipt_duration);
            tvPortion = (TextView) itemView.findViewById(R.id.tv_fav_receipt_portion);

            ivThumb = (ImageView) itemView.findViewById(R.id.iv_fav_receipt_image);

            btnDelete = (ImageButton) itemView.findViewById(R.id.btn_fav_delete);
        }
    }
}
