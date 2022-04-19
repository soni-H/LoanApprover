package com.example.loanapprover.pojo;

public class PredictionCase {

    double loan_amnt;
    String term;
    String emp_length;
    String home_ownership;
    double annual_inc;
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

    public double getAnnual_inc() {
        return annual_inc;
    }

    public void setAnnual_inc(double annual_inc) {
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

    public int getDelinq_2yrs() {
        return delinq_2yrs;
    }

    public void setDelinq_2yrs(int delinq_2yrs) {
        this.delinq_2yrs = delinq_2yrs;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public double getInterest_rate() {
        return interest_rate;
    }

    public void setInterest_rate(double interest_rate) {
        this.interest_rate = interest_rate;
    }

    String addr_state;
    int delinq_2yrs;
    String prediction;
    double confidence;
    double interest_rate;
}
