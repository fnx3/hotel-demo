package by.timo.hotel.demo.hoteldemo.mapper;

import by.timo.hotel.demo.hoteldemo.dto.AddressDto;
import by.timo.hotel.demo.hoteldemo.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressDto fromEntityToDto(Address address) {
        return new AddressDto(address.getHouseNumber(),
                address.getStreet(),
                address.getCity(),
                address.getCounty(),
                address.getPostCode());
    }

    public Address fromDtoToEntity(AddressDto addressDto) {
        return new Address(addressDto.getHouseNumber(),
                addressDto.getStreet(),
                addressDto.getCity(),
                addressDto.getCounty(),
                addressDto.getPostCode());
    }
}
