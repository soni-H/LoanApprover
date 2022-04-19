package com.example.loanapprover.service;

public interface UserAccess {

    public boolean userLogin(String username,String password);
    public boolean userRegister(String username,String password,String emailID,String fullName);
}
