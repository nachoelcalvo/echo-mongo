package com.personal.mongo.echomongo.service;

import com.personal.mongo.echomongo.domain.Policy;
import com.personal.mongo.echomongo.domain.Review;
import com.personal.mongo.echomongo.domain.vo.Address;
import com.personal.mongo.echomongo.dto.AddressDTO;
import com.personal.mongo.echomongo.dto.PolicyRequest;
import com.personal.mongo.echomongo.repository.PolicyRepository;
import com.personal.mongo.echomongo.service.impl.PolicyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

public class PolicyServiceImplTest {

    @Mock
    private PolicyRepository policyRepositoryMock;

    private PolicyService policyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        policyService = new PolicyServiceImpl(policyRepositoryMock);
    }

    @Test
    @DisplayName("Should get all policies")
    void shouldGetAllPolicies() {
        given(policyRepositoryMock.findAll()).willReturn(obtainListTestPolicies());

        List<Policy> policies = policyService.getAllPolicies();

        assertThat(policies, not(empty()));
        assertThat(policies, hasSize(1));

        then(policyRepositoryMock).should()
                .findAll();
    }

    @Test
    @DisplayName("Should get a policy by city")
    void shouldGetPolicyByCity() {
        given(policyRepositoryMock.findByCity("madrid")).willReturn(obtainListTestPolicies());

        List<Policy> policies = policyService.getByCity("madrid");

        assertThat(policies, hasSize(1));
        assertThat(getCityFromPolicy(policies.get(0)), equalTo("madrid"));

        then(policyRepositoryMock).should()
                .findByCity("madrid");
    }

    @Test
    @DisplayName("Should get policy by Id")
    void shouldGetPolicyById() {
        final String POLICY_ID = "5b3221823f9d8e1a899c347f";

        Optional<Policy> testPolicy = Optional.of(obtainTestPolicy());

        given(policyRepositoryMock.findById(anyString())).willReturn(testPolicy);

        Optional<Policy> policy = policyService.getById(POLICY_ID);

        assertThat(policy, equalTo(testPolicy));

        then(policyRepositoryMock).should().findById(POLICY_ID);
    }

    @Test
    @DisplayName("Should create a policy")
    void shouldCreatePolicy() {
        Policy testPolicy = obtainTestPolicy();

        given(policyRepositoryMock.save(BDDMockito.any())).willReturn(obtainTestPolicy());

        Policy policySaved = policyService.createPolicy(obtainTestPolicyRequest());

        assertThat(policySaved, equalTo(testPolicy));

        then(policyRepositoryMock).should().save(BDDMockito.any());
    }

    private String getCityFromPolicy(Policy policy) {
        return policy.getAddress().getCity();
    }

    private List<Policy> obtainListTestPolicies() {
        return Arrays.asList(obtainTestPolicy());
    }

    private Policy obtainTestPolicy() {
        return new Policy("test_type", 100, new Address("madrid", "Spain"),
                Arrays.asList(
                        new Review("user", 8, false)
                ));
    }

    private PolicyRequest obtainTestPolicyRequest(){
        return new PolicyRequest("test_type", new AddressDTO("madrid", "spain"), 100);
    }
}