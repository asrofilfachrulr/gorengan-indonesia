package com.example.gorenganindonesia.Model.api.User;

import com.google.gson.annotations.SerializedName;

public class PutPasswordRequest {
    @SerializedName("old_password")
    String oldPassword;
    @SerializedName("new_password")
    String newPassword;

    public PutPasswordRequest(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
