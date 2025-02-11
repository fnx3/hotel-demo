package by.timo.hotel.demo.hoteldemo.service.core;

import by.timo.hotel.demo.hoteldemo.dto.HotelCreateDto;
import by.timo.hotel.demo.hoteldemo.dto.HotelDto;
import by.timo.hotel.demo.hoteldemo.dto.HotelShortInfoDto;
import by.timo.hotel.demo.hoteldemo.exception.AmenityNotFoundException;
import by.timo.hotel.demo.hoteldemo.exception.HotelNotFoundException;
import by.timo.hotel.demo.hoteldemo.model.Hotel;
import by.timo.hotel.demo.hoteldemo.mapper.HotelMapper;
import by.timo.hotel.demo.hoteldemo.mapper.HotelShortInfoMapper;
import by.timo.hotel.demo.hoteldemo.repository.HotelPropertyViewRepository;
import by.timo.hotel.demo.hoteldemo.repository.HotelPropertyViewRepositoryCustom;
import by.timo.hotel.demo.hoteldemo.repository.specification.HotelSpecifications;
import by.timo.hotel.demo.hoteldemo.service.AmenityRepository;
import by.timo.hotel.demo.hoteldemo.service.HotelPropertyViewService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DefaultHotelPropertyViewService implements HotelPropertyViewService {

    private final HotelPropertyViewRepository hotelPropertyViewRepository;
    private final HotelPropertyViewRepositoryCustom repositoryCustom;
    private final HotelShortInfoMapper hotelShortInfoMapper;
    private final HotelMapper hotelMapper;
    private final AmenityRepository amenityRepository;

    public DefaultHotelPropertyViewService(HotelPropertyViewRepository hotelPropertyViewRepository, HotelPropertyViewRepositoryCustom repositoryCustom, AmenityRepository amenityRepository, HotelShortInfoMapper hotelShortInfoMapper, HotelMapper hotelMapper) {
        this.hotelPropertyViewRepository = hotelPropertyViewRepository;
        this.repositoryCustom = repositoryCustom;
        this.hotelShortInfoMapper = hotelShortInfoMapper;
        this.hotelMapper = hotelMapper;
        this.amenityRepository = amenityRepository;
    }

    @Override
    public List<HotelShortInfoDto> findAllHotels() {
        List<HotelShortInfoDto> hotels = hotelPropertyViewRepository.findAll().stream()
                .map(hotelShortInfoMapper::fromEntityToDto)
                .collect(Collectors.toList());

        if (hotels.isEmpty()) {
            throw new HotelNotFoundException("Not a single hotel was found.");
        }

        return hotels;
    }

    @Override
    public HotelDto findHotelById(Long id) {
        return hotelPropertyViewRepository.findById(id).map(hotelMapper::fromEntityToDto)
                .orElseThrow(() -> new HotelNotFoundException("Hotel was not found."));
    }

    @Override
    public List<HotelShortInfoDto> searchHotelsByParams(String name, String brand, String city,
                                                        String county, List<String> amenities) {
        Specification<Hotel> spec = Specification.where(HotelSpecifications.hasName(name))
                .and(HotelSpecifications.hasBrand(brand))
                .and(HotelSpecifications.hasCity(city))
                .and(HotelSpecifications.hasCounty(county))
                .and(HotelSpecifications.hasAmenities(amenities));

        List<Hotel> hotels = hotelPropertyViewRepository.findAll(spec);

        if (hotels.isEmpty()) {
            throw new HotelNotFoundException("No hotels found with the specified parameters.");
        }

        return hotels.stream()
                .map(hotelShortInfoMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, Integer> getHistogram(String columnName) {
        return switch (columnName.toLowerCase()) {
            case "brand", "city", "county", "amenities" -> repositoryCustom.getHistogramByColumn(columnName);
            default -> throw new IllegalArgumentException("Invalid parameter: " + columnName);
        };
    }

    @Override
    public HotelShortInfoDto createHotel(HotelCreateDto hotelDto) {
        Hotel hotel = hotelMapper.fromDtoToEntity(hotelDto);
        Hotel savedHotel = hotelPropertyViewRepository.save(hotel);

        return hotelShortInfoMapper.fromEntityToDto(savedHotel);
    }

    @Override
    public List<String> addHotelAmenities(List<String> amenityDtos, Long id) {
        int hotelCount = hotelPropertyViewRepository.countHotelById(id);
        if (hotelCount == 0) {
            throw new HotelNotFoundException("Отель с id " + id + " не найден");
        }

        int amenitiesCount = amenityRepository.countAmenitiesByNames(amenityDtos);
        if (amenitiesCount != amenityDtos.size()) {
            throw new AmenityNotFoundException("Не все удобства найдены");
        }
        hotelPropertyViewRepository.updateHotelAmenities(amenityDtos, id);

        return amenityDtos;
    }
}
