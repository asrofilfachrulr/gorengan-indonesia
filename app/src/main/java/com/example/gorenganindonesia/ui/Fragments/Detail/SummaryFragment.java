package com.example.gorenganindonesia.ui.Fragments.Detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gorenganindonesia.Activity.RatingActivity;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.ViewModel.FavouriteViewModel;
import com.example.gorenganindonesia.Model.data.Receipt.Receipt;
import com.example.gorenganindonesia.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class SummaryFragment extends Fragment {
    TextView tvTitle, tvCategory, tvDifficulty, tvTime, tvPortion, tvStep, tvIngridient;
    ImageButton ibMore;
    Button btnShare, btnSaveOffline, btnSeeUserRating;
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

        ibMore = (ImageButton) view.findViewById(R.id.ib_more_detail);

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

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(view.getContext());
        bottomSheetDialog.setContentView(R.layout.bottom_dialog_menu_detail);

        btnShare = (Button) bottomSheetDialog.findViewById(R.id.btn_share);
        btnSaveOffline = (Button) bottomSheetDialog.findViewById(R.id.btn_save_offline);
        btnSeeUserRating = (Button) bottomSheetDialog.findViewById(R.id.btn_see_user_rating);


        ibMore.setOnClickListener(v -> bottomSheetDialog.show());

        btnShare.setOnClickListener(v -> {
            // Create an Intent with the ACTION_SEND action
            Intent shareIntent = new Intent(Intent.ACTION_SEND);

            shareIntent.setType("text/plain");

            String shareText = receipt.toString();
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);

            startActivity(Intent.createChooser(shareIntent, "Bagikan melalui"));
            bottomSheetDialog.dismiss();
        });


        btnSaveOffline.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Save Offline clicked", Toast.LENGTH_SHORT).show();
            bottomSheetDialog.dismiss();
        });

        btnSeeUserRating.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
            Intent intent = new Intent(getContext(), RatingActivity.class);
            getContext().startActivity(intent);
        });

        return view;
    }
}