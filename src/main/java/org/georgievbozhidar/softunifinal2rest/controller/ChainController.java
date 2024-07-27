package org.georgievbozhidar.softunifinal2rest.controller;

import org.georgievbozhidar.softunifinal2rest.entity.dto.ChainDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateChainDTO;
import org.georgievbozhidar.softunifinal2rest.exception.ChainNotFoundException;
import org.georgievbozhidar.softunifinal2rest.exception.UserNotFoundException;
import org.georgievbozhidar.softunifinal2rest.service.ChainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/chain")
public class ChainController {
    private final ChainService chainService;

    public ChainController(ChainService chainService) {
        this.chainService = chainService;
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
    public ResponseEntity<ChainDTO> createChain(@RequestBody CreateChainDTO createChainDTO) {
        try {
            return new ResponseEntity<>(chainService.createChain(createChainDTO), HttpStatus.CREATED);
        } catch (IllegalStateException ise) {
            throw new IllegalStateException(ise.getMessage());
        } catch (UserNotFoundException unfe) {
            throw new UserNotFoundException(unfe.getMessage());
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<ChainDTO> updateChain(@RequestBody @Valid UpdateChainDTO updateChainDTO, @PathVariable Long id){
//        try {
//            return chainService.updateChain(updateChainDTO, id);
//        } catch (RuntimeException e){
//            throw new RuntimeException(e.getMessage());
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChain(@PathVariable("id") Long id) {
        try {
            chainService.deleteChain(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ChainNotFoundException cnfe){
            throw new ChainNotFoundException(cnfe.getMessage());
        }
    }
}
