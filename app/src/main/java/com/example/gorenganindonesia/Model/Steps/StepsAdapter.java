package com.example.gorenganindonesia.Model.Steps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gorenganindonesia.R;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder>{
    List<String> steps;

    public StepsAdapter() {}

    public StepsAdapter(List<String> steps){
        this.steps = steps;
    }

    @NonNull
    @Override
    public StepsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.steps_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StepsAdapter.ViewHolder holder, int position) {
        holder.tvNumbering.setText(String.valueOf(position+1));
        holder.tvStep.setText(steps.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumbering, tvStep;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNumbering = (TextView) itemView.findViewById(R.id.tv_numbering);
            tvStep = (TextView) itemView.findViewById(R.id.tv_step);
        }
    }
}
