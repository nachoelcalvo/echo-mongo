package com.personal.mongo.echomongo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    private String userName;
    private int rating;
    private boolean approved;
}
