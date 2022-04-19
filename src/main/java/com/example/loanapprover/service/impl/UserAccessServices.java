package com.example.loanapprover.service.impl;

import org.hibernate.Session;
import com.example.loanapprover.service.UserAccess;
import com.example.loanapprover.utils.HibernateSessionUtil;

import java.util.List;

public class UserAccessServices implements UserAccess {
    @Override
    public boolean userLogin(String username, String password) {
        boolean response=false;
        try(Session session= HibernateSessionUtil.getSession()){
            System.out.println(username+":"+password);
            List<Object[]> queryResponses=session.createQuery("select emailID from UserDetails u where u.emailID=:emailId and u.password=:password")
                    .setParameter("password",password).setParameter("emailId",username).getResultList();
            if(queryResponses.size()==1)
                response=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public boolean userRegister(String username, String password, String emailID, String fullName) {
        return false;
    }
}
