package com.example.gorenganindonesia.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.gorenganindonesia.API.Handlers.FavouriteHandler;
import com.example.gorenganindonesia.API.Handlers.RecipeDetailHandler;
import com.example.gorenganindonesia.Model.DTO.APIHandlerDTO;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.ViewModel.FavouriteViewModel;
import com.example.gorenganindonesia.Model.data.Ingredient.Ingredient;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.Util.CustomToast;
import com.example.gorenganindonesia.ui.Adapters.DetailFragmentAdapter;
import com.example.gorenganindonesia.ui.Fragments.Detail.IngredientsFragment;
import com.example.gorenganindonesia.ui.Fragments.Detail.StepsFragment;
import com.example.gorenganindonesia.ui.Fragments.Detail.SummaryFragment;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    TextView tvTitleRingkasan, tvTitleLangkah, tvTitleBahan, tvStep, tvIngredient, tvLoading;
    ImageView ivThumb;
    ImageButton btnBack, btnToggleFavourite;
    ViewPager vp;
    LinearLayout llRootLoadingDetail;
    Activity thisActivity;

    Fragment summaryFragment, ingredientsFragment, stepsFragment;

    int position;

    View summaryView;
    View view;

    RecipeDetailHandler recipeDetailHandler;
    FavouriteHandler favouriteHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        view = LayoutInflater.from(this).inflate(R.layout.activity_detail, null);

        thisActivity = this;

        tvTitleRingkasan = (TextView) findViewById(R.id.tv_title_ringkasan);
        tvTitleBahan = (TextView) findViewById(R.id.tv_title_bahan);
        tvTitleLangkah = (TextView) findViewById(R.id.tv_title_langkah);
        tvLoading = (TextView) findViewById(R.id.tv_root_loading_detail);

        // inside summary fragment
        summaryView = LayoutInflater.from(this).inflate(R.layout.fragment_summary, null);
        tvStep = (TextView) summaryView.findViewById(R.id.tv_step_detail);
        tvIngredient = (TextView) summaryView.findViewById(R.id.tv_ingredient_detail);

        ivThumb = (ImageView) findViewById(R.id.iv_thumb_detail);

        btnBack = (ImageButton) findViewById(R.id.ib_close_detail);
        btnToggleFavourite = (ImageButton) findViewById(R.id.ib_toggle_favourite);

        llRootLoadingDetail = (LinearLayout) findViewById(R.id.ll_root_loading_detail);

        vp = (ViewPager) findViewById(R.id.vp_detail);

        APIHandlerDTO dao = new APIHandlerDTO(
                view,
                llRootLoadingDetail,
                tvLoading,
                this
        );

        recipeDetailHandler = new RecipeDetailHandler(dao);
        favouriteHandler = new FavouriteHandler(dao);

        Intent intent = getIntent();
        Recipe recipe = intent.getParcelableExtra("recipe");
        position = intent.getIntExtra("position", -1);

        if (position == -1)
            this.finish();

        Glide
                .with(this)
                .load(recipe.getImgUrl())
                .placeholder(R.drawable.solid_grey_landscape)
                .error(R.drawable.img_404_landscape)
                .into(ivThumb);

        btnBack.setOnClickListener(v -> {
            this.finish();
        });

        FavouriteViewModel favViewModel = ((GlobalModel) getApplication()).getFavouriteViewModel();

        final boolean[] isReceiptExistInFav = {false};

        if (!favViewModel.ifFavouriteExist(recipe)) {
            btnToggleFavourite.setImageResource(R.drawable.ic_favourite_outline);
        } else {
            btnToggleFavourite.setImageResource(R.drawable.ic_favourite_solid);
            isReceiptExistInFav[0] = true;
        }

        btnToggleFavourite.setOnClickListener(v -> {
            if (isReceiptExistInFav[0]) {
                APIHandlerDTO daoRemove = favouriteHandler.getDto();
                daoRemove.setCallback(() -> {
                    new CustomToast("Berhasil menghapus dari Favorit", v, false).show();
                    btnToggleFavourite.setImageResource(R.drawable.ic_favourite_outline);
                    isReceiptExistInFav[0] = false;

                    favouriteHandler.getFavourites();
                });
                favouriteHandler.setDto(daoRemove);
                favouriteHandler.deleteFavourite(recipe.getId());
//                favViewModel.removeFavourite(recipe);
            } else {
                APIHandlerDTO daoPush = favouriteHandler.getDto();
                daoPush.setCallback(() -> {
                    new CustomToast("Berhasil menambahkan ke Favorit!", v, false).show();
                    btnToggleFavourite.setImageResource(R.drawable.ic_favourite_solid);
                    isReceiptExistInFav[0] = true;

                    favouriteHandler.getFavourites();
                });
                favouriteHandler.setDto(daoPush);
                favouriteHandler.postFavourite(recipe.getId());
//                favViewModel.pushFavourite(recipe);
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


        if (recipe.getIngredients() == null || recipe.getSteps() == null) {
            llRootLoadingDetail.setVisibility(View.VISIBLE);

            Ingredient[] ingredientsEmpty = {};
            recipe.setIngredients(ingredientsEmpty);

            String[] stepsEmpty = {};
            recipe.setSteps(stepsEmpty);

            recipeDetailHandler.getIngredients(recipe.getId(), position);
            recipeDetailHandler.getSteps(recipe.getId(), position);
        }

        summaryFragment = new SummaryFragment(recipe, position);
        ingredientsFragment = new IngredientsFragment(recipe.getIngredients(), position);
        stepsFragment = new StepsFragment(recipe.getSteps(), position);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(summaryFragment);
        fragments.add(ingredientsFragment);
        fragments.add(stepsFragment);

        DetailFragmentAdapter detailFragmentAdapter = new DetailFragmentAdapter(getSupportFragmentManager(), fragments);
        vp.setAdapter(detailFragmentAdapter);
    }
}