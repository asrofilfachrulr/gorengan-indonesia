package com.example.gorenganindonesia.Util;

import java.util.regex.Pattern;

public class RegexHelper {
    String target;

    public RegexHelper(){}

    public RegexHelper(String target){
        this.target = target;
    }

    public boolean isBlank(){
        if(this.target == null) return true;
        return this.target.matches("^$|^\\s+$");
    }

    public boolean isValidEmail(){
        if(this.target == null) return false;
        return this.target.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])*)*$\n");
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public RegexHelper create(String target){
        return new RegexHelper(target);
    }
}
