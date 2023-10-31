package com.example.gorenganindonesia.ui.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gorenganindonesia.Activity.DetailActivity;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.data.Receipt.Receipt;
import com.example.gorenganindonesia.R;

import java.util.List;

public class MyReceiptAdapter extends RecyclerView.Adapter<MyReceiptAdapter.ViewHolder> {
    List<Receipt> dataList;

    public MyReceiptAdapter(List<Receipt> receipts){
        this.dataList = receipts;
    }

    @NonNull
    @Override
    public MyReceiptAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_receipt_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyReceiptAdapter.ViewHolder holder, int position) {
        holder.ivImage.setImageResource(dataList.get(position).getThumb());
        holder.tvTitle.setText(dataList.get(position).getTitle());
        holder.tvDifficulty.setText(dataList.get(position).getDifficulty());
        holder.tvPortion.setText(String.valueOf(dataList.get(position).getPortion()));
        holder.tvMinuteDuration.setText(String.valueOf(dataList.get(position).getMinuteDuration()));
        holder.tvAuthorUsername.setText("oleh @" + dataList.get(position).getAuthorUsername());

        holder.ibDelete.setOnClickListener(v -> {
            ((GlobalModel) v.getContext().getApplicationContext())
                    .getReceiptViewModel()
                    .deleteMyReceipt(
                            dataList.get(position),
                            ((GlobalModel) v.getContext().getApplicationContext())
                                    .getAccountViewModel()
                                    .getUsername()
                            );
        });

        View[] targetViews = {holder.llMyReceipt, holder.ivImage};

        for(View _: targetViews)
            _.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("receipt", dataList.get(position));
                v.getContext().startActivity(intent);
            });
    }

    public void updateData(List<Receipt> receipts){
        this.dataList = receipts;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvTitle, tvDifficulty, tvPortion, tvMinuteDuration, tvAuthorUsername;
        ImageButton ibDelete;
        LinearLayout llMyReceipt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivImage = (ImageView) itemView.findViewById(R.id.iv_my_receipt_image);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_my_receipt_title);
            tvDifficulty = (TextView) itemView.findViewById(R.id.tv_my_receipt_difficulty);
            tvPortion = (TextView) itemView.findViewById(R.id.tv_my_receipt_portion);
            tvMinuteDuration = (TextView) itemView.findViewById(R.id.tv_my_receipt_duration);
            tvAuthorUsername = (TextView) itemView.findViewById(R.id.tv_my_receipt_author);

            ibDelete = (ImageButton) itemView.findViewById(R.id.ib_delete_my_receipt);

            llMyReceipt = (LinearLayout) itemView.findViewById(R.id.ll_my_receipt_item);
        }
    }
}
