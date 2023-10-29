package com.example.gorenganindonesia.ui.Adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gorenganindonesia.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<String> dataList;
    ReceiptAdapter receiptAdapter;

    public CategoryAdapter(ArrayList<String> dataList, ReceiptAdapter receiptAdapter) {
        this.dataList = dataList;
        this.receiptAdapter = receiptAdapter;
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
        String btnText = dataList.get(position).toString();
        holder.btnCategory.setText(btnText);

        holder.btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(view.getContext(), "Category " + btnText + " pressed", Toast.LENGTH_SHORT).show();
                receiptAdapter.applyFilterCategory(btnText);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        Button btnCategory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btnCategory = itemView.findViewById(R.id.btn_category);
        }
    }
}