package org.georgievbozhidar.softunifinal2rest.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.georgievbozhidar.softunifinal2rest.entity.dto.LocationDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateFoodDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.FoodDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.update.UpdateFoodDTO;
import org.georgievbozhidar.softunifinal2rest.exception.FoodNotFoundException;
import org.georgievbozhidar.softunifinal2rest.service.FoodService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    private final FoodService foodService;
    private final ModelMapper modelMapper;

    public FoodController(FoodService foodService, ModelMapper modelMapper) {
        this.foodService = foodService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodDTO> getFood(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(foodService.getById(id), HttpStatus.OK);
        } catch (FoodNotFoundException fnfe) {
            throw new FoodNotFoundException(fnfe.getMessage());
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<FoodDTO> createFood(@RequestBody @Valid CreateFoodDTO createFoodDTO) {
        return new ResponseEntity<>(foodService.createFood(createFoodDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FoodDTO> updateFood(@RequestBody @Valid UpdateFoodDTO updateFoodDTO, @PathVariable Long id){
        try {
            return new ResponseEntity<>(foodService.updateFood(id, updateFoodDTO), HttpStatus.OK);
        } catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        try {
            foodService.deleteFood(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (FoodNotFoundException fnfe){
            throw new FoodNotFoundException(fnfe.getMessage());
        }
    }
}
