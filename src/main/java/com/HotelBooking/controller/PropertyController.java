package com.HotelBooking.controller;
import com.HotelBooking.entity.Property;
import com.HotelBooking.repository.PropertyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


// we develop Controller of Search.. Part-2

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {


        private PropertyRepository propertyRepository;

    public PropertyController(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @GetMapping("/{locationName}")
        public ResponseEntity<List<Property>>  findProperty(@PathVariable String locationName)
        {
           List<Property> properties = propertyRepository.findPropertyByLocation(locationName);
            return new ResponseEntity<>(properties, HttpStatus.OK);
        }

}
