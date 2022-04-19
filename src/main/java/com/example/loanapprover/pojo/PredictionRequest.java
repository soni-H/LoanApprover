package com.example.loanapprover.pojo;

public class PredictionRequest {

    String loan_amnt;
    String term;
    String emp_length;
    String home_ownership;
    String annual_inc;
    String purpose;
    String addr_state;
    String delinq_2yrs;

    public String getLoan_amnt() {
        return loan_amnt;
    }

    public void setLoan_amnt(String loan_amnt) {
        this.loan_amnt = loan_amnt;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getEmp_length() {
        return emp_length;
    }

    public void setEmp_length(String emp_length) {
        this.emp_length = emp_length;
    }

    public String getHome_ownership() {
        return home_ownership;
    }

    public void setHome_ownership(String home_ownership) {
        this.home_ownership = home_ownership;
    }

    public String getAnnual_inc() {
        return annual_inc;
    }

    public void setAnnual_inc(String annual_inc) {
        this.annual_inc = annual_inc;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getAddr_state() {
        return addr_state;
    }

    public void setAddr_state(String addr_state) {
        this.addr_state = addr_state;
    }

    public String getDelinq_2yrs() {
        return delinq_2yrs;
    }

    public void setDelinq_2yrs(String delinq_2yrs) {
        this.delinq_2yrs = delinq_2yrs;
    }

    public String getRevol_util() {
        return revol_util;
    }

    public void setRevol_util(String revol_util) {
        this.revol_util = revol_util;
    }

    public String getTotal_acc() {
        return total_acc;
    }

    public void setTotal_acc(String total_acc) {
        this.total_acc = total_acc;
    }

    public String getLongest_credit_length() {
        return longest_credit_length;
    }

    public void setLongest_credit_length(String longest_credit_length) {
        this.longest_credit_length = longest_credit_length;
    }

    public String getVerification_status() {
        return verification_status;
    }

    public void setVerification_status(String verification_status) {
        this.verification_status = verification_status;
    }

    String revol_util;
    String total_acc;
    String longest_credit_length;
    String verification_status;
}
