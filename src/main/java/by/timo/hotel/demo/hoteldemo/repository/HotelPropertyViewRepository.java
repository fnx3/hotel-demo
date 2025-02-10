package by.timo.hotel.demo.hoteldemo.repository;

import by.timo.hotel.demo.hoteldemo.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface HotelPropertyViewRepository extends JpaRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> {
}
