package com.example.loanapprover.service;

import com.example.loanapprover.pojo.UserRegister;

public interface UserAccess {

    public boolean userLogin(String username,String password);
    public int userRegister(UserRegister user);
}
