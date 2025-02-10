package by.timo.hotel.demo.hoteldemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class HotelShortInfoDto {
    @Schema(description = "Уникальный идентификатор отеля", example = "1")
    private Long id;

    @Schema(description = "Название отеля", example = "Grand Plaza")
    private String name;

    @Schema(description = "Описание отеля", example = "A luxurious hotel with stunning views.")
    private String description;

    @Schema(description = "Адрес отеля", example = "123 Main Street, New York, NY 10001")
    private String address;

    @Schema(description = "Контактный телефон отеля", example = "+375 29 123-45-67")
    private String phone;

    public HotelShortInfoDto() {
    }

    public HotelShortInfoDto(Long id, String name, String description, String address, String phone) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.phone = phone;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
