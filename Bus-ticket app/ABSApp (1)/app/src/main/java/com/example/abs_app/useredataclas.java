package com.example.abs_app;

public class useredataclas {

    private String full_name;

    private String password;
    private String email;
    private String phone_number;
    private String role;
    private String key;

    public String getFull_name() {
        return full_name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getRole() {
        return role;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }



    public useredataclas(String full_name, String role, String password, String email, String phone_number) {
        this.full_name = full_name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.phone_number = phone_number;
    }

    public useredataclas() {
    }
}
