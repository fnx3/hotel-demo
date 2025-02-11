package by.timo.hotel.demo.hoteldemo.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import by.timo.hotel.demo.hoteldemo.dto.HotelCreateDto;
import by.timo.hotel.demo.hoteldemo.dto.HotelDto;
import by.timo.hotel.demo.hoteldemo.exception.AmenityNotFoundException;
import by.timo.hotel.demo.hoteldemo.exception.HotelNotFoundException;
import by.timo.hotel.demo.hoteldemo.mapper.HotelMapper;
import by.timo.hotel.demo.hoteldemo.mapper.HotelShortInfoMapper;
import by.timo.hotel.demo.hoteldemo.repository.specification.HotelSpecifications;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import by.timo.hotel.demo.hoteldemo.repository.HotelPropertyViewRepository;
import by.timo.hotel.demo.hoteldemo.repository.HotelPropertyViewRepositoryCustom;
import by.timo.hotel.demo.hoteldemo.service.core.DefaultHotelPropertyViewService;
import by.timo.hotel.demo.hoteldemo.model.Hotel;
import by.timo.hotel.demo.hoteldemo.dto.HotelShortInfoDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DefaultHotelPropertyViewServiceTest {

    @Mock
    private HotelPropertyViewRepository hotelPropertyViewRepository;

    @Mock
    private HotelPropertyViewRepositoryCustom repositoryCustom;

    @Mock
    private AmenityRepository amenityRepository;

    @Mock
    private HotelMapper hotelMapper;

    @Mock
    private HotelShortInfoMapper hotelShortInfoMapper;

    @InjectMocks
    private DefaultHotelPropertyViewService service;

    private Hotel hotel;
    private HotelDto hotelDto;
    private HotelShortInfoDto hotelShortInfoDto;
    private List<Hotel> hotels;

    @BeforeEach
    public void setUp() {
        hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName("Test Hotel");
        hotels = Collections.singletonList(hotel);

        hotelDto = new HotelDto();
        hotelDto.setId(hotel.getId());
        hotelDto.setName(hotel.getName());

        hotelShortInfoDto = new HotelShortInfoDto();
        hotelShortInfoDto.setId(1L);
        hotelShortInfoDto.setName("Test Hotel");
    }

    @Test
    public void findAllHotels_shouldReturnHotels() {
        when(hotelPropertyViewRepository.findAll()).thenReturn(hotels);
        when(hotelShortInfoMapper.fromEntityToDto(hotel)).thenReturn(hotelShortInfoDto);

        List<HotelShortInfoDto> result = service.findAllHotels();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());

        verify(hotelShortInfoMapper, times(1)).fromEntityToDto(hotel);
        verify(hotelPropertyViewRepository, times(1)).findAll();
    }

    @Test
    public void findAllHotels_shouldThrowHotelNotFoundException() {
        when(hotelPropertyViewRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(HotelNotFoundException.class, () -> service.findAllHotels());
        verify(hotelPropertyViewRepository, times(1)).findAll();
    }


    @Test
    void findHotelById_shouldReturnHotel() {
        when(hotelPropertyViewRepository.findById(1L)).thenReturn(Optional.of(hotel));
        when(hotelMapper.fromEntityToDto(hotel)).thenReturn(hotelDto);

        HotelDto result = service.findHotelById(1L);

        assertNotNull(result);
        assertEquals(hotel.getId() ,result.getId());
        assertEquals(hotel.getName(), result.getName());

        verify(hotelMapper, times(1)).fromEntityToDto(hotel);
        verify(hotelPropertyViewRepository, times(1)).findById(1L);
    }

    @Test
    void findHotelBy_shouldThrowHotelNotFoundException() {
        when(hotelPropertyViewRepository.findById(333L)).thenReturn(Optional.empty());

        assertThrows(HotelNotFoundException.class, () -> service.findHotelById(333L));
        verify(hotelPropertyViewRepository, times(1)).findById(333L);
    }

    @Test
    void searchHotelsByParams_shouldReturnFilteredHotels() {
        Specification<Hotel> spec = Specification.where(HotelSpecifications.hasName("Test"))
                .and(HotelSpecifications.hasBrand(null))
                .and(HotelSpecifications.hasCity(null))
                .and(HotelSpecifications.hasCounty(null))
                .and(HotelSpecifications.hasAmenities(null));

        when(hotelPropertyViewRepository.findAll(any(Specification.class))).thenReturn(hotels);
        when(hotelShortInfoMapper.fromEntityToDto(hotel)).thenReturn(hotelShortInfoDto);

        List<HotelShortInfoDto> result = service.searchHotelsByParams("Test", null, null, null, null);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());

        verify(hotelPropertyViewRepository, times(1)).findAll(any(Specification.class));
        verify(hotelShortInfoMapper, times(1)).fromEntityToDto(hotel);
    }

    @Test
    void searchHotelsByParams_shouldThrowHotelNotFoundException() {
        Specification<Hotel> spec = Specification.where(HotelSpecifications.hasName("Unknown"))
                .and(HotelSpecifications.hasBrand(null))
                .and(HotelSpecifications.hasCity(null))
                .and(HotelSpecifications.hasCounty(null))
                .and(HotelSpecifications.hasAmenities(null));

        when(hotelPropertyViewRepository.findAll(any(Specification.class))).thenReturn(Collections.emptyList());

        assertThrows(HotelNotFoundException.class, () -> service.searchHotelsByParams("Unknown", null, null, null, null));
        verify(hotelPropertyViewRepository, times(1)).findAll(any(Specification.class));
    }

    @Test
    void getHistogram_shouldReturnHistogramForValidColumn() {
        Map<String, Integer> histogram = Map.of("brand", 5, "city", 10);
        when(repositoryCustom.getHistogramByColumn("brand")).thenReturn(histogram);

        Map<String, Integer> result = service.getHistogram("brand");

        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        verify(repositoryCustom, times(1)).getHistogramByColumn("brand");
    }

    @Test
    void getHistogram_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> service.getHistogram("invalid_column"));
    }

    @Test
    @Transactional
    void createHotel_shouldReturnCreatedHotel() {
        HotelCreateDto hotelCreateDto = new HotelCreateDto();
        hotelCreateDto.setName("New Hotel");
        hotelCreateDto.setBrand("Test Brand");

        Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName("New Hotel");

        HotelShortInfoDto hotelShortInfoDto = new HotelShortInfoDto();
        hotelShortInfoDto.setId(1L);
        hotelShortInfoDto.setName("New Hotel");

        when(hotelMapper.fromDtoToEntity(hotelCreateDto)).thenReturn(hotel);
        when(hotelPropertyViewRepository.save(hotel)).thenReturn(hotel);
        when(hotelShortInfoMapper.fromEntityToDto(hotel)).thenReturn(hotelShortInfoDto);

        HotelShortInfoDto result = service.createHotel(hotelCreateDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("New Hotel", result.getName());
        verify(hotelMapper, times(1)).fromDtoToEntity(hotelCreateDto);
        verify(hotelPropertyViewRepository, times(1)).save(hotel);
        verify(hotelShortInfoMapper, times(1)).fromEntityToDto(hotel);
    }

    @Test
    @Transactional
    void addHotelAmenities_shouldAddAmenitiesToHotel() {
        List<String> amenityNames = List.of("Free WiFi");

        when(hotelPropertyViewRepository.countHotelById(1L)).thenReturn(1);
        when(amenityRepository.countAmenitiesByNames(amenityNames)).thenReturn(1);

        List<String> result = service.addHotelAmenities(amenityNames, 1L);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Free WiFi", result.get(0));
        verify(hotelPropertyViewRepository, times(1)).countHotelById(1L);
        verify(amenityRepository, times(1)).countAmenitiesByNames(amenityNames);
    }

    @Test
    @Transactional
    void addHotelAmenities_shouldThrowHotelNotFoundException() {
        when(hotelPropertyViewRepository.countHotelById(3L)).thenReturn(0);

        assertThrows(HotelNotFoundException.class, () -> service.addHotelAmenities(Collections.singletonList("Free WiFi"), 3L));
        verify(hotelPropertyViewRepository, times(1)).countHotelById(3L);
    }

    @Test
    @Transactional
    void addHotelAmenities_shouldThrowAmenityNotFoundException() {
        List<String> amenityNames = List.of("Free Coffee");

        when(hotelPropertyViewRepository.countHotelById(1L)).thenReturn(1);
        when(amenityRepository.countAmenitiesByNames(amenityNames)).thenReturn(0);

        assertThrows(AmenityNotFoundException.class, () -> service.addHotelAmenities(Collections.singletonList("Free Coffee"), 1L));
        verify(hotelPropertyViewRepository, times(1)).countHotelById(1L);
        verify(amenityRepository, times(1)).countAmenitiesByNames(amenityNames);
    }
}
