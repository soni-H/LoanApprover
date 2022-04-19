package com.example.loanapprover.beans;


import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="user_details")
public class UserDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="userID")
    private Integer userID;

    @Column(name="fullName")
    private String fullName;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name="emailID")
    private String emailID;

    @Column(name="password")
    private String password;
}
