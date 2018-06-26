package com.personal.mongo.echomongo.repository;

import com.personal.mongo.echomongo.domain.Policy;
import com.personal.mongo.echomongo.domain.Review;
import com.personal.mongo.echomongo.domain.vo.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class PolicyRepositoryTest {

    @Autowired
    private PolicyRepository policyRepository;

    @BeforeEach
    void setUp() {

        Policy policy = new Policy("test_type", 100, new Address("madrid", "Spain"),
                Arrays.asList(
                        new Review("user", 8, false)
                )
        );

        Policy policySaved = policyRepository.save(policy);
    }

    @DisplayName("Should find a policy by city")
    @Test
    public void shouldFindByAddress() {
        List<Policy> policies = policyRepository.findByCity("madrid");
        assertThat(policies, not(empty()));
        assertThat(policies.size(), is(1));
    }
}