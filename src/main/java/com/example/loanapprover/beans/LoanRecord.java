package com.example.loanapprover.beans;

import javax.persistence.*;

@Entity
@Table(name="historical_record")
public class LoanRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="recordID")
    private Integer recordID;

    @Column(name="term")
    private String term;

    @Column(name="emp_length")
    private int empLength;

    @Column(name="home_ownership")
    private String homeOwnership;

    @Column(name="annual_inc")
    private int annualIncome;

    @Column(name="verification_status")
    private String verificationStatus;

    @Column(name="purpose")
    private String purpose;

    @Column(name="addr_state")
    private String addressState;

    @Column(name="dti")
    private double debtToIncomeRatio;

    @Column(name="delinq_2yrs")
    private int misdemeanourYears;

    @Column(name="revol_util")
    private double revolvingCredit;

    @Column(name="total_acc")
    private int totalAccounts;

    public Integer getRecordID() {
        return recordID;
    }

    public void setRecordID(Integer recordID) {
        this.recordID = recordID;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getEmpLength() {
        return empLength;
    }

    public void setEmpLength(int empLength) {
        this.empLength = empLength;
    }

    public String getHomeOwnership() {
        return homeOwnership;
    }

    public void setHomeOwnership(String homeOwnership) {
        this.homeOwnership = homeOwnership;
    }

    public int getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(int annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public double getDebtToIncomeRatio() {
        return debtToIncomeRatio;
    }

    public void setDebtToIncomeRatio(double debtToIncomeRatio) {
        this.debtToIncomeRatio = debtToIncomeRatio;
    }

    public int getMisdemeanourYears() {
        return misdemeanourYears;
    }

    public void setMisdemeanourYears(int misdemeanourYears) {
        this.misdemeanourYears = misdemeanourYears;
    }

    public double getRevolvingCredit() {
        return revolvingCredit;
    }

    public void setRevolvingCredit(double revolvingCredit) {
        this.revolvingCredit = revolvingCredit;
    }

    public int getTotalAccounts() {
        return totalAccounts;
    }

    public void setTotalAccounts(int totalAccounts) {
        this.totalAccounts = totalAccounts;
    }

    public int getLongestCreditLength() {
        return longestCreditLength;
    }

    public void setLongestCreditLength(int longestCreditLength) {
        this.longestCreditLength = longestCreditLength;
    }

    public String getLoanVerification() {
        return loanVerification;
    }

    public void setLoanVerification(String loanVerification) {
        this.loanVerification = loanVerification;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    @Column(name="longest_credit_length")
    private int longestCreditLength;

    @Column(name="loan_verification")
    private String loanVerification;

    @Column(name="loan_amnt")
    private double loanAmount;
}
