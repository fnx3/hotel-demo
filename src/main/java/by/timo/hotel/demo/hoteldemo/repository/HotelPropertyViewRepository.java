package by.timo.hotel.demo.hoteldemo.repository;

import by.timo.hotel.demo.hoteldemo.model.Hotel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelPropertyViewRepository extends JpaRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> {

    @Query(value = "SELECT COUNT(h) FROM Hotel h WHERE h.id = :hotelId", nativeQuery = true)
    int countHotelById(@Param("hotelId") Long hotelId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO hotel_amenities (hotel_id, amenity_id) " +
            "SELECT :hotel_id, a.id " +
            "FROM amenity a " +
            "WHERE a.name IN (:amenities) " +
            "AND NOT EXISTS (SELECT 1 " +
            "FROM hotel_amenities ha " +
            "WHERE ha.hotel_id = :hotel_id AND ha.amenity_id = a.id)", nativeQuery = true)
    void updateHotelAmenities(@Param("amenities") List<String> amenityNames, @Param("hotel_id") Long id);
}
