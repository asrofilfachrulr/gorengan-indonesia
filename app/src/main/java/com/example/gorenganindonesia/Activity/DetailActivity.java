package com.example.gorenganindonesia.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.gorenganindonesia.Util.CustomToast;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.ViewModel.FavouriteViewModel;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.ui.Adapters.DetailFragmentAdapter;
import com.example.gorenganindonesia.ui.Fragments.Detail.IngredientsFragment;
import com.example.gorenganindonesia.ui.Fragments.Detail.StepsFragment;
import com.example.gorenganindonesia.ui.Fragments.Detail.SummaryFragment;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    TextView tvTitleRingkasan, tvTitleLangkah, tvTitleBahan;
    ImageView ivThumb;
    ImageButton btnBack, btnToggleFavourite;
    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitleRingkasan = (TextView) findViewById(R.id.tv_title_ringkasan);
        tvTitleBahan = (TextView) findViewById(R.id.tv_title_bahan);
        tvTitleLangkah = (TextView) findViewById(R.id.tv_title_langkah);

        ivThumb = (ImageView) findViewById(R.id.iv_thumb_detail);

        btnBack = (ImageButton) findViewById(R.id.ib_close_detail);
        btnToggleFavourite = (ImageButton) findViewById(R.id.ib_toggle_favourite);


        vp = (ViewPager) findViewById(R.id.vp_detail);

        Intent intent = getIntent();
        Recipe recipe = intent.getParcelableExtra("receipt");

        Glide
                .with(this)
                .load(recipe.getImgUrl())
                .placeholder(R.drawable.solid_grey_landscape)
                .error(R.drawable.img_404_landscape)
                .into(ivThumb);

        btnBack.setOnClickListener(v -> onBackPressed());

        FavouriteViewModel favViewModel = ((GlobalModel) getApplication()).getFavouriteViewModel();

        final boolean[] isReceiptExistInFav = {false};

        if(!favViewModel.ifFavouriteExist(recipe)){
            btnToggleFavourite.setImageResource(R.drawable.ic_favourite_outline);
        } else {
            btnToggleFavourite.setImageResource(R.drawable.ic_favourite_solid);
            isReceiptExistInFav[0] = true;
        }

        btnToggleFavourite.setOnClickListener(v -> {
            if(isReceiptExistInFav[0]){
                favViewModel.removeFavourite(recipe);
                new CustomToast("Berhasil menghapus dari Favorit", v, false).show();
                btnToggleFavourite.setImageResource(R.drawable.ic_favourite_outline);
                isReceiptExistInFav[0] = false;
            } else {
                favViewModel.pushFavourite(recipe);
                new CustomToast("Berhasil menambahkan ke Favorit!", v, false).show();
                btnToggleFavourite.setImageResource(R.drawable.ic_favourite_solid);
                isReceiptExistInFav[0] = true;
            }
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
        fragments.add(new SummaryFragment(recipe));
        fragments.add(new IngredientsFragment(recipe.getIngredients()));
        fragments.add(new StepsFragment(recipe.getSteps()));

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