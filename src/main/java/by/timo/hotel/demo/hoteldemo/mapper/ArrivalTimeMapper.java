package by.timo.hotel.demo.hoteldemo.mapper;

import by.timo.hotel.demo.hoteldemo.dto.ArrivalTimeDto;
import by.timo.hotel.demo.hoteldemo.model.ArrivalTime;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class ArrivalTimeMapper {

    public ArrivalTimeDto fromEntityToDto(ArrivalTime arrivalTime) {
        String checkIn = arrivalTime.getCheckIn().toString();
        String checkOut = arrivalTime.getCheckOut() != null ? arrivalTime.getCheckOut().toString() : null;

        return new ArrivalTimeDto(checkIn, checkOut);
    }

    public ArrivalTime fromDtoToEntity(ArrivalTimeDto arrivalTimeDto) {
        LocalTime checkIn = LocalTime.parse(arrivalTimeDto.getCheckIn(), DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime checkOut = arrivalTimeDto.getCheckOut() != null
                ? LocalTime.parse(arrivalTimeDto.getCheckOut(), DateTimeFormatter.ofPattern("HH:mm"))
                : null;

        return new ArrivalTime(checkIn, checkOut);
    }
}
