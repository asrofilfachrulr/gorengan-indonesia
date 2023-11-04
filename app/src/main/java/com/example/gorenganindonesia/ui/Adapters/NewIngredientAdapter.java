package com.example.gorenganindonesia.ui.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gorenganindonesia.R;

public class NewIngredientAdapter extends RecyclerView.Adapter<NewIngredientAdapter.ViewHolder> {
    private int inputFieldCount = 2; // default

    public NewIngredientAdapter(){}

    @NonNull
    @Override
    public NewIngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_recipe_ingredient_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewIngredientAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return getInputFieldCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public int getInputFieldCount() {
        return inputFieldCount;
    }

    public void addInputFieldCount(int inputFieldCount) {
        this.inputFieldCount += 1;
        notifyItemInserted(getInputFieldCount() - 1);
    }
}
