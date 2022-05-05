package com.example.loanapprover;

import com.example.loanapprover.pojo.*;
import com.example.loanapprover.service.impl.LoanCaseServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import com.example.loanapprover.service.impl.UserAccessServices;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@SpringBootApplication
@RestController
public class LoanapproverApplication {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(LoanapproverApplication.class, args);
    }

    //@CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    //@CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/login")
    public boolean userLogin(@RequestParam MultiValueMap<String,String> params) throws  Exception{
        boolean response=new UserAccessServices().userLogin(params.getFirst("username"),params.getFirst("password"));
        return response;

    }

    //@CrossOrigin(origins = "http://localhost:8081")
    @GetMapping("/predictLoan")
    public PredictionResponse predictLoan(@RequestParam MultiValueMap<String,String> params) throws Exception{
        return new LoanCaseServices().predictLoanCase(params);
    }

    //@CrossOrigin(origins = "http://localhost:8081")
    @PostMapping("/saveCase")
    public Integer saveLoanCase(@RequestBody PredictionCase predictionCase) throws  Exception{
        return new LoanCaseServices().postLoanCase(predictionCase);
    }

    //@CrossOrigin(origins = "http://localhost:8081")
    @PostMapping("/registerUser")
    public Integer registerUser(@RequestBody UserRegister userRegister) throws Exception{
        return new UserAccessServices().userRegister(userRegister);
    }

    @PostMapping("/saveHistoricalRecord")
    public Integer saveHistoricalRecord(@RequestBody HistoricalRecord historicalRecord) throws Exception{
        return new LoanCaseServices().saveHistoricalRecord(historicalRecord);
    }

    @GetMapping("/getAll")
    public List<Integer> getAllSavedCases() throws Exception{
        return new LoanCaseServices().getAllSavedCases();
    }

    @GetMapping("/getAll/{caseID}")
    public PredictionCase getLoanCase(@PathVariable int caseID) throws Exception{
        return new LoanCaseServices().getLoanCase(caseID);
    }
}
