package org.georgievbozhidar.softunifinal2rest.service;

import jakarta.transaction.Transactional;
import org.georgievbozhidar.softunifinal2rest.entity.dto.*;
import org.georgievbozhidar.softunifinal2rest.entity.dto.create.*;
import org.georgievbozhidar.softunifinal2rest.entity.dto.update.UpdateChainDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.update.UpdateLocationDTO;
import org.georgievbozhidar.softunifinal2rest.entity.model.Chain;
import org.georgievbozhidar.softunifinal2rest.exception.ChainNotFoundException;

import java.util.Set;

public interface ChainService {
    public ChainDTO getByName(String name) throws ChainNotFoundException;
    public ChainDTO getById(Long id) throws ChainNotFoundException;
    public Chain findByName(String name) throws ChainNotFoundException;
    public Chain findById(Long id) throws ChainNotFoundException;

    public Set<Chain> findAllChainsByOwner(UserDTO userDTO) throws ChainNotFoundException;
    public Set<ChainWithLocationsDTO> getAllChainsByOwner(UserDTO userDTO) throws ChainNotFoundException;

    @Transactional
    ChainDTO createLocationAtChain(Long chainId, CreateLocationDTO createLocationDTO);

    @Transactional
    LocationDTO createLocationAtChain(CreateLocationAtChainDTO createLocationAtChainDTO);

    @Transactional
    ChainDTO addLocationToChain(Long chainId, Long locationId);

    UserDTO addToFavourites(Long userId, Long chainId);

    void removeFromFavourites(Long userId, Long chainId);

    public Set<ChainDTO> getAllChains() throws IllegalStateException;

    public ChainDTO createChain(CreateChainDTO createChainDTO);

    public ChainDTO updateChain(Long id, UpdateChainDTO updateChainDTO);

    public LocationInnerDTO updateLocation(Long id, UpdateLocationDTO updateLocationDTO);

    public void deleteChain(Long id);

    Set<LocationInnerDTO> getAllLocations(Long chainId) throws ChainNotFoundException;

    ChainDTO createFoodAtAllLocations(Long id, CreateFoodDTO createFoodDTO);
    ChainDTO addFoodToAllLocations(Long chainId, Long foodId);

    ChainDTO createDrinkAtAllLocations(Long chainId, CreateDrinkDTO createDrinkDTO);
    ChainDTO addDrinkToAllLocations(Long chainId, Long drinkId);
}
