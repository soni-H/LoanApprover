package com.example.loanapprover;

import com.example.loanapprover.pojo.PredictionRequest;
import com.example.loanapprover.pojo.PredictionResponse;
import com.example.loanapprover.service.impl.LoanCaseServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.loanapprover.pojo.UserDetails;
import com.example.loanapprover.service.impl.UserAccessServices;

@SpringBootApplication
@RestController
public class LoanapproverApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanapproverApplication.class, args);
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/login")
    public boolean userLogin(@RequestBody UserDetails userDetails) throws  Exception{
        boolean response=new UserAccessServices().userLogin(userDetails.getUsername(),userDetails.getPassword());
        return response;

    }

    @GetMapping("/predictLoan")
    public PredictionResponse predictLoan(@RequestParam MultiValueMap<String,String> params) throws Exception{
        return new LoanCaseServices().predictLoanCase(params);
    }

}
