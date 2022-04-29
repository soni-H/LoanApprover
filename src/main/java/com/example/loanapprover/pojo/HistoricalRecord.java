package com.example.loanapprover.pojo;

public class HistoricalRecord {

    double loan_amnt;
    String term;
    int emp_length;
    String home_ownership;
    int annual_inc;
    String purpose;

    public double getLoan_amnt() {
        return loan_amnt;
    }

    public void setLoan_amnt(double loan_amnt) {
        this.loan_amnt = loan_amnt;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getEmp_length() {
        return emp_length;
    }

    public void setEmp_length(int emp_length) {
        this.emp_length = emp_length;
    }

    public String getHome_ownership() {
        return home_ownership;
    }

    public void setHome_ownership(String home_ownership) {
        this.home_ownership = home_ownership;
    }

    public int getAnnual_inc() {
        return annual_inc;
    }

    public void setAnnual_inc(int annual_inc) {
        this.annual_inc = annual_inc;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public double getRevol_util() {
        return revol_util;
    }

    public void setRevol_util(double revol_util) {
        this.revol_util = revol_util;
    }

    public double getDti() {
        return dti;
    }

    public void setDti(double dti) {
        this.dti = dti;
    }

    public int getTotal_acc() {
        return total_acc;
    }

    public void setTotal_acc(int total_acc) {
        this.total_acc = total_acc;
    }

    public int getLongest_credit_length() {
        return longest_credit_length;
    }

    public void setLongest_credit_length(int longest_credit_length) {
        this.longest_credit_length = longest_credit_length;
    }

    public String getVerification_status() {
        return verification_status;
    }

    public void setVerification_status(String verification_status) {
        this.verification_status = verification_status;
    }

    public String getAddr_state() {
        return addr_state;
    }

    public void setAddr_state(String addr_state) {
        this.addr_state = addr_state;
    }

    public int getDelinq_2yrs() {
        return delinq_2yrs;
    }

    public void setDelinq_2yrs(int delinq_2yrs) {
        this.delinq_2yrs = delinq_2yrs;
    }

    public String getLoan_verification() {
        return loan_verification;
    }

    public void setLoan_verification(String loan_verification) {
        this.loan_verification = loan_verification;
    }

    double revol_util;
    double dti;
    int total_acc;
    int longest_credit_length;
    String verification_status;

    String addr_state;
    int delinq_2yrs;
    String loan_verification;
}
