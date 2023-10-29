package com.example.gorenganindonesia.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gorenganindonesia.CustomToast;
import com.example.gorenganindonesia.Model.ViewModel.FavouriteViewModel;
import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.ui.Adapters.DetailFragmentAdapter;
import com.example.gorenganindonesia.ui.Fragments.Detail.IngredientsFragment;
import com.example.gorenganindonesia.ui.Fragments.Detail.StepsFragment;
import com.example.gorenganindonesia.ui.Fragments.Detail.SummaryFragment;
import com.example.gorenganindonesia.Model.data.Receipt.Receipt;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    TextView tvTitleRingkasan, tvTitleLangkah, tvTitleBahan;
    ImageView ivThumb;
    ImageButton btnBack, btnMore;
    ViewPager vp;

    Button btnShare, btnAddFavourite, btnSaveOffline, btnSeeUserRating;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitleRingkasan = (TextView) findViewById(R.id.tv_title_ringkasan);
        tvTitleBahan = (TextView) findViewById(R.id.tv_title_bahan);
        tvTitleLangkah = (TextView) findViewById(R.id.tv_title_langkah);

        ivThumb = (ImageView) findViewById(R.id.iv_thumb_detail);

        btnBack = (ImageButton) findViewById(R.id.btn_close_detail);
        btnMore = (ImageButton) findViewById(R.id.btn_more_detail);


        vp = (ViewPager) findViewById(R.id.vp_detail);

        Intent intent = getIntent();
        Receipt receipt = intent.getParcelableExtra("receipt");

        ivThumb.setImageResource(receipt.getThumb());

        btnBack.setOnClickListener(v -> onBackPressed());

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_dialog_menu_detail);

        btnShare = (Button) bottomSheetDialog.findViewById(R.id.btn_share);
        btnAddFavourite = (Button) bottomSheetDialog.findViewById(R.id.btn_add_to_favourite);
        btnSaveOffline = (Button) bottomSheetDialog.findViewById(R.id.btn_save_offline);
        btnSeeUserRating = (Button) bottomSheetDialog.findViewById(R.id.btn_see_user_rating);

        btnMore.setOnClickListener(v -> {
            bottomSheetDialog.show();
        });

        btnShare.setOnClickListener(v -> {
            // Create an Intent with the ACTION_SEND action
            Intent shareIntent = new Intent(Intent.ACTION_SEND);

            shareIntent.setType("text/plain");

            String shareText = receipt.toString();
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);

            startActivity(Intent.createChooser(shareIntent, "Bagikan melalui"));
            bottomSheetDialog.dismiss();
        });

        btnAddFavourite.setOnClickListener(v -> {
            new ViewModelProvider(this)
                    .get(FavouriteViewModel.class)
                    .pushFavourite(receipt);
            new CustomToast("Berhasil menambahkan ke Favorit!", v).show();
            bottomSheetDialog.dismiss();
        });

        btnSaveOffline.setOnClickListener(v -> {
            Toast.makeText(this, "Save Offline clicked", Toast.LENGTH_SHORT).show();
            bottomSheetDialog.dismiss();
        });

        btnSeeUserRating.setOnClickListener(v -> {
            Toast.makeText(this, "See User Rating clicked", Toast.LENGTH_SHORT).show();
            bottomSheetDialog.dismiss();
        });


        tvTitleRingkasan.setOnClickListener(v -> {
            if (vp.getCurrentItem() != 0) vp.setCurrentItem(0, true);
        });

        tvTitleBahan.setOnClickListener(v -> {
            if (vp.getCurrentItem() != 1) vp.setCurrentItem(1, true);
        });

        tvTitleLangkah.setOnClickListener(v -> {
            if (vp.getCurrentItem() != 2) vp.setCurrentItem(2, true);
        });

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new SummaryFragment(receipt));
        fragments.add(new IngredientsFragment(receipt.getIngredients()));
        fragments.add(new StepsFragment(receipt.getSteps()));

        DetailFragmentAdapter detailFragmentAdapter = new DetailFragmentAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(detailFragmentAdapter);

        List<TextView> pagerTitles = new ArrayList<TextView>() {{
            add(tvTitleRingkasan);
            add(tvTitleBahan);
            add(tvTitleLangkah);
        }};

        final int[] prevCurrPos = {0, 0}; // {prev, curr}

        tvTitleRingkasan.setTextAppearance(R.style.highlighted_pager_title);

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                pagerTitles.get(position).setTextAppearance(R.style.highlighted_pager_title);

                if (position != prevCurrPos[1]) {
                    prevCurrPos[0] = prevCurrPos[1];
                    prevCurrPos[1] = position;

                    pagerTitles.get(prevCurrPos[0]).setTextAppearance(R.style.unhighlighted_pager_title);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed(); // This is optional, but it's a good practice to call the super method.

        // Your custom code here, if needed.
    }

}