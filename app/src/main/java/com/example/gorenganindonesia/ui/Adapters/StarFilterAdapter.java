package com.example.gorenganindonesia.ui.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gorenganindonesia.Util.CustomToast;
import com.example.gorenganindonesia.R;

import java.util.List;

public class StarFilterAdapter extends RecyclerView.Adapter<StarFilterAdapter.ViewHolder> {
    private List<String> dataList;

    public StarFilterAdapter(List<String> dataList){
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public StarFilterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.star_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StarFilterAdapter.ViewHolder holder, int position) {
        holder.btnFilterStarRating.setText(dataList.get(position).toString());

        if(holder.btnFilterStarRating.getText().toString().equals("semua")){
            holder.btnFilterStarRating.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }

        holder.btnFilterStarRating.setOnClickListener(v -> applyFilterStar(dataList.get(position), v));
    }

    public void applyFilterStar(String star, View v) {
        //TODO: Complete filter on rating based on star count
        new CustomToast(star + " filter clicked", v, false).show();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button btnFilterStarRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btnFilterStarRating = (Button) itemView.findViewById(R.id.btn_filter_star_rating);
        }
    }
}
