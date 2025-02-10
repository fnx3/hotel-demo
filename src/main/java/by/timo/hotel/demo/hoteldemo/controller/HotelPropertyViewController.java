package by.timo.hotel.demo.hoteldemo.controller;

import by.timo.hotel.demo.hoteldemo.dto.HotelCreateDto;
import by.timo.hotel.demo.hoteldemo.dto.HotelDto;
import by.timo.hotel.demo.hoteldemo.dto.HotelShortInfoDto;
import by.timo.hotel.demo.hoteldemo.service.HotelPropertyViewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/property-view")
@Tag(name = "Hotel Property View", description = "API для работы с отелями")
public class HotelPropertyViewController {

    private final HotelPropertyViewService hotelPropertyViewService;

    public HotelPropertyViewController(HotelPropertyViewService hotelPropertyViewService) {
        this.hotelPropertyViewService = hotelPropertyViewService;
    }

    @GetMapping("/hotels")
    @Operation(summary = "Получить краткую информацию обо всех отелях",
            description = "Возвращает список всех отелей с краткой информацией")
    @ApiResponse(responseCode = "200", description = "Успешный запрос",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = HotelShortInfoDto.class)))
    public List<HotelShortInfoDto> getShortHotelsInformation() {
        return hotelPropertyViewService.findAllHotels();
    }

    @GetMapping("/hotels/{id}")
    @Operation(summary = "Получить информацию об отеле по ID",
            description = "Возвращает полную информацию об отеле по его идентификатору")
    @ApiResponse(responseCode = "200", description = "Отель найден",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = HotelDto.class)))
    public ResponseEntity<HotelDto> getHotel(
            @Parameter(description = "ID отеля", required = true, example = "1")
            @Valid @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelPropertyViewService.findHotelById(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Поиск отелей по параметрам",
            description = "Возвращает список отелей, соответствующих заданным параметрам")
    @ApiResponse(responseCode = "200", description = "Успешный запрос",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = HotelShortInfoDto.class),
                    examples = @ExampleObject(
                            value = "[{\"id\": 1, \"name\": \"Grand Plaza\", \"description\": \"A luxurious hotel with stunning views.\", \"address\": \"123 Main Street, New York, NY 10001\", \"phone\": \"+375 29 123-45-67\"}, " +
                                    "{\"id\": 2, \"name\": \"Ocean View\", \"description\": \"A beautiful resort by the ocean.\", \"address\": \"456 Beach Road, Miami, FL 33101\", \"phone\": \"+1 305 555-5678\"}]"
                    )))
    public ResponseEntity<List<HotelShortInfoDto>> searchHotels(
            @Parameter(description = "Название отеля", example = "Grand Plaza")
            @RequestParam(required = false) String name,
            @Parameter(description = "Бренд отеля", example = "Luxury Hotels")
            @RequestParam(required = false) String brand,
            @Parameter(description = "Город", example = "New York")
            @RequestParam(required = false) String city,
            @Parameter(description = "Область", example = "NY")
            @RequestParam(required = false) String county,
            @Parameter(description = "Список удобств",
                    schema = @Schema(type = "array",
                            example = "[\"Free WiFi\", \"Free Parking\"]"))
            @RequestParam(required = false) List<String> amenities) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelPropertyViewService
                .searchHotelsByParams(name, brand, city, county, amenities));
    }

    @GetMapping("/histogram/{param}")
    @Operation(summary = "Получить гистограмму по параметру",
            description = "Возвращает количество отелей, сгруппированных по указанному параметру")
    @ApiResponse(responseCode = "200", description = "Успешный запрос",
            content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(value = """
                            {
                                "Minsk": 1,
                                "Moscow": 2,
                                "Grodno": 0
                            }""")))
    public ResponseEntity<Map<String, Integer>> getHotelGroup(
            @Parameter(description = "Параметр для группировки", example = "city", required = true)
            @PathVariable String param) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelPropertyViewService.getHistogram(param));
    }

    @PostMapping("/hotels")
    @Operation(summary = "Создать новый отель",
            description = "Создает новый отель на основе переданных данных")
    @ApiResponse(responseCode = "200", description = "Отель успешно создан",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = HotelShortInfoDto.class)))
    public ResponseEntity<HotelShortInfoDto> createHotel(
            @Parameter(description = "Данные отеля", required = true)
            @Valid @RequestBody HotelCreateDto hotelDto) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelPropertyViewService.createHotel(hotelDto));
    }

    @PostMapping("/hotels/{id}/amenities")
    @Operation(summary = "Добавить удобства к отелю",
            description = "Добавляет список удобств к указанному отелю")
    @ApiResponse(responseCode = "200", description = "Удобства успешно добавлены",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class),
                    examples = @ExampleObject(
                            value = "[\"Free WiFi\", \"Free Parking\"]"
                    )))
    public ResponseEntity<List<String>> addHotelAmenities(
            @Parameter(description = "Список удобств",
                    schema = @Schema(type = "array",
                            example = "[\"Free WiFi\", \"Free Parking\"]"))
            @Valid @RequestBody List<String> amenityDtos,
            @Parameter(description = "ID отеля", required = true, example = "3")
            @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(hotelPropertyViewService
                .addHotelAmenities(amenityDtos, id));
    }
}