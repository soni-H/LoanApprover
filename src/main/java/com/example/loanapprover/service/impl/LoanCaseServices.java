package com.example.loanapprover.service.impl;

import com.example.loanapprover.beans.LoanCases;
import com.example.loanapprover.beans.LoanRecord;
import com.example.loanapprover.pojo.HistoricalRecord;
import com.example.loanapprover.pojo.PredictionCase;
import com.example.loanapprover.pojo.PredictionRequest;
import com.example.loanapprover.pojo.PredictionResponse;
import com.example.loanapprover.service.LoanCase;

import com.example.loanapprover.utils.HibernateSessionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

public class LoanCaseServices implements LoanCase {

    private static final Logger logger = LogManager.getLogger(LoanCaseServices.class);

    @Override
    public PredictionResponse predictLoanCase(MultiValueMap predictionRequest) {
        PredictionResponse res=null;
try {
    String url = "http://localhost:5000/";
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
    HttpEntity<?> entity = new HttpEntity<>(headers);
    String urlTemplate = UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("loan_amnt", "{loan_amnt}")
            .queryParam("term", "{term}")
            .queryParam("emp_length", "{emp_length}")
            .queryParam("home_ownership", "{home_ownership}")
            .queryParam("annual_inc", "{annual_inc}")
            .queryParam("purpose", "{purpose}")
            .queryParam("addr_state", "{addr_state}")
            .queryParam("dti", "{dti}")
            .queryParam("delinq_2yrs", "{delinq_2yrs}")
            .queryParam("revol_util", "{revol_util}")
            .queryParam("total_acc", "{total_acc}")
            .queryParam("longest_credit_length", "{longest_credit_length}")
            .queryParam("verification_status", "{verification_status}")
            .encode()
            .toUriString();


Map<String,String> params=mapRequestToUrl(predictionRequest);

    HttpEntity<String> response = restTemplate.exchange(
            urlTemplate,
            HttpMethod.GET,
            entity,
            String.class,
            params
    );
    logger.info("predictLoanCase : Successfully executed predictLoan api call");
    JSONObject jsonObject = new JSONObject(response.getBody());
    res = new PredictionResponse();
    res.setConfidence(Double.parseDouble(jsonObject.get("confidence").toString()));
    res.setPrediction(jsonObject.get("prediction").toString());
    if(res.getPrediction().equalsIgnoreCase("Verified"))
        res.setInterest_rate(Double.parseDouble(jsonObject.get("interest_rate").toString()));
}catch(Exception e){
    e.printStackTrace();
    logger.error("predictLoanCase : Error occurred while calling the predictLoan api.");
}
        return res;
    }

    @Override
    public Integer postLoanCase(PredictionCase predictionCase) {
        int caseId=-1;
        LoanCases loanCase=mapRequestToLoanCase(predictionCase);
        try(Session session= HibernateSessionUtil.getSession()){
            Transaction transaction=session.beginTransaction();
            session.persist(loanCase);
            transaction.commit();
            caseId=loanCase.getCaseID();
            logger.info("postLoanCase : Successfully saved a loan case in database.");
        }catch(Exception e){
            e.printStackTrace();
            logger.error("postLoanCase : Error from database");
        }
        return caseId;
    }

    @Override
    public Integer saveHistoricalRecord(HistoricalRecord historicalRecord) {
        int recordID=-1;
        LoanRecord loanRecord=mapHistoricalRecordToLoanRecord(historicalRecord);
        try(Session session= HibernateSessionUtil.getSession()){
            Transaction transaction=session.beginTransaction();
            session.persist(loanRecord);
            transaction.commit();
            recordID=loanRecord.getRecordID();
            logger.info("saveHistoricalRecord : Successfully saved a historical record in database.");
        }catch(Exception e){
            e.printStackTrace();
            logger.error("saveHistoricalRecord : Error from database");
        }
        return recordID;
    }
    public LoanRecord mapHistoricalRecordToLoanRecord(HistoricalRecord historicalRecord){
        LoanRecord loanRecord=new LoanRecord();
        loanRecord.setLoanAmount(historicalRecord.getLoan_amnt());
        loanRecord.setAddressState(historicalRecord.getAddr_state());
        loanRecord.setLoanVerification(historicalRecord.getLoan_verification());
        loanRecord.setAnnualIncome(historicalRecord.getAnnual_inc());
        loanRecord.setPurpose(historicalRecord.getPurpose());
        loanRecord.setEmpLength(historicalRecord.getEmp_length());
        loanRecord.setDebtToIncomeRatio(historicalRecord.getDti());
        loanRecord.setHomeOwnership(historicalRecord.getHome_ownership());
        loanRecord.setLongestCreditLength(historicalRecord.getLongest_credit_length());
        loanRecord.setMisdemeanourYears(historicalRecord.getDelinq_2yrs());
        loanRecord.setRevolvingCredit(historicalRecord.getRevol_util());
        loanRecord.setTerm(historicalRecord.getTerm());
        loanRecord.setTotalAccounts(historicalRecord.getTotal_acc());
        loanRecord.setVerificationStatus(historicalRecord.getVerification_status());
        return loanRecord;

    }


    public LoanCases mapRequestToLoanCase(PredictionCase predictionCase){
        LoanCases loanCase=new LoanCases();

        loanCase.setLoanAmount(predictionCase.getLoan_amnt());
        loanCase.setTerm(predictionCase.getTerm());
        loanCase.setAddressState(predictionCase.getAddr_state());
        loanCase.setAnnualIncome(predictionCase.getAnnual_inc());
        loanCase.setDebtToIncomeRatio(predictionCase.getDti());
        loanCase.setEmpLength(predictionCase.getEmp_length());
        loanCase.setHomeOwnership(predictionCase.getHome_ownership());
        loanCase.setLongestCreditLength(predictionCase.getLongest_credit_length());
        loanCase.setMisdemeanourYears(predictionCase.getDelinq_2yrs());
        loanCase.setTotalAccounts(predictionCase.getTotal_acc());
        loanCase.setRevolvingCredit(predictionCase.getRevol_util());
        loanCase.setPurpose(predictionCase.getPurpose());
        loanCase.setVerificationStatus(predictionCase.getVerification_status());

        loanCase.setPrediction(predictionCase.getPrediction());
        loanCase.setConfidence(predictionCase.getConfidence());
        loanCase.setInterestRate(predictionCase.getInterest_rate());

        return loanCase;

    }
    public Map<String, String> mapRequestToUrl(MultiValueMap predictionRequest){
        Map<String, String> params = new HashMap<>();
        params.put("loan_amnt", predictionRequest.getFirst("loan_amnt").toString());
        params.put("term", predictionRequest.getFirst("term").toString());
        params.put("emp_length", predictionRequest.getFirst("emp_length").toString());
        params.put("home_ownership", predictionRequest.getFirst("home_ownership").toString());
        params.put("annual_inc", predictionRequest.getFirst("annual_inc").toString());
        params.put("addr_state", predictionRequest.getFirst("addr_state").toString());
        params.put("dti", predictionRequest.getFirst("dti").toString());
        params.put("purpose", predictionRequest.getFirst("purpose").toString());
        params.put("delinq_2yrs", predictionRequest.getFirst("delinq_2yrs").toString());
        params.put("revol_util", predictionRequest.getFirst("revol_util").toString());
        params.put("total_acc", predictionRequest.getFirst("total_acc").toString());
        params.put("longest_credit_length", predictionRequest.getFirst("longest_credit_length").toString());
        params.put("verification_status", predictionRequest.getFirst("verification_status").toString());

        return params;
    }


}
