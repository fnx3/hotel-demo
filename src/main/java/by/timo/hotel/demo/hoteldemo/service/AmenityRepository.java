package by.timo.hotel.demo.hoteldemo.service;

import by.timo.hotel.demo.hoteldemo.model.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {
    Amenity findByName(String name);
}
