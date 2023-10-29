package com.example.gorenganindonesia.ui.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gorenganindonesia.Model.data.Ingredient.Ingredient;
import com.example.gorenganindonesia.R;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder>{
    List<Ingredient> ingredients;

    public IngredientAdapter(List<Ingredient> ingredients){
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients_item,parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientAdapter.ViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);
        if(ingredient.getQty().equals(""))
            holder.tvQtyUnit.setText(ingredient.getUnit().toString());
        else
            holder.tvQtyUnit.setText(ingredient.getQty().toString()+"\n"+ingredient.getUnit().toString());
        holder.tvIngredient.setText(ingredient.getName().toString());
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvQtyUnit, tvIngredient;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIngredient = (TextView) itemView.findViewById(R.id.tv_ingredient);
            tvQtyUnit = (TextView) itemView.findViewById(R.id.tv_qty_unit);
        }
    }
}
