package by.timo.hotel.demo.hoteldemo.mapper;

import by.timo.hotel.demo.hoteldemo.model.Amenity;
import org.springframework.stereotype.Component;

@Component
public class AmenityMapper {

    public String fromEntityToDto(Amenity amenity) {
        return amenity.getName();
    }

    public Amenity fromDtoToEntity(String amenityDto) {
        return new Amenity(amenityDto);
    }
}
