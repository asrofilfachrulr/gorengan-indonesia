package com.example.gorenganindonesia.ui.Fragments.Main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gorenganindonesia.Model.ViewModel.FavouriteViewModel;
import com.example.gorenganindonesia.Model.data.Receipt.Receipt;
import com.example.gorenganindonesia.databinding.FragmentFavouriteBinding;
import com.example.gorenganindonesia.ui.Adapters.FavouritesAdapter;

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

        favouriteReceipt = favouriteViewModel.getFavourites().getValue();

        RecyclerView recyclerView = binding.rvFavourites;
        FavouritesAdapter adapter = new FavouritesAdapter(favouriteReceipt);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        favouriteViewModel.getFavourites().observe(getViewLifecycleOwner(), new Observer<List<Receipt>>() {
            @Override
            public void onChanged(List<Receipt> receipts) {
                adapter.updateData(receipts);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}