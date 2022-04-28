package com.example.loanapprover.service.impl;

import com.example.loanapprover.beans.UserDetails;
import com.example.loanapprover.pojo.UserRegister;
import org.hibernate.Session;
import com.example.loanapprover.service.UserAccess;
import com.example.loanapprover.utils.HibernateSessionUtil;
import org.hibernate.Transaction;

import java.util.List;

public class UserAccessServices implements UserAccess {
    @Override
    public boolean userLogin(String username, String password) {
        boolean response=false;
        try(Session session= HibernateSessionUtil.getSession()){
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
    public int userRegister(UserRegister user) {

        UserDetails userDetails=new UserDetails();
        userDetails.setEmailID(user.getEmailID());
        userDetails.setFullName(user.getFullName());
        userDetails.setPassword(user.getPassword());

        int userId=-1;
        try(Session session=HibernateSessionUtil.getSession()){
            Transaction transaction=session.beginTransaction();
            session.persist(userDetails);
            transaction.commit();
            userId=userDetails.getUserID();
        }catch(Exception e){
            e.printStackTrace();
        }
        return userId;
    }
}
