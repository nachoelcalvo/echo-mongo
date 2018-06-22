package com.personal.mongo.echomongo.dto;


import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PolicyRequest {

    private String name;
    private AddressDTO address;
    private int price;
}
