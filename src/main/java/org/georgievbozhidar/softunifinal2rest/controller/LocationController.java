package org.georgievbozhidar.softunifinal2rest.controller;

import jakarta.validation.Valid;
import org.georgievbozhidar.softunifinal2rest.entity.dto.ChainDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.CreateLocationDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.LocationDTO;
import org.georgievbozhidar.softunifinal2rest.entity.model.Location;
import org.georgievbozhidar.softunifinal2rest.exception.ChainNotFoundException;
import org.georgievbozhidar.softunifinal2rest.exception.LocationNotFoundException;
import org.georgievbozhidar.softunifinal2rest.service.ChainService;
import org.georgievbozhidar.softunifinal2rest.service.LocationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/location")
public class LocationController {
    private final LocationService locationService;
    private final ChainService chainService;
    private final ModelMapper modelMapper;

    public LocationController(LocationService locationService, ChainService chainService, ModelMapper modelMapper) {
        this.locationService = locationService;
        this.chainService = chainService;
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

    @DeleteMapping("/{locationId}")
    public ResponseEntity<Void> deleteLocation(@PathVariable("chainId") Long chainId, @PathVariable("locationId") Long id){
        try {
            ChainDTO chainDTO = chainService.getById(chainId);
            if (chainDTO.getLocations().contains(modelMapper.map(locationService.getById(id), Location.class))) {
                locationService.deleteLocationById(id);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (LocationNotFoundException lnfe){
            throw new LocationNotFoundException(lnfe.getMessage());
        } catch (ChainNotFoundException cnfe){
            throw new ChainNotFoundException(cnfe.getMessage());
        }
    }


}
