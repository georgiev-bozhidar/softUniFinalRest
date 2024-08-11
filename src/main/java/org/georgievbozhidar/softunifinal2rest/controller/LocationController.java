package org.georgievbozhidar.softunifinal2rest.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.georgievbozhidar.softunifinal2rest.entity.dto.ChainInnerDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.LocationInnerDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateDrinkDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateFoodDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.LocationDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateLocationAtChainDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateLocationDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.update.UpdateLocationDTO;
import org.georgievbozhidar.softunifinal2rest.exception.LocationNotFoundException;
import org.georgievbozhidar.softunifinal2rest.service.ChainService;
import org.georgievbozhidar.softunifinal2rest.service.DrinkService;
import org.georgievbozhidar.softunifinal2rest.service.FoodService;
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
    private final FoodService foodService;
    private final DrinkService drinkService;
    private final ChainService chainService;

    public LocationController(LocationService locationService, ModelMapper modelMapper, FoodService foodService, DrinkService drinkService, ChainService chainService) {
        this.locationService = locationService;
        this.modelMapper = modelMapper;
        this.foodService = foodService;
        this.drinkService = drinkService;
        this.chainService = chainService;
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
    public ResponseEntity<LocationDTO> createLocation(@RequestBody @Valid CreateLocationAtChainDTO createLocationAtChainDTO) {
        try {
            return new ResponseEntity<>(locationService.createLocation(createLocationAtChainDTO), HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<LocationInnerDTO> updateLocation(@RequestBody @Valid UpdateLocationDTO updateLocationDTO, @PathVariable Long id){
        try {
            return new ResponseEntity<>(chainService.updateLocation(id, updateLocationDTO), HttpStatus.OK);
        } catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{locationId}")
    public ResponseEntity<Void> deleteLocation(@PathVariable("locationId") Long id){
        try {
            locationService.deleteLocation(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (LocationNotFoundException lnfe) {
            throw new LocationNotFoundException(lnfe.getMessage());
        }
    }

    @PostMapping("/{locationId}/food/{foodId}")
    public ResponseEntity<LocationDTO> addFoodToLocation(@PathVariable Long locationId, @PathVariable Long foodId){
        return new ResponseEntity<>(locationService.addFood(locationService.getById(locationId), foodService.getById(foodId)), HttpStatus.OK);
    }

    @PostMapping("/{locationId}/food")
    public ResponseEntity<LocationDTO> createFoodAtLocation(@PathVariable Long locationId, @RequestBody @Valid CreateFoodDTO createFoodDTO){
        return new ResponseEntity<>(locationService.addFood(locationService.getById(locationId), foodService.createFood(createFoodDTO)), HttpStatus.OK);
    }

    @PostMapping("/{locationId}/drink/{drinkId}")
    public ResponseEntity<LocationDTO> addDrinkToLocation(@PathVariable Long locationId, @PathVariable Long drinkId){
        return new ResponseEntity<>(locationService.addDrink(locationService.getById(locationId), drinkService.getById(drinkId)), HttpStatus.OK);
    }

    @PostMapping("/{locationId}/drink")
    public ResponseEntity<LocationDTO> createDrinkAtLocation(@PathVariable Long locationId, @RequestBody @Valid CreateDrinkDTO createDrinkDTO){
        return new ResponseEntity<>(locationService.addDrink(locationService.getById(locationId), drinkService.createDrink(createDrinkDTO)), HttpStatus.OK);
    }

}
