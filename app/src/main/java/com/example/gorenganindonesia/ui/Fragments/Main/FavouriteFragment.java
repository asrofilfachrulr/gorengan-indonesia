package com.example.gorenganindonesia.ui.Fragments.Main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gorenganindonesia.Model.ViewModel.FavouriteViewModel;
import com.example.gorenganindonesia.Model.api.Receipt.Receipt;
import com.example.gorenganindonesia.databinding.FragmentFavouriteBinding;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment {

    private FragmentFavouriteBinding binding;

    List<Receipt> favouriteReceipt;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FavouriteViewModel favouriteViewModel =
                new ViewModelProvider(this).get(FavouriteViewModel.class);

        binding = FragmentFavouriteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textFavourite;
        favouriteViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        List<Receipt> buff = favouriteViewModel.getFavourites().getValue();

        favouriteReceipt = buff == null ? new ArrayList<Receipt>() : favouriteViewModel.getFavourites().getValue();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}