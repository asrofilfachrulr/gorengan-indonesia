package com.example.gorenganindonesia.ui.Fragments.Detail;

import android.app.AlertDialog;
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

import com.example.gorenganindonesia.API.Handlers.RecipeHandler;
import com.example.gorenganindonesia.Activity.RatingActivity;
import com.example.gorenganindonesia.Activity.RecipeEditorActivity;
import com.example.gorenganindonesia.Model.DTO.APIHandlerDTO;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.data.Ingredient.Ingredient;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.Util.Logger;
import com.example.gorenganindonesia.Util.ToastUseCase;
import com.google.android.material.bottomsheet.BottomSheetDialog;


public class SummaryFragment extends Fragment {
    TextView tvTitle, tvCategory, tvDifficulty, tvTime, tvPortion, tvStep, tvIngridient, tvAuthorUsername, tvStarRating;
    ImageButton ibMore;
    Button btnShare, btnSaveOffline, btnSeeUserRating, btnEdit, btnDelete;
    LinearLayout llSteps, llIngridients;
    ViewPager vp;
    Recipe recipe;
    int index;

    public SummaryFragment() { }

    public SummaryFragment(Recipe recipe, int index){
        this.recipe = recipe;
        this.index = index;
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
        tvAuthorUsername = (TextView) view.findViewById(R.id.tv_author_username_detail);
        tvStarRating = (TextView) view.findViewById(R.id.tv_rating_star_detail);

        llSteps = (LinearLayout) view.findViewById(R.id.ll_steps_detail);
        llIngridients = (LinearLayout) view.findViewById(R.id.ll_ingredients_detail);

        ibMore = (ImageButton) view.findViewById(R.id.ib_more_detail);

        if(recipe == null){
            ToastUseCase.showUnexpectedError(view);
            getActivity().finish();
            return null;
        }

        setViewData();

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

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
        bottomSheetDialog.setContentView(R.layout.bottom_dialog_menu_detail);

        btnShare = (Button) bottomSheetDialog.findViewById(R.id.btn_share);
        btnSaveOffline = (Button) bottomSheetDialog.findViewById(R.id.btn_save_offline);
        btnSeeUserRating = (Button) bottomSheetDialog.findViewById(R.id.btn_see_user_rating);

        btnEdit = (Button) bottomSheetDialog.findViewById(R.id.btn_edit_recipe);
        btnDelete = (Button) bottomSheetDialog.findViewById(R.id.btn_delete_recipe);


        String username = ((GlobalModel) requireContext().getApplicationContext()).getAccountViewModel().getUsername();
        String authorUsername = recipe.getAuthorUsername();

        if(username.equals(authorUsername)){
            btnDelete.setVisibility(View.VISIBLE);
            btnEdit.setVisibility(View.VISIBLE);

            // delete
            btnDelete.setOnClickListener(v -> {
                bottomSheetDialog.dismiss();
                APIHandlerDTO dto = new APIHandlerDTO(v, requireContext(), null);

                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder
                        .setMessage("Anda yakin ingin menghapus Resep \"" + recipe.getTitle() + "\"? Aktivitas ini tidak dapat dipulihkan")
                        .setPositiveButton("Hapus", (dialog, which) -> {
                            dto.setCallback(() -> {
                                APIHandlerDTO getDTO = dto;
                                getDTO.setCallback(() -> {
                                    getActivity().finish();
                                });
                                RecipeHandler getHandler = new RecipeHandler(getDTO);
                                getHandler.getAllRecipes();

                                dialog.dismiss();
                            });
                            RecipeHandler deleteHandler = new RecipeHandler(dto);
                            deleteHandler.deleteRecipe(recipe.getId());
                        })
                        .setNegativeButton("Batal", (dialog, which) -> {dialog.dismiss();})
                        .show();
            });

            // edit
            btnEdit.setOnClickListener(v -> {
                Intent intent = new Intent(requireContext(), RecipeEditorActivity.class);
                intent.putExtra("recipe", recipe);

                bottomSheetDialog.dismiss();

                startActivity(intent);
            });
        }


        ibMore.setOnClickListener(v -> bottomSheetDialog.show());

        btnShare.setOnClickListener(v -> {
            // Create an Intent with the ACTION_SEND action
            Intent shareIntent = new Intent(Intent.ACTION_SEND);

            shareIntent.setType("text/plain");

            String shareText = recipe.toString();
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);

            startActivity(Intent.createChooser(shareIntent, "Bagikan melalui"));
            bottomSheetDialog.dismiss();
        });


        btnSaveOffline.setOnClickListener(v -> {
            ToastUseCase.showInDevelopment(v);
            bottomSheetDialog.dismiss();
        });

        btnSeeUserRating.setOnClickListener(v -> {
            bottomSheetDialog.dismiss();
            Intent intent = new Intent(getContext(), RatingActivity.class);
            intent.putExtra("recipe", recipe);
            intent.putExtra("index", index);
            getContext().startActivity(intent);
        });

        ((GlobalModel) getContext().getApplicationContext()).getRecipeViewModel().getAllRecipes().observe(getViewLifecycleOwner(), updatedRecipes -> {
            Logger.SimpleLog("[Summary Fragment] Recipes Observer Triggered");
            Recipe updatedRecipe = updatedRecipes.get(index);
            if(updatedRecipe.getSteps() != null && updatedRecipe.getIngredients() != null){
                recipe = updatedRecipe;
            } else {
                Ingredient[] ingredients = recipe.getIngredients();
                String[] steps = recipe.getSteps();

                recipe = updatedRecipe;
                recipe.setIngredients(ingredients);
                recipe.setSteps(steps);
            }
            setViewData();
        });

        return view;
    }

    public void setViewData(){
        tvTitle.setText(recipe.getTitle().toString());
        tvCategory.setText("Kategori " + recipe.getCategory().toString());
        tvDifficulty.setText(recipe.getDifficulty().toString());
        tvPortion.setText(String.valueOf(recipe.getPortion()) + " Porsi");
        tvTime.setText( "±" + String.valueOf(recipe.getMinuteDuration()) + " Menit");
        tvStep.setText(String.valueOf(recipe.getSteps().length) + " Langkah");
        tvIngridient.setText(String.valueOf(recipe.getIngredients().length) + " Bahan");
        tvAuthorUsername.setText("Resep oleh @"+ recipe.getAuthorUsername().toString());
        tvStarRating.setText(String.valueOf(recipe.getStars()));
    }
}