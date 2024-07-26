package org.georgievbozhidar.softunifinal2rest.controller;

import jakarta.validation.Valid;
import org.georgievbozhidar.softunifinal2rest.entity.dto.CreateFoodDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.FoodDTO;
import org.georgievbozhidar.softunifinal2rest.entity.model.Food;
import org.georgievbozhidar.softunifinal2rest.exception.FoodNotFoundException;
import org.georgievbozhidar.softunifinal2rest.service.FoodService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.OutputKeys;

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
            return new ResponseEntity<>(modelMapper.map(foodService.getFoodById(id), FoodDTO.class), HttpStatus.OK);
        } catch (FoodNotFoundException fnfe) {
            throw new FoodNotFoundException(fnfe.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<FoodDTO> createFood(@RequestBody @Valid CreateFoodDTO createFoodDTO) {
        return new ResponseEntity<>(foodService.createFood(createFoodDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        try {
            foodService.deleteFoodById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (FoodNotFoundException fnfe){
            throw new FoodNotFoundException(fnfe.getMessage());
        }
    }
}
