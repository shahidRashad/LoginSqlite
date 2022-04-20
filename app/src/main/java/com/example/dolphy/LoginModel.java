package com.example.dolphy;

public class LoginModel {
    private String user_name;
    private String e_mail;
    private String pass_word;

    public LoginModel(String user_name, String e_mail, String pass_word) {
        this.user_name = user_name;
        this.e_mail = e_mail;
        this.pass_word = pass_word;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getPass_word() {
        return pass_word;
    }

    public void setPass_word(String pass_word) {
        this.pass_word = pass_word;
    }
}
