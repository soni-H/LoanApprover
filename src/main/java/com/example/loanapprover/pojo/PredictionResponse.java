package com.example.loanapprover.pojo;

public class PredictionResponse {

    String predcition;
    double confidence;

    public String getPredcition() {
        return predcition;
    }

    public void setPredcition(String predcition) {
        this.predcition = predcition;
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

    double interest_rate;
}
