package com.personal.mongo.echomongo.runner;

import com.personal.mongo.echomongo.domain.Address;
import com.personal.mongo.echomongo.domain.Hotel;
import com.personal.mongo.echomongo.domain.Review;
import com.personal.mongo.echomongo.repository.HotelRepository;
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


    private HotelRepository hotelRepository;

    public DbSeeder(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Hotel marriot = new Hotel(
                "Marriot",
                130,
                new Address("paris", "France"),
                Arrays.asList(
                        new Review("John", 8, false),
                        new Review("Mary", 7, true)
                )
        );

        Hotel ibis = new Hotel(
                "Ibis",
                90,
                new Address("Bucharest", "Romania"),
                Arrays.asList(
                        new Review("Teddy", 9, true)
                )
        );

        Hotel sofitel = new Hotel(
                "Sofitel",
                200,
                new Address("Rome", "Italy"),
                new ArrayList<>()
        );

        seedData(Arrays.asList(marriot, ibis, sofitel));
    }

    private void seedData(List<Hotel> hotels) {

        hotelRepository.deleteAll();
        hotelRepository.saveAll(hotels);
    }
}
