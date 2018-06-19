package com.personal.mongo.echomongo.web;


import com.personal.mongo.echomongo.domain.Policy;
import com.personal.mongo.echomongo.service.PolicyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/policies")
public class PolicyController {

    private PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping(path = "/all")
    public List<Policy> getPolicies(){
        log.info("Fetching all policies from controller");
        return policyService.getAllPolicies();
    }

    @GetMapping(path = "/addresses/{city}")
    public List<Policy> getByPolicyByCity(@PathVariable String city){
        log.info("Fetching policies with city: " + city + " from controller");
        return policyService.getByCity(city);
    }
}