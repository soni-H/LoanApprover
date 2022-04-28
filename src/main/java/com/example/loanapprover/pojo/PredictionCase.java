package com.example.loanapprover.pojo;

public class PredictionCase {

    double loan_amnt;
    String term;
    int emp_length;
    String home_ownership;
    int annual_inc;

    public double getDti() {
        return dti;
    }

    public void setDti(double dti) {
        this.dti = dti;
    }

    String purpose;
    double revol_util;
double dti;
    public double getRevol_util() {
        return revol_util;
    }

    public void setRevol_util(double revol_util) {
        this.revol_util = revol_util;
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

    int total_acc;
    int longest_credit_length;
    String verification_status;

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

    public String getAddr_state() {
        return addr_state;
    }

    @Override
    public String toString() {
        return "PredictionCase{" +
                "loan_amnt=" + loan_amnt +
                ", term='" + term + '\'' +
                ", emp_length=" + emp_length +
                ", home_ownership='" + home_ownership + '\'' +
                ", annual_inc=" + annual_inc +
                ", purpose='" + purpose + '\'' +
                ", revol_util=" + revol_util +
                ", dti=" + dti +
                ", total_acc=" + total_acc +
                ", longest_credit_length=" + longest_credit_length +
                ", verification_status='" + verification_status + '\'' +
                ", addr_state='" + addr_state + '\'' +
                ", delinq_2yrs=" + delinq_2yrs +
                ", prediction='" + prediction + '\'' +
                ", confidence=" + confidence +
                ", interest_rate=" + interest_rate +
                '}';
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
