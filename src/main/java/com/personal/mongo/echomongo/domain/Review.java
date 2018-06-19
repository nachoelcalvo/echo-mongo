package com.personal.mongo.echomongo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review implements Serializable{

    private String userName;
    private int rating;
    private boolean approved;
}
