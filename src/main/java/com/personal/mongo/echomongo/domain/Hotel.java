package com.personal.mongo.echomongo.domain;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "hotels")
public class Hotel {

    @Id
    private String id;
    private String name;
    private Address address;
    private List<Review> reviews;
    @Indexed(direction = IndexDirection.ASCENDING)
    private int pricePerNight;

    public Hotel(String name, int pricePerNight, Address address, List<Review> reviews) {
        this.name = name;
        this.address = address;
        this.reviews = reviews;
        this.pricePerNight = pricePerNight;
    }
}


