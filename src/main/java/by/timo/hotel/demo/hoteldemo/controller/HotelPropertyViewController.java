package by.timo.hotel.demo.hoteldemo.controller;

import by.timo.hotel.demo.hoteldemo.dto.HotelCreateDto;
import by.timo.hotel.demo.hoteldemo.dto.HotelDto;
import by.timo.hotel.demo.hoteldemo.dto.HotelShortInfoDto;
import by.timo.hotel.demo.hoteldemo.service.HotelPropertyViewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/property-view")
@Tag(name = "Hotel Property View", description = "API для работы с отелями")
public class HotelPropertyViewController implements HotelPropertyApi {

    private final HotelPropertyViewService hotelPropertyViewService;

    public HotelPropertyViewController(HotelPropertyViewService hotelPropertyViewService) {
        this.hotelPropertyViewService = hotelPropertyViewService;
    }

    @Override
    @GetMapping("/hotels")
    public List<HotelShortInfoDto> getShortHotelsInformation() {
        return hotelPropertyViewService.findAllHotels();
    }

    @Override
    @GetMapping("/hotels/{id}")
    public ResponseEntity<HotelDto> getHotel(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelPropertyViewService.findHotelById(id));
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<List<HotelShortInfoDto>> searchHotels(String name, String brand, String city,
                                                                String county, List<String> amenities) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelPropertyViewService
                .searchHotelsByParams(name, brand, city, county, amenities));
    }

    @Override
    @GetMapping("/histogram/{param}")
    public ResponseEntity<Map<String, Integer>> getHotelGroup(String param) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelPropertyViewService.getHistogram(param));
    }

    @Override
    @PostMapping("/hotels")
    public ResponseEntity<HotelShortInfoDto> createHotel(HotelCreateDto hotelDto) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelPropertyViewService.createHotel(hotelDto));
    }

    @Override
    @PostMapping("/hotels/{id}/amenities")
    public ResponseEntity<List<String>> addHotelAmenities(List<String> amenityDtos, Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelPropertyViewService
                .addHotelAmenities(amenityDtos, id));
    }
}