package by.timo.hotel.demo.hoteldemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class AddressDto {

    @NotNull(message = "Номер дома не может быть пустым")
    @Min(value = 1, message = "Номер дома должен быть положительным числом")
    @Digits(integer = 10, fraction = 0, message = "Номер дома должен содержать только цифры")
    @Schema(description = "Номер дома", example = "123")
    private int houseNumber;

    @NotBlank(message = "Улица не может быть пустой")
    @Schema(description = "Название улицы", example = "Main Street")
    private String street;

    @NotBlank(message = "Город не может быть пустым")
    @Schema(description = "Название города", example = "New York")
    private String city;

    @NotBlank(message = "Округ не может быть пустой")
    @Schema(description = "Название округа", example = "NY")
    private String county;

    @Pattern(regexp = "\\d{6}", message = "Почтовый индекс должен состоять из 6 цифр")
    @Schema(description = "Почтовый индекс", example = "123456")
    private String postCode;

    public AddressDto() {
    }

    public AddressDto(int houseNumber, String street, String city, String county, String postCode) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.county = county;
        this.postCode = postCode;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
