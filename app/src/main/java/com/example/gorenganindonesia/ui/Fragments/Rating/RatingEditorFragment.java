package com.example.gorenganindonesia.ui.Fragments.Rating;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.gorenganindonesia.API.Handlers.RatingHandler;
import com.example.gorenganindonesia.Model.DTO.APIHandlerDTO;
import com.example.gorenganindonesia.R;

import java.util.ArrayList;
import java.util.List;

public class RatingEditorFragment extends DialogFragment {
    ImageButton ibStar1, ibStar2, ibStar3, ibStar4, ibStar5;
    EditText etComment;
    TextView tvLoading, tvTitle;
    LinearLayout llRootLoading;
    Button btnSave, btnCancel;
    List<ImageButton> stars = new ArrayList<>();
    Runnable getRatingsCallback;
    int starCount;
    String comment, recipeId;

    public static final int EDITMODE = 1;
    public static final int CREATEMODE = 0;

    int editorMode = 0;

    // create rating mode
    public RatingEditorFragment(
            String recipeId,
            TextView tvLoading,
            LinearLayout llRootLoading,
            Runnable getRatingsCallback
    ){
        super();

        this.starCount = 5; // default 5 stars
        this.comment = "";

        this.recipeId = recipeId;
        this.tvLoading = tvLoading;
        this.llRootLoading = llRootLoading;
        this.getRatingsCallback = getRatingsCallback;
        this.editorMode = RatingEditorFragment.CREATEMODE;
    }

    // edit rating mode
    public RatingEditorFragment(
            int starCount,
            String comment,
            String recipeId,
            TextView tvLoading,
            LinearLayout llRootLoading,
            Runnable getRatingsCallback
    ){
        super();

        this.starCount = starCount;
        this.comment = comment;
        this.recipeId = recipeId;
        this.tvLoading = tvLoading;
        this.llRootLoading = llRootLoading;
        this.getRatingsCallback = getRatingsCallback;
        this.editorMode = RatingEditorFragment.EDITMODE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.rating_editor_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        view.setBackgroundResource(android.R.color.transparent);

        tvTitle = (TextView) view.findViewById(R.id.tv_rating_editor_title);

        ibStar1 = (ImageButton) view.findViewById(R.id.ib_rating_editor_star_1);
        ibStar2 = (ImageButton) view.findViewById(R.id.ib_rating_editor_star_2);
        ibStar3 = (ImageButton) view.findViewById(R.id.ib_rating_editor_star_3);
        ibStar4 = (ImageButton) view.findViewById(R.id.ib_rating_editor_star_4);
        ibStar5 = (ImageButton) view.findViewById(R.id.ib_rating_editor_star_5);

        etComment = (EditText) view.findViewById(R.id.et_rating_editor_comment);

        btnSave = (Button) view.findViewById(R.id.btn_rating_editor_save);
        btnCancel = (Button) view.findViewById(R.id.btn_rating_editor_cancel);

        if(editorMode == RatingEditorFragment.EDITMODE)
            tvTitle.setText("Edit Ulasan");
        else if(editorMode == RatingEditorFragment.CREATEMODE)
            tvTitle.setText("Buat Ulasan");

        stars.add(ibStar1);
        stars.add(ibStar2);
        stars.add(ibStar3);
        stars.add(ibStar4);
        stars.add(ibStar5);

        populateData();

        for(int i = 0; i < 5; i++){
            int finalI = i;
            stars.get(i).setOnClickListener(v -> {
                starCount = finalI + 1;

                for(int j = 4; j >= 0; j--){
                    if(j > finalI)
                        stars.get(j).setImageResource(R.drawable.ic_star_outline_yellow);
                    else
                        stars.get(j).setImageResource(R.drawable.ic_star_solid_yellow);
                }
            });
        }

        btnCancel.setOnClickListener(v -> closeDialog());
        btnSave.setOnClickListener(v -> {
            comment = etComment.getText().toString();

            Log.i("Debug Rating Editor: Save", "comment: " + comment + "\nstar: " + String.valueOf(starCount));


            APIHandlerDTO dao = new APIHandlerDTO(view, tvLoading, tvTitle, requireContext());
            dao.setCallback(getRatingsCallback);

            RatingHandler ratingHandler = new RatingHandler(dao);

            if(editorMode == RatingEditorFragment.CREATEMODE)
                ratingHandler.postRating(recipeId, comment, starCount);
            else if (editorMode == RatingEditorFragment.EDITMODE)
                ratingHandler.putRating(recipeId, comment, starCount);

            closeDialog();
        });
    }

    public void populateData(){
        for(int i = 0; i < starCount; i++)
            stars.get(i).setImageResource(R.drawable.ic_star_solid_yellow);

        etComment.setText(comment);
    }

    public void closeDialog(){
        dismiss();
    }
}
