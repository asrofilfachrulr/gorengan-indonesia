package com.example.gorenganindonesia.ui.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.data.Rating.Rating;
import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.Util.CustomToast;
import com.example.gorenganindonesia.Util.DateHelper;
import com.example.gorenganindonesia.ui.Fragments.Rating.OptionEditFragment;

import java.util.ArrayList;
import java.util.List;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.ViewHolder>{
    List<Rating> dataList;
    List<Rating> originalList;
    FragmentManager fragmentManager;

    public RatingAdapter(List<Rating> dataList, FragmentManager fragmentManager){
        this.dataList = dataList;
        this.originalList = dataList;

        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public RatingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rating_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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


        ImageView[] target = {holder.ivStar1, holder.ivStar2, holder.ivStar3, holder.ivStar4, holder.ivStar5};
        for(int star = 0; star < target.length; star++){
            if(star < rating.getStarCount())
                target[star].setImageResource(R.drawable.ic_star_solid_yellow);
            else
                target[star].setImageResource(R.drawable.ic_star_outline_yellow);
        }

        String username = ((GlobalModel) holder.view.getContext().getApplicationContext())
                .getAccountViewModel().getAccount().getUsername();

        if(rating.getAuthorUsername().equals(username)){
            holder.ibOption.setVisibility(View.VISIBLE);

            Context context = holder.view.getContext();
            PopupMenu popupMenu = new PopupMenu(context, holder.ibOption);

            popupMenu.inflate(R.menu.rating_option_menu);

            holder.ibOption.setOnClickListener(v -> {
                popupMenu.show();
            });

            popupMenu.setOnMenuItemClickListener(item -> {
                int itemId = item.getItemId();
                final int editId = getResourceId(context, "menu_rating_option_edit");
                final int deleteId = getResourceId(context, "menu_rating_option_delete");

                if(itemId == editId){
                    OptionEditFragment editFragment = new OptionEditFragment(rating.getStarCount(), rating.getComment());

                    editFragment.show(fragmentManager, "edit dialogue");
                } else if(itemId == deleteId){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Apakah Anda yakin menghapus ulasan ini?")
                            .setPositiveButton("Hapus", (dialog, which) -> {

                            })
                            .setNegativeButton("Batal", (dialog, which) -> {

                            });

                    builder.create().show();
                } else {
                    new CustomToast("Option Error", holder.view.getRootView(), false).show();
                }

                return true;
            });
        }

    }
    public int getResourceId(Context context, String resourceName) {
        return context.getResources().getIdentifier(resourceName, "id", context.getPackageName());
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
        ImageButton ibOption;
        TextView tvName, tvTime, tvText;
        View view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivThumb = (ImageView) itemView.findViewById(R.id.iv_thumb_rating);
            ivStar1 = (ImageView) itemView.findViewById(R.id.iv_star_1);
            ivStar2 = (ImageView) itemView.findViewById(R.id.iv_star_2);
            ivStar3 = (ImageView) itemView.findViewById(R.id.iv_star_3);
            ivStar4 = (ImageView) itemView.findViewById(R.id.iv_star_4);
            ivStar5 = (ImageView) itemView.findViewById(R.id.iv_star_5);

            ibOption = (ImageButton) itemView.findViewById(R.id.ib_option_rating);

            tvName = (TextView) itemView.findViewById(R.id.tv_name_rating);
            tvText = (TextView) itemView.findViewById(R.id.tv_text_rating);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time_rating);

            view = itemView;
        }
    }
}
