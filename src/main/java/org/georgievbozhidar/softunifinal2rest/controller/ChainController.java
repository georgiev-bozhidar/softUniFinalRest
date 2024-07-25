package org.georgievbozhidar.softunifinal2rest.controller;

import org.georgievbozhidar.softunifinal2rest.entity.dto.ChainDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.CreateChainDTO;
import org.georgievbozhidar.softunifinal2rest.entity.model.Chain;
import org.georgievbozhidar.softunifinal2rest.exception.ChainNotFoundException;
import org.georgievbozhidar.softunifinal2rest.exception.UserNotFoundException;
import org.georgievbozhidar.softunifinal2rest.service.ChainService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/chain")
public class ChainController {
    private final ChainService chainService;

    public ChainController(ChainService chainService) {
        this.chainService = chainService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChainDTO> getChain(@PathVariable("id") Long id){
        try {
            ChainDTO chainDTO = chainService.getById(id);
            return new ResponseEntity<>(chainDTO, HttpStatus.OK);
        } catch (ChainNotFoundException cnfe) {
            throw new ChainNotFoundException(cnfe.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity<ChainDTO> createChain(@RequestBody CreateChainDTO createChainDTO) {
        try {
            ChainDTO chainDTO = chainService.createChain(createChainDTO);
            return new ResponseEntity<>(chainDTO, HttpStatus.CREATED);
        } catch (IllegalStateException ise) {
            throw new IllegalStateException(ise.getMessage());
        } catch (UserNotFoundException unfe){
            throw new UserNotFoundException(unfe.getMessage());
        }
    }

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
