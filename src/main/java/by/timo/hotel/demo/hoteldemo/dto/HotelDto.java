package by.timo.hotel.demo.hoteldemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public class HotelDto {

    @Schema(description = "Уникальный идентификатор отеля", example = "1")
    private Long id;

    @NotBlank(message = "Название отеля не может быть пустым")
    @Schema(description = "Название отеля", example = "Grand Plaza")
    private String name;

    @NotBlank(message = "Бренд отеля не может быть пустым")
    @Schema(description = "Бренд отеля", example = "Luxury Hotels")
    private String brand;

    @JsonIgnore
    @Schema(description = "Описание отеля", example = "A luxurious hotel with stunning views.", nullable = true)
    private String description;

    @Valid
    @Schema(description = "Адрес отеля")
    private AddressDto address;

    @Valid
    @Schema(description = "Контактная информация отеля")
    private ContactsDto contacts;

    @Valid
    @Schema(description = "Время заезда и выезда")
    private ArrivalTimeDto arrivalTime;

    @Valid
    @Schema(description = "Список удобств отеля", example = "[\"Free WiFi\", \"Free Parking\"]")
    private Set<String> amenities;

    public HotelDto() {
    }

    public HotelDto(Long id, String name, String brand, AddressDto address, ContactsDto contacts, ArrivalTimeDto arrivalTime, Set<String> amenities) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.address = address;
        this.contacts = contacts;
        this.arrivalTime = arrivalTime;
        this.amenities = amenities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }

    public ContactsDto getContacts() {
        return contacts;
    }

    public void setContacts(ContactsDto contacts) {
        this.contacts = contacts;
    }

    public ArrivalTimeDto getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(ArrivalTimeDto arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Set<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(Set<String> amenities) {
        this.amenities = amenities;
    }
}
