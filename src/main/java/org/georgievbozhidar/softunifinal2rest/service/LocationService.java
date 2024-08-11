package org.georgievbozhidar.softunifinal2rest.service;

import org.georgievbozhidar.softunifinal2rest.entity.dto.*;
import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateLocationAtChainDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateLocationDTO;
import org.georgievbozhidar.softunifinal2rest.entity.model.Location;
import org.georgievbozhidar.softunifinal2rest.exception.LocationNotFoundException;

import java.util.Set;

public interface LocationService {
    public Location findById(Long id) throws LocationNotFoundException;
    public Location findByAddress(String address) throws LocationNotFoundException;
    public LocationDTO getById(Long id) throws LocationNotFoundException;
    public LocationDTO getByAddress(String address) throws LocationNotFoundException;

    LocationDTO createLocation(CreateLocationDTO createLocationDTO);

    LocationDTO createLocation(CreateLocationAtChainDTO createLocationAtChainDTO);

    public void deleteLocation(Long id);

    public LocationDTO addFood(LocationDTO locationDTO, FoodDTO foodDTO);
    public LocationDTO addDrink(LocationDTO locationDTO, DrinkDTO drinkDTO);
    public LocationInnerDTO addFood(LocationInnerDTO locationDTO, FoodDTO foodDTO);
    public LocationInnerDTO addDrink(LocationInnerDTO locationDTO, DrinkDTO drinkDTO);

    public Set<FoodDTO> getFoods(Long id);
    public Set<DrinkDTO> getDrinks(Long id);
}
