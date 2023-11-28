package com.example.gorenganindonesia.ui.Fragments.Main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gorenganindonesia.API.RetrofitClient;
import com.example.gorenganindonesia.API.Services.FavouritesService;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.ViewModel.FavouriteViewModel;
import com.example.gorenganindonesia.Model.api.GetFavouritesResponse;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.Util.CustomToast;
import com.example.gorenganindonesia.databinding.FragmentFavouriteBinding;
import com.example.gorenganindonesia.ui.Adapters.FavouritesAdapter;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteFragment extends Fragment {

    private FragmentFavouriteBinding binding;

    public List<Recipe> favouriteRecipe;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavouriteViewModel favouriteViewModel = ((GlobalModel) requireContext().getApplicationContext()).getFavouriteViewModel();

        binding = FragmentFavouriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        favouriteRecipe = favouriteViewModel.getFavourites().getValue();


        RecyclerView recyclerView = binding.rvFavourites;
        FavouritesAdapter adapter = new FavouritesAdapter(requireContext(), favouriteRecipe);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        favouriteViewModel.getFavourites().observe(getViewLifecycleOwner(), updatedFavourites -> {
            adapter.updateData(updatedFavourites);
            if(updatedFavourites.size() > 0){
                binding.svListFav.setVisibility(View.VISIBLE);
                binding.llEmptyFavSign.setVisibility(View.GONE);
            } else {
                binding.svListFav.setVisibility(View.GONE);
                binding.llEmptyFavSign.setVisibility(View.VISIBLE);
            }
            Log.e("Debug Live Data", "List Size on Fragment: " + String.valueOf(favouriteRecipe.size()));
        });

        String token = "Bearer " + ((GlobalModel) requireContext().getApplicationContext()).getSessionManager().getToken();
        getFavourite(token, root);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void getFavourite(String token, View view){
        binding.llRootLoadingFavourite.setVisibility(View.VISIBLE);
        RetrofitClient
                .getInstance()
                .create(FavouritesService.class)
                .getFavourites(token)
                .enqueue(new Callback<GetFavouritesResponse>() {
                    @Override
                    public void onResponse(Call<GetFavouritesResponse> call, Response<GetFavouritesResponse> response) {
                        if(response.isSuccessful()){
                            String[] recipeids = response.body().getFavourites();

                            List<Recipe> favRecipes = ((GlobalModel) requireContext().getApplicationContext()).getRecipeViewModel().getRecipesByIds(recipeids);
                            ((GlobalModel) requireContext().getApplicationContext()).getFavouriteViewModel().setFavourites(favRecipes);
                            binding.llRootLoadingFavourite.setVisibility(View.GONE);
                        } else {
                            binding.llRootLoadingFavourite.setVisibility(View.GONE);
                            try {
                                new CustomToast("Error Mendapatkan Data: " + response.errorBody().string(), view, false).show();
                            } catch (IOException e) {
                                new CustomToast("Error Mengolah Data", view, false).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<GetFavouritesResponse> call, Throwable t) {
                        binding.llRootLoadingFavourite.setVisibility(View.GONE);
                        new CustomToast("Error Memuat Data", view, false).show();
                    }
                });
    }
}