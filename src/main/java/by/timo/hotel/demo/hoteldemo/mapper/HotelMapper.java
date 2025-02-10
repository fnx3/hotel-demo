package by.timo.hotel.demo.hoteldemo.mapper;

import by.timo.hotel.demo.hoteldemo.dto.AddressDto;
import by.timo.hotel.demo.hoteldemo.dto.ArrivalTimeDto;
import by.timo.hotel.demo.hoteldemo.dto.ContactsDto;
import by.timo.hotel.demo.hoteldemo.dto.HotelCreateDto;
import by.timo.hotel.demo.hoteldemo.dto.HotelDto;
import by.timo.hotel.demo.hoteldemo.model.Address;
import by.timo.hotel.demo.hoteldemo.model.ArrivalTime;
import by.timo.hotel.demo.hoteldemo.model.Contacts;
import by.timo.hotel.demo.hoteldemo.model.Hotel;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class HotelMapper {

    private final AddressMapper addressMapper;
    private final ContactsMapper contactsMapper;
    private final ArrivalTimeMapper arrivalTimeMapper;
    private final AmenityMapper amenityMapper;

    public HotelMapper(AddressMapper addressMapper, ContactsMapper contactsMapper, ArrivalTimeMapper arrivalTimeMapper, AmenityMapper amenityMapper) {
        this.addressMapper = addressMapper;
        this.contactsMapper = contactsMapper;
        this.arrivalTimeMapper = arrivalTimeMapper;
        this.amenityMapper = amenityMapper;
    }

    public HotelDto fromEntityToDto(Hotel hotel) {
        AddressDto addressDto = addressMapper.fromEntityToDto(hotel.getAddress());
        ContactsDto contactsDto = contactsMapper.fromEntityToDto(hotel.getContacts());
        ArrivalTimeDto arrivalTimeDto = arrivalTimeMapper.fromEntityToDto(hotel.getArrivalTime());

        Set<String> amenitiesDto = hotel.getAmenities().stream()
                .map(amenityMapper::fromEntityToDto)
                .collect(Collectors.toSet());

        return new HotelDto(hotel.getId(),
                hotel.getName(),
                hotel.getBrand(),
                addressDto,
                contactsDto,
                arrivalTimeDto,
                amenitiesDto);
    }

    public Hotel fromDtoToEntity(HotelCreateDto hotelDto) {
        Address address = addressMapper.fromDtoToEntity(hotelDto.getAddress());
        Contacts contacts = contactsMapper.fromDtoToEntity(hotelDto.getContacts());
        ArrivalTime arrivalTime = arrivalTimeMapper.fromDtoToEntity(hotelDto.getArrivalTime());

        return new Hotel(hotelDto.getName(),
                hotelDto.getBrand(),
                hotelDto.getDescription(),
                address,
                contacts,
                arrivalTime);
    }
}
