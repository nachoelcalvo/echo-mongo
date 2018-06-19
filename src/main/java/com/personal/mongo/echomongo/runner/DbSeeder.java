package com.personal.mongo.echomongo.runner;

import com.personal.mongo.echomongo.domain.Address;
import com.personal.mongo.echomongo.domain.Policy;
import com.personal.mongo.echomongo.domain.Review;
import com.personal.mongo.echomongo.repository.PolicyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class DbSeeder implements ApplicationRunner {


    private PolicyRepository policyRepository;

    public DbSeeder(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Policy marriot = new Policy(
                "auto",
                130,
                new Address("madrid", "spain"),
                Arrays.asList(
                        new Review("John", 8, false),
                        new Review("Mary", 7, true)
                )
        );

        Policy ibis = new Policy(
                "vida",
                90,
                new Address("burgos", "spain"),
                Arrays.asList(
                        new Review("Teddy", 9, true)
                )
        );

        Policy sofitel = new Policy(
                "hogar",
                200,
                new Address("valladolid", "spain"),
                new ArrayList<>()
        );

        seedData(Arrays.asList(marriot, ibis, sofitel));
    }

    private void seedData(List<Policy> policies) {

        policyRepository.deleteAll();
        policyRepository.saveAll(policies);
    }
}
