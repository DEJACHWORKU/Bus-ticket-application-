package com.example.abs_app.trafic;

public class DataClass {
    private String email;
    private String full_name;
    private String phone_number;
    private String password;
    private String key;
    public String getKey() {
        return key;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public DataClass(String dataTitle, String dataDesc, String dataLang, String dataImage) {
        this.email = dataTitle;
        this.full_name = dataDesc;
        this.phone_number = dataLang;
        this.password = dataImage;
    }
    public DataClass(){
    }
}