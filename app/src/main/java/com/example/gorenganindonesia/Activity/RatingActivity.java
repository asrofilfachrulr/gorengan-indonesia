package com.example.gorenganindonesia.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gorenganindonesia.API.RetrofitClient;
import com.example.gorenganindonesia.API.Services.recipe.recipeId.RatingsService;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.api.Recipe.Ratings.GetRatingsResponse;
import com.example.gorenganindonesia.Model.api.Recipe.Ratings.RatingData;
import com.example.gorenganindonesia.Model.data.Rating.Rating;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.Util.CustomToast;
import com.example.gorenganindonesia.Util.RecyclerViewItemSpacing;
import com.example.gorenganindonesia.ui.Adapters.RatingAdapter;
import com.example.gorenganindonesia.ui.Adapters.StarFilterAdapter;
import com.example.gorenganindonesia.ui.View.EmptySpaceView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RatingActivity extends AppCompatActivity {
    TextView tvTitle, tvStar, tvRatingCount, tvLoading;
    ImageButton ibBack;
    ProgressBar pb5, pb4, pb3, pb2, pb1;
    RecyclerView rvStarFilter, rvRating;
    Map<Integer, Integer> starCountMap = new HashMap<>();
    LinearLayout llRootLoadingRating;
    Recipe recipe;
    int index;
    View view;

    List<Rating> ratings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        view = LayoutInflater.from(this).inflate(R.layout.activity_rating, null);

        Intent intent = getIntent();
        recipe = intent.getParcelableExtra("recipe");
        index = intent.getIntExtra("index", -1);

        if (index == -1) this.finish();

        tvStar = (TextView) findViewById(R.id.tv_star_rating);
        tvTitle = (TextView) findViewById(R.id.tv_title_rating);
        tvRatingCount = (TextView) findViewById(R.id.tv_ratingcount_rating);
        tvLoading = (TextView) findViewById(R.id.tv_root_loading_rating);

        ibBack = (ImageButton) findViewById(R.id.ib_back_rating);

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
            llRootLoadingRating.setVisibility(View.VISIBLE);
            tvLoading.setText("Memuat Ulasan...");
            getRatings(recipe.getId());
        };

        getRatingsCallback.run();

        RatingAdapter ratingAdapter = new RatingAdapter(ratings, recipe.getId(), getRatingsCallback, tvLoading, llRootLoadingRating, getSupportFragmentManager());
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
            if (updatedData.get(index).getRatings() != null) {
                ratingAdapter.updateData(Arrays.asList(updatedData.get(index).getRatings()));
                tvRatingCount.setText("(" + String.valueOf(updatedData.get(index).getRatings().length) + " Ulasan)");

                initMapperPbRating();

                updateStarCountMapper(updatedData.get(index).getRatings());
                updatePbRating(updatedData.get(index).getRatings().length);

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
    private void updateStarCountMapper(Rating[] ratings) {
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

    private void getRatings(String recipeId, String queryOrder) {
        getRatingsAPIRequest(recipeId, queryOrder);
    }

    private void getRatingsAPIRequest(String recipeId, String queryOrder) {
        String token = "Bearer " + ((GlobalModel) getApplication()).getSessionManager().getToken();

        RetrofitClient
                .getInstance()
                .create(RatingsService.class)
                .getRatingsByRecipeId(recipeId, token, queryOrder)
                .enqueue(new Callback<GetRatingsResponse>() {
                    @Override
                    public void onResponse(Call<GetRatingsResponse> call, Response<GetRatingsResponse> response) {
                        llRootLoadingRating.setVisibility(View.GONE);

                        if (response.isSuccessful()) {
                            RatingData[] ratingData = response.body().getRatingData();
                            final int sz = ratingData.length;
                            Rating[] ratings = new Rating[sz];

                            for (int i = 0; i < sz; i++) {
                                Rating rating = new Rating(
                                        ratingData[i].getComment(),
                                        ratingData[i].getDate(),
                                        ratingData[i].getUsername(),
                                        ratingData[i].getStars(),
                                        ratingData[i].getThumbUrl(),
                                        ratingData[i].getLikeCount()
                                );

                                ratings[i] = rating;
                            }

                            ((GlobalModel) getApplication()).getRecipeViewModel().setRatings(ratings, index);
                        } else {
                            try {
                                new CustomToast("Error Mengolah Data: " + response.errorBody().string(), view, false).show();
                            } catch (IOException e) {
                                new CustomToast("Error Mengolah Data", view, false).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<GetRatingsResponse> call, Throwable t) {
                        llRootLoadingRating.setVisibility(View.GONE);
                        new CustomToast("Error Memuat Data", view, false).show();
                    }
                });
    }
}