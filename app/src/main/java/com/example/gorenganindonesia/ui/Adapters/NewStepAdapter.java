package com.example.gorenganindonesia.ui.Adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gorenganindonesia.Model.data.Step.Step;
import com.example.gorenganindonesia.Util.CustomToast;
import com.example.gorenganindonesia.R;

import java.util.List;

public class NewStepAdapter extends RecyclerView.Adapter<NewStepAdapter.ViewHolder> {
    List<Step> dataList;

    public NewStepAdapter(List<Step> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public NewStepAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_recipe_step_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewStepAdapter.ViewHolder holder, int position) {
        Step step = dataList.get(holder.getBindingAdapterPosition());

        holder.tvNumbering.setText(String.valueOf(step.getNumber())+".");
        holder.etNewStep.setText(step.getDescription());

        holder.etNewStep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String newDesc = s.toString();
                step.setDescription(newDesc);
            }

            @Override
            public void afterTextChanged(Editable s) {
                dataList.set(holder.getBindingAdapterPosition(), step);
            }
        });

        holder.ibDelete.setOnClickListener(v -> {
            int pos = holder.getBindingAdapterPosition();
//            new CustomToast("delete clicked from pos: " + String.valueOf(pos), v, false).show();

            dataList.remove(pos);
            updateDataListNumbering(pos);

            notifyItemRemoved(pos);
            notifyItemRangeChanged(pos, dataList.size() - pos);
        });
    }

    public void updateDataListNumbering(int start){
        int prevNum = 0;

        for(int i = start; i < dataList.size(); i++){
            if(start != 0)
                prevNum = dataList.get(i - 1).getNumber();

            Step currStep = dataList.get(i);
            currStep.setNumber(++prevNum);
            dataList.set(i, currStep);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
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

    public void addEmptyItem(){
        int lastNumber = dataList.size() != 0 ? dataList.get(dataList.size() - 1).getNumber() + 1 : 1;
        dataList.add(new Step("", lastNumber));

        notifyItemInserted(dataList.size() - 1);
    }

    public List<Step> getDataList(){
        return dataList;
    }
}
