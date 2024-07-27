package org.georgievbozhidar.softunifinal2rest.controller;

import jakarta.validation.Valid;
import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateLocationDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.LocationDTO;
import org.georgievbozhidar.softunifinal2rest.exception.ChainNotFoundException;
import org.georgievbozhidar.softunifinal2rest.exception.LocationNotFoundException;
import org.georgievbozhidar.softunifinal2rest.service.LocationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/location")
public class LocationController {
    private final LocationService locationService;
    private final ModelMapper modelMapper;

    public LocationController(LocationService locationService, ModelMapper modelMapper) {
        this.locationService = locationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<LocationDTO> getLocation(@PathVariable Long locationId) {
        try {
            return new ResponseEntity<>(locationService.getById(locationId), HttpStatus.OK);
        } catch (LocationNotFoundException e) {
            throw new LocationNotFoundException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<LocationDTO> createLocation(@RequestBody @Valid CreateLocationDTO createLocationDTO) {
        try {
            return new ResponseEntity<>(locationService.createLocation(createLocationDTO), HttpStatus.CREATED);
        } catch (ChainNotFoundException cnfe){
            throw new ChainNotFoundException(cnfe.getMessage());
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<LocationDTO> updateLocation(@RequestBody @Valid UpdateLocationDTO updateLocationDTO, @PathVariable Long id){
//        try {
//            return locationService.updateLocation(updateLocationDTO, id);
//        } catch (RuntimeException e){
//            throw new RuntimeException(e.getMessage());
//        }
//    }

    @DeleteMapping("/{locationId}")
    public ResponseEntity<Void> deleteLocation(@PathVariable("locationId") Long id){
        try {
            locationService.deleteLocation(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (LocationNotFoundException lnfe) {
            throw new LocationNotFoundException(lnfe.getMessage());
        }
    }
}
