package by.timo.hotel.demo.hoteldemo.repository.specification;

import by.timo.hotel.demo.hoteldemo.model.Amenity;
import by.timo.hotel.demo.hoteldemo.model.Hotel;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class HotelSpecifications {

    public static Specification<Hotel> hasName(String name) {
        return (root, query, cb) -> (name == null || name.isEmpty()) ? cb.conjunction() :
                cb.equal(cb.lower(root.get("name")), name.toLowerCase());
    }

    public static Specification<Hotel> hasBrand(String brand) {
        return (root, query, cb) -> (brand == null || brand.isEmpty()) ? cb.conjunction() :
                cb.equal(cb.lower(root.get("brand")), brand.toLowerCase());
    }

    public static Specification<Hotel> hasCity(String city) {
        return (root, query, cb) -> (city == null || city.isEmpty()) ? cb.conjunction() :
             cb.equal(cb.lower(root.get("address").get("city")), city.toLowerCase());
    }

    public static Specification<Hotel> hasCounty(String county) {
        return (root, query, cb) -> (county == null || county.isEmpty())?  cb.conjunction() :
                    cb.equal(cb.lower(root.get("address").get("county")), county.toLowerCase());
    }

    public static Specification<Hotel> hasAmenities(List<String> amenities) {
        return (root, query, criteriaBuilder) -> {
            if (amenities == null || amenities.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            Join<Hotel, Amenity> amenitiesJoin = root.join("amenities");
            CriteriaBuilder.In<String> inClause = criteriaBuilder.in(criteriaBuilder.lower(amenitiesJoin.get("name")));

            for (String amenity : amenities) {
                inClause.value(amenity.toLowerCase());
            }

            query.distinct(true);

            return inClause;
        };
    }
}
