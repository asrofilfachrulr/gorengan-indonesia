package com.example.gorenganindonesia.ui.Fragments.Main;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.gorenganindonesia.API.Handlers.UserHandler;
import com.example.gorenganindonesia.Activity.AccountSettingActivity;
import com.example.gorenganindonesia.Model.DTO.APIHandlerDTO;
import com.example.gorenganindonesia.Model.data.Account.Account;
import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.Util.BitmapHelper;
import com.example.gorenganindonesia.Util.CustomToast;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.ViewModel.AccountViewModel;
import com.example.gorenganindonesia.Util.ToastUseCase;
import com.example.gorenganindonesia.databinding.FragmentAccountBinding;
import com.example.gorenganindonesia.ui.Fragments.Account.AppInfoFragment;
import com.example.gorenganindonesia.ui.Fragments.Account.TermsOfServiceFragment;

import java.io.InputStream;

import okhttp3.MultipartBody;

public class AccountFragment extends Fragment {

    private FragmentAccountBinding binding;
    private AccountViewModel accountViewModel;
    private static final int PICK_FILE_REQUEST_CODE = 8283;
    private static final int MAX_SIZE_IMAGE = 1024; // KB or 1MB

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentAccountBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        accountViewModel = ((GlobalModel) getContext().getApplicationContext()).getAccountViewModel();

        populateChangingData(accountViewModel.getAccount());

        ((GlobalModel) getContext().getApplicationContext()).getAccountViewModel().getLiveAccount().observe(getViewLifecycleOwner(), updatedAccount -> {
            populateChangingData(updatedAccount);
        });

        binding.ibEditImageAccount.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_FILE_REQUEST_CODE);
        });

        binding.btnAccountAccount.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AccountSettingActivity.class);
            getActivity().startActivity(intent);
        });

        binding.btnOfflineRecipeAccount.setOnClickListener(v -> {
            ToastUseCase.showInDevelopment(view);
        });

        binding.btnAppInfoAccount.setOnClickListener(v -> {
            AppInfoFragment appInfoFragment = new AppInfoFragment();
            appInfoFragment.show(requireActivity().getSupportFragmentManager(), "APPINFO_FRAGMENT");
        });

        binding.btnTermsOfServiceAccount.setOnClickListener(v -> {
            TermsOfServiceFragment tosFragment = new TermsOfServiceFragment();
            tosFragment.show(requireActivity().getSupportFragmentManager(), "TOS_FRAGMENT");
        });

        binding.btnCustomerServiceAccount.setOnClickListener(v -> {
            String phoneNumber = getString(R.string.cs_phone);
            String url = "https://api.whatsapp.com/send?phone="+ phoneNumber;
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            new CustomToast("Mengalihkan ke WhatsApp", view, false).show();
            startActivity(i);
        });

        binding.btnLogoutAccount.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder
                    .setMessage("Apakah Anda yakin ingin keluar?")
                    .setPositiveButton("Keluar", (dialog, which) -> ((GlobalModel) getContext().getApplicationContext())
                            .getSessionManager()
                            .logout(getContext(), getActivity(), "Keluar berhasil"))
                    .setNegativeButton("Batal", (dialog, which) -> dialog.dismiss());
            builder.show();
        });

        runDaemon();
    }

    void populateChangingData(Account account){
        Glide
                .with(requireContext())
                .load(account.getImageUrl())
                .circleCrop()
                .placeholder(R.drawable.baseline_account_circle_150)
                .error(R.drawable.img_404_landscape)
                .into(binding.ivProfilePhotoAccount);


        binding.tvNameAccount.setText(
                account.getName()
        );

        binding.tvEmailAccount.setText(
                account.getEmail()
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            switch(requestCode){
                case PICK_FILE_REQUEST_CODE:
                    ProgressDialog progressDialog = new ProgressDialog(getContext());
                    progressDialog.setMessage("Memproses Gambar");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    MultipartBody.Part imageData;
                    String prevPath = ((GlobalModel) requireContext().getApplicationContext()).getAccountViewModel().getImagePath();

                    try {
                        Uri selectedFileUri = data.getData();
                        BitmapHelper bitmapHelper = new BitmapHelper(getContext());
                        Bitmap compressedBitmap = bitmapHelper.compressImage(selectedFileUri, requireContext().getContentResolver());

                        if (compressedBitmap == null) {
                            new CustomToast("Error Memproses Gambar!", binding.getRoot()).show();
                            throw new RuntimeException();
                        }

                        imageData = bitmapHelper.bitmapToMultipartBody(compressedBitmap, selectedFileUri);

                    } catch (Exception e) {
                        progressDialog.dismiss();
                        return;
                    }

                    progressDialog.dismiss();

                    APIHandlerDTO dto = new APIHandlerDTO(
                            binding.getRoot(),
                            binding.llRootLoadingAccountFragment,
                            binding.tvRootLoadingAccountFragment,
                            requireContext()
                    );

                    UserHandler userHandler = new UserHandler(dto);
                    userHandler.putUserBioImg(imageData, prevPath);

                    break;
            }
        }
    }

    void runDaemon(){
        APIHandlerDTO dto = new APIHandlerDTO(binding.getRoot(), requireContext(), null);
        UserHandler userHandler = new UserHandler(dto);

        userHandler.getUser();
    }
}