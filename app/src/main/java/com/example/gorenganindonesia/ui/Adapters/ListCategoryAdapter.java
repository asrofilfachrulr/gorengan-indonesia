package com.example.gorenganindonesia.ui.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gorenganindonesia.Activity.ListRecipeActivityRVG;
import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.Util.Constants;

import java.util.List;

public class ListCategoryAdapter extends RecyclerView.Adapter<ListCategoryAdapter.ViewHolder> {
    List<String> dataList;
    RecipeListRVGAdapter recipeListRVGAdapter;

    RecyclerView recipeRv, categoryRv;

    final Button[] stateCategory = {null, null}; // {previous, current}

    public String getCurrentCategory(){
        if(stateCategory[1] != null)
            return stateCategory[1].getText().toString();
        return "";
    }

    public ListCategoryAdapter(List<String> dataList, RecipeListRVGAdapter recipeListRVGAdapter, RecyclerView recipeRv, RecyclerView categoryRv) {
        this.dataList = dataList;
        this.recipeListRVGAdapter = recipeListRVGAdapter;
        this.recipeRv = recipeRv;
        this.categoryRv = categoryRv;
    }

    public void updateData(List<String> updatedCategory){
        this.dataList = updatedCategory;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListCategoryAdapter.ViewHolder holder, int position) {
        String btnText = dataList.get(holder.getBindingAdapterPosition()).toString();
        holder.btnCategory.setText(btnText);

        if(btnText.toLowerCase().contains("semua")){
            stateCategory[0] = holder.btnCategory;
            stateCategory[1] = holder.btnCategory;

            stateCategory[1].setTextAppearance(R.style.btn_accent_light_rounded);
            stateCategory[1].setBackground(ResourcesCompat.getDrawable(holder.context.getResources(), R.drawable.btn_accent_light, null));

            if(recipeListRVGAdapter != null)
                recipeListRVGAdapter.applyFilter(Constants.EMPTY_STRING, Constants.EMPTY_STRING);
        }

        holder.btnCategory.setOnClickListener(view -> {
            recipeRv.scrollToPosition(0);

            stateCategory[0] = stateCategory[1];
            stateCategory[1] = holder.btnCategory;

            // set to inactive style
            stateCategory[0].setTextAppearance(R.style.btn_accent_inactive);
            stateCategory[0].setBackground(ResourcesCompat.getDrawable(holder.context.getResources(), R.drawable.btn_transparent, null));

            // set to active style
            stateCategory[1].setTextAppearance(R.style.btn_accent_light_rounded);
            stateCategory[1].setBackground(ResourcesCompat.getDrawable(holder.context.getResources(), R.drawable.btn_accent_light, null));

            if(recipeListRVGAdapter != null)
                recipeListRVGAdapter.applyFilter(btnText, Constants.EMPTY_STRING);
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