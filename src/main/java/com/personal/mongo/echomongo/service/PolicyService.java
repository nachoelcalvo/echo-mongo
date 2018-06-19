package com.personal.mongo.echomongo.service;

import com.personal.mongo.echomongo.domain.Policy;
import com.personal.mongo.echomongo.repository.PolicyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@CacheConfig(cacheNames = {"policies"})
@Service
public class PolicyService {

    private PolicyRepository policyRepository;

    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    public List<Policy> getAllPolicies(){
        log.info("Fetching all policies from service");
        return policyRepository.findAll();
    }

    @Cacheable("policy")
    public List<Policy> getByCity(String city){
        log.info("Fetching policies with city: " + city + " from service");
        return policyRepository.findByCity(city);
    }
}

