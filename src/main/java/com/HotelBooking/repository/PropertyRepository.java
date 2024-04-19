package com.HotelBooking.repository;

import com.HotelBooking.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


// Search features Based On Location,Country,Property... we develop Query Parts-2

public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query("Select p from Property p JOIN  Location l on p.location = l.id JOIN Country c  on p.country= c.id where  l.locationName=:locationName or c.countryName=:locationName")
    List<Property> findPropertyByLocation(@Param("locationName") String locationName);

}


// jpql query :- it can be join 2,3 tables and i will try to get the output

// P = P here is Property class