package com.personal.mongo.echomongo.service.impl;

import com.personal.mongo.echomongo.domain.Policy;
import com.personal.mongo.echomongo.dto.PolicyRequest;

import java.util.List;
import java.util.Optional;

public interface PolicyService {

    List<Policy> getAllPolicies();

    List<Policy> getByCity(String city);

    Optional<Policy> getById(String id);

    Policy createPolicy(PolicyRequest policyRequest);
}
