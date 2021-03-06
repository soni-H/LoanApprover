package com.example.loanapprover.service;

import com.example.loanapprover.pojo.HistoricalRecord;
import com.example.loanapprover.pojo.PredictionCase;
import com.example.loanapprover.pojo.PredictionRequest;
import com.example.loanapprover.pojo.PredictionResponse;
import org.springframework.util.MultiValueMap;

import java.util.List;

public interface LoanCase {

    public PredictionResponse predictLoanCase(MultiValueMap predictionRequest);
    public Integer postLoanCase(PredictionCase predictionCase);
    public Integer saveHistoricalRecord(HistoricalRecord historicalRecord);
    public PredictionCase getLoanCase(int caseID);
    public List<Integer> getAllSavedCases();
}
