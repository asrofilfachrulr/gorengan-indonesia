package com.example.gorenganindonesia.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.gorenganindonesia.Util.CustomToast;
import com.example.gorenganindonesia.Model.data.Rating.Rating;
import com.example.gorenganindonesia.Model.data.Rating.RatingData;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.Util.RecyclerViewItemSpacing;
import com.example.gorenganindonesia.ui.Adapters.RatingAdapter;
import com.example.gorenganindonesia.ui.Adapters.StarFilterAdapter;

import java.util.ArrayList;
import java.util.List;

public class RatingActivity extends AppCompatActivity {
    TextView tvTitle, tvStar;
    ImageButton ibSort, ibBack;
    RecyclerView rvStarFilter, rvRating;
    Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        Intent intent = getIntent();
        recipe = intent.getParcelableExtra("receipt");

        tvStar = (TextView) findViewById(R.id.tv_star_rating);
        tvTitle = (TextView) findViewById(R.id.tv_title_rating);

        ibSort = (ImageButton) findViewById(R.id.ib_sort_rating);
        ibBack = (ImageButton) findViewById(R.id.ib_back_rating);

        rvRating = (RecyclerView) findViewById(R.id.rv_rating);
        rvStarFilter = (RecyclerView) findViewById(R.id.rv_rating_star_fitler);

        tvTitle.setText(recipe.getTitle().toString());
        tvStar.setText(recipe.getRatingStar() + "/5.0");

        List<String> stars = new ArrayList<>();

        stars.add("semua");
        stars.add("5");
        stars.add("4");
        stars.add("3");
        stars.add("2");
        stars.add("1");

        StarFilterAdapter starAdapter = new StarFilterAdapter(stars);
        LinearLayoutManager starLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvStarFilter.setLayoutManager(starLinearLayoutManager);
        rvStarFilter.setAdapter(starAdapter);
        rvStarFilter.addItemDecoration(new RecyclerViewItemSpacing(this, 10));

        //TODO: change to real rating data when API is ready, bind rating to receipt object
        List<Rating> ratings = RatingData.generate();
        RatingAdapter ratingAdapter = new RatingAdapter(ratings);
        LinearLayoutManager ratingLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvRating.setLayoutManager(ratingLinearLayoutManager);
        rvRating.setAdapter(ratingAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvRating.getContext(), DividerItemDecoration.VERTICAL);
        rvRating.addItemDecoration(dividerItemDecoration);

        ibSort.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(this, v);
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.sort_rating_menu, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(item -> {
                if(item.equals(R.id.menu_sort_latest)){
                    new CustomToast("Urutkan Terbaru...", v, false).show();
                } else if (item.equals(R.id.menu_sort_most_popular)) {
                    new CustomToast("Urutkan Paling Populer...", v, false).show();
                }

                return true;
            });

            popupMenu.show();
        });

        ibBack.setOnClickListener(v -> {
            this.finish();
        });

    }
}