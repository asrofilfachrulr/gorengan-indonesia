package com.example.gorenganindonesia.ui.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gorenganindonesia.Util.CustomToast;
import com.example.gorenganindonesia.Model.data.Rating.Rating;
import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.Util.DateHelper;

import java.util.ArrayList;
import java.util.List;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.ViewHolder>{
    List<Rating> dataList;
    List<Rating> originalList;

    public RatingAdapter(List<Rating> dataList){
        this.dataList = dataList;
        this.originalList = dataList;
    }

    @NonNull
    @Override
    public RatingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rating_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RatingAdapter.ViewHolder holder, int position) {
        Rating rating = dataList.get(position);

        Glide
            .with(holder.itemView.getContext())
            .load(rating.getThumbUrl())
            .placeholder(R.drawable.solid_grey_landscape)
            .error(R.drawable.img_404_landscape)
            .into(holder.ivThumb);

        holder.tvName.setText("@" + rating.getAuthorUsername());
        holder.tvText.setText(rating.getComment());
        holder.tvTime.setText(DateHelper.getHumanReadableDate(rating.getDate()));
        holder.tvLikeCount.setText(String.valueOf(rating.getLikeCount()));

        holder.ibToggleLike.setOnClickListener(v -> {
            new CustomToast("Like clicked!", v, false).show();
        });

        ImageView[] target = {holder.ivStar1, holder.ivStar2, holder.ivStar3, holder.ivStar4, holder.ivStar5};
        for(int star = 0; star < rating.getStarCount(); star++){
            target[star].setImageResource(R.drawable.ic_star_solid_yellow);
        }
    }

    public void filterByStar(String star){
        if(star.toLowerCase().contains("semua")){
            this.dataList = this.originalList;
        } else {
            try{
                int starInt = Integer.parseInt(star);

                List<Rating> filteredRating = new ArrayList<>();
                for(Rating rating: originalList)
                    if(rating.getStarCount() == starInt)
                        filteredRating.add(rating);

                this.dataList = filteredRating;
            } catch (RuntimeException e){

            }
        }

        notifyDataSetChanged();
    }

    public void updateData(List<Rating> updatedDataList){
        this.dataList = updatedDataList;
        this.originalList = updatedDataList;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumb, ivStar1, ivStar2, ivStar3, ivStar4, ivStar5;
        TextView tvName, tvTime, tvText, tvLikeCount;
        ImageButton ibToggleLike;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivThumb = (ImageView) itemView.findViewById(R.id.iv_thumb_rating);
            ivStar1 = (ImageView) itemView.findViewById(R.id.iv_star_1);
            ivStar2 = (ImageView) itemView.findViewById(R.id.iv_star_2);
            ivStar3 = (ImageView) itemView.findViewById(R.id.iv_star_3);
            ivStar4 = (ImageView) itemView.findViewById(R.id.iv_star_4);
            ivStar5 = (ImageView) itemView.findViewById(R.id.iv_star_5);

            tvName = (TextView) itemView.findViewById(R.id.tv_name_rating);
            tvText = (TextView) itemView.findViewById(R.id.tv_text_rating);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time_rating);
            tvLikeCount = (TextView) itemView.findViewById(R.id.tv_like_count_rating);

            ibToggleLike = (ImageButton) itemView.findViewById(R.id.ib_toggle_like_rating);
        }
    }
}
