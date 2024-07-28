package org.georgievbozhidar.softunifinal2rest.service.impl;

import jakarta.transaction.Transactional;
import org.georgievbozhidar.softunifinal2rest.entity.dto.*;
import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateChainDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateDrinkDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateFoodDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateLocationDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.update.UpdateChainDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.update.UpdateLocationDTO;
import org.georgievbozhidar.softunifinal2rest.entity.model.Chain;
import org.georgievbozhidar.softunifinal2rest.entity.model.Location;
import org.georgievbozhidar.softunifinal2rest.entity.model.User;
import org.georgievbozhidar.softunifinal2rest.exception.ChainNotFoundException;
import org.georgievbozhidar.softunifinal2rest.exception.UserNotFoundException;
import org.georgievbozhidar.softunifinal2rest.repository.ChainRepository;
import org.georgievbozhidar.softunifinal2rest.repository.LocationRepository;
import org.georgievbozhidar.softunifinal2rest.repository.UserRepository;
import org.georgievbozhidar.softunifinal2rest.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ChainServiceImpl implements ChainService {
    private final ChainRepository chainRepository;
    private final LocationRepository locationRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final FoodService foodService;
    private final DrinkService drinkService;
    private final LocationService locationService;
    private final UserRepository userRepository;

    public ChainServiceImpl(ChainRepository chainRepository, LocationRepository locationRepository, ModelMapper modelMapper, UserService userService, FoodService foodService, DrinkService drinkService, LocationService locationService, UserRepository userRepository) {
        this.chainRepository = chainRepository;
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.foodService = foodService;
        this.drinkService = drinkService;
        this.locationService = locationService;
    }

    @Override
    public Set<ChainDTO> getAllChainsByOwner(UserDTO userDTO) {
        Set<ChainDTO> ownedChains = userService.getById(userDTO.getId()).getOwnedChains();

        return ownedChains;
    }

    @Override
    public ChainDTO getById(Long id){
        return modelMapper.map(this.findById(id), ChainDTO.class);
    }

    @Override
    @Transactional
    public ChainDTO createChain(CreateChainDTO createChainDTO) {
        Chain chain = modelMapper.map(createChainDTO, Chain.class);

        User user = userService.findById(createChainDTO.getOwner().getId());
        chain.setOwner(user);

        if (chainRepository.findByName(chain.getName()).isPresent()){
            throw new IllegalStateException("Chain by that name already exists");
        }

        chainRepository.save(chain);

        return modelMapper.map(chainRepository.findByName(chain.getName()), ChainDTO.class);
    }

    @Override
    @Transactional
    public void deleteChain(Long id) {
        Optional<Chain> optChain = chainRepository.findById(id);
        if (optChain.isEmpty()){
            throw new ChainNotFoundException();
        }

        chainRepository.delete(optChain.get());
    }

    @Override
    @Transactional
    public ChainDTO createLocationAtChain(Long chainId, CreateLocationDTO createLocationDTO) {
        LocationDTO locationDTO = locationService.createLocation(createLocationDTO);
        this.addLocationToChain(chainId, locationDTO.getId());
        return this.getById(chainId);
    }

    @Override
    @Transactional
    public ChainDTO addLocationToChain(Long chainId, Long locationId){
        Chain chain = this.findById(chainId);
        chain.addLocation(locationService.findById(locationId));
        chainRepository.save(chain);
        return this.getById(chainId);
    }

    @Override
    public UserDTO addToFavourites(Long userId, Long chainId) {
        User user = userService.findById(userId);
        user.addFavouriteChain(this.findById(chainId));
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserDTO.class);
    }

    @Override
    public void removeFromFavourites(Long userId, Long chainId) {
        User user = userService.findById(userId);
        user.removeFavouriteChain(this.findById(chainId));
        userRepository.save(user);
    }

    @Override
    public Set<ChainDTO> getAllChains() throws IllegalStateException {
        Set<Chain> chains = new HashSet<>(chainRepository.findAll());
        if (chains.isEmpty()){
            throw new IllegalStateException("No chains found");
        }
        Set<ChainDTO> chainDTOs = new HashSet<>();
        for (Chain chain : chains) {
            chainDTOs.add(modelMapper.map(chain, ChainDTO.class));
        }
        return chainDTOs;
    }

    @Override
    public ChainDTO getByName(String name) {
        return modelMapper.map(this.findByName(name), ChainDTO.class);
   }

    @Override
    public Chain findByName(String name) throws ChainNotFoundException {
        Optional<Chain> optChain = chainRepository.findByName(name);
        if (optChain.isEmpty()){
            throw new ChainNotFoundException();
        }
        return optChain.get();
    }

    @Override
    public Chain findById(Long id) throws ChainNotFoundException {
        Optional<Chain> optChain = chainRepository.findById(id);
        if (optChain.isEmpty()) {
            throw new ChainNotFoundException();
        }

        return optChain.get();
    }

    @Override
    public Set<Chain> findAllChainsByOwner(UserDTO userDTO) throws UserNotFoundException {
        return chainRepository.findAllByOwner(userService.findById(userDTO.getId()));
    }

    @Override
    public Set<LocationDTO> getAllLocations(Long chainId) throws ChainNotFoundException {
        ChainDTO chainDTO = this.getById(chainId);
        return chainDTO.getLocations();
    }

    @Override
    @Transactional
    public ChainDTO addFoodToAllLocations(Long chainId, Long foodId){
        ChainDTO chainDTO = this.getById(chainId);
        Set<LocationDTO> locations = chainDTO.getLocations();
        for (LocationDTO locationDTO : locations) {
            locationService.addFood(locationDTO, foodService.getById(foodId));
        }
        return chainDTO;
    }

    @Override
    @Transactional
    public ChainDTO createFoodAtAllLocations(Long id, CreateFoodDTO createFoodDTO) {
        ChainDTO chainDTO = this.getById(id);
        Set<LocationDTO> locations = chainDTO.getLocations();
        FoodDTO foodDTO = foodService.createFood(createFoodDTO);
        for (LocationDTO locationDTO : locations) {
            locationService.addFood(locationDTO, foodDTO);
        }
        return chainDTO;
    }

    @Override
    public ChainDTO updateChain(Long id, UpdateChainDTO updateChainDTO) {
        Chain chain = this.findById(id);
        if (updateChainDTO.getName() != null) chain.setName(updateChainDTO.getName());
        if (updateChainDTO.getOwner() != null) chain.setOwner(userService.findById(updateChainDTO.getOwner().getId()));

        ChainDTO chainDTO = modelMapper.map(chainRepository.save(chain), ChainDTO.class);
        return chainDTO;
    }

    @Override
    public LocationDTO updateLocation(Long id, UpdateLocationDTO updateLocationDTO) {
        Location location = locationService.findById(id);
        if (updateLocationDTO.getAddress() != null) location.setAddress(updateLocationDTO.getAddress());
        if (updateLocationDTO.getLocationType() != null) location.setLocationType(updateLocationDTO.getLocationType());
        if (updateLocationDTO.getOwnedBy() != null) location.setOwnedBy(this.findById(updateLocationDTO.getOwnedBy().getId()));

        LocationDTO locationDTO = modelMapper.map(locationRepository.save(location), LocationDTO.class);
        return locationDTO;
    }


    @Override
    @Transactional
    public ChainDTO addDrinkToAllLocations(Long chainId, Long drinkId){
        ChainDTO chainDTO = this.getById(chainId);
        Set<LocationDTO> locations = chainDTO.getLocations();
        for (LocationDTO locationDTO : locations) {
            locationService.addDrink(locationDTO, drinkService.getById(drinkId));
        }
        return chainDTO;
    }

    @Override
    @Transactional
    public ChainDTO createDrinkAtAllLocations(Long id, CreateDrinkDTO createDrinkDTO) {
        ChainDTO chainDTO = this.getById(id);
        Set<LocationDTO> locations = chainDTO.getLocations();
        DrinkDTO drinkDTO = drinkService.createDrink(createDrinkDTO);
        for (LocationDTO locationDTO : locations) {
            locationService.addDrink(locationDTO, drinkDTO);
        }
        return chainDTO;
    }
}
