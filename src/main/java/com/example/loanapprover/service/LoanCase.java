package com.example.loanapprover.service;

import com.example.loanapprover.pojo.PredictionCase;
import com.example.loanapprover.pojo.PredictionRequest;
import com.example.loanapprover.pojo.PredictionResponse;
import org.springframework.util.MultiValueMap;

public interface LoanCase {

    public PredictionResponse predictLoanCase(MultiValueMap predictionRequest);
    public Integer postLoanCase(PredictionCase predictionCase);
}
