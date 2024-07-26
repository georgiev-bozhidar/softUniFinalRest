package org.georgievbozhidar.softunifinal2rest.controller;

import jakarta.validation.Valid;
import org.georgievbozhidar.softunifinal2rest.entity.dto.CreateDrinkDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.DrinkDTO;
import org.georgievbozhidar.softunifinal2rest.exception.DrinkNotFoundException;
import org.georgievbozhidar.softunifinal2rest.service.DrinkService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drink")
public class DrinkController {
    private final DrinkService drinkService;
    private final ModelMapper modelMapper;

    public DrinkController(DrinkService drinkService, ModelMapper modelMapper) {
        this.drinkService = drinkService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrinkDTO> getDrink(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(modelMapper.map(drinkService.getDrinkById(id), DrinkDTO.class), HttpStatus.OK);
        } catch (DrinkNotFoundException fnfe) {
            throw new DrinkNotFoundException(fnfe.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<DrinkDTO> createDrink(@RequestBody @Valid CreateDrinkDTO createDrinkDTO) {
        return new ResponseEntity<>(drinkService.createDrink(createDrinkDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrink(@PathVariable Long id) {
        try {
            drinkService.deleteDrinkById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DrinkNotFoundException fnfe){
            throw new DrinkNotFoundException(fnfe.getMessage());
        }
    }
}
