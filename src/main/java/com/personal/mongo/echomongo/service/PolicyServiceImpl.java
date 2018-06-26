package com.personal.mongo.echomongo.service;

import com.personal.mongo.echomongo.domain.Policy;
import com.personal.mongo.echomongo.domain.vo.Address;
import com.personal.mongo.echomongo.dto.AddressDTO;
import com.personal.mongo.echomongo.dto.PolicyRequest;
import com.personal.mongo.echomongo.repository.PolicyRepository;
import com.personal.mongo.echomongo.service.impl.PolicyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@CacheConfig(cacheNames = {"policies"})
@Service
public class PolicyServiceImpl implements PolicyService{

    private PolicyRepository policyRepository;

    public PolicyServiceImpl(PolicyRepository policyRepository) {
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

    public Policy createPolicy(PolicyRequest policyRequest){
        Policy policy = obtainPolicy(policyRequest);
        return policyRepository.save(policy);
    }

    private Policy obtainPolicy(PolicyRequest policyRequest) {

        AddressDTO addressDTO = policyRequest.getAddress();
        Address address = new Address(addressDTO.getCity(), addressDTO.getCountry());

        return new Policy(policyRequest.getName(), address, policyRequest.getPrice());
    }
}

