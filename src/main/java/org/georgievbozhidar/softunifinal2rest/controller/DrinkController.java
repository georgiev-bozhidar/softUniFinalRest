package org.georgievbozhidar.softunifinal2rest.controller;

import jakarta.validation.Valid;
import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateDrinkDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.DrinkDTO;
import org.georgievbozhidar.softunifinal2rest.exception.DrinkNotFoundException;
import org.georgievbozhidar.softunifinal2rest.service.DrinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drink")
public class DrinkController {
    private final DrinkService drinkService;

    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrinkDTO> getDrink(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(drinkService.getById(id), HttpStatus.OK);
        } catch (DrinkNotFoundException fnfe) {
            throw new DrinkNotFoundException(fnfe.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<DrinkDTO> createDrink(@RequestBody @Valid CreateDrinkDTO createDrinkDTO) {
        return new ResponseEntity<>(drinkService.createDrink(createDrinkDTO), HttpStatus.CREATED);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<DrinkDTO> updateDrink(@RequestBody @Valid UpdateDrinkDTO updateDrinkDTO, @PathVariable Long id){
//        try {
//            return drinkService.updateDrink(updateDrinkDTO, id);
//        } catch (RuntimeException e){
//            throw new RuntimeException(e.getMessage());
//        }
//    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrink(@PathVariable Long id) {
        try {
            drinkService.deleteDrink(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DrinkNotFoundException fnfe){
            throw new DrinkNotFoundException(fnfe.getMessage());
        }
    }
}
