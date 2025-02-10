package by.timo.hotel.demo.hoteldemo.repository;

import java.util.Map;

public interface HotelPropertyViewRepositoryCustom {

    Map<String, Integer> getHistogramByColumn(String column);
}
