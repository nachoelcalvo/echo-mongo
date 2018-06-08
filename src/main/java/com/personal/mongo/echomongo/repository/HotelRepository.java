package com.personal.mongo.echomongo.repository;

import com.personal.mongo.echomongo.domain.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends MongoRepository<Hotel,String>, QuerydslPredicateExecutor {

    @Query(value ="{'address.city':?0}")
    List<Hotel> findByCity(String city);
}
