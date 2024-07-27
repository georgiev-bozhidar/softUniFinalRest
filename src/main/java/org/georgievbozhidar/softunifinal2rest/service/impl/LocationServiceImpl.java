package org.georgievbozhidar.softunifinal2rest.service.impl;

import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateLocationDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.ChainDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.DrinkDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.FoodDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.LocationDTO;
import org.georgievbozhidar.softunifinal2rest.entity.model.Chain;
import org.georgievbozhidar.softunifinal2rest.entity.model.Drink;
import org.georgievbozhidar.softunifinal2rest.entity.model.Food;
import org.georgievbozhidar.softunifinal2rest.entity.model.Location;
import org.georgievbozhidar.softunifinal2rest.exception.ChainNotFoundException;
import org.georgievbozhidar.softunifinal2rest.exception.LocationNotFoundException;
import org.georgievbozhidar.softunifinal2rest.repository.LocationRepository;
import org.georgievbozhidar.softunifinal2rest.service.ChainService;
import org.georgievbozhidar.softunifinal2rest.service.LocationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final ModelMapper modelMapper;
    private final ChainService chainService;

    public LocationServiceImpl(LocationRepository locationRepository, ModelMapper modelMapper, ChainService chainService) {
        this.locationRepository = locationRepository;
        this.modelMapper = modelMapper;
        this.chainService = chainService;
    }

    @Override
    public Location findById(Long id) {
        Optional<Location> optLocation = locationRepository.findById(id);
        if (optLocation.isEmpty()) {
            throw new LocationNotFoundException();
        }

        return optLocation.get();
    }

    @Override
    public LocationDTO getById(Long id) throws LocationNotFoundException {
        return modelMapper.map(this.findById(id), LocationDTO.class);
    }

    @Override
    public Location findByAddress(String address) throws LocationNotFoundException {
        Optional<Location> optLocation = locationRepository.findByAddress(address);
        if (optLocation.isEmpty()) {
            throw new LocationNotFoundException();
        }

        return optLocation.get();
    }

    @Override
    public LocationDTO getByAddress(String address) throws LocationNotFoundException  {
        Optional<Location> optLocation = locationRepository.findByAddress(address);
        if (optLocation.isEmpty()) {
            throw new LocationNotFoundException();
        }

        return modelMapper.map(optLocation.get(), LocationDTO.class);
    }

    @Override
    public Set<LocationDTO> getAllByChain(ChainDTO chainDTO) throws ChainNotFoundException {
        ChainDTO chainDTO1 = chainService.getById(chainDTO.getId());
        Set<Location> locations = chainDTO1.getLocations();

        Set<LocationDTO> locationDTOs = new HashSet<>();
        for (Location location : locations) {
            locationDTOs.add(modelMapper.map(location, LocationDTO.class));
        }

        return locationDTOs;
    }

    @Override
    public LocationDTO createLocation(CreateLocationDTO createLocationDTO) {
        Location location = modelMapper.map(createLocationDTO, Location.class);
        location.setOwnedBy(modelMapper.map(chainService.getById(createLocationDTO.getOwnedBy().getId()), Chain.class));
        locationRepository.save(location);

        chainService.addLocationToChain(location, createLocationDTO.getOwnedBy().getId());

        return modelMapper.map(location, LocationDTO.class);
    }

    @Override
    public void deleteLocation(Long id) throws LocationNotFoundException {
        locationRepository.delete(this.findById(id));
    }

    @Override
    public LocationDTO addFood(LocationDTO locationDTO, FoodDTO foodDTO) {
        Location location = modelMapper.map(this.getById(locationDTO.getId()), Location.class);
        Set<Food> locationFoods = location.getFoods();
        locationFoods.add(modelMapper.map(foodDTO, Food.class));
        location.setFoods(locationFoods);
        locationRepository.save(location);

        return modelMapper.map(location, LocationDTO.class);
    }

    @Override
    public LocationDTO addDrink(LocationDTO locationDTO, DrinkDTO drinkDTO) {
        Location location = modelMapper.map(this.getById(locationDTO.getId()), Location.class);
        Set<Drink> locationDrinks = location.getDrinks();
        locationDrinks.add(modelMapper.map(drinkDTO, Drink.class));
        location.setDrinks(locationDrinks);
        locationRepository.save(location);

        return modelMapper.map(location, LocationDTO.class);
    }

    @Override
    public Set<FoodDTO> getFoods(Long id) {
        LocationDTO locationDTO = this.getById(id);
        return locationDTO.getFoods();
    }

    @Override
    public Set<DrinkDTO> getDrinks(Long id) {
        LocationDTO locationDTO = this.getById(id);
        return locationDTO.getDrinks();
    }

}
