package com.example.gorenganindonesia.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gorenganindonesia.API.Handlers.RatingHandler;
import com.example.gorenganindonesia.Model.DTO.APIHandlerDTO;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.data.Rating.Rating;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.Util.RecyclerViewItemSpacing;
import com.example.gorenganindonesia.Util.ToastUseCase;
import com.example.gorenganindonesia.ui.Adapters.RatingAdapter;
import com.example.gorenganindonesia.ui.Adapters.StarFilterAdapter;
import com.example.gorenganindonesia.ui.Fragments.Rating.RatingEditorFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RatingActivity extends AppCompatActivity {
    TextView tvTitle, tvStar, tvRatingCount, tvLoading;
    ImageButton ibBack, ibAdd;
    ProgressBar pb5, pb4, pb3, pb2, pb1;
    RecyclerView rvStarFilter, rvRating;
    Map<Integer, Integer> starCountMap = new HashMap<>();
    LinearLayout llRootLoadingRating;
    Recipe recipe;
    String recipeId;

    View view;

    List<Rating> ratings;

    String username;
    public float starAvg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        view = LayoutInflater.from(this).inflate(R.layout.activity_rating, null);

        Intent intent = getIntent();
        recipe = intent.getParcelableExtra("recipe");
        recipeId = intent.getStringExtra("recipeId");

        if (recipeId.isEmpty() || recipeId == null) {
            ToastUseCase.showUnexpectedError(view);
            this.finish();
            return;
        }

        username = ((GlobalModel) getApplication()).getAccountViewModel().getUsername();

        tvStar = (TextView) findViewById(R.id.tv_star_rating);
        tvTitle = (TextView) findViewById(R.id.tv_title_rating);
        tvRatingCount = (TextView) findViewById(R.id.tv_ratingcount_rating);
        tvLoading = (TextView) findViewById(R.id.tv_root_loading_rating);

        ibBack = (ImageButton) findViewById(R.id.ib_back_rating);
        ibAdd = (ImageButton) findViewById(R.id.ib_add_rating);

        pb5 = (ProgressBar) findViewById(R.id.pb_5_rating);
        pb4 = (ProgressBar) findViewById(R.id.pb_4_rating);
        pb3 = (ProgressBar) findViewById(R.id.pb_3_rating);
        pb2 = (ProgressBar) findViewById(R.id.pb_2_rating);
        pb1 = (ProgressBar) findViewById(R.id.pb_1_rating);

        llRootLoadingRating = (LinearLayout) findViewById(R.id.ll_root_loading_rating);

        ibBack.setOnClickListener(v -> this.finish());


        rvRating = (RecyclerView) findViewById(R.id.rv_rating);
        rvStarFilter = (RecyclerView) findViewById(R.id.rv_rating_star_fitler);

        tvTitle.setText(recipe.getTitle());
        tvStar.setText(String.valueOf(recipe.getStars()) + "/5.0");
        tvRatingCount.setText("(0 Ulasan)");


        List<String> stars = new ArrayList<>();

        stars.add("semua");
        stars.add("5");
        stars.add("4");
        stars.add("3");
        stars.add("2");
        stars.add("1");

        ratings = new ArrayList<>();

        Runnable getRatingsCallback = () -> {
            getRatings(recipe.getId());
        };

        getRatingsCallback.run();

        ibAdd.setOnClickListener(v -> {
            RatingEditorFragment ratingEditorFragment = new RatingEditorFragment(
                    recipe.getId(),
                    tvLoading,
                    llRootLoadingRating,
                    getRatingsCallback
            );

            ratingEditorFragment.show(getSupportFragmentManager(), "RATING_EDITOR_FRAGMENT");
        });

        RatingAdapter ratingAdapter = new RatingAdapter(ratings, recipe.getId(), getRatingsCallback, tvLoading, llRootLoadingRating, username, getSupportFragmentManager());
        LinearLayoutManager ratingLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvRating.setLayoutManager(ratingLinearLayoutManager);
        rvRating.setAdapter(ratingAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvRating.getContext(), DividerItemDecoration.VERTICAL);
        rvRating.addItemDecoration(dividerItemDecoration);

        StarFilterAdapter starAdapter = new StarFilterAdapter(stars, ratingAdapter);
        LinearLayoutManager starLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvStarFilter.setLayoutManager(starLinearLayoutManager);
        rvStarFilter.setAdapter(starAdapter);
        rvStarFilter.addItemDecoration(new RecyclerViewItemSpacing(this, 10));


        ((GlobalModel) getApplication()).getRecipeViewModel().getAllRecipes().observe(this, updatedData -> {
            List<Rating> updatedRating = getRatings();
            if (updatedRating != null) {
                ratingAdapter.updateData(updatedRating);
                tvRatingCount.setText("(" + String.valueOf(updatedRating.size()) + " Ulasan)");

                initMapperPbRating();

                updateStarCountMapper(updatedRating);
                updatePbRating(updatedRating.size());

                ibAdd.setVisibility(View.VISIBLE);

                for(Rating rating: updatedRating){
                    if(rating.getAuthorUsername().equals(username)){
                        ibAdd.setVisibility(View.GONE);
                        break;
                    }
                }

                if(starAvg != 0)
                    tvStar.setText(String.valueOf(starAvg) + "/5.0");

                rvRating.scrollToPosition(0);
                view.scrollTo(0,0);
            }
        });
    }

    public void initMapperPbRating(){
        starCountMap = new HashMap<>();

        starCountMap.put(5,0);
        starCountMap.put(4,0);
        starCountMap.put(3,0);
        starCountMap.put(2,0);
        starCountMap.put(1,0);
    }

    // only invoked when ratings is populated
    private void updateStarCountMapper(List<Rating> ratings) {
        for(Rating rating: ratings){
            switch (rating.getStarCount()){
                case 5:
                    starCountMap.put(5, starCountMap.get(5) + 1);
                    break;
                case 4:
                    starCountMap.put(4, starCountMap.get(4) + 1);
                    break;
                case 3:
                    starCountMap.put(3, starCountMap.get(3) + 1);
                    break;
                case 2:
                    starCountMap.put(2, starCountMap.get(2) + 1);
                    break;
                case 1:
                    starCountMap.put(1, starCountMap.get(1) + 1);
                    break;
            }
        }
    }

    private void updatePbRating(int total){
        if(total == 0) return;

        starCountMap.forEach((key, value) -> System.out.println(key + " " + value));

        pb5.setProgress(Math.round((100 * starCountMap.get(5)) / total));
        pb4.setProgress(Math.round((100 * starCountMap.get(4)) / total));
        pb3.setProgress(Math.round((100 * starCountMap.get(3)) / total));
        pb2.setProgress(Math.round((100 * starCountMap.get(2)) / total));
        pb1.setProgress(Math.round((100 * starCountMap.get(1)) / total));
    }

    private void getRatings(String recipeId) {
        getRatingsAPIRequest(recipeId, "date");
    }
    
    private void getRatingsAPIRequest(String recipeId, String queryOrder) {
        APIHandlerDTO dto = new APIHandlerDTO(view, llRootLoadingRating, tvLoading, this);
        RatingHandler ratingHandler = new RatingHandler(dto);

        ratingHandler.getRatings(recipeId, queryOrder, this);
    }

    private List<Rating> getRatings(){
        Rating[] ratings = ((GlobalModel) getApplication()).getRecipeViewModel().getRecipeById(recipeId).getRatings();

        return ratings == null ? new ArrayList<>(): Arrays.asList(ratings);
    }
}