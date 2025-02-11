package by.timo.hotel.demo.hoteldemo.repository.extension;

import by.timo.hotel.demo.hoteldemo.model.Amenity;
import by.timo.hotel.demo.hoteldemo.model.Hotel;
import by.timo.hotel.demo.hoteldemo.repository.HotelPropertyViewRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class HotelPropertyViewCustomRepositoryImpl implements HotelPropertyViewRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Map<String, Integer> getHistogramByColumn(String column) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<Hotel> hotel = query.from(Hotel.class);

        Path<?> groupByColumn = switch (column) {
            case "city", "county" -> hotel.get("address").get(column);
            case "amenities" -> hotel.<Hotel, Amenity>join("amenities", JoinType.RIGHT);
            default -> hotel.get(column);
        };

        query.multiselect(groupByColumn, cb.count(hotel))
                .groupBy(groupByColumn);

        List<Object[]> result = entityManager.createQuery(query).getResultList();

        return result.stream()
                .collect(Collectors.toMap(
                        obj -> column.equals("amenities") ? ((Amenity) obj[0]).getName() : (String) obj[0],
                        obj -> ((Number) obj[1]).intValue()
                ));
    }
}
