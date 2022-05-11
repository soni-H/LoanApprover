package com.example.loanapprover;



import com.example.loanapprover.beans.LoanCases;
import com.example.loanapprover.beans.LoanRecord;
import com.example.loanapprover.beans.UserDetails;
import com.example.loanapprover.pojo.HistoricalRecord;
import com.example.loanapprover.pojo.PredictionCase;
import com.example.loanapprover.service.impl.LoanCaseServices;
import com.example.loanapprover.utils.HibernateSessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

import static org.junit.Assert.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoanCaseServicesTest {

    LoanCaseServices loanCaseServicesTest;

    HistoricalRecord historicalRecord=null;

    MultiValueMap predictionRequest=null;

    PredictionCase predictionCase=null;

    @Mock
    Session session;

    @Mock
    Transaction transaction;

    private static HibernateSessionUtil hibernateSessionUtil;

    @Before
    public void setUp(){
        loanCaseServicesTest=new LoanCaseServices();
        historicalRecord=new HistoricalRecord();
        historicalRecord.setAddr_state("CA");
        historicalRecord.setAnnual_inc(1000);
        historicalRecord.setDti(3.14);
        historicalRecord.setLoan_amnt(2000);
        historicalRecord.setEmp_length(2);
        historicalRecord.setDelinq_2yrs(3);
        historicalRecord.setHome_ownership("RENT");
        historicalRecord.setLoan_verification("verified");
        historicalRecord.setLongest_credit_length(4);
        historicalRecord.setPurpose("Wedding");
        historicalRecord.setRevol_util(4.2);
        historicalRecord.setTerm("60 months");
        historicalRecord.setTotal_acc(10);

        predictionRequest=new LinkedMultiValueMap<String, String>();

        predictionRequest.set("loan_amnt","10000");
        predictionRequest.set("term","60 months");
        predictionRequest.set("emp_length","3");
        predictionRequest.set("home_ownership","RENT");
        predictionRequest.set("annual_inc","3000");
        predictionRequest.set("addr_state","GA");
        predictionRequest.set("dti","3.4");
        predictionRequest.set("purpose","Wedding");
        predictionRequest.set("delinq_2yrs","2");
        predictionRequest.set("revol_util","2.3");
        predictionRequest.set("total_acc","2");
        predictionRequest.set("longest_credit_length","2");
        predictionRequest.set("verification_status","verified");

        predictionCase=new PredictionCase();
        predictionCase.setPrediction("Y");
        predictionCase.setPurpose("Wedding");
        predictionCase.setConfidence(0.89);
        predictionCase.setTerm("12 months");
        predictionCase.setAddr_state("CA");
        predictionCase.setEmp_length(2);
        predictionCase.setDti(2.3);
        predictionCase.setInterest_rate(2.3);
        predictionCase.setDelinq_2yrs(2);
        predictionCase.setLoan_amnt(3000);
        predictionCase.setHome_ownership("NONE");
        predictionCase.setVerification_status("verified");
        predictionCase.setAnnual_inc(20000);
        predictionCase.setRevol_util(3);
        predictionCase.setTotal_acc(2);

    }
    @Test
    public void mapHistoricalRecordToLoanRecord() {
        LoanRecord loanRecord=loanCaseServicesTest.mapHistoricalRecordToLoanRecord(historicalRecord);
        assertEquals("CA",loanRecord.getAddressState());
        assertEquals(1000,loanRecord.getAnnualIncome());
        assertEquals(2,loanRecord.getEmpLength());
        assertEquals(4,loanRecord.getLongestCreditLength());
        assertEquals("RENT", loanRecord.getHomeOwnership());
        assertEquals("Wedding",loanRecord.getPurpose());
        assertEquals(4.2,loanRecord.getRevolvingCredit());
        assertEquals("60 months",loanRecord.getTerm());
        assertEquals(10,loanRecord.getTotalAccounts());
        assertEquals(3,loanRecord.getMisdemeanourYears());
        assertEquals(3.14,loanRecord.getDebtToIncomeRatio());
        assertEquals(2000,loanRecord.getLoanAmount());
        assertEquals("verified",loanRecord.getLoanVerification());
    }

    @Test
    public void mapRequestToUrlTest(){
        Map<String,String> params=loanCaseServicesTest.mapRequestToUrl(predictionRequest);
        assertEquals("10000",params.get("loan_amnt"));
        assertEquals("60 months",params.get("term"));
        assertEquals("3",params.get("emp_length"));
        assertEquals("RENT",params.get("home_ownership"));
        assertEquals("3000",params.get("annual_inc"));
        assertEquals("GA",params.get("addr_state"));
        assertEquals("3.4",params.get("dti"));
        assertEquals("Wedding",params.get("purpose"));
        assertEquals("2",params.get("delinq_2yrs"));
        assertEquals("2.3",params.get("revol_util"));
        assertEquals("2",params.get("total_acc"));
        assertEquals("2",params.get("longest_credit_length"));
        assertEquals("verified",params.get("verification_status"));


    }

    @Test
    public void mapRequestToLoanCaseTest(){
        LoanCases loanCase=loanCaseServicesTest.mapRequestToLoanCase(predictionCase);

        assertEquals("CA",loanCase.getAddressState());
        assertEquals(20000,loanCase.getAnnualIncome());
        assertEquals(2,loanCase.getEmpLength());
        assertEquals(0,loanCase.getLongestCreditLength());
        assertEquals("NONE", loanCase.getHomeOwnership());
        assertEquals("Wedding",loanCase.getPurpose());
        assertEquals(3,loanCase.getRevolvingCredit());
        assertEquals("12 months",loanCase.getTerm());
        assertEquals(2,loanCase.getTotalAccounts());
        assertEquals(2,loanCase.getMisdemeanourYears());
        assertEquals(2.3,loanCase.getDebtToIncomeRatio());
        assertEquals(3000,loanCase.getLoanAmount());
        assertEquals("verified",loanCase.getVerificationStatus());
        assertEquals("Y",loanCase.getPrediction());
        assertEquals(0.89,loanCase.getConfidence());
        assertEquals(2.3,loanCase.getInterestRate());



    }


}