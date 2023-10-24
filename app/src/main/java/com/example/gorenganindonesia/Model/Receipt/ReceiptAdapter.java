package com.example.gorenganindonesia.Model.Receipt;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gorenganindonesia.DetailActivity;
import com.example.gorenganindonesia.R;

import java.util.ArrayList;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.ViewHolder> {
    ArrayList<Receipt> dataList;
    ArrayList<Receipt> originalList;

    RecyclerView rv;

    public ReceiptAdapter(ArrayList<Receipt> dataList, RecyclerView rv) {
        this.dataList = dataList;
        this.originalList = dataList;
        this.rv = rv;
    }

    public void applyFilterCategory(String category) {
        ArrayList<Receipt> filteredList = new ArrayList<>();
        if (category.toLowerCase().contains("semua")){
            this.dataList = this.originalList;
        } else {
            for(Receipt receipt: originalList){
                if(receipt.getCategory().toLowerCase().contains(category.toLowerCase())){
                    filteredList.add(receipt);
                }
            }
            this.dataList = filteredList;
        }

        notifyDataSetChanged();
        rv.scrollToPosition(0);
    }

    public void applyFilterTitle(String title) {
        ArrayList<Receipt> filteredList = new ArrayList<>();
        if (title.matches("\\s") || title.equals("")){
            this.dataList = this.originalList;
        } else {
            for(Receipt receipt: originalList){
                if(receipt.getTitle().toLowerCase().contains(title.toLowerCase())){
                    filteredList.add(receipt);
                }
            }
            this.dataList = filteredList;
        }
        notifyDataSetChanged();
        rv.scrollToPosition(0);
    }

    @NonNull
    @Override
    public ReceiptAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.receipt_item,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReceiptAdapter.ViewHolder holder, int position) {
        Receipt receipt = dataList.get(position);
        String receiptTitle = receipt.getTitle().toString();

        holder.ivReceiptThumb.setImageResource(receipt.getThumb());
        holder.tvReceiptTitle.setText(receipt.getTitle().toString());
        holder.tvDifficulty.setText(receipt.getDifficulty().toString());
        holder.tvPortion.setText(String.valueOf(receipt.getPortion()) + " Porsi");
        holder.tvDuration.setText(String.valueOf(receipt.getMinuteDuration()) + "mnt");

        holder.cvReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);

                intent.putExtra("receipt", receipt);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        CardView cvReceipt;
        ImageView ivReceiptThumb;
        TextView tvReceiptTitle, tvDifficulty, tvPortion, tvDuration;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cvReceipt = itemView.findViewById(R.id.cv_receipt);
            ivReceiptThumb = itemView.findViewById(R.id.iv_receipt_thumb);
            tvReceiptTitle = itemView.findViewById(R.id.tv_receipt_title);
            tvDifficulty = itemView.findViewById(R.id.tv_difficulty);
            tvPortion = itemView.findViewById(R.id.tv_portion);
            tvDuration = itemView.findViewById(R.id.tv_duration);
        }
    }
}