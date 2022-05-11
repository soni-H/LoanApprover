package com.example.loanapprover.service.impl;

import com.example.loanapprover.beans.LoanCases;
import com.example.loanapprover.beans.LoanRecord;
import com.example.loanapprover.pojo.*;
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

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoanCaseServices implements LoanCase {

    private static final Logger logger = LogManager.getLogger(LoanCaseServices.class);

    @Override
    public PredictionResponse predictLoanCase(MultiValueMap predictionRequest) {
        logger.debug("predictLoanCase : started");
        ServiceResponse serviceResponse=new ServiceResponse();
        PredictionResponse res=null;
try {
    String url = "http://webla:5000/";
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
    serviceResponse.setMessage("Successfully executed predictLoan api call");
    serviceResponse.setResponseCode(200);
    JSONObject jsonObject = new JSONObject(response.getBody());
    res = new PredictionResponse();
    res.setConfidence(Double.parseDouble(jsonObject.get("confidence").toString()));
    res.setPrediction(jsonObject.get("prediction").toString());
    if(res.getPrediction().equalsIgnoreCase("Verified"))
        res.setInterest_rate(Double.parseDouble(jsonObject.get("interest_rate").toString()));
    logger.info(serviceResponse);
    logger.debug("predictLoanCase : ended");
}catch(Exception e){
    e.printStackTrace();
    serviceResponse.setMessage("Error occurred while calling the predictLoan api");
    serviceResponse.setResponseCode(500);
    logger.error("predictLoanCase :  Ended");
}
        return res;
    }

    @Override
    public Integer postLoanCase(PredictionCase predictionCase) {

        ServiceResponse response=new ServiceResponse();
        logger.debug("postLoanCase : Started");
        int caseId=-1;
        LoanCases loanCase=mapRequestToLoanCase(predictionCase);
        try(Session session= HibernateSessionUtil.getSession()){
            Transaction transaction=session.beginTransaction();
            session.persist(loanCase);
            transaction.commit();
            caseId=loanCase.getCaseID();
            response.setResponseCode(200);
            response.setMessage("Successfully saved a loan case in database");
            logger.info(response);
            logger.debug("postLoanCase : Ended");
        }catch(Exception e){
            e.printStackTrace();
            response.setResponseCode(500);
            response.setMessage("Error from database");
            logger.debug("postLoanCase : Ended with error");
            logger.error(response);
        }
        return caseId;
    }

    @Override
    public Integer saveHistoricalRecord(HistoricalRecord historicalRecord) {
        logger.debug("saveHistoricalRecord : Started");
        ServiceResponse response=new ServiceResponse();
        int recordID=-1;

        LoanRecord loanRecord=mapHistoricalRecordToLoanRecord(historicalRecord);
        try(Session session= HibernateSessionUtil.getSession()){
            Transaction transaction=session.beginTransaction();
            session.persist(loanRecord);
            transaction.commit();
            recordID=loanRecord.getRecordID();
            response.setResponseCode(200);
            response.setMessage("Successfully saved a historical record in database");
            logger.info(response);
            logger.debug("saveHistoricalRecord : Ended");
        }catch(Exception e){
            e.printStackTrace();
            response.setResponseCode(500);
            response.setMessage("Error saving record in database");
            logger.error(response);
            logger.error("saveHistoricalRecord : Ended");
        }
        return recordID;
    }

    @Override
    public PredictionCase getLoanCase(int caseID) {
        logger.debug("getLoanCase : Started");
        ServiceResponse res=new ServiceResponse();
        PredictionCase response=new PredictionCase();
        try(Session session= HibernateSessionUtil.getSession()){
            List<Object[]> queryResponses=session.createQuery("select loanAmount,annualIncome,homeOwnership," +
                            "verificationStatus,longestCreditLength,interestRate,confidence,prediction,revolvingCredit," +
                            "term,debtToIncomeRatio,misdemeanourYears,totalAccounts,purpose,addressState," +
                            "empLength from LoanCases u where u.caseID=:caseID ")
                    .setParameter("caseID",caseID).getResultList();
            if(queryResponses.size()==1) {
                Object[] loanCase = queryResponses.get(0);
                response.setLoan_amnt(Double.parseDouble(loanCase[0].toString()));
                response.setAnnual_inc(Integer.parseInt(loanCase[1].toString()));
                response.setHome_ownership(loanCase[2].toString());
                response.setVerification_status(loanCase[3].toString());
                response.setLongest_credit_length(Integer.parseInt(loanCase[4].toString()));
                response.setInterest_rate(Double.parseDouble(loanCase[5].toString()));
                response.setConfidence(Double.parseDouble(loanCase[6].toString()));
                response.setPrediction(loanCase[7].toString());
                response.setRevol_util(Double.parseDouble(loanCase[8].toString()));
                response.setTerm(loanCase[9].toString());
                response.setDti(Double.parseDouble(loanCase[10].toString()));
                response.setDelinq_2yrs(Integer.parseInt(loanCase[11].toString()));
                response.setTotal_acc(Integer.parseInt(loanCase[12].toString()));
                response.setPurpose(loanCase[13].toString());
                response.setAddr_state(loanCase[14].toString());
                response.setEmp_length(Integer.parseInt(loanCase[15].toString()));
                res.setMessage("Predict loan Api called successfully");
                res.setResponseCode(200);
                logger.info(res);
                logger.debug("getLoanCase : Ended");
            }else return null;
        }catch(Exception e){
            e.printStackTrace();
            res.setMessage("Error calling predict loan api");
            res.setResponseCode(500);
            logger.error(res);
            logger.error("getLoanCase : Ended");
        }
        return response;
    }

    @Override
    public List<Integer> getAllSavedCases() {
        logger.debug("getAllSavedCases : Started");
        ServiceResponse res=new ServiceResponse();
        List<Integer> responses=new ArrayList<>();
        try(Session session= HibernateSessionUtil.getSession()){
            responses=session.createQuery("select caseID from LoanCases u ")
                    .getResultList();
            res.setResponseCode(200);
            res.setMessage("Api for fetching saved case called successfully.");
            logger.info(res);
            logger.debug("getAllSavedCases : Ended");
        }catch(Exception e){
            e.printStackTrace();
            res.setResponseCode(500);
            res.setMessage("Api for fetching saved case returned error");
            logger.error(res);
            logger.error("getAllSavedCases : ended");
        }
        return responses;
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
