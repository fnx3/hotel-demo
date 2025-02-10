package by.timo.hotel.demo.hoteldemo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public class ArrivalTimeDto {

    @NotBlank(message = "Время заезда не может быть пустым")
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Время заезда должно быть в формате HH:MM")
    @Schema(description = "Время заезда в формате HH:MM", example = "15:00")
    private String checkIn;

    @Schema(description = "Время выезда в формате HH:MM", example = "11:00", nullable = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String checkOut;

    public ArrivalTimeDto() {
    }

    public ArrivalTimeDto(String checkIn, String checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }
}
