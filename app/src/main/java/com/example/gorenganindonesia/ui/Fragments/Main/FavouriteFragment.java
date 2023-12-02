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

import com.example.gorenganindonesia.API.Handlers.FavouriteHandler;
import com.example.gorenganindonesia.Model.DTO.APIHandlerDTO;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.ViewModel.FavouriteViewModel;
import com.example.gorenganindonesia.Model.data.Recipe.Recipe;
import com.example.gorenganindonesia.databinding.FragmentFavouriteBinding;
import com.example.gorenganindonesia.ui.Adapters.FavouritesAdapter;

import java.util.List;

public class FavouriteFragment extends Fragment {

    private FragmentFavouriteBinding binding;

    private List<Recipe> favouriteRecipe;
    private FavouriteHandler favouriteHandler;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavouriteViewModel favouriteViewModel = ((GlobalModel) requireContext().getApplicationContext()).getFavouriteViewModel();

        binding = FragmentFavouriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        favouriteHandler = new FavouriteHandler(new APIHandlerDTO(
                root,
                binding.llRootLoadingFavourite,
                binding.tvRootLoadingFavourites,
                requireContext()
        ));

        favouriteRecipe = favouriteViewModel.getFavourites().getValue();


        RecyclerView recyclerView = binding.rvFavourites;
        FavouritesAdapter adapter = new FavouritesAdapter(requireContext(), favouriteRecipe, favouriteHandler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
//        recyclerView.addItemDecoration(dividerItemDecoration);

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

        favouriteHandler.getFavourites();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}