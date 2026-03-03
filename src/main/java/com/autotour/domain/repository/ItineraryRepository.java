package com.autotour.domain.repository;

import com.autotour.domain.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
    @Query("SELECT DISTINCT i FROM Itinerary i LEFT JOIN FETCH i.items ORDER BY i.id DESC")
    List<Itinerary> findAllWithItems();
}