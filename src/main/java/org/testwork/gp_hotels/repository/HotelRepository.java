package org.testwork.gp_hotels.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.testwork.gp_hotels.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {
}
