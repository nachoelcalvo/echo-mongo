package com.personal.mongo.echomongo.service;

import com.personal.mongo.echomongo.domain.Hotel;
import com.personal.mongo.echomongo.domain.QHotel;
import com.personal.mongo.echomongo.repository.HotelRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {

    private HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> getAllHotels(){
        return hotelRepository.findAll();
    }

    public List<Hotel> getByCity(String city){
        return hotelRepository.findByCity(city);
    }

    public List<Hotel> getByCountry(String country){
        QHotel qHotel = new QHotel("hotel");
        BooleanExpression filterEq = qHotel.address.country.eq(country);
        return (List<Hotel>) hotelRepository.findAll(filterEq);
    }

    public List<Hotel> getRecommended(){

        QHotel qHotel = new QHotel("hotel");
        BooleanExpression filterByPrice = qHotel.pricePerNight.lt(100);
        BooleanExpression filterByRating = qHotel.reviews.any().rating.gt(6);

        return (List<Hotel>) hotelRepository.findAll(filterByPrice.and(filterByRating));
    }
}
