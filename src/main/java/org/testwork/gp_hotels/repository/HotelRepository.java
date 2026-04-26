package org.testwork.gp_hotels.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.testwork.gp_hotels.entity.Hotel;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {
    @Query("SELECT DISTINCT h FROM Hotel h " +
            "WHERE (:name IS NULL OR LOWER(h.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:brand IS NULL OR h.brand = :brand) " +
            "AND (:city IS NULL OR h.address.city = :city) " +
            "AND (:country IS NULL OR h.address.country = :country) " +
            "AND (:amenities IS NULL OR " +
            "(SELECT COUNT(a) FROM h.amenities a WHERE a IN :amenities) = :size)")
    List<Hotel> search(@Param("name") String name,
                       @Param("brand") String brand,
                       @Param("city") String city,
                       @Param("country") String country,
                       @Param("amenities") List<String> amenities,
                       @Param("size") long size);
}
