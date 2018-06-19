package com.personal.mongo.echomongo.repository;

import com.personal.mongo.echomongo.domain.Policy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolicyRepository extends MongoRepository<Policy,String> {

    @Query(value ="{'address.city': ?0}")
    List<Policy> findByCity(String city);
}
