package com.personal.mongo.echomongo.web;


import com.personal.mongo.echomongo.domain.Policy;
import com.personal.mongo.echomongo.dto.PolicyRequest;
import com.personal.mongo.echomongo.exception.ResourceNotFoundException;
import com.personal.mongo.echomongo.service.PolicyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/policies")
public class PolicyController {

    private PolicyServiceImpl policyServiceImpl;

    public PolicyController(PolicyServiceImpl policyServiceImpl) {
        this.policyServiceImpl = policyServiceImpl;
    }

    @GetMapping(path = "/all")
    public List<Policy> getPolicies(){
        log.info("Fetching all policies from controller");
        return policyServiceImpl.getAllPolicies();
    }

    @GetMapping(path = "/{id}")
    public Policy getByPolicyById(@PathVariable String id){
        log.info("Fetching policies with id: " + id + " from controller");
        return policyServiceImpl.getById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Policy with id %s not found", id)));
    }

    @GetMapping(path = "/addresses/{city}")
    public List<Policy> getByPolicyByCity(@PathVariable String city){
        log.info("Fetching policies with city: " + city + " from controller");
        return policyServiceImpl.getByCity(city);
    }

    @PostMapping()
    public Policy createPolicy(@RequestBody PolicyRequest policyRequest){
        return policyServiceImpl.createPolicy(policyRequest);
    }
}
