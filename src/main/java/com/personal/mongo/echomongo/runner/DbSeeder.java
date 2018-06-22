package com.personal.mongo.echomongo.runner;

import com.personal.mongo.echomongo.domain.vo.Address;
import com.personal.mongo.echomongo.domain.Policy;
import com.personal.mongo.echomongo.domain.Review;
import com.personal.mongo.echomongo.repository.PolicyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class DbSeeder implements ApplicationRunner {


    private PolicyRepository policyRepository;

    public DbSeeder(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<String> users = Arrays.asList("Juan", "Jose", "pedro");
        List<String> types = Arrays.asList("hogar", "vida", "auto");
        List<String> cities = Arrays.asList("madrid", "barcelona", "valencia");
        List<Integer> prices = Arrays.asList(130, 200, 500, 800);


        Runnable runnable = () -> {
            Policy policy = createPolicy(users, types, cities, prices);
            policyRepository.save(policy);
        };

        cleanDB();

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(runnable, 1,100, TimeUnit.MILLISECONDS);
    }

    private void cleanDB() {
        policyRepository.deleteAll();
    }

    private Policy createPolicy(List<String> users, List<String> types, List<String> cities, List<Integer> prices) {
        return new Policy(
                        types.get(new Random().nextInt(types.size())),
                        prices.get(new Random().nextInt(prices.size())),
                        new Address(cities.get(new Random().nextInt(cities.size())), "Spain"),
                        Arrays.asList(
                                new Review(users.get(new Random().nextInt(users.size())), 8, false)
                        )
                );
    }
}
