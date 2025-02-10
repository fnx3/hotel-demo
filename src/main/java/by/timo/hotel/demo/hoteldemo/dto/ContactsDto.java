package by.timo.hotel.demo.hoteldemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ContactsDto {

    @NotBlank
    @Pattern(regexp = "^\\+\\d{3} \\d{2} \\d{3}-\\d{2}-\\d{2}$",
            message = "Номер телефона должен соответствовать формату +XXX XX XXX-XX-XX")
    @Schema(description = "Номер телефона в формате +XXX XX XXX-XX-XX", example = "+375 29 123-45-67")
    private String phone;

    @NotBlank
    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Некорректный формат адреса электронной почты")
    @Schema(description = "Адрес электронной почты", example = "info@example.com")
    private String email;

    public ContactsDto() {
    }

    public ContactsDto(String phone, String email) {
        this.phone = phone;
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
