package org.georgievbozhidar.softunifinal2rest.service;

import jakarta.transaction.Transactional;
import org.georgievbozhidar.softunifinal2rest.entity.dto.ChainDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.LocationDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateChainDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.UserDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateDrinkDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateFoodDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateLocationDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.update.UpdateChainDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.update.UpdateLocationDTO;
import org.georgievbozhidar.softunifinal2rest.entity.model.Chain;
import org.georgievbozhidar.softunifinal2rest.entity.model.Location;
import org.georgievbozhidar.softunifinal2rest.exception.ChainNotFoundException;

import java.util.Set;

public interface ChainService {
    public ChainDTO getByName(String name) throws ChainNotFoundException;
    public ChainDTO getById(Long id) throws ChainNotFoundException;
    public Chain findByName(String name) throws ChainNotFoundException;
    public Chain findById(Long id) throws ChainNotFoundException;

    public Set<Chain> findAllChainsByOwner(UserDTO userDTO) throws ChainNotFoundException;
    public Set<ChainDTO> getAllChainsByOwner(UserDTO userDTO) throws ChainNotFoundException;

    @Transactional
    ChainDTO createLocationAtChain(Long chainId, CreateLocationDTO createLocationDTO);

    @Transactional
    ChainDTO addLocationToChain(Long chainId, Long locationId);

    UserDTO addToFavourites(Long userId, Long chainId);

    void removeFromFavourites(Long userId, Long chainId);

    public Set<ChainDTO> getAllChains() throws IllegalStateException;

    public ChainDTO createChain(CreateChainDTO createChainDTO);

    public ChainDTO updateChain(Long id, UpdateChainDTO updateChainDTO);

    public LocationDTO updateLocation(Long id, UpdateLocationDTO updateLocationDTO);

    public void deleteChain(Long id);

    ChainDTO createFoodAtAllLocations(Long id, CreateFoodDTO createFoodDTO);

    Set<LocationDTO> getAllLocations(Long chainId) throws ChainNotFoundException;

    ChainDTO addFoodToAllLocations(Long chainId, Long foodId);

    ChainDTO createDrinkAtAllLocations(Long chainId, CreateDrinkDTO createDrinkDTO);
    ChainDTO addDrinkToAllLocations(Long chainId, Long drinkId);
}
