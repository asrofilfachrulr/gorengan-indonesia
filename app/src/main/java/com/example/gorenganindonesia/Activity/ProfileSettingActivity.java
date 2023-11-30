package com.example.gorenganindonesia.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.gorenganindonesia.API.Handlers.UserHandler;
import com.example.gorenganindonesia.Model.DAO.APIHandlerDAO;
import com.example.gorenganindonesia.Model.GlobalModel;
import com.example.gorenganindonesia.Model.api.User.PutUserBioRequest;
import com.example.gorenganindonesia.Model.data.Account.Account;
import com.example.gorenganindonesia.Util.RegexHelper;
import com.example.gorenganindonesia.databinding.ActivityProfileSettingBinding;
import com.example.gorenganindonesia.ui.Fragments.BasicInfoFragment;

public class ProfileSettingActivity extends AppCompatActivity {
    ActivityProfileSettingBinding binding;
    Account account;

    private static final String EMPTY_FIELD_ERROR_MSG = "Tidak boleh kosong!";
    private static final String USERNAME_CONTAINS_WS_ERROR_MSG = "Nama Pengguna tidak boleh mengandung spasi!";
    private static final String INVALID_EMAIL_ERROR_MSG = "Email tidak valid!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileSettingBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.ibBackProfileSetting.setOnClickListener(v -> {
            this.finish();
        });

        refreshAccount();
        populateDefault();

        ((GlobalModel) getApplication()).getAccountViewModel().getLiveAccount().observe(this, updatedAccount -> {
            refreshAccount();
            populateDefault();
        });


        binding.btnResetProfileSetting.setOnClickListener(v -> {
            populateDefault();
            clearErrorTexts();
        });

        binding.btnSaveProfileSetting.setOnClickListener(v -> {
            clearErrorTexts();
            clearAllFocus();

            boolean isAnyEmpty = false;
            RegexHelper regexHelper = new RegexHelper();

            String name = binding.etNameProfileSetting.getText().toString();
            String username = binding.etUsernameProfileSetting.getText().toString();
            String email = binding.etEmailProfileSetting.getText().toString();

            if(regexHelper.create(name).isBlank()){
                binding.tvNameErrorProfileSetting.setText(EMPTY_FIELD_ERROR_MSG);
                binding.tvNameErrorProfileSetting.setVisibility(View.VISIBLE);
                isAnyEmpty = true;
            }

            if(regexHelper.create(username).isBlank()){
                binding.tvUsernameErrorProfileSetting.setText(EMPTY_FIELD_ERROR_MSG);
                binding.tvUsernameErrorProfileSetting.setVisibility(View.VISIBLE);
                isAnyEmpty = true;
            }

            if(regexHelper.create(email).isBlank()){
                binding.tvEmailErrorProfileSetting.setText(EMPTY_FIELD_ERROR_MSG);
                binding.tvEmailErrorProfileSetting.setVisibility(View.VISIBLE);
                isAnyEmpty = true;
            }

            if(isAnyEmpty) return;

            if(username.contains(" ")){
                binding.tvUsernameErrorProfileSetting.setText(USERNAME_CONTAINS_WS_ERROR_MSG);
                binding.tvUsernameErrorProfileSetting.setVisibility(View.VISIBLE);
                return;
            }

            if(regexHelper.create(email).isValidEmail()){
                binding.tvEmailErrorProfileSetting.setText(INVALID_EMAIL_ERROR_MSG);
                binding.tvEmailErrorProfileSetting.setVisibility(View.VISIBLE);
            }

            APIHandlerDAO dao = new APIHandlerDAO(
                    binding.getRoot(),
                    binding.llRootLoadingProfileSetting,
                    binding.tvRootLoadingProfileSetting,
                    binding.getRoot().getContext()
            );

            dao.setCallback(() -> {
                UserHandler userHandler = new UserHandler(dao);
                APIHandlerDAO getUserDAO = userHandler.getDao();
                getUserDAO.setCallback(() -> {
                    BasicInfoFragment basicInfoFragment = new BasicInfoFragment(
                            "Profil Berhasil Diperbarui",
                            "Anda berhasil memperbarui profil",
                            BasicInfoFragment.DEFAULT_TIMER,
                            BasicInfoFragment.DEFAULT_IMG
                    );

                    basicInfoFragment.show(getSupportFragmentManager(), "BASIC_INFO_FRAGMENT");
                });
                userHandler.setDao(getUserDAO);
                userHandler.getUser();
            });

            UserHandler userHandler = new UserHandler(dao);

            PutUserBioRequest putUserBioRequest = new PutUserBioRequest(username, email, name);

            userHandler.putUserBio(putUserBioRequest);
        });
    }

    void refreshAccount(){
        account = ((GlobalModel) getApplication()).getAccountViewModel().getAccount();
    }

    void populateDefault(){
        if(account != null){
            binding.etNameProfileSetting.setText(account.getName());
            binding.etEmailProfileSetting.setText(account.getEmail());
            binding.etUsernameProfileSetting.setText(account.getUsername());
        }
    }

    void clearErrorTexts(){
        binding.tvNameErrorProfileSetting.setText("");
        binding.tvNameErrorProfileSetting.setVisibility(View.GONE);

        binding.tvEmailErrorProfileSetting.setText("");
        binding.tvEmailErrorProfileSetting.setVisibility(View.GONE);

        binding.tvUsernameErrorProfileSetting.setText("");
        binding.tvUsernameErrorProfileSetting.setVisibility(View.GONE);
    }

    void clearAllFocus(){
        binding.etNameProfileSetting.clearFocus();
        binding.etUsernameProfileSetting.clearFocus();
        binding.etEmailProfileSetting.clearFocus();
    }
}