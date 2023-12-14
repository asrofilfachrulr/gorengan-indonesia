package com.example.gorenganindonesia.ui.Fragments.Main;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

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

        APIHandlerDTO dto = new APIHandlerDTO(root,requireContext());
        dto.setDaemonMode(APIHandlerDTO.DAEMON_MODE);
        favouriteHandler = new FavouriteHandler(dto);

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
                binding.nsvListFav.setVisibility(View.VISIBLE);
                binding.llEmptyFavSign.setVisibility(View.GONE);
            } else {
                binding.nsvListFav.setVisibility(View.GONE);
                binding.llEmptyFavSign.setVisibility(View.VISIBLE);
            }
            Log.e("Debug Live Data", "List Size on Fragment: " + String.valueOf(favouriteRecipe.size()));
        });

        favouriteHandler.getFavourites();

        binding.etSearchFavourite.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString();
                adapter.applyFiler(str);
            }
        });

        binding.ivUnderlayTopFavourite.setOnTouchListener((v, event) -> {
            binding.etSearchFavourite.clearFocus();
            InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            return false;
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}