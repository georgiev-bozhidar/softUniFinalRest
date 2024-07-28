package org.georgievbozhidar.softunifinal2rest.service;

import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateLocationDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.ChainDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.DrinkDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.FoodDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.LocationDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.update.UpdateLocationDTO;
import org.georgievbozhidar.softunifinal2rest.entity.model.Location;
import org.georgievbozhidar.softunifinal2rest.exception.ChainNotFoundException;
import org.georgievbozhidar.softunifinal2rest.exception.LocationNotFoundException;

import java.util.Set;

public interface LocationService {
    public Location findById(Long id) throws LocationNotFoundException;
    public Location findByAddress(String address) throws LocationNotFoundException;
    public LocationDTO getById(Long id) throws LocationNotFoundException;
    public LocationDTO getByAddress(String address) throws LocationNotFoundException;

    LocationDTO createLocation(CreateLocationDTO createLocationDTO);


    public void deleteLocation(Long id);

    public LocationDTO addFood(LocationDTO locationDTO, FoodDTO foodDTO);
    public LocationDTO addDrink(LocationDTO locationDTO, DrinkDTO drinkDTO);

    public Set<FoodDTO> getFoods(Long id);
    public Set<DrinkDTO> getDrinks(Long id);
}
