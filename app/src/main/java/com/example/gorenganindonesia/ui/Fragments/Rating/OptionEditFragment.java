package com.example.gorenganindonesia.ui.Fragments.Rating;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LifecycleObserver;

import com.example.gorenganindonesia.R;

import java.util.ArrayList;
import java.util.List;

public class OptionEditFragment extends DialogFragment {
    ImageButton ibStar1, ibStar2, ibStar3, ibStar4, ibStar5;
    EditText etComment;
    Button btnSave, btnCancel;
    List<ImageButton> stars = new ArrayList<>();
    int starCount;
    String comment;

    public OptionEditFragment(int starCount, String comment){
        super();

        this.starCount = starCount;
        this.comment = comment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.rating_option_dialogue, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        view.setBackgroundResource(android.R.color.transparent);

        ibStar1 = (ImageButton) view.findViewById(R.id.ib_rating_option_edit_star_1);
        ibStar2 = (ImageButton) view.findViewById(R.id.ib_rating_option_edit_star_2);
        ibStar3 = (ImageButton) view.findViewById(R.id.ib_rating_option_edit_star_3);
        ibStar4 = (ImageButton) view.findViewById(R.id.ib_rating_option_edit_star_4);
        ibStar5 = (ImageButton) view.findViewById(R.id.ib_rating_option_edit_star_5);

        etComment = (EditText) view.findViewById(R.id.et_rating_option_edit_comment);

        btnSave = (Button) view.findViewById(R.id.btn_rating_option_edit_save);
        btnCancel = (Button) view.findViewById(R.id.btn_rating_option_edit_cancel);

        stars.add(ibStar1);
        stars.add(ibStar2);
        stars.add(ibStar3);
        stars.add(ibStar4);
        stars.add(ibStar5);

        populateData();

        for(int i = 0; i < 5; i++){
            int finalI = i;
            stars.get(i).setOnClickListener(v -> {
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
            //TODO: retrofit put new rating
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
