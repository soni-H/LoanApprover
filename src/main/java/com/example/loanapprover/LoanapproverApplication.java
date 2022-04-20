package com.example.loanapprover;

import com.example.loanapprover.pojo.*;
import com.example.loanapprover.service.impl.LoanCaseServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/saveCase")
    public Integer saveLoanCase(@RequestBody PredictionCase predictionCase) throws  Exception{
        return new LoanCaseServices().postLoanCase(predictionCase);
    }

    @PostMapping("/registerUser")
    public Integer registerUser(@RequestBody UserRegister userRegister) throws Exception{
        return new UserAccessServices().userRegister(userRegister);
    }
}
