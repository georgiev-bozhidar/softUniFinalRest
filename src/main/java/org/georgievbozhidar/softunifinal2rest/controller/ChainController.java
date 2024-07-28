package org.georgievbozhidar.softunifinal2rest.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.georgievbozhidar.softunifinal2rest.entity.dto.ChainDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateChainDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateDrinkDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateFoodDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.update.UpdateChainDTO;
import org.georgievbozhidar.softunifinal2rest.exception.ChainNotFoundException;
import org.georgievbozhidar.softunifinal2rest.exception.UserNotFoundException;
import org.georgievbozhidar.softunifinal2rest.service.ChainService;
import org.georgievbozhidar.softunifinal2rest.service.FoodService;
import org.georgievbozhidar.softunifinal2rest.service.impl.FoodServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/chain")
public class ChainController {
    private final ChainService chainService;
    private final FoodService foodService;

    public ChainController(ChainService chainService, FoodServiceImpl foodService) {
        this.chainService = chainService;
        this.foodService = foodService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChainDTO> getChain(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(chainService.getById(id), HttpStatus.OK);
        } catch (ChainNotFoundException cnfe) {
            throw new ChainNotFoundException(cnfe.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Set<ChainDTO>> getAllChains() {
        try {
            return new ResponseEntity<>(chainService.getAllChains(), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ChainDTO> createChain(@RequestBody CreateChainDTO createChainDTO) {
        try {
            return new ResponseEntity<>(chainService.createChain(createChainDTO), HttpStatus.CREATED);
        } catch (IllegalStateException ise) {
            throw new IllegalStateException(ise.getMessage());
        } catch (UserNotFoundException unfe) {
            throw new UserNotFoundException(unfe.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ChainDTO> updateChain(@RequestBody @Valid UpdateChainDTO updateChainDTO, @PathVariable Long id){
        try {
            return new ResponseEntity<>(chainService.updateChain(id, updateChainDTO), HttpStatus.OK);
        } catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteChain(@PathVariable("id") Long id) {
        try {
            chainService.deleteChain(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ChainNotFoundException cnfe){
            throw new ChainNotFoundException(cnfe.getMessage());
        }
    }

    @PostMapping("/{chainId}/food")
    @Transactional
    public ResponseEntity<ChainDTO> createFoodAtAllLocations(@PathVariable Long chainId, @RequestBody @Valid CreateFoodDTO createFoodDTO){
        return new ResponseEntity<>(chainService.createFoodAtAllLocations(chainId, createFoodDTO), HttpStatus.OK);
    }

    @PostMapping("/{chainId}/food/{foodId}")
    @Transactional
    public ResponseEntity<ChainDTO> addFoodAtAllLocations(@PathVariable("chainId") Long chainId, @PathVariable("foodId") Long foodId){
        return new ResponseEntity<>(chainService.addFoodToAllLocations(chainId, foodId), HttpStatus.OK);
    }

    @PostMapping("/{chainId}/drink")
    @Transactional
    public ResponseEntity<ChainDTO> createDrinkAtAllLocations(@PathVariable Long chainId, @RequestBody @Valid CreateDrinkDTO createDrinkDTO){
        return new ResponseEntity<>(chainService.createDrinkAtAllLocations(chainId, createDrinkDTO), HttpStatus.OK);
    }

    @PostMapping("/{chainId}/drink/{drinkId}")
    @Transactional
    public ResponseEntity<ChainDTO> addDrinkAtAllLocations(@PathVariable("chainId") Long chainId, @PathVariable("drinkId") Long drinkId){
        return new ResponseEntity<>(chainService.addDrinkToAllLocations(chainId, drinkId), HttpStatus.OK);
    }
}
