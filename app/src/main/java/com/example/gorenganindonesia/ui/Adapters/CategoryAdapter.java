package com.example.gorenganindonesia.ui.Adapters;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gorenganindonesia.Activity.ListRecipeActivityRVG;
import com.example.gorenganindonesia.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    List<String> dataList;
    Context context;

    public CategoryAdapter(List<String> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    public void updateData(List<String> updatedCategory){
        this.dataList = updatedCategory;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        String btnText = dataList.get(holder.getBindingAdapterPosition()).toString();
        holder.btnCategory.setText(btnText);

        if(btnText.toLowerCase().contains("semua")){
            holder.btnCategory.setTextAppearance(R.style.btn_accent_light_rounded);
            holder.btnCategory.setBackground(ResourcesCompat.getDrawable(holder.context.getResources(), R.drawable.btn_accent_light, null));
        }

        holder.btnCategory.setOnClickListener(view -> {
            Intent intent = new Intent(context, ListRecipeActivityRVG.class);
            intent.putExtra("categoryInput", btnText.toLowerCase());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        Button btnCategory;
        Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btnCategory = itemView.findViewById(R.id.btn_category);
            context = itemView.getContext();
        }
    }
}