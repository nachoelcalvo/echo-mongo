package com.personal.mongo.echomongo.web;


import com.personal.mongo.echomongo.domain.Hotel;
import com.personal.mongo.echomongo.service.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/hotels")
public class HotelsController {

    private HotelService hotelService;

    public HotelsController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping(path = "/all")
    public List<Hotel> getHotels(){
        return hotelService.getAllHotels();
    }

    @GetMapping(path = "/address/{city}")
    public List<Hotel> getByHotelsByCity(@PathVariable String city){
        return hotelService.getByCity(city);
    }

    @GetMapping(path = "/country/{country}")
    public List<Hotel> getByHotelsByCountry(@PathVariable String country){
        return hotelService.getByCountry(country);
    }

}
