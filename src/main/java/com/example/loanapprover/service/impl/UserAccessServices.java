package com.example.loanapprover.service.impl;

import com.example.loanapprover.beans.UserDetails;
import com.example.loanapprover.pojo.UserRegister;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import com.example.loanapprover.service.UserAccess;
import com.example.loanapprover.utils.HibernateSessionUtil;
import org.hibernate.Transaction;

import java.util.List;

public class UserAccessServices implements UserAccess {

    private static final Logger logger = LogManager.getLogger(UserAccessServices.class);

    @Override
    public boolean userLogin(String username, String password) {
        boolean response=false;
        System.out.println(username+":"+password);
        try(Session session= HibernateSessionUtil.getSession()){
            List<Object[]> queryResponses=session.createQuery("select emailID from UserDetails u where u.emailID=:emailId and u.password=:password")
                    .setParameter("password",password).setParameter("emailId",username).getResultList();
            System.out.println(queryResponses.size());
            if(queryResponses.size()==1)
                response=true;
            logger.info("userLogin : api called successfully.");
        }catch(Exception e){
            e.printStackTrace();
            logger.error("userLogin : error calling api");
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
            logger.info("userRegister : Successfully saved user in database.");
        }catch(Exception e){
            e.printStackTrace();
            logger.error("userRegister : error from database");
        }
        return userId;
    }
}
