package by.timo.hotel.demo.hoteldemo.service;

import by.timo.hotel.demo.hoteldemo.dto.HotelCreateDto;
import by.timo.hotel.demo.hoteldemo.dto.HotelDto;
import by.timo.hotel.demo.hoteldemo.dto.HotelShortInfoDto;

import java.util.List;
import java.util.Map;

public interface HotelPropertyViewService {

    List<HotelShortInfoDto> findAllHotels();

    HotelDto findHotelById(Long id);

    Map<String, Integer> getHistogram(String param);

    List<HotelShortInfoDto> searchHotelsByParams(String name, String brand, String city, String county,
                                                 List<String> amenities);

    HotelShortInfoDto createHotel(HotelCreateDto hotelDto);

    List<String> addHotelAmenities(List<String> amenityDtos, Long id);
}
