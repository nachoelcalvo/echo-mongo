package com.personal.mongo.echomongo.domain;


import com.personal.mongo.echomongo.domain.vo.Address;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "policies")
public class Policy implements Serializable {

    @Id
    private String id;
    private String name;
    private Address address;
    private List<Review> reviews;
    @Indexed(direction = IndexDirection.ASCENDING)
    private int price;

    public Policy(String name, Address address, int price) {
        this.name = name;
        this.address = address;
        this.price = price;
    }

    public Policy(String name, int price, Address address, List<Review> reviews) {
        this.name = name;
        this.address = address;
        this.reviews = reviews;
        this.price = price;
    }
}


