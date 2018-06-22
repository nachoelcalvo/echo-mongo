package com.personal.mongo.echomongo.service;

import com.personal.mongo.echomongo.domain.Policy;
import com.personal.mongo.echomongo.repository.PolicyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Policy> getByCity(String city){
        log.info("Fetching policies with city: " + city + " from service");
        return policyRepository.findByCity(city);
    }

    @Cacheable(key="#id")
    public Optional<Policy> getById(String id){
        log.info("Fetching policies with id: " + id + " from service");
        return policyRepository.findById(id);
    }
}

