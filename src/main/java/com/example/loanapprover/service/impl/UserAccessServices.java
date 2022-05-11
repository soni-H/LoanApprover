package com.example.loanapprover.service.impl;

import com.example.loanapprover.beans.UserDetails;
import com.example.loanapprover.pojo.ServiceResponse;
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
        logger.debug("userLogin : started");
        ServiceResponse res=new ServiceResponse();
        boolean response=false;
        logger.debug(username+":"+password);
        try(Session session= HibernateSessionUtil.getSession()){
            List<Object[]> queryResponses=session.createQuery("select emailID from UserDetails u where u.emailID=:emailId and u.password=:password")
                    .setParameter("password",password).setParameter("emailId",username).getResultList();
            System.out.println(queryResponses.size());
            if(queryResponses.size()==1)
                response=true;
            res.setMessage("Login api called successfully.");
            res.setResponseCode(200);
            logger.info(res);
            logger.debug("userLogin : Ended");
        }catch(Exception e){
            e.printStackTrace();
            res.setMessage("Login api returned error");
            res.setResponseCode(500);
            logger.error(res);
            logger.error("userLogin : ended");
        }
        return response;
    }

    @Override
    public int userRegister(UserRegister user) {
        logger.debug("userRegister : Started");
        ServiceResponse response=new ServiceResponse();
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
            response.setResponseCode(200);
            response.setMessage("Successfully saved user in database.");
            logger.info(response);
            logger.debug("userRegister : Ended");
        }catch(Exception e){
            e.printStackTrace();
            response.setResponseCode(500);
            response.setMessage("error from database");
            logger.error(response);
            logger.debug("userRegister : Ended");
        }
        return userId;
    }
}
