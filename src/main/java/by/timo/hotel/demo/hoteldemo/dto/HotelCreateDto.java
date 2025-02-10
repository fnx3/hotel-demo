package by.timo.hotel.demo.hoteldemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public class HotelCreateDto {

    @NotBlank(message = "Название отеля не может быть пустым")
    @Schema(description = "Название отеля", example = "Grand Plaza")
    private String name;

    @NotBlank(message = "Бренд отеля не может быть пустым")
    @Schema(description = "Бренд отеля", example = "Luxury Hotels")
    private String brand;

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

    public HotelCreateDto() {
    }

    public HotelCreateDto(String name, String brand, AddressDto address, ContactsDto contacts, ArrivalTimeDto arrivalTime) {
        this.name = name;
        this.brand = brand;
        this.address = address;
        this.contacts = contacts;
        this.arrivalTime = arrivalTime;
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
}

