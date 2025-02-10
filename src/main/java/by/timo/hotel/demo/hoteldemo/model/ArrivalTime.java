package by.timo.hotel.demo.hoteldemo.model;

import jakarta.persistence.Embeddable;

import java.time.LocalTime;
import java.util.Objects;

@Embeddable
public class ArrivalTime {

    private LocalTime checkIn;
    private LocalTime checkOut;

    public ArrivalTime() {
    }

    public ArrivalTime(LocalTime checkIn, LocalTime checkOut) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public LocalTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalTime checkOut) {
        this.checkOut = checkOut;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrivalTime that)) return false;

        return Objects.equals(checkIn, that.checkIn) && Objects.equals(checkOut, that.checkOut);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(checkIn);
        result = 31 * result + Objects.hashCode(checkOut);
        return result;
    }
}
