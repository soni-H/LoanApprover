package com.example.loanapprover.beans;

import org.springframework.context.annotation.Bean;

import javax.persistence.*;

@Entity
@Table(name="loan_cases")
public class LoanCases {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="caseID")
    private Integer caseID;

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

    public Integer getCaseID() {
        return caseID;
    }

    public void setCaseID(Integer caseID) {
        this.caseID = caseID;
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

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Column(name="revol_util")
    private double revolvingCredit;

    @Column(name="total_acc")
    private int totalAccounts;

    @Column(name="longest_credit_length")
    private int longestCreditLength;

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    @Column(name="prediction")
    private String prediction;

    @Column(name="confidence")
    private double confidence;

    @Column(name="interest_rate")
    private double interestRate;

    @Column(name="loan_amnt")
    private double loanAmount;
}
