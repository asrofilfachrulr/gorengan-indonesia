package com.example.gorenganindonesia.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.gorenganindonesia.API.Handlers.PasswordHandler;
import com.example.gorenganindonesia.Model.DAO.APIHandlerDAO;
import com.example.gorenganindonesia.Model.api.User.PutPasswordRequest;
import com.example.gorenganindonesia.R;
import com.example.gorenganindonesia.Util.InputMethodHelper;
import com.example.gorenganindonesia.Util.RegexHelper;
import com.example.gorenganindonesia.databinding.ActivityPasswordSettingBinding;
import com.example.gorenganindonesia.ui.Fragments.BasicInfoFragment;

import java.util.HashMap;

/** @noinspection ALL*/
public class PasswordSettingActivity extends AppCompatActivity {
    ActivityPasswordSettingBinding binding;
    /** @noinspection Since15*/

    private final String EMPTY_FIELD_ERROR_MSG = "Tidak boleh kosong!";
    private final String PASSWORD_NOT_MATCH_ERROR_MSG = "Kata Sandi Tidak Sama!";

    private final String INVALID_OLD_PASSWORD_ERROR_MSG = "Kata Sandi Salah!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPasswordSettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.ibBackPasswordSetting.setOnClickListener(v -> {
            this.finish();
        });

        binding.llContentPasswordSetting.setOnClickListener(v -> {
            InputMethodHelper.hideKeyboard(v, (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
        });

        HashMap<ImageButton, EditText> toggleButtonEditTextPasswordMap = new HashMap<>();
        toggleButtonEditTextPasswordMap.put(binding.ibToggleOldPasswordPasswordSetting, binding.etOldPasswordPasswordSetting);
        toggleButtonEditTextPasswordMap.put(binding.ibToggleNewPasswordPasswordSetting, binding.etNewPasswordPasswordSetting);
        toggleButtonEditTextPasswordMap.put(binding.ibRepeatToggleNewPasswordPasswordSetting, binding.etRepeatNewPasswordPasswordSetting);

        for(ImageButton toggleBtn: toggleButtonEditTextPasswordMap.keySet()){
            toggleBtn.setOnClickListener(v -> {
                if(toggleButtonEditTextPasswordMap.get(toggleBtn).getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)){
                    toggleBtn.setImageResource(R.drawable.baseline_remove_red_eye_24);
                    toggleButtonEditTextPasswordMap.get(toggleBtn).setInputType(InputType.TYPE_CLASS_TEXT);
                } else if(toggleButtonEditTextPasswordMap.get(toggleBtn).getInputType() == InputType.TYPE_CLASS_TEXT){
                    toggleBtn.setImageResource(R.drawable.outline_remove_red_eye_24);
                    toggleButtonEditTextPasswordMap.get(toggleBtn).setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            });
        }

        binding.btnResetPasswordSetting.setOnClickListener(v -> {
            clearEditTexts();
            clearErrorTexts();
        });

        binding.btnSavePasswordSetting.setOnClickListener(v -> {
            InputMethodHelper.hideKeyboard(binding.getRoot(), (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
            clearAllFocus();
            clearErrorTexts();

            boolean isFieldEmpty = false;

            String oldPassword = binding.etOldPasswordPasswordSetting.getText().toString();
            String newPassword = binding.etNewPasswordPasswordSetting.getText().toString();
            String repeatNewPassword = binding.etRepeatNewPasswordPasswordSetting.getText().toString();

            RegexHelper regexHelper = new RegexHelper();

            if(regexHelper.create(oldPassword).isBlank()) {
                binding.tvOldPasswordErrorPasswordSetting.setText(EMPTY_FIELD_ERROR_MSG);
                binding.tvOldPasswordErrorPasswordSetting.setVisibility(View.VISIBLE);
                isFieldEmpty = true;
            }
            if(regexHelper.create(newPassword).isBlank()) {
                binding.tvNewPasswordErrorPasswordSetting.setText(EMPTY_FIELD_ERROR_MSG);
                binding.tvNewPasswordErrorPasswordSetting.setVisibility(View.VISIBLE);
                isFieldEmpty = true;
            }
            if(regexHelper.create(repeatNewPassword).isBlank()) {
                binding.tvRepeatNewPasswordErrorPasswordSetting.setText(EMPTY_FIELD_ERROR_MSG);
                binding.tvRepeatNewPasswordErrorPasswordSetting.setVisibility(View.VISIBLE);
                isFieldEmpty = true;
            }

            if(isFieldEmpty) {
                return;
            }

            if(!newPassword.equals(repeatNewPassword)){
                binding.tvNewPasswordErrorPasswordSetting.setText(PASSWORD_NOT_MATCH_ERROR_MSG);
                binding.tvNewPasswordErrorPasswordSetting.setVisibility(View.VISIBLE);

                binding.tvRepeatNewPasswordErrorPasswordSetting.setText(PASSWORD_NOT_MATCH_ERROR_MSG);
                binding.tvRepeatNewPasswordErrorPasswordSetting.setVisibility(View.VISIBLE);
                return;
            }

            APIHandlerDAO dao = new APIHandlerDAO(
                    binding.getRoot(),
                    binding.llRootLoadingPasswordSetting,
                    binding.tvRootLoadingPasswordSetting,
                    binding.getRoot().getContext()
            );

            dao.setCallback(() -> {
                BasicInfoFragment basicInfoFragment = new BasicInfoFragment(
                        "Pembaruan Berhasil",
                        "Kata Sandi Berhasil Diperbarui",
                        BasicInfoFragment.DEFAULT_TIMER,
                        BasicInfoFragment.DEFAULT_IMG
                );

                basicInfoFragment.show(getSupportFragmentManager(), "BASIC_INFO_FRAGMENT");

                clearEditTexts();
            });

            // wrong old password
            dao.setNegativeCallback(() -> {
                binding.tvOldPasswordErrorPasswordSetting.setText(INVALID_OLD_PASSWORD_ERROR_MSG);
                binding.tvOldPasswordErrorPasswordSetting.setVisibility(View.VISIBLE);
            });

            PasswordHandler passwordHandler = new PasswordHandler(dao);
            PutPasswordRequest putPasswordRequest = new PutPasswordRequest(oldPassword, newPassword);

            passwordHandler.putPassword(putPasswordRequest);
        });
    }

    public void clearEditTexts(){
        binding.etOldPasswordPasswordSetting.setText("");
        binding.etNewPasswordPasswordSetting.setText("");
        binding.etRepeatNewPasswordPasswordSetting.setText("");
    }

    public void clearErrorTexts(){
        binding.tvOldPasswordErrorPasswordSetting.setVisibility(View.GONE);
        binding.tvNewPasswordErrorPasswordSetting.setVisibility(View.GONE);
        binding.tvRepeatNewPasswordErrorPasswordSetting.setVisibility(View.GONE);

        binding.tvOldPasswordErrorPasswordSetting.setText("");
        binding.tvNewPasswordErrorPasswordSetting.setText("");
        binding.tvRepeatNewPasswordErrorPasswordSetting.setText("");
    }

    public void clearAllFocus(){
        binding.etOldPasswordPasswordSetting.clearFocus();
        binding.etNewPasswordPasswordSetting.clearFocus();
        binding.etRepeatNewPasswordPasswordSetting.clearFocus();
    }
}