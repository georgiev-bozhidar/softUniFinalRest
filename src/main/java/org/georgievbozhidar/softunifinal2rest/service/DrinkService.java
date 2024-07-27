package org.georgievbozhidar.softunifinal2rest.service;

import org.georgievbozhidar.softunifinal2rest.entity.dto.create.CreateDrinkDTO;
import org.georgievbozhidar.softunifinal2rest.entity.dto.DrinkDTO;
import org.georgievbozhidar.softunifinal2rest.entity.enums.DrinkType;
import org.georgievbozhidar.softunifinal2rest.entity.model.Drink;

import java.util.Set;

public interface DrinkService {
    Drink findById(Long id);
    DrinkDTO getById(Long id);
    Drink findByName(String name);
    DrinkDTO getByName(String name);
    Set<DrinkDTO> getAll();
    Set<DrinkDTO> getAllByType(DrinkType drinkType);

    DrinkDTO createDrink(CreateDrinkDTO createDrinkDTO);

//    DrinkDTO updateDrink(DrinkDTO drinkDTO);

    void deleteDrink(Long id);
}
