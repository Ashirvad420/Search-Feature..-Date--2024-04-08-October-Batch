package com.HotelBooking.repository;

import com.HotelBooking.entity.Country;
import com.HotelBooking.entity.Location;
import com.HotelBooking.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {


}