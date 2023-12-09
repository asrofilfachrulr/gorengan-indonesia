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
import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.Util.Logger;

import java.util.List;

public class NewStepAdapter extends RecyclerView.Adapter<NewStepAdapter.ViewHolder> {
    List<Step> dataList;
    boolean isTwEnabled;

    public NewStepAdapter(List<Step> dataList) {
        this.dataList = dataList;
        isTwEnabled = true;
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
        holder.reattachTextWatcher();
        Step step = dataList.get(holder.getBindingAdapterPosition());

        Logger.SimpleLog("onBindViewHolder on Pos: " + Integer.valueOf(holder.getBindingAdapterPosition()));

        holder.tvNumbering.setText(String.valueOf(step.getNumber())+".");
        holder.etNewStep.setText(step.getDescription());

        holder.ibDelete.setOnClickListener(v -> {
            // temporarily disable all text watchers to prevent undesired behaviour
            isTwEnabled = false;

            int pos = holder.getBindingAdapterPosition();
//            new CustomToast("delete clicked from pos: " + String.valueOf(pos), v, false).show();

            holder.deleteTextWatcher();

            Logger.SimpleLog("Before Remove: " + getDataListFlat());
            dataList.remove(pos);
            Logger.SimpleLog("After Remove: " + getDataListFlat());
            updateDataListNumbering(pos);

            notifyItemRemoved(pos);
            notifyItemRangeChanged(pos, getItemCount() - pos);
            isTwEnabled = true;
        });
    }

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        super.onViewRecycled(holder);
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
        Logger.SimpleLog("After Renumbering: " + getDataListFlat());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumbering;
        EditText etNewStep;
        ImageButton ibDelete;
        TextWatcher textWatcher;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNumbering = (TextView) itemView.findViewById(R.id.tv_numbering_new_step_new_recipe);
            etNewStep = (EditText) itemView.findViewById(R.id.et_new_step_new_recipe);
            ibDelete = (ImageButton) itemView.findViewById(R.id.ib_delete_new_step_new_recipe);

            textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}

                @Override
                public void afterTextChanged(Editable s) {
                    if(isTwEnabled) {
                        int pos = getBindingAdapterPosition();
                        Step step = dataList.get(pos);
                        Logger.SimpleLog("[Set Data] Pos: " + String.valueOf(pos) + ", Data Before: " + step.toString());
                        String newDesc = s.toString();
                        step.setDescription(newDesc);
                        Logger.SimpleLog("[Set Data] Pos: " + String.valueOf(pos) + ", Data After: " + step.toString());
                        dataList.set(pos, step);
                    }
                }
            };

            attachTextWatcher();
        }

        public void reattachTextWatcher(){
            deleteTextWatcher();
            attachTextWatcher();
        }

        public void deleteTextWatcher(){
            etNewStep.removeTextChangedListener(textWatcher);
        }

        public void attachTextWatcher(){
            etNewStep.addTextChangedListener(textWatcher);
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

    private String getDataListFlat(){
        StringBuilder builder = new StringBuilder();

        builder.append("[ ");

        for(Step step: dataList){
            builder.append("{ ");
            builder.append(String.valueOf(step.getNumber()));
            builder.append(" ");
            builder.append(step.getDescription());
            builder.append(" } ");
        }

        builder.append("]");
        return builder.toString();
    }
}
