package com.example.gorenganindonesia.ui.Adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gorenganindonesia.Model.data.Ingredient.Ingredient;
import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.Util.Logger;

import java.util.List;

public class NewIngredientAdapter extends RecyclerView.Adapter<NewIngredientAdapter.ViewHolder> {

    private List<Ingredient> dataList;

    public NewIngredientAdapter(List<Ingredient> dataList){
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public NewIngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_recipe_ingredient_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewIngredientAdapter.ViewHolder holder, int position) {
//        int pos = holder.getBindingAdapterPosition();

        Ingredient ingredient = dataList.get(holder.getBindingAdapterPosition());

        // default constructor of ingredient is initializing empty string to all attribute so it is safe
        // to access all attribute regardless no real data has been inserted
        holder.etQty.setText(ingredient.getQty());
        holder.etUnit.setText(ingredient.getUnit());
        holder.etName.setText(ingredient.getName());

        holder.etQty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String newQty = s.toString();
                ingredient.setQty(newQty);
            }

            @Override
            public void afterTextChanged(Editable s) {
                dataList.set(holder.getBindingAdapterPosition(), ingredient);
            }
        });

        holder.etUnit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String newUnit = s.toString();
                ingredient.setUnit(newUnit);
            }

            @Override
            public void afterTextChanged(Editable s) {
                dataList.set(holder.getBindingAdapterPosition(), ingredient);
            }
        });

        holder.etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String newName = s.toString();
                ingredient.setName(newName);
            }

            @Override
            public void afterTextChanged(Editable s) {
                dataList.set(holder.getBindingAdapterPosition(), ingredient);
            }
        });

        holder.btnDelete.setOnClickListener(v -> {
            int adapterPos = holder.getBindingAdapterPosition();
            Logger.SimpleLog("Pos: " + Integer.valueOf(adapterPos));
            dataList.remove(adapterPos);
            notifyItemRemoved(adapterPos);
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText etQty, etUnit, etName;
        ImageButton btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            etQty = (EditText) itemView.findViewById(R.id.et_qty_new_ingredient_new_recipe);
            etUnit = (EditText) itemView.findViewById(R.id.et_unit_new_ingredient_new_recipe);
            etName = (EditText) itemView.findViewById(R.id.et_name_new_ingredient_new_recipe);

            btnDelete = (ImageButton) itemView.findViewById(R.id.ib_delete_new_ingredient_new_recipe);
        }
    }

    public List<Ingredient> getDataList(){ return dataList; }
    public void addEmptyItem(){
        dataList.add(new Ingredient());
        for(Ingredient ingredient: dataList){
            Logger.SimpleLog(ingredient.toString());
        }
        Logger.SimpleLog("New Insert At: " + Integer.valueOf(dataList.size() - 1));
        notifyItemInserted(dataList.size() - 1);
    }
}
