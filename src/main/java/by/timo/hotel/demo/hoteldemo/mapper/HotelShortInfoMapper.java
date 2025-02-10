package by.timo.hotel.demo.hoteldemo.mapper;

import by.timo.hotel.demo.hoteldemo.dto.HotelShortInfoDto;
import by.timo.hotel.demo.hoteldemo.model.Address;
import by.timo.hotel.demo.hoteldemo.model.Hotel;
import org.springframework.stereotype.Component;

@Component
public class HotelShortInfoMapper {

    public HotelShortInfoDto fromEntityToDto(Hotel hotel) {
        Address address = hotel.getAddress();
        String addressAsString = address.getHouseNumber() + " " + address.getStreet() + ", "
                + address.getCity() + ", " + address.getPostCode() + ", " + address.getCounty();

        return new HotelShortInfoDto(hotel.getId(),
                hotel.getName(),
                hotel.getDescription(),
                addressAsString,
                hotel.getContacts().getPhone());
    }
}
