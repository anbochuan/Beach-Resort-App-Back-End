package com.bochuan.springboot.jwt;

public class UsernamePasswordAuthenticationRequest {

    private String username;
    private String password;

    public UsernamePasswordAuthenticationRequest() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
