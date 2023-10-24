package com.example.gorenganindonesia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView tvTitle, tvCategory, tvDifficulty, tvTime, tvPortion, tvStep, tvIngridient;
    ImageView ivThumb;
    ImageButton btnBack, btnShare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = (TextView) findViewById(R.id.tv_title_detail);
        tvCategory = (TextView) findViewById(R.id.tv_category_detail);
        tvDifficulty = (TextView) findViewById(R.id.tv_difficulty_detail);
        tvTime = (TextView) findViewById(R.id.tv_time_detail);
        tvPortion = (TextView) findViewById(R.id.tv_portion_detail);
        tvStep = (TextView) findViewById(R.id.tv_step_detail);
        tvIngridient = (TextView) findViewById(R.id.tv_ingridient_detail);

        ivThumb = (ImageView) findViewById(R.id.iv_thumb_detail);

        btnBack = (ImageButton) findViewById(R.id.btn_close_detail);
        btnShare = (ImageButton) findViewById(R.id.btn_share_detail);

        Intent intent = getIntent();
        Receipt receipt = intent.getParcelableExtra("receipt");

        tvTitle.setText(receipt.getTitle().toString());
        tvCategory.setText("Kategori " + receipt.getCategory().toString());
        tvDifficulty.setText(receipt.getDifficulty().toString());
        tvPortion.setText(String.valueOf(receipt.getPortion()) + " Porsi");
        tvTime.setText( "±" + String.valueOf(receipt.getMinuteDuration()) + " Menit");
        tvStep.setText(String.valueOf(receipt.getSteps().length) + " Langkah");
        tvIngridient.setText(String.valueOf(receipt.getIngredients().length) + " Bahan");

        ivThumb.setImageResource(receipt.getThumb());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed(); // This is optional, but it's a good practice to call the super method.

        // Your custom code here, if needed.
    }

}