package com.example.gorenganindonesia.ui.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gorenganindonesia.CustomToast;
import com.example.gorenganindonesia.R;

public class NewStepAdapter extends RecyclerView.Adapter<NewStepAdapter.ViewHolder> {

    public NewStepAdapter() {}

    @NonNull
    @Override
    public NewStepAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_recipe_step_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewStepAdapter.ViewHolder holder, int position) {
        holder.tvNumbering.setTag("tv_numbering_new_recipe_"+position);

        holder.tvNumbering.setText(String.valueOf(getItemCount()+1)+".");

        holder.ibDelete.setOnClickListener(v -> {
            new CustomToast("delete clicked from pos: " + String.valueOf(getItemCount()+1), v, false).show();
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumbering;
        EditText etNewStep;
        ImageButton ibDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNumbering = (TextView) itemView.findViewById(R.id.tv_numbering_new_step_new_recipe);
            etNewStep = (EditText) itemView.findViewById(R.id.et_new_step_new_recipe);
            ibDelete = (ImageButton) itemView.findViewById(R.id.ib_delete_new_step_new_recipe);
        }
    }
}
