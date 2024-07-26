package org.georgievbozhidar.softunifinal2rest.service;

import org.georgievbozhidar.softunifinal2rest.entity.dto.*;
import org.georgievbozhidar.softunifinal2rest.exception.ChainNotFoundException;
import org.georgievbozhidar.softunifinal2rest.exception.LocationNotFoundException;

import java.util.Set;

public interface LocationService {
    LocationDTO createLocation(CreateLocationDTO createLocationDTO);
    void deleteLocationById(Long id);

    LocationDTO getById(Long id) throws LocationNotFoundException;
    LocationDTO getByAddress(String address) throws LocationNotFoundException;
    Set<LocationDTO> getAllByChain(ChainDTO chainDTO) throws ChainNotFoundException;

    LocationDTO addFood(LocationDTO locationDTO, FoodDTO foodDTO);
    LocationDTO addDrink(LocationDTO locationDTO, DrinkDTO drinkDTO);

    Set<FoodDTO> getFoods(Long id);
    Set<DrinkDTO> getDrinks(Long id);
}
