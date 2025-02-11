package by.timo.hotel.demo.hoteldemo.service;

import by.timo.hotel.demo.hoteldemo.model.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {
    @Query(value = "SELECT COUNT(a) FROM Amenity a WHERE a.name IN :amenityNames", nativeQuery = true)
    int countAmenitiesByNames(@Param("amenityNames") List<String> amenityNames);
}
