package com.example.gorenganindonesia.Detail.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gorenganindonesia.Model.Receipt.Receipt;
import com.example.gorenganindonesia.R;


public class SummaryFragment extends Fragment {
    TextView tvTitle, tvCategory, tvDifficulty, tvTime, tvPortion, tvStep, tvIngridient;
    LinearLayout llSteps, llIngridients;
    ViewPager vp;
    Receipt receipt;

    public SummaryFragment() { }

    public SummaryFragment(Receipt receipt){
        this.receipt = receipt;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        vp = getActivity().findViewById(R.id.vp_detail);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_summary, container, false);

        tvTitle = (TextView) view.findViewById(R.id.tv_title_detail);
        tvCategory = (TextView) view.findViewById(R.id.tv_category_detail);
        tvDifficulty = (TextView) view.findViewById(R.id.tv_difficulty_detail);
        tvTime = (TextView) view.findViewById(R.id.tv_time_detail);
        tvPortion = (TextView) view.findViewById(R.id.tv_portion_detail);
        tvStep = (TextView) view.findViewById(R.id.tv_step_detail);
        tvIngridient = (TextView) view.findViewById(R.id.tv_ingredient_detail);

        llSteps = (LinearLayout) view.findViewById(R.id.ll_steps_detail);
        llIngridients = (LinearLayout) view.findViewById(R.id.ll_ingredients_detail);

        tvTitle.setText(receipt.getTitle().toString());
        tvCategory.setText("Kategori " + receipt.getCategory().toString());
        tvDifficulty.setText(receipt.getDifficulty().toString());
        tvPortion.setText(String.valueOf(receipt.getPortion()) + " Porsi");
        tvTime.setText( "±" + String.valueOf(receipt.getMinuteDuration()) + " Menit");
        tvStep.setText(String.valueOf(receipt.getSteps().length) + " Langkah");
        tvIngridient.setText(String.valueOf(receipt.getIngredients().length) + " Bahan");

        llSteps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(2, true);
            }
        });

        llIngridients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vp.setCurrentItem(1, true);
            }
        });

        return view;
    }
}